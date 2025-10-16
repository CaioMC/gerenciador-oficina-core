output "security_group_id" {
  description = "The ID of the security group"
  value       = module.sg_rds.security_group_id
}