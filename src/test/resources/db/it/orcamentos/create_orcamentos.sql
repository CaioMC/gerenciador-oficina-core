INSERT INTO "_IT".clientes
(id, nome, ativo, email, telefone, documento_identificacao_origem, documento_identificacao_numero, endereco)
VALUES('113e83b4-02e3-4059-8498-f4beafbb5ed9', 'Hakuna', true, 'hakuna.matata@gmail.com', '47-981050203', 0, '82711650030', 'Av. Santos Dumont, 831 - Térreo - Santo Antônio, Joinville - SC, 89218-900');

INSERT INTO "_IT".veiculos
(id, cliente_id, placa, modelo, marca, ano)
VALUES('e30facee-4403-4494-b506-89feddf52ab1', '113e83b4-02e3-4059-8498-f4beafbb5ed9', 'CUA1H50', 'FIAT 147', 'FIAT', 2025);

INSERT INTO "_IT".pecas_insumos (id, modelo_veiculo, descricao, valor_unitario, quantidade_estoque, quantidade_minimo_estoque)
VALUES
('ace50297-1785-4a7d-ae6d-8ec2dc450af6','fusca 1600', 'Óleo do Motor', 45.50, 10, 5);

INSERT INTO "_IT".servicos
(id, descricao, valor_unitario, ativo, observacao)
VALUES('84c052e5-3aa5-465b-9e14-6551e4ba1a65', 'Troca de Óleo',69.00, true, 'xpto');

INSERT INTO "_IT".orcamentos
(id, descricao, cliente_id, veiculo_id, status, observacao, hodometro, data_criacao, usuario_id)
VALUES('3ccaf177-2aa0-4f11-b681-431e1b2323b0', 'XPTO', '113e83b4-02e3-4059-8498-f4beafbb5ed9', 'e30facee-4403-4494-b506-89feddf52ab1', 'AGUARDANDO_APROVACAO', 'OBS', 11000, NOW(), 'ace50297-1785-4a7d-ae6d-8ec2dc450af6');

INSERT INTO "_IT".orcamentos_pecas_insumos
(id, pecas_insumos_id, orcamento_id, descricao, valor_unitario, quantidade)
VALUES('afe064ba-de28-4e67-9b7a-daf2586b95fe', 'ace50297-1785-4a7d-ae6d-8ec2dc450af6', '3ccaf177-2aa0-4f11-b681-431e1b2323b0', 'Óleo do Motor', 45.5, 1);

INSERT INTO "_IT".orcamentos_servicos
(id, servico_id, orcamento_id, descricao, valor_unitario, quantidade)
VALUES('7422d4fd-ea12-415a-98af-810c803ab35c', '84c052e5-3aa5-465b-9e14-6551e4ba1a65', '3ccaf177-2aa0-4f11-b681-431e1b2323b0', 'Troca de Óleo', 69.00, 1);