package br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.AtualizaMedicamentoRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.application.interfaces.UseCase;
import org.yaml.snakeyaml.util.Tuple;

public interface AtualizarMedicamentoUseCase extends
        UseCase<Tuple<AtualizaMedicamentoRequest, Long>, Tuple<MedicamentoResponse, String>> {
}
