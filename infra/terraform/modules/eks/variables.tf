## EKS Variables ##
variable "cluster_name" { default = "projeto" }
variable "eks_auto_mode" {
  description = "Habilita EKS Auto Mode (zonal shift, compute, storage, network configs)"
  default     = false
}

## VPC ##
variable "vpc_subnets" {}
variable "vpc_id" {}

## Role ##
variable "role_arn" {}
variable "node_role_arn" {}

