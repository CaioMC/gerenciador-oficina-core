CREATE TABLE diagnosticos
(
    id                  UUID            NOT NULL,
    descricao           VARCHAR(255)    NOT NULL,
    data_criacao        TIMESTAMP WITH TIME ZONE     NOT NULL,
    ordem_servico_id   UUID            NOT NULL,
    CONSTRAINT diagnostico_pkey  PRIMARY KEY (id)
);

-- Índices para melhor performance
CREATE INDEX idx_diagnosticos_ordem_servico ON diagnosticos(ordem_servico_id);