package br.com.postechfiap.medicamentosservice.adapter.medicamento;

import br.com.postechfiap.medicamentosservice.adapter.AbstractAdapter;
import br.com.postechfiap.medicamentosservice.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.entities.Medicamento;
import br.com.postechfiap.medicamentosservice.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoAdapter extends AbstractAdapter<MedicamentoRequest, Medicamento> {

    public MedicamentoAdapter(JsonUtils jsonUtils) {
        super(Medicamento.class, jsonUtils);
    }
}
