package br.com.postechfiap.medicamentosservice.interfaces.usecases;

import br.com.postechfiap.medicamentosservice.dto.medicamento.request.MedicamentoRequestParams;
import br.com.postechfiap.medicamentosservice.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.interfaces.UseCase;

import java.util.List;

public interface BuscarMedicamentoUseCase extends UseCase<MedicamentoRequestParams, List<MedicamentoResponse>> {
}
