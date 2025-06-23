package br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EstoqueRequest(
        @Schema(description = "Nome comercial do medicamento associado a este estoque.", example = "Neosaldina")
        @NotBlank(message = "O nome não pode estar em branco.")
        String nome,

        @Schema(description = "SKU (Stock Keeping Unit) único do produto de estoque.", example = "DIPIR-300MG35MG50MG-NEOSA-HYPE")
        @NotBlank(message = "O SKU não pode estar em branco.")
        String sku,

        @Schema(description = "Quantidade de itens em estoque. Deve ser um valor positivo.", example = "150")
        @Min(value = 0, message = "A quantidade em estoque não pode ser negativa.") // Geralmente estoque inicial pode ser 0
        int estoque
) {
}