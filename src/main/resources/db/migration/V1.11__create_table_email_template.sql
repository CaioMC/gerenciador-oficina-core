CREATE TABLE email_template (
    id          UUID         NOT NULL,
    template    VARCHAR(32)  NOT NULL,
    assunto     VARCHAR(255) NOT NULL,
    corpo       TEXT         NOT NULL,
    data_criacao TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT email_template_pkey PRIMARY KEY (id)
);

-- Índices para melhor performance
CREATE INDEX IF NOT EXISTS idx_email_template_template ON email_template(template);