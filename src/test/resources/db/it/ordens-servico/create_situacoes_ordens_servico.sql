INSERT INTO "_IT".usuarios
(id, nome, sobre_nome, ativo, email, senha)
VALUES('ace50297-1785-4a7d-ae6d-8ec2dc450af6', 'Severino', 'da Silva',true,'severino.dasilva@gmail.com','1234');

INSERT INTO "_IT".usuarios
(id, nome, sobre_nome, ativo, email, senha)
VALUES('37255ba6-ca9b-4024-af66-d2135eb7ffca', 'Florinda', 'Oliveira',true,'florinta.oliveira@gmail.com','1234');

INSERT INTO "_IT".clientes
(id, nome, ativo, email, telefone, documento_identificacao_origem, documento_identificacao_numero, endereco)
VALUES('113e83b4-02e3-4059-8498-f4beafbb5ed9', 'Hakuna', true, 'hakuna.matata@gmail.com', '47-981050203', 0, '82711650030', 'Av. Santos Dumont, 831 - Térreo - Santo Antônio, Joinville - SC, 89218-900');

INSERT INTO "_IT".veiculos
(id, cliente_id, placa, modelo, marca, ano)
VALUES('e30facee-4403-4494-b506-89feddf52ab1', '113e83b4-02e3-4059-8498-f4beafbb5ed9', 'CUA1H50', 'FIAT 147', 'FIAT', 2025);

INSERT INTO "_IT".diagnosticos
(id, descricao, data_criacao, ordem_servico_id)
VALUES('a52a365c-bbe5-4993-b1af-5dcc6ac125d0', 'Amortecedor vazando', NOW(), 'da088a1a-67ce-463b-a79b-a4ab28fe44ae');

INSERT INTO "_IT".ordens_servico
(id, cliente_id, veiculo_id, status, data_criacao, data_conclusao, diagnostico_id, usuario_id)
VALUES('01bd50f7-2c6d-45f5-bee2-cea5b7e0be9a', '113e83b4-02e3-4059-8498-f4beafbb5ed9', 'e30facee-4403-4494-b506-89feddf52ab1', 'ENTREGUE', NOW(), null, 'a52a365c-bbe5-4993-b1af-5dcc6ac125d0','37255ba6-ca9b-4024-af66-d2135eb7ffca' );

INSERT INTO "_IT".situacoes_ordens_servico
(id, status, ordem_servico_id, data_criacao, usuario_id)
VALUES('765d2248-48bd-47da-8fc8-abd3867acae8','CRIADA', '01bd50f7-2c6d-45f5-bee2-cea5b7e0be9a', '2025-08-08T12:06:50-03:00', '37255ba6-ca9b-4024-af66-d2135eb7ffca');

INSERT INTO "_IT".situacoes_ordens_servico
(id, status, ordem_servico_id, data_criacao, usuario_id)
VALUES('df483ff4-9527-42aa-98c0-0b68ba777962','EM_DIAGNOSTICO', '01bd50f7-2c6d-45f5-bee2-cea5b7e0be9a', '2025-08-09T10:06:50-03:00', 'ace50297-1785-4a7d-ae6d-8ec2dc450af6');

INSERT INTO "_IT".situacoes_ordens_servico
(id, status, ordem_servico_id, data_criacao, usuario_id)
VALUES('8c666774-41f3-4eff-a9d8-70cc32dbc6d2','AGUARDANDO_APROVACAO', '01bd50f7-2c6d-45f5-bee2-cea5b7e0be9a', '2025-08-10T10:06:50-03:00', 'ace50297-1785-4a7d-ae6d-8ec2dc450af6');

INSERT INTO "_IT".situacoes_ordens_servico
(id, status, ordem_servico_id, data_criacao, usuario_id)
VALUES('bd41fb0f-0776-4252-b4c4-3b934179ac27','EM_EXECUCAO', '01bd50f7-2c6d-45f5-bee2-cea5b7e0be9a', '2025-08-11T10:06:50-03:00', 'ace50297-1785-4a7d-ae6d-8ec2dc450af6');

 INSERT INTO "_IT".situacoes_ordens_servico
 (id, status, ordem_servico_id, data_criacao, usuario_id)
 VALUES('99a42b05-f88b-40b4-be95-88494e661fc3','FINALIZADA', '01bd50f7-2c6d-45f5-bee2-cea5b7e0be9a', '2025-08-12T10:06:50-03:00', 'ace50297-1785-4a7d-ae6d-8ec2dc450af6');

 INSERT INTO "_IT".situacoes_ordens_servico
 (id, status, ordem_servico_id, data_criacao, usuario_id)
 VALUES('a5eeb67b-4e9c-4c31-bfab-e1abc3f54e94','AGUARDANDO_RETIRADA', '01bd50f7-2c6d-45f5-bee2-cea5b7e0be9a', '2025-08-12T12:06:50-03:00', '37255ba6-ca9b-4024-af66-d2135eb7ffca');

 INSERT INTO "_IT".situacoes_ordens_servico
 (id, status, ordem_servico_id, data_criacao, usuario_id)
 VALUES('ddcffd69-cf96-4260-a56f-573cba69899c','ENTREGUE', '01bd50f7-2c6d-45f5-bee2-cea5b7e0be9a', '2025-08-13T10:06:50-03:00', '37255ba6-ca9b-4024-af66-d2135eb7ffca');

