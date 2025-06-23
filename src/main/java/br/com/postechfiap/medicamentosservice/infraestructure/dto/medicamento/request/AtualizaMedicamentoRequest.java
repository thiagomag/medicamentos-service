package br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record AtualizaMedicamentoRequest(
        @Schema(description = "Nome comercial do medicamento", example = "Neosaldina")
        String nome,

        @Schema(description = "Princípio(s) ativo(s) do medicamento", example = "Dipirona Monoidratada, Maleato de Orfenadrina, Cafeína Anidra")
        String principioAtivo,

        @Schema(description = "Laboratório fabricante do medicamento", example = "Hypera Pharma")
        String laboratorio,

        @Schema(description = "Dosagem do medicamento", example = "300mg + 35mg + 50mg")
        String dosagem,

        @Schema(description = "Descrição detalhada do medicamento e sua finalidade", example = "Analgésico e relaxante muscular, indicado para o tratamento de diversos tipos de dores de cabeça e outras dores musculares leves a moderadas.")
        String descricao,

        @Schema(description = "Preço de venda do medicamento", example = "15.99", format = "double")
        Double preco
) {
}