package br.com.postechfiap.medicamentosservice.infraestructure.gateways;

import br.com.postechfiap.medicamentosservice.application.configuration.FornecedorFeignClientConfig;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.fornecedor.FornecedorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "${client.fornecedor-service.name}",
        url = "${client.fornecedor-service.url}",
        configuration = FornecedorFeignClientConfig.class
)
public interface FornecedorClient {

    @GetMapping("/fornecedores/{id}")
    FornecedorResponse buscarFornecedorPorId(@PathVariable("id") Long id);
}
