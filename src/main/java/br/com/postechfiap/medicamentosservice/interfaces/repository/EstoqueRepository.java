package br.com.postechfiap.medicamentosservice.interfaces.repository;

import br.com.postechfiap.fiap_estoque_service.entities.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Long> {
    List<EstoqueEntity> findByNomeContainingIgnoreCase(String nome);
    Optional<EstoqueEntity> findBySku(String sku);
    List<EstoqueEntity> findByNomeContainingIgnoreCaseOrSkuIgnoreCase(String nome, String sku);
}
