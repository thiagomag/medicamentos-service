package br.com.postechfiap.medicamentosservice.controllers;


import br.com.postechfiap.medicamentosservice.dto.medicamento.request.MedicamentoRequest;
import br.com.postechfiap.medicamentosservice.dto.medicamento.request.MedicamentoRequestParams;
import br.com.postechfiap.medicamentosservice.dto.medicamento.response.MedicamentoResponse;
import br.com.postechfiap.medicamentosservice.interfaces.usecases.BuscarMedicamentoUseCase;
import br.com.postechfiap.medicamentosservice.interfaces.usecases.medicamento.AtualizarMedicamentoUseCase;
import br.com.postechfiap.medicamentosservice.interfaces.usecases.medicamento.CadastrarMedicamentoUseCase;
import br.com.postechfiap.medicamentosservice.interfaces.usecases.medicamento.DeletarMedicamentoUseCase;
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
    public ResponseEntity<MedicamentoResponse> cadastrarNovoMedicamento(@RequestBody @Valid MedicamentoRequest dto) {

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
        String mensagem = deletarMedicamentoUseCase.execute(id);
        return ResponseEntity.ok(mensagem);
    }
}