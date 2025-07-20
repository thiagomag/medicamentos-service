package br.com.postechfiap.medicamentosservice.infraestructure.controllers;



import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.AtualizarEstoqueUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.BuscarEstoquePorSkuUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.CadastrarEstoqueUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.DeletarEstoqueUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.BuscarMedicamentoUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.AtualizarMedicamentoUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.CadastrarMedicamentoUseCase;
import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.medicamento.DeletarMedicamentoUseCase;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request.AtualizarEstoqueDto;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request.EstoqueRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.AtualizaMedicamentoRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request.MedicamentoRequestParams;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.response.MedicamentoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final AtualizarEstoqueUseCase atualizarEstoqueUseCase;
    private final BuscarEstoquePorSkuUseCase buscarEstoqueUseCase;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_MEDIC', 'ROLE_NURSE')")
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

        var medicamentoBuscado = buscarMedicamentoUseCase.execute(requestParams);

        var medicamentosComEstoque = medicamentoBuscado.stream()
                .map(medicamento -> {

                    EstoqueResponse estoqueResponse = buscarEstoqueUseCase.execute(medicamento.getSku())
                            .estoques().get(0);

                    // 2. Atualiza o campo 'estoque' no objeto MedicamentoResponse
                    medicamento.setEstoque(estoqueResponse.quantidade());

                    // 3. Retorna o MedicamentoResponse modificado
                    return medicamento;
                })
                .toList();




        return ResponseEntity.ok(medicamentosComEstoque);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Medicamento", description = "Atualizar um medicamento")
    public ResponseEntity<MedicamentoResponse> atualizarMedicamento(@PathVariable Long id,
                                                                    @Valid @RequestBody AtualizaMedicamentoRequest request) {

        var medicamento = atualizarMedicamentoUseCase.execute(new Tuple<>(request, id));

        AtualizarEstoqueDto atualizarEstoque = new AtualizarEstoqueDto(medicamento._2(),
                medicamento._1().getNome(),medicamento._1().getSku());

        System.out.println("Atualizar Estoque DTO: " + atualizarEstoque);

        atualizarEstoqueUseCase.execute(atualizarEstoque);


        return ResponseEntity.ok(medicamento._1());
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @Operation(summary = "Deletar Medicamento", description = "Remove um medicamento pelo ID e retorna uma mensagem de confirmação.")
    public ResponseEntity<String> deletarMedicamento(@PathVariable Long id) {

        String skuDeletado = deletarMedicamentoUseCase.execute(id);

        deletarEstoqueUseCase.execute(skuDeletado);

        return ResponseEntity.ok("Estoque com sku " + skuDeletado + " foi deletado com sucesso!");
    }



}