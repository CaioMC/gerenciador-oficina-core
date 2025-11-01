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




