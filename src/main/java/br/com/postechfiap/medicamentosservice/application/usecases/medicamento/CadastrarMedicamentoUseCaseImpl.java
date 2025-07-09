package br.com.postechfiap.medicamentosservice.application.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.application.gateways.MedicamentoGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento.MedicamentoResponseAdapter;
import br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento.MedicamentoAdapter;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.MedicamentoEntity;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.CadastrarMedicamentoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CadastrarMedicamentoUseCaseImpl implements CadastrarMedicamentoUseCase {

    private final MedicamentoAdapter medicamentoAdapter;
    private final MedicamentoResponseAdapter medicamentoResponseAdapter;
    private final MedicamentoGateway medicamentoGateway;

    @Override
    public MedicamentoResponse execute(MedicamentoRequest request) {
        var medicamento = MedicamentoEntity.builder().nome(request.nome()).principioAtivo(request.principioAtivo())
                .laboratorio(request.laboratorio()).dosagem(request.dosagem())
                .descricao(request.descricao()).preco(request.preco()).build();

        medicamento.gerarSku();

        final var medicamentoSalvo = medicamentoGateway.save(medicamento);
        // Transforma Entity em DTO response
        MedicamentoResponse response = medicamentoResponseAdapter.adapt(medicamentoSalvo);

        response.setEstoque(request.quantidadeEmEstoque());


        return response;
    }
}
