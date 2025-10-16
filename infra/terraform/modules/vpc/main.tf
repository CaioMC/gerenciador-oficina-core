#### LOCALS ####
locals {

  # Subnets públicas — para RDS e Load Balancers
  public_subnets = {
    "1a" = cidrsubnet(var.vpc_cidr, 4, 0)
    "1b" = cidrsubnet(var.vpc_cidr, 4, 1)
    "1c" = cidrsubnet(var.vpc_cidr, 4, 2)
  }

  # Subnets privadas — usadas pelo EKS Auto Mode (pods e nodes)
  private_subnets = {
    "1a" = cidrsubnet(var.vpc_cidr, 4, 3)
    "1b" = cidrsubnet(var.vpc_cidr, 4, 4)
    "1c" = cidrsubnet(var.vpc_cidr, 4, 5)
  }
}

#### VPC ####
resource "aws_vpc" "main" {
  cidr_block           = var.vpc_cidr
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    Name      = "vpc-${var.vpc_name}"
    Terraform = "true"
  }
}

# Internet Gateway (para subnets públicas)
resource "aws_internet_gateway" "gw" {
  vpc_id = aws_vpc.main.id
  tags   = { Name = "igw-${var.vpc_name}" }
}

# Elastic IP para o NAT Gateway
resource "aws_eip" "nat" {
  domain = "vpc"
  tags   = { Name = "eip-nat-${var.vpc_name}" }
}

# NAT Gateway (para subnets privadas saírem para a internet)
resource "aws_nat_gateway" "nat" {
  allocation_id = aws_eip.nat.id
  subnet_id     = aws_subnet.public["1a"].id
  tags          = { Name = "nat-${var.vpc_name}" }

  depends_on = [aws_internet_gateway.gw]
}

# Subnets públicas (para RDS e Load Balancers)
resource "aws_subnet" "public" {
  for_each = local.public_subnets

  vpc_id                  = aws_vpc.main.id
  cidr_block              = each.value
  availability_zone       = "us-east-${each.key}"
  map_public_ip_on_launch = true

  tags = {
    Name                                    = "sn-${var.vpc_name}-public-${each.key}"
    "kubernetes.io/role/elb"                = 1
    "kubernetes.io/cluster/${var.vpc_name}" = "shared"
  }
}

# Subnets privadas (usadas pelo EKS Auto Mode)
resource "aws_subnet" "private" {
  for_each = local.private_subnets

  vpc_id                  = aws_vpc.main.id
  cidr_block              = each.value
  availability_zone       = "us-east-${each.key}"
  map_public_ip_on_launch = false

  tags = {
    Name                                    = "sn-${var.vpc_name}-private-${each.key}"
    "kubernetes.io/role/internal-elb"       = 1
    "kubernetes.io/cluster/${var.vpc_name}" = "shared"
  }
}

# Rota pública (Internet Gateway)
resource "aws_route_table" "public" {
  vpc_id = aws_vpc.main.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.gw.id
  }
  tags = { Name = "rt-public-${var.vpc_name}" }
}

# Rota privada (via NAT Gateway)
resource "aws_route_table" "private" {
  vpc_id = aws_vpc.main.id
  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.nat.id
  }
  tags = { Name = "rt-private-${var.vpc_name}" }
}

# Associação das subnets públicas à rota pública
resource "aws_route_table_association" "public_assoc" {
  for_each       = aws_subnet.public
  subnet_id      = each.value.id
  route_table_id = aws_route_table.public.id
}

# Associação das subnets privadas à rota privada
resource "aws_route_table_association" "private_assoc" {
  for_each       = aws_subnet.private
  subnet_id      = each.value.id
  route_table_id = aws_route_table.private.id
}
