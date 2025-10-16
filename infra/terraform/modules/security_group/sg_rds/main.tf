module "sg_rds" {
  source  = "terraform-aws-modules/security-group/aws"
  version = ">= 5.3.0"

  name        = "rds"
  description = "Permite acesso interno as aplicacoes no RDS"
  vpc_id      = var.vpc_id


  ingress_with_cidr_blocks = [
    {
      from_port   = 5432
      to_port     = 5432
      protocol    = "tcp"
      description = "Acesso liberado para todos os seres humanos"
      cidr_blocks = "0.0.0.0/0"
    }
  ]

  egress_with_cidr_blocks = [
    {
      from_port   = 0
      to_port     = 0
      protocol    = -1
      description = "Saida para Internet"
      cidr_blocks = "0.0.0.0/0"
    }
  ]
}
