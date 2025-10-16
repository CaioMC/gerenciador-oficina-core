CREATE TABLE clientes
(
    id         UUID           NOT NULL,
    nome       VARCHAR(255)   NOT NULL,
    ativo      BOOLEAN        NOT NULL,
    email      VARCHAR(255),
    telefone   VARCHAR(32),
    endereco   VARCHAR(255),
    documento_identificacao_origem SMALLINT NOT NULL,
    documento_identificacao_numero VARCHAR(64) NOT NULL UNIQUE,
    CONSTRAINT cliente_pkey  PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS idx_clientes_nome ON clientes(nome);
CREATE INDEX IF NOT EXISTS idx_cliente_documento_identificacao_numero ON clientes(documento_identificacao_numero);





