package br.com.postechfiap.medicamentosservice.controllers;


import br.com.postechfiap.medicamentosservice.dto.medicamento.request.MedicamentoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/medicamento")
@RequiredArgsConstructor
@Validated
@Tag(name = "Medicamento", description = "API para gerenciar medicamentos")
public class MedicamentosController {

    private final CadastrarMedicamentoUseCase cadastrarMedicamentoUseCase;
    private final BuscarMedicamentoUseCase buscarMedicamentoUseCase;
    private final AtualizarMedicamentoUseCase atualizarMedicamentoUseCase;
    private final DeletarMedicamentoUseCase deletarMedicamentoUseCase;


    @PostMapping
    @Operation(summary = "Cadastrar Medicamento", description = "Cadastra novos medicamentos.")
    public ResponseEntity<CriacaoMedicamentoResponse> cadastrarNovoMedicamento(@RequestBody @Valid MedicamentoRequest dto) {

        var novoProduto = cadastrarMedicamentoUseCase.execute(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoProduto.id())
                .toUri();

        return ResponseEntity.created(location).body(novoProduto);
    }

    @GetMapping
    @Operation(summary = "Buscar Medicamento", description = "Buscar medicamento por nome ou sku")
    public ResponseEntity<ListaMedicamentoResponse> buscarMedicamento(@RequestParam String query) {

        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("O parâmetro de busca não pode estar vazio.");
        }

        var listaMedicamento = buscarMedicamentoUseCase.execute(query);

        return ResponseEntity.ok(listaMedicamento);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Medicamento", description = "Atualizar um medicamento")
    public ResponseEntity<MedicamentoResponse> atualizarMedicamento(@PathVariable Long id,
                                                                    @Valid @RequestBody MedicamentoRequest dto) {

        var medicamento = atualizarMedicamentoUseCase.execute(new AtualizarProdutoDTO(id, dto));

        return ResponseEntity.ok(medicamento);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Medicamento", description = "Remove um medicamento pelo ID e retorna uma mensagem de confirmação.")
    public ResponseEntity<String> deletarMedicamento(@PathVariable Long id) {
        String mensagem = deletarMedicamentoUseCase.execute(id);
        return ResponseEntity.ok(mensagem);
    }
}