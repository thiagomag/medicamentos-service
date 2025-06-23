package br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MedicamentoRequestParams {

    @Schema(description = "Nome comercial do medicamento para busca.", example = "Dipirona")
    private String nomeMedicamento;

    @Schema(description = "SKU (Stock Keeping Unit) do medicamento para busca.", example = "DIPIR-300MG35MG50MG-NEOSA-HYPE")
    private String sku;

    @Schema(description = "Princípio ativo do medicamento para busca.", example = "Dipirona Monoidratada")
    private String principioAtivo;

    @Schema(description = "Laboratório fabricante do medicamento para busca.", example = "Eurofarma")
    private String laboratorio;
}

