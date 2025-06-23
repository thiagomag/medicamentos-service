package br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AlterarEstoqueRequest(
        @Schema(description = "SKU (Stock Keeping Unit) do medicamento a ser alterado.", example = "DIPIR-300MG35MG50MG-NEOSA-HYPE")
        @NotBlank(message = "O SKU não pode estar em branco.") // Adicionei mensagem para NotBlank
        @NotNull(message = "O SKU não pode ser nulo.") // Adicionei mensagem para NotNull
        String sku,

        @Schema(description = "Quantidade a ser adicionada ou reduzida no estoque. Deve ser um valor positivo.", example = "50")
        @Min(value = 1, message = "A quantidade deve ser maior que zero.")
        int quantidade
) {
}
