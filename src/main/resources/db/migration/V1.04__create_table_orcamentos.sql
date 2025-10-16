CREATE TABLE orcamentos
(
    id               UUID         NOT NULL,
    descricao        VARCHAR(255),
    cliente_id       UUID         NOT NULL,
    veiculo_id       UUID         NOT NULL,
    status           VARCHAR(32)  NOT NULL,
    ordem_servico_id UUID,
    observacao       VARCHAR(255),
    hodometro        BIGINT        NOT NULL DEFAULT 0,
    data_criacao     TIMESTAMP WITH TIME ZONE   NOT NULL,
    CONSTRAINT orcamentos_pkey   PRIMARY KEY (id),
    CONSTRAINT fk_orcamentos_clientes FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    CONSTRAINT fk_veiculos  FOREIGN KEY (veiculo_id)  REFERENCES veiculos(id)
);
CREATE INDEX IF NOT EXISTS idx_orcamentos_cliente_id ON orcamentos(cliente_id);
CREATE INDEX IF NOT EXISTS idx_orcamentos_ordem_servico_id ON orcamentos(ordem_servico_id);

CREATE TABLE orcamentos_servicos
(
    id             UUID         NOT NULL,
    servico_id     UUID         NOT NULL,
    orcamento_id   UUID ,
    descricao      VARCHAR(255) NOT NULL,
    valor_unitario NUMERIC      NOT NULL,
    quantidade     NUMERIC      NOT NULL,
    CONSTRAINT orcamentos_servicos_pkey  PRIMARY KEY (id),
    CONSTRAINT fk_orcamentos_servicos FOREIGN KEY (servico_id) REFERENCES servicos(id)
);




