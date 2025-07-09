package br.com.postechfiap.medicamentosservice.infraestructure.gateways;

import br.com.postechfiap.medicamentosservice.application.gateways.MedicamentoGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.MedicamentoEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.MedicamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class MedicamentoRepositoryGateway implements MedicamentoGateway {

    private final MedicamentoRepository repository;

    @Override
    public MedicamentoEntity save(MedicamentoEntity entity) {
        entity.setCreatedAt(LocalDateTime.now());
        return repository.save(entity) ;
    }

    @Override
    public List<MedicamentoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MedicamentoEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(MedicamentoEntity entity) {
        entity.delete();
        repository.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public MedicamentoEntity update(MedicamentoEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
        return  repository.save(entity);
    }

    @Override
    public List<MedicamentoEntity> findByRequestParams(String nomeMedicamento, String sku, String principioAtivo, String laboratorio) {
        return repository.findByRequestParams(nomeMedicamento, sku, principioAtivo, laboratorio);
    }

    @Override
    public Optional<MedicamentoEntity> findBySku(String sku) {
        return repository.findBySku(sku);
    }
}
