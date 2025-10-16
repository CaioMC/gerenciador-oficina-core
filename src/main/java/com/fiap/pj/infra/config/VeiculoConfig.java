package com.fiap.pj.infra.config;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.veiculo.app.AdicionarVeiculoClienteUseCaseImpl;
import com.fiap.pj.core.veiculo.app.RemoverVeiculoClienteUseCaseImpl;
import com.fiap.pj.core.veiculo.app.gateways.VeiculoGateway;
import com.fiap.pj.infra.veiculo.gateways.VeiculoRepositoryGatewayImpl;
import com.fiap.pj.infra.veiculo.persistence.VeiculoRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VeiculoConfig {


    @Bean
    AdicionarVeiculoClienteUseCaseImpl adicionarVeiculoClienteUseCase(VeiculoGateway veiculoGateway, ClienteGateway clienteGateway
    ) {
        return new AdicionarVeiculoClienteUseCaseImpl(clienteGateway, veiculoGateway);
    }

    @Bean
    RemoverVeiculoClienteUseCaseImpl removerVeiculoClienteUseCase(VeiculoGateway veiculoGateway, ClienteGateway clienteGateway
    ) {
        return new RemoverVeiculoClienteUseCaseImpl(clienteGateway, veiculoGateway);
    }


    @Bean
    VeiculoRepositoryGatewayImpl veiculoGateway(VeiculoRepositoryJpa repository
    ) {
        return new VeiculoRepositoryGatewayImpl(repository);
    }
}
