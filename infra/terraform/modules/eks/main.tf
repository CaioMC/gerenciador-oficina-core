## EKS CLUSTER ##

resource "aws_eks_cluster" "cluster" {
  name     = var.cluster_name
  role_arn = var.role_arn
  version  = "1.33"

  vpc_config {
    subnet_ids              = var.vpc_subnets
    security_group_ids      = [aws_security_group.eks_extra.id]
    endpoint_private_access = "true"
    endpoint_public_access  = "true"
  }

  access_config {
    authentication_mode                         = "API"
    bootstrap_cluster_creator_admin_permissions = true
  }

  bootstrap_self_managed_addons = false

  dynamic "zonal_shift_config" {
    for_each = var.eks_auto_mode ? [1] : []
    content {
      enabled = var.eks_auto_mode
    }
  }

  dynamic "compute_config" {
    for_each = var.eks_auto_mode ? [1] : []
    content {
      enabled       = var.eks_auto_mode
      node_pools    = ["general-purpose", "system"]
      node_role_arn = var.node_role_arn
    }
  }

  dynamic "kubernetes_network_config" {
    for_each = var.eks_auto_mode ? [1] : []
    content {
      elastic_load_balancing {
        enabled = var.eks_auto_mode
      }
    }
  }

  dynamic "storage_config" {
    for_each = var.eks_auto_mode ? [1] : []
    content {
      block_storage {
        enabled = var.eks_auto_mode
      }
    }
  }

  depends_on = [aws_security_group.eks_extra]
}

## EKS SG Adicional ##
resource "aws_security_group" "eks_extra" {
  name        = "eks-${var.cluster_name}-sg"
  description = "Extra SG para o cluster EKS"
  vpc_id      = var.vpc_id

  ingress {
    description = "Permitir VPC acessar API server"
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "eks-${var.cluster_name}-sg"
  }
}
