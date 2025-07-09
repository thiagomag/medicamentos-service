package br.com.postechfiap.medicamentosservice.application.usecases.medicamento;

import br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento.MedicamentoResponseAdapter;
import br.com.postechfiap.medicamentosservice.infraestructure.adapter.medicamento.MedicamentoAdapter;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.fornecedor.FornecedorResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.AtualizaMedicamentoRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.fornecedor.FornecedorNotFoundException;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.medicamento.MedicamentoNotFoundException;
import br.com.postechfiap.medicamentosservice.infraestructure.gateways.FornecedorClient;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.MedicamentoEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.MedicamentoRepository;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.AtualizarMedicamentoUseCase;
import feign.FeignException;
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
    private final FornecedorClient fornecedorClient;

    @Override
    public Tuple<MedicamentoResponse,String> execute(Tuple<AtualizaMedicamentoRequest, Long> medicamentoRequestLongTuple) {
        final var request = medicamentoRequestLongTuple._1();
        final var medicamentoId = medicamentoRequestLongTuple._2();
        final var medicamentoDb = medicamentoRepository.findById(medicamentoId)
                .orElseThrow(MedicamentoNotFoundException::new);

        final String skuAntigo = medicamentoDb.getSku();
        System.out.println("Medicamento DB SKU ANTIGO! : " + skuAntigo);
        System.out.println("Medicamento DB Objeto : " + medicamentoDb);

        FornecedorResponse fornecedor;
        try {
            fornecedor = fornecedorClient.buscarFornecedorPorId(request.fornecedorId());
        } catch (FeignException.NotFound e) {
            throw new FornecedorNotFoundException(request.fornecedorId());
        }

        final var medicamentoAtualizado = MedicamentoEntity.builder().id(medicamentoId).nome(request.nome())
                .principioAtivo(request.principioAtivo()).laboratorio(fornecedor.getNome())
                .dosagem(request.dosagem()).descricao(request.descricao()).preco(request.preco()).build();
        medicamentoAtualizado.gerarSku();

        medicamentoRepository.save(medicamentoAtualizado);
        var response =  medicamentoResponseAdapter.adapt(medicamentoAtualizado);


        System.out.println("Medicamento DB SKU ANTIGO! Antes do Retorno : " + skuAntigo);
        System.out.println("SKU NOVO : " + response.getSku());
        
        return new Tuple<>(response, skuAntigo);
    }
}
