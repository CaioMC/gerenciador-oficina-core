package com.fiap.pj.core.cliente.app.usecase;

import com.fiap.pj.infra.cliente.controller.request.ListarClienteRequest;
import com.fiap.pj.infra.cliente.controller.response.ClienteResponse;
import com.fiap.pj.infra.sk.api.Slice;

public interface ListarClienteUseCase {

    Slice<ClienteResponse> handle(ListarClienteRequest request);
}
