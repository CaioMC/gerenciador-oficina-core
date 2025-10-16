package com.fiap.pj.core.email.domain.enums;

public enum Template {

    BOAS_VINDAS,
    ORCAMENTO_CRIADO,
    ORCAMENTO_APROVADO,
    ORCAMENTO_RECUSADO,
    ORDEM_SERVICO_MUDANCA_STATUS;

    public String getAssuntoPadrao() {
        switch (this) {
            case BOAS_VINDAS:
                return "Bem-vindo à nossa oficina!";
            case ORCAMENTO_CRIADO:
                return "Orçamento criado";
            case ORCAMENTO_APROVADO:
                return "Orçamento aprovado";
            case ORCAMENTO_RECUSADO:
                return "Orçamento recusado";
            case ORDEM_SERVICO_MUDANCA_STATUS:
                return "Mudança de status da ordem de serviço";
            default:
                return this.name();
        }
    }
}