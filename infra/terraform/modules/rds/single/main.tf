resource "aws_db_subnet_group" "rds_snet_group" {
  name       = "rds_snet_group"
  subnet_ids = var.database_subnets

  tags = {
    Name = "rds_snet_group"
  }
}

resource "aws_db_parameter_group" "db_parameter_group" {
  name        = "db-rds-${var.rds_engine}-16"
  family      = var.db_parameter_group_family
  description = "RDS cluster parameter group"
}


resource "aws_db_instance" "rds_instance" {
  identifier              = "${var.rds_name}-instance"
  engine                  = var.rds_engine
  engine_version          = var.rds_engine_version
  instance_class          = var.rds_instance_class
  allocated_storage       = var.rds_allocated_storage
  storage_type            = var.rds_storage_type
  db_name                 = var.rds_db_name
  username                = jsondecode(aws_secretsmanager_secret_version.secret_password_postgres.secret_string)["username"]
  password                = jsondecode(aws_secretsmanager_secret_version.secret_password_postgres.secret_string)["password"]
  parameter_group_name    = aws_db_parameter_group.db_parameter_group.name
  db_subnet_group_name    = aws_db_subnet_group.rds_snet_group.name
  vpc_security_group_ids  = var.security_group_id
  skip_final_snapshot     = true
  publicly_accessible     = false # Libera para acesso externo
  multi_az                = false
  backup_retention_period = 0 # zero para evitar snapshots automáticos; em produção defina >=1
  deletion_protection     = false

  tags = {
    Terraform = "true"
  }

  # otimizações para reduzir custo/free-tier:
  auto_minor_version_upgrade   = true
  performance_insights_enabled = false

}


