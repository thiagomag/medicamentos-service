package br.com.postechfiap.medicamentosservice.interfaces.repository;

import br.com.postechfiap.medicamentosservice.entities.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    List<Estoque> findByNomeContainingIgnoreCase(String nome);
    Optional<Estoque> findBySku(String sku);
    List<Estoque> findByNomeContainingIgnoreCaseOrSkuIgnoreCase(String nome, String sku);
}
