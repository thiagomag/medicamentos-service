package br.com.postechfiap.medicamentosservice.infraestructure.gateways;

import br.com.postechfiap.medicamentosservice.application.gateways.EstoqueGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.EstoqueEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EstoqueRepositoryGateway implements EstoqueGateway {

   private final EstoqueRepository estoqueRepository;

    @Override
    public EstoqueEntity save(EstoqueEntity entity) {
        entity.setCreatedAt(LocalDateTime.now());
        return estoqueRepository.save(entity) ;
    }

    @Override
    public List<EstoqueEntity> findAll() {
        return estoqueRepository.findAll();
    }

    @Override
    public Optional<EstoqueEntity> findById(Long id) {
        return estoqueRepository.findById(id);
    }

    @Override
    public void delete(EstoqueEntity entity) {
        entity.delete();
        estoqueRepository.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return estoqueRepository.existsById(id);
    }

    @Override
    public EstoqueEntity update(EstoqueEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
        return  estoqueRepository.save(entity);
    }

    @Override
    public List<EstoqueEntity> findByNomeContainingIgnoreCase(String nome) {
        return estoqueRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public Optional<EstoqueEntity> findBySku(String sku) {
        return estoqueRepository.findBySku(sku);
    }

    @Override
    public List<EstoqueEntity> findByNomeContainingIgnoreCaseOrSkuIgnoreCase(String nome, String sku) {
        return estoqueRepository.findByNomeContainingIgnoreCaseOrSkuIgnoreCase(nome, sku);
    }

    @Override
    public List<EstoqueEntity> findByQuantidadeLessThan(int quantidade) {
        return estoqueRepository.findByQuantidadeLessThanAndReposicaoPendenteIsFalse(quantidade);
    }
}
