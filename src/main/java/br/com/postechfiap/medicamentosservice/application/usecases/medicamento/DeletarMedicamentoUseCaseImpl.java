package br.com.postechfiap.medicamentosservice.application.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.medicamento.MedicamentoNotFoundException;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.MedicamentoRepository;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.DeletarMedicamentoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeletarMedicamentoUseCaseImpl implements DeletarMedicamentoUseCase {

    private final MedicamentoRepository medicamentoRepository;

    @Override
    public String execute(Long medicamentoId) {
        final var medicamentoDb = medicamentoRepository.findById(medicamentoId)
                .orElseThrow(MedicamentoNotFoundException::new);
        medicamentoDb.delete();
        medicamentoRepository.save(medicamentoDb);
        return medicamentoDb.getSku();
    }
}
