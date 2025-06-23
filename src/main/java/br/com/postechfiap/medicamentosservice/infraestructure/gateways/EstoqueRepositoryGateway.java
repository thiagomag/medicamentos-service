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

   private final EstoqueRepository repository;

    @Override
    public EstoqueEntity save(EstoqueEntity entity) {
        entity.setCreatedAt(LocalDateTime.now());
        return repository.save(entity) ;
    }

    @Override
    public List<EstoqueEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<EstoqueEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(EstoqueEntity entity) {
        repository.delete(entity);

    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public EstoqueEntity update(EstoqueEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
        return  repository.save(entity);
    }
}
