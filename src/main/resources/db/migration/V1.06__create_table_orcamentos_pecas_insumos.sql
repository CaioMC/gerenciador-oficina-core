CREATE TABLE orcamentos_pecas_insumos
(
    id               UUID         NOT NULL,
    pecas_insumos_id UUID         NOT NULL,
    orcamento_id     UUID ,
    descricao        VARCHAR(255)  NOT NULL,
    valor_unitario   NUMERIC(10,2) NOT NULL,
    quantidade       INTEGER NOT NULL,
    CONSTRAINT orcamentos_pecas_insumos_pkey  PRIMARY KEY (id),
    CONSTRAINT fk_orcamentos_pecas_insumos FOREIGN KEY (pecas_insumos_id) REFERENCES pecas_insumos(id)
);