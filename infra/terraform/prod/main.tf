## Ambiente para rodar no FREE TIER da AWS

# Provider Config ##
provider "aws" {
  region = var.aws_region
}


# ## IAM ROLES Config ##
module "eks_iam_roles" {
  source        = "../modules/eks-iam-roles"
  eks_auto_mode = var.eks_auto_mode
}

## VPC Config ##
module "vpc" {
  source   = "../modules/vpc"
  vpc_cidr = var.vpc_cidr
  vpc_name = var.project_name
}

# ## EKS Config ##
module "eks" {
  source        = "../modules/eks"
  cluster_name  = var.project_name
  role_arn      = module.eks_iam_roles.cluster_role_arn
  node_role_arn = module.eks_iam_roles.node_role_arn
  vpc_id        = module.vpc.vpc_id
  vpc_subnets   = module.vpc.public_subnet_ids
  eks_auto_mode = var.eks_auto_mode

  depends_on = [module.eks_iam_roles, module.vpc]

}


# SG Config ##
module "sg_rds" {
  source     = "../modules/security_group/sg_rds"
  vpc_cidr   = var.vpc_cidr
  vpc_id     = module.vpc.vpc_id
  depends_on = [module.vpc]
}

## RDS Config ##
module "rds" {
  source                    = "../modules/rds/single"
  rds_name                  = var.project_name
  database_subnets          = module.vpc.private_subnet_ids
  security_group_id         = [module.sg_rds.security_group_id]
  rds_engine                = var.aws_rds_engine
  rds_engine_version        = var.aws_rds_engine_version
  rds_instance_class        = var.aws_rds_instance_class
  rds_storage_type          = var.aws_rds_storage_type
  db_parameter_group_family = var.db_parameter_group_family

  depends_on = [module.sg_rds]
}


