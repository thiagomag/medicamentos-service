package br.com.postechfiap.medicamentosservice.infraestructure.controllers;

import br.com.postechfiap.medicamentosservice.application.interfaces.usecases.estoque.*;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request.*;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.EstoqueResponse;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response.ListaEstoqueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/estoque")
@RequiredArgsConstructor
@Validated
@Tag(name = "Estoque",description = "Api de gerenciamento de estoque")
public class EstoqueController {


    private final BuscarEstoquePorSkuUseCase buscarEstoquePorSku;
    private final BuscarEstoquePorNomeUseCase buscarEstoquePorNome;
    private final ReduzirEstoqueUseCase reduzirEstoque;
    private final AdicionarEstoqueUseCase adicionarEstoque;
    private final AtualizarReposicaoPendenteUseCase atualizarReposicaoPendente;


    @GetMapping("/BuscaSku")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_MEDIC', 'ROLE_NURSE')")
    @Operation(summary = "Busca Estoque por SKU", description = "Busca Estoque por Nome")
    public ResponseEntity<ListaEstoqueResponse> buscarEstoque(@RequestParam String sku) {
        if(sku == null || sku.trim().isEmpty()){
            throw new IllegalArgumentException("O parametro de busca não pode ser vazio");
        }

        var listaEstoque = buscarEstoquePorSku.execute(sku);

        return  ResponseEntity.ok(listaEstoque);
    }

    @GetMapping("/BuscaNome")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_MEDIC', 'ROLE_NURSE')")
    @Operation(summary = "Busca Estoque por Nome", description = "Busca Estoque por Nome")
    public ResponseEntity<ListaEstoqueResponse> buscarEstoquePorNome(@RequestParam String nome) {
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O parametro de busca não pode ser vazio");
        }

        var listaEstoque = buscarEstoquePorNome.execute(nome);

        return  ResponseEntity.ok(listaEstoque);
    }

    @PutMapping("/reduzir")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_MEDIC')")
    @Operation(summary = "Reduzir o Estoque", description = "Reduzir o estoque com a sku")
    public  ResponseEntity<EstoqueResponse> reduzirEstoque(@RequestBody AlterarEstoqueRequest request) {

        var novoEstoque = reduzirEstoque.execute(request);
        return  ResponseEntity.ok(novoEstoque);
    }

    @PutMapping("/adicionar")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @Operation(summary = "Adicionar o Estoque", description = "Adicionar o esto com a sku")
    public  ResponseEntity<EstoqueResponse> adicionarEstoque(@Valid @RequestBody AlterarEstoqueRequest request) {

        var novoEstoque = adicionarEstoque.execute(request);
        return  ResponseEntity.ok(novoEstoque);
    }

    @PutMapping("/reposicao-pendente/{sku}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @Operation(summary = "Atualizar Reposição Pendente", description = "Atualiza o status de reposição pendente do estoque")
    public ResponseEntity<Void> atualizarReposicaoPendente(@PathVariable String sku) {
        return ResponseEntity.ok(atualizarReposicaoPendente.execute(sku));
    }
}
