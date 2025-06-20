package br.com.postechfiap.medicamentosservice.application.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento.MedicamentoResponseAdapter;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequestParams;
import br.com.postechfiap.medicamentosservice.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.MedicamentoRepository;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.BuscarMedicamentoUseCase;
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
    private final MedicamentoRepository medicamentoRepository;

    @Override
    public List<MedicamentoResponse> execute(MedicamentoRequestParams medicamentoRequestParams) {
        final var nomeMedicamento = Optional.ofNullable(medicamentoRequestParams.getNomeMedicamento()).orElse("");
        final var sku = Optional.ofNullable(medicamentoRequestParams.getSku()).orElse("");
        final var principioAtivo = Optional.ofNullable(medicamentoRequestParams.getPrincipioAtivo()).orElse("");
        final var laboratorio = Optional.ofNullable(medicamentoRequestParams.getLaboratorio()).orElse("");
        return medicamentoRepository.findByRequestParams(nomeMedicamento, sku, principioAtivo, laboratorio)
                .stream()
                .map(medicamentoResponseAdapter::adapt)
                .toList();
    }
}
