package br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.adapter.AbstractAdapter;
import br.com.postechfiap.medicamentosservice.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.domain.entities.Medicamento;
import br.com.postechfiap.medicamentosservice.infraestructure.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoResponseAdapter extends AbstractAdapter<Medicamento, MedicamentoResponse> {

    public MedicamentoResponseAdapter(JsonUtils jsonUtils) {
        super(MedicamentoResponse.class, jsonUtils);
    }
}
