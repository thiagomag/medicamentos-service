package br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository;

import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Long> {
    List<EstoqueEntity> findByNomeContainingIgnoreCase(String nome);
    Optional<EstoqueEntity> findBySku(String sku);
    List<EstoqueEntity> findByNomeContainingIgnoreCaseOrSkuIgnoreCase(String nome, String sku);
    List<EstoqueEntity> findByQuantidadeLessThanAndReposicaoPendenteIsFalse(Integer quantidadeIsLessThan);
}
