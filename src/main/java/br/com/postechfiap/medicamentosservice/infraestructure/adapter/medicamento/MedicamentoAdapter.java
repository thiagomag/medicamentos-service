package br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.adapter.AbstractAdapter;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.MedicamentoEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoAdapter extends AbstractAdapter<MedicamentoRequest, MedicamentoEntity> {

    public MedicamentoAdapter(JsonUtils jsonUtils) {
        super(MedicamentoEntity.class, jsonUtils);
    }
}
