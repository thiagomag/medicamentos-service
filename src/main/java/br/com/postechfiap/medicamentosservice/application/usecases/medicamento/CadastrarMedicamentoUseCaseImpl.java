package br.com.postechfiap.medicamentosservice.application.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento.MedicamentoResponseAdapter;
import br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento.MedicamentoAdapter;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.fornecedor.FornecedorResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.fornecedor.FornecedorNotFoundException;
import br.com.postechfiap.medicamentosservice.infraestructure.gateways.FornecedorClient;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.MedicamentoEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.MedicamentoRepository;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.CadastrarMedicamentoUseCase;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CadastrarMedicamentoUseCaseImpl implements CadastrarMedicamentoUseCase {

    private final MedicamentoAdapter medicamentoAdapter;
    private final MedicamentoResponseAdapter medicamentoResponseAdapter;
    private final MedicamentoRepository medicamentoRepository;

    private final FornecedorClient fornecedorClient;

    @Override
    public MedicamentoResponse execute(MedicamentoRequest request) {

        FornecedorResponse fornecedor;

        try {
            fornecedor = fornecedorClient.buscarFornecedorPorId(request.fornecedorId());
        } catch (FeignException.NotFound e) {
            throw new FornecedorNotFoundException(request.fornecedorId());
        }

        var medicamento = MedicamentoEntity.builder().nome(request.nome()).principioAtivo(request.principioAtivo())
                .fornecedorId(request.fornecedorId()).laboratorio(fornecedor.getNome()).dosagem(request.dosagem())
                .descricao(request.descricao()).preco(request.preco()).build();

        medicamento.gerarSku();

        final var medicamentoSalvo = medicamentoRepository.save(medicamento);
        // Transforma Entity em DTO response
        MedicamentoResponse response = medicamentoResponseAdapter.adapt(medicamentoSalvo);

        response.setEstoque(request.quantidadeEmEstoque());


        return response;
    }
}
