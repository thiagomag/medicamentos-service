package br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para requisição de atualização de um registro de estoque existente.")
public record AtualizarEstoqueDto(
        @Schema(description = "SKU atual do medicamento a ser atualizado.", example = "DIPIR-300MG35MG50MG-NEOSA-HYPE")
        @NotNull(message = "O SKU antigo não pode ser nulo.")
        String skuAntigo,

        @Schema(description = "Novo nome comercial para o medicamento no estoque.", example = "Neosaldina Forte")
        @NotBlank(message = "O nome não pode estar em branco.")
        String nome,

        @Schema(description = "Novo SKU para o medicamento no estoque (será o novo identificador único).", example = "DIPIR-500MG-GENE-COMP-NOVO")
        @NotNull(message = "O SKU novo não pode ser nulo.")
        String skuNovo
) {
}