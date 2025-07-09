package br.com.postechfiap.medicamentosservice.application.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.application.gateways.MedicamentoGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento.MedicamentoResponseAdapter;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequestParams;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.medicamento.MedicamentoNotFoundException;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.BuscarMedicamentoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuscarMedicamentoUseCaseImpl implements BuscarMedicamentoUseCase {

    private final MedicamentoResponseAdapter medicamentoResponseAdapter;
    private final MedicamentoGateway medicamentoGateway;

    @Override
    public List<MedicamentoResponse> execute(MedicamentoRequestParams medicamentoRequestParams) {
        final var nomeMedicamento = Optional.ofNullable(medicamentoRequestParams.getNomeMedicamento()).orElse("");
        final var sku = Optional.ofNullable(medicamentoRequestParams.getSku()).orElse("");
        final var principioAtivo = Optional.ofNullable(medicamentoRequestParams.getPrincipioAtivo()).orElse("");
        final var laboratorio = Optional.ofNullable(medicamentoRequestParams.getLaboratorio()).orElse("");

        final var medicamentosEncontrados = medicamentoGateway.findByRequestParams(nomeMedicamento,
                sku, principioAtivo, laboratorio);

        if (medicamentosEncontrados.isEmpty()) {
            throw new MedicamentoNotFoundException();
        }


        return medicamentosEncontrados.stream().map(medicamentoResponseAdapter::adapt)
                .toList();
    }

}
