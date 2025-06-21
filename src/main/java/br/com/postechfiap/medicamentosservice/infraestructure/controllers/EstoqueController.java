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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/estoque")
@RequiredArgsConstructor
@Validated
@Tag(name = "Estoque",description = "Api de gerenciamento de estoque")
public class EstoqueController {

    private final CadastrarEstoqueUseCase cadastrarEstoque;
    private final BuscarEstoqueUseCase buscarEstoque;
    private final AtualizarEstoqueUseCase atualizarEstoque;
    private final ReduzirEstoqueUseCase reduzirEstoque;
    private final AdicionarEstoqueUseCase adicionarEstoque;
    private final DeletarEstoqueUseCase deletarEstoque;


    @PostMapping
    @Operation(summary = "Cadastra estoque", description = "Cadastra novo estoque")
    public ResponseEntity<EstoqueResponse> create(@Valid @RequestBody EstoqueRequest estoqueRequest) {
        var novoEstoque = cadastrarEstoque.execute(estoqueRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoEstoque.id())
                .toUri();
        return ResponseEntity.created(location).body(novoEstoque);
    }

    @GetMapping
    @Operation(summary = "Busca Estoque", description = "Busca Estoque por Nome")
    public ResponseEntity<ListaEstoqueResponse> buscarEstoque(@RequestParam String nome) {
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O parametro de busca não pode ser vazio");
        }

        var listaEstoque = buscarEstoque.execute(nome);

        return  ResponseEntity.ok(listaEstoque);
    }

    @PutMapping("/{sku}")
    @Operation(summary = "Atualiza o Estoque", description = "Atualiza o esto com a sku")
    public ResponseEntity<EstoqueResponse> atualizarEstoque(@PathVariable String sku,
                                                            @Valid @RequestBody EstoqueRequest estoqueRequest
    ) {
        var novoEstoque = atualizarEstoque.execute(new AtualizarEstoqueDto(sku, estoqueRequest));
        return  ResponseEntity.ok(novoEstoque);
    }

    @PutMapping("/reduzir/{sku}")
    @Operation(summary = "Reduzir o Estoque", description = "Reduzir o esto com a sku")
    public  ResponseEntity<EstoqueResponse> reduzirEstoque(@PathVariable String sku,
                                                           @Valid @RequestBody ReduzirEstoqueRequest reduzirEstoqueRequest) {
        var novoEstoque = reduzirEstoque.execute(new ReduzirEstoqueDto(sku,reduzirEstoqueRequest));
        return  ResponseEntity.ok(novoEstoque);
    }

    @PutMapping("/adicionar/{sku}")
    @Operation(summary = "Adicionar o Estoque", description = "Adicionar o esto com a sku")
    public  ResponseEntity<EstoqueResponse> reduzirEstoque(@PathVariable String sku,
                                                           @Valid @RequestBody AdicionarEstoqueRequest adicionarEstoqueRequest) {
        var novoEstoque = adicionarEstoque.execute(new AdicionarEstoqueDto(sku,adicionarEstoqueRequest));
        return  ResponseEntity.ok(novoEstoque);
    }

    @DeleteMapping("/{sku}")
    @Operation(summary = "Deleta um Estoque", description = "Deleta um item do estoque")
    public ResponseEntity<String> delete(@PathVariable String sku) {
        String mensagem =deletarEstoque.execute(sku);
        return ResponseEntity.ok(mensagem);
    }
}
