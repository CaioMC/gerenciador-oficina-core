package com.fiap.pj.infra.externo.config;

import com.fiap.pj.core.externo.orcamento.app.AprovarOrcamentoExternoUseCaseImpl;
import com.fiap.pj.core.externo.orcamento.app.ReprovarOrcamentoExternoUseCaseImpl;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.externo.orcamento.usecase.AprovarOrcamentoExternoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.AprovarOrcamentoUseCase;
import com.fiap.pj.core.externo.orcamento.usecase.ReprovarOrcamentoExternoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.ReprovarOrcamentoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrcamentoExternoConfig {

    @Bean
    AprovarOrcamentoExternoUseCase aprovarOrcamentoExternoUseCase(OrcamentoGateway orcamentoGateway,
                                                           AprovarOrcamentoUseCase aprovarOrcamentoUseCase) {
        return new AprovarOrcamentoExternoUseCaseImpl(orcamentoGateway, aprovarOrcamentoUseCase);
    }

    @Bean
    ReprovarOrcamentoExternoUseCase reprovarOrcamentoExternoUseCase(OrcamentoGateway orcamentoGateway,
                                                             ReprovarOrcamentoUseCase reprovarOrcamentoUseCase) {
        return new ReprovarOrcamentoExternoUseCaseImpl(orcamentoGateway, reprovarOrcamentoUseCase);
    }
}