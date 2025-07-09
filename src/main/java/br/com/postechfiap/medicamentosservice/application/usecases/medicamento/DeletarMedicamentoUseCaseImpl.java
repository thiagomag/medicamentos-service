package br.com.postechfiap.medicamentosservice.application.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.application.gateways.MedicamentoGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.medicamento.MedicamentoNotFoundException;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.DeletarMedicamentoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeletarMedicamentoUseCaseImpl implements DeletarMedicamentoUseCase {

    private final MedicamentoGateway medicamentoGateway;

    @Override
    public String execute(Long medicamentoId) {
        final var medicamentoDb = medicamentoGateway.findById(medicamentoId)
                .orElseThrow(MedicamentoNotFoundException::new);
        medicamentoGateway.delete(medicamentoDb);
        return medicamentoDb.getSku();
    }
}
