#!/bin/bash
set -e

# =============================
# Deploy da aplicação Spring Boot no Kubernetes EKS
# =============================

NAMESPACE="gerenciador-oficina-core"
K8S_PATH="./devops/k8s/prod"

echo "Iniciando deploy Kubernetes no namespace: $NAMESPACE"

# Verifica se o namespace existe
kubectl get namespace $NAMESPACE || kubectl apply -f $K8S_PATH/namespace.yaml

echo "Aplicando secrets..."
kubectl apply -f $K8S_PATH/postgres-secret.yaml -n $NAMESPACE
kubectl apply -f $K8S_PATH/smtp-secret.yaml -n $NAMESPACE

echo "Aplicando configmap..."
kubectl apply -f $K8S_PATH/configmap.yaml -n $NAMESPACE

echo "Aplicando Metrics Server..."
kubectl apply -f $K8S_PATH/metrics-server.yaml

echo "Subindo aplicação Spring Boot..."
kubectl apply -f $K8S_PATH/deployment.yaml -n $NAMESPACE

echo "Criando service para expor aplicação..."
kubectl apply -f $K8S_PATH/services.yaml -n $NAMESPACE


echo "Aplicando Horizontal Pod Autoscaler..."
kubectl apply -f $K8S_PATH/hpa.yaml -n $NAMESPACE

echo "✅ Deploy realizado com sucesso no EKS!"
