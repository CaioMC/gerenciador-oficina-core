output "cluster_role_arn" {
  description = "ARN do IAM Role do cluster"
  value       = aws_iam_role.cluster.arn
}

output "node_role_arn" {
  description = "ARN do IAM Role do Node Group"
  value       = aws_iam_role.node.arn
}
