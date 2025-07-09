package br.com.postechfiap.medicamentosservice.application.gateways;

import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.EstoqueEntity;

import java.util.List;
import java.util.Optional;

public interface EstoqueGateway extends  BaseGateway<EstoqueEntity,Long> {
    List<EstoqueEntity> findByNomeContainingIgnoreCase(String nome);
    Optional<EstoqueEntity> findBySku(String sku);
    List<EstoqueEntity> findByNomeContainingIgnoreCaseOrSkuIgnoreCase(String nome, String sku);
    List<EstoqueEntity> findByQuantidadeLessThan(int quantidade);
}
