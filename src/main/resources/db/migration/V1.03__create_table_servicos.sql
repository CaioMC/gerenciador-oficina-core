CREATE TABLE servicos
(
    id            UUID             NOT NULL,
    descricao     VARCHAR(255)     NOT NULL,
    valor_unitario NUMERIC,
    ativo         BOOLEAN,
    observacao    VARCHAR(255) ,
    CONSTRAINT servicos_pkey  PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_servicos_descricao ON servicos(descricao);






