package br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.adapter.AbstractAdapter;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.MedicamentoEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoResponseAdapter extends AbstractAdapter<MedicamentoEntity, MedicamentoResponse> {

    public MedicamentoResponseAdapter(JsonUtils jsonUtils) {
        super(MedicamentoResponse.class, jsonUtils);
    }
}
