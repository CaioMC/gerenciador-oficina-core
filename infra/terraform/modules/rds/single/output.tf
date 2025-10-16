output "db_endpoint" {
  description = "Endpoint do banco (host)"
  value       = aws_db_instance.rds_instance.address
}

output "db_port" {
  description = "Porta do banco"
  value       = aws_db_instance.rds_instance.port
}
