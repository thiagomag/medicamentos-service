package br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.application.interfaces.UseCase;

public interface CadastrarMedicamentoUseCase extends UseCase<MedicamentoRequest, MedicamentoResponse> {
}
