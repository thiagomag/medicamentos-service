package br.com.postechfiap.medicamentosservice.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.adapter.medicamento.MEdicamentoResponseAdapter;
import br.com.postechfiap.medicamentosservice.adapter.medicamento.MedicamentoAdapter;
import br.com.postechfiap.medicamentosservice.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.interfaces.repository.MerdicamentoRepository;
import br.com.postechfiap.medicamentosservice.interfaces.usecases.medicamento.CadastrarMedicamentoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CadastrarMedicamentoUseCaseImpl implements CadastrarMedicamentoUseCase {

    private final MedicamentoAdapter medicamentoAdapter;
    private final MEdicamentoResponseAdapter medicamentoResponseAdapter;
    private final MerdicamentoRepository medicamentoRepository;

    @Override
    public MedicamentoResponse execute(MedicamentoRequest medicamentoRequest) {
        final var medicamento = medicamentoAdapter.adapt(medicamentoRequest);
        medicamento.gerarSku();
        final var medicamentoSalvo = medicamentoRepository.save(medicamento);

        return medicamentoResponseAdapter.adapt(medicamentoSalvo);
    }
}
