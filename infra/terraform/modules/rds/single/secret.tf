resource "aws_secretsmanager_secret" "secret_user_postgres" {
  name                    = "rds/${var.rds_name}/postgres"
  description             = "Credenciais do banco PostgreSQL RDS"
  recovery_window_in_days = 0
}

resource "aws_secretsmanager_secret_version" "secret_password_postgres" {
  secret_id = aws_secretsmanager_secret.secret_user_postgres.id
  secret_string = jsonencode({
    username = "postgres"
    password = var.password
  })
}

