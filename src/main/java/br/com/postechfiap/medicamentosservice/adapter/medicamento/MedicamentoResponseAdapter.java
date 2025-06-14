package br.com.postechfiap.medicamentosservice.adapter.medicamento;

import br.com.postechfiap.medicamentosservice.adapter.AbstractAdapter;
import br.com.postechfiap.medicamentosservice.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.entities.Medicamento;
import br.com.postechfiap.medicamentosservice.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoResponseAdapter extends AbstractAdapter<Medicamento, MedicamentoResponse> {

    public MedicamentoResponseAdapter(JsonUtils jsonUtils) {
        super(MedicamentoResponse.class, jsonUtils);
    }
}
