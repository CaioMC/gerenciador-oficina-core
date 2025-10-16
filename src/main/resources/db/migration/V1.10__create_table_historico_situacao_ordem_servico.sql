CREATE TABLE situacoes_ordens_servico
(
    id                UUID         NOT NULL,
    status            VARCHAR(32)  NOT NULL,
    ordem_servico_id  UUID         NOT NULL,
    data_criacao      TIMESTAMP WITH TIME ZONE  NOT NULL,
    usuario_id        UUID         NOT NULL,

    CONSTRAINT situacoes_ordens_servico_pkey  PRIMARY KEY (id),
    CONSTRAINT fk_situacoes_ordens_servico FOREIGN KEY (ordem_servico_id) REFERENCES ordens_servico(id)
);

-- Índices para melhor performance
CREATE INDEX idx_situacoes_ordens_servico_ordens_servico ON situacoes_ordens_servico(ordem_servico_id);