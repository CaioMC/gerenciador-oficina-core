package com.fiap.pj.core.cliente.app.gateways;

import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.infra.cliente.controller.request.ListarClienteRequest;
import com.fiap.pj.infra.cliente.controller.response.ClienteResponse;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.jpa.BaseRepositoryGateway;

public interface ClienteGateway extends BaseRepositoryGateway<Cliente> {


    boolean existsByDocumentoIdentificacaoNumero(String documento);

    Slice<ClienteResponse> listarUsuarios(ListarClienteRequest request);
}
