package br.com.postechfiap.medicamentosservice.interfaces.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.interfaces.UseCase;
import org.yaml.snakeyaml.util.Tuple;

public interface AtualizarMedicamentoUseCase extends UseCase<Tuple<MedicamentoRequest, Long>, MedicamentoResponse> {
}
