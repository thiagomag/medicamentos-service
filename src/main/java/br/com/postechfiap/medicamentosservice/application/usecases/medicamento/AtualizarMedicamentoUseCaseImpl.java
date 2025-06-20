package br.com.postechfiap.medicamentosservice.application.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento.MedicamentoResponseAdapter;
import br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento.MedicamentoAdapter;
import br.com.postechfiap.medicamentosservice.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.medicamento.MedicamentoNotFoundException;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.MedicamentoRepository;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.AtualizarMedicamentoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.Tuple;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtualizarMedicamentoUseCaseImpl implements AtualizarMedicamentoUseCase {

    private final MedicamentoAdapter medicamentoAdapter;
    private final MedicamentoResponseAdapter medicamentoResponseAdapter;
    private final MedicamentoRepository medicamentoRepository;

    @Override
    public MedicamentoResponse execute(Tuple<MedicamentoRequest, Long> medicamentoRequestLongTuple) {
        final var medicamentoRequest = medicamentoRequestLongTuple._1();
        final var medicamentoId = medicamentoRequestLongTuple._2();
        final var medicamentoDb = medicamentoRepository.findById(medicamentoId)
                .orElseThrow(MedicamentoNotFoundException::new);
        final var medicamento = medicamentoAdapter.adapt(medicamentoRequest, medicamentoDb);
        final var medicamentoAtualizado = medicamentoRepository.save(medicamento);
        return medicamentoResponseAdapter.adapt(medicamentoAtualizado);
    }
}
