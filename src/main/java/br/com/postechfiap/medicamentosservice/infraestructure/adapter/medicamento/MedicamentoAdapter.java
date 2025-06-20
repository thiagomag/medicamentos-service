package br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.adapter.AbstractAdapter;
import br.com.postechfiap.medicamentosservice.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.domain.entities.Medicamento;
import br.com.postechfiap.medicamentosservice.infraestructure.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoAdapter extends AbstractAdapter<MedicamentoRequest, Medicamento> {

    public MedicamentoAdapter(JsonUtils jsonUtils) {
        super(Medicamento.class, jsonUtils);
    }
}
