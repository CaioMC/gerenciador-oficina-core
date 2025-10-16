variable "rds_name" { default = "projeto" }
variable "rds_db_name" { default = "postgres" }
variable "rds_engine" { default = "postgres" }
variable "rds_engine_version" { default = "16.6" }
variable "rds_instance_class" { default = "db.t4g.micro" }
variable "rds_storage_type" { default = "gp2" }
variable "db_parameter_group_family" { default = "postgres16" }
variable "password" { default = "oficina123" }

variable "rds_allocated_storage" {
  description = "Armazenamento em GB (Free Tier tipicamente até 20GB)"
  default     = 20
}

variable "database_subnets" {}
variable "security_group_id" {}

