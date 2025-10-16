CREATE TABLE ordens_servico
(
    id               UUID         NOT NULL,
    cliente_id       UUID         NOT NULL,
    veiculo_id       UUID         NOT NULL,
    status           VARCHAR(32)  NOT NULL,
    data_criacao     TIMESTAMP WITH TIME ZONE  NOT NULL,
    data_conclusao   TIMESTAMP WITH TIME ZONE,
    diagnostico_id   UUID,
    CONSTRAINT ordens_servico_pkey  PRIMARY KEY (id),
    CONSTRAINT fk_ordens_servico_diagnostico FOREIGN KEY (diagnostico_id) REFERENCES diagnosticos(id)
);