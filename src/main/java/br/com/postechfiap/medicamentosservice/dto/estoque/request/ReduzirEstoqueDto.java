package br.com.postechfiap.medicamentosservice.dto.estoque.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ReduzirEstoqueDto(
        @NotNull
        String sku,
        @Valid @NotNull ReduzirEstoqueRequest reduzirEstoqueRequest
) {
}

