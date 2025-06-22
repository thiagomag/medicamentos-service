package br.com.postechfiap.medicamentosservice.infraestructure.controllers;



import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.CadastrarEstoqueUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.DeletarEstoqueUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.BuscarMedicamentoUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.AtualizarMedicamentoUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.CadastrarMedicamentoUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.DeletarMedicamentoUseCase;
import br.com.postechfiap.medicamentosservice.domain.entities.Estoque;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request.EstoqueRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequestParams;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.response.MedicamentoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.yaml.snakeyaml.util.Tuple;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/medicamentos")
@RequiredArgsConstructor
@Validated
@Tag(name = "Medicamento", description = "API para gerenciar medicamentos")
public class MedicamentosController {

    private final CadastrarMedicamentoUseCase cadastrarMedicamentoUseCase;
    private final BuscarMedicamentoUseCase buscarMedicamentoUseCase;
    private final AtualizarMedicamentoUseCase atualizarMedicamentoUseCase;
    private final DeletarMedicamentoUseCase deletarMedicamentoUseCase;
    private final CadastrarEstoqueUseCase cadastrarEstoqueUseCase;
    private final DeletarEstoqueUseCase deletarEstoqueUseCase;

    @PostMapping
    @Operation(summary = "Cadastrar Medicamento e Criar Estoque", description = "Cadastra novos medicamentos e estoque.")
    public ResponseEntity<MedicamentoResponse> cadastrarNovoMedicamento(@RequestBody @Valid MedicamentoRequest dto) {

        // Cadastra Medicamento
        var novoProduto = cadastrarMedicamentoUseCase.execute(dto);

        EstoqueRequest estoqueRequest = new EstoqueRequest(novoProduto.getNome(), novoProduto.getSku(),
                novoProduto.getEstoque());

        var novoEstoque = cadastrarEstoqueUseCase.execute(estoqueRequest);

        novoProduto.setEstoque(novoEstoque.quantidade());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoProduto.getId())
                .toUri();

        return ResponseEntity.created(location).body(novoProduto);
    }

    @GetMapping
    @Operation(summary = "Buscar Medicamento", description = "Buscar medicamento por nome ou sku")
    public ResponseEntity<List<MedicamentoResponse>> buscarMedicamento(@RequestParam(required = false) String nomeMedicamento,
                                                                       @RequestParam(required = false) String sku,
                                                                       @RequestParam(required = false) String principioAtivo,
                                                                       @RequestParam(required = false) String laboratorio) {
        final var requestParams = MedicamentoRequestParams.builder()
                .nomeMedicamento(nomeMedicamento)
                .sku(sku)
                .principioAtivo(principioAtivo)
                .laboratorio(laboratorio)
                .build();

        return ResponseEntity.ok(buscarMedicamentoUseCase.execute(requestParams));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Medicamento", description = "Atualizar um medicamento")
    public ResponseEntity<MedicamentoResponse> atualizarMedicamento(@PathVariable Long id,
                                                                    @Valid @RequestBody MedicamentoRequest request) {

        var medicamento = atualizarMedicamentoUseCase.execute(new Tuple<>(request, id));

        return ResponseEntity.ok(medicamento);
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Medicamento", description = "Remove um medicamento pelo ID e retorna uma mensagem de confirmação.")
    public ResponseEntity<String> deletarMedicamento(@PathVariable Long id) {

        String skuDeletado = deletarMedicamentoUseCase.execute(id);

        deletarEstoqueUseCase.execute(skuDeletado);

        return ResponseEntity.ok("Estoque com sku " + skuDeletado + " foi deletado com sucesso!");
    }



}