package br.com.postechfiap.medicamentosservice.application.gateways;


import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.MedicamentoEntity;

import java.util.List;
import java.util.Optional;

public interface MedicamentoGateway extends BaseGateway<MedicamentoEntity, Long> {

    List<MedicamentoEntity> findByRequestParams(String nomeMedicamento, String sku, String principioAtivo, String laboratorio);

    Optional<MedicamentoEntity> findBySku(String sku);
}
