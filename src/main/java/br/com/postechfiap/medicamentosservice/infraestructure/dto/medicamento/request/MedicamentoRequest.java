package br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record MedicamentoRequest(
        @Schema(description = "Nome comercial do medicamento.", example = "Neosaldina")
        String nome,

        @Schema(description = "Princípio(s) ativo(s) do medicamento.", example = "Dipirona Monoidratada, Maleato de Orfenadrina, Cafeína Anidra")
        String principioAtivo,

        @Schema(description = "Nome do laboratório fabricante do medicamento.", example = "Hypera Pharma")
        String laboratorio,

        @Schema(description = "Dosagem do medicamento (ex: '300mg', '10mg/ml').", example = "300mg + 35mg + 50mg")
        String dosagem,

        @Schema(description = "Descrição detalhada do medicamento, incluindo sua finalidade e modo de uso.", example = "Analgésico e relaxante muscular, indicado para o tratamento de diversos tipos de dores de cabeça e outras dores musculares leves a moderadas.")
        String descricao,

        @Schema(description = "Preço de venda do medicamento.", example = "15.99", format = "double")
        Double preco,

        @Schema(description = "Quantidade inicial do medicamento a ser adicionada ao estoque.", example = "500")
        int quantidadeEmEstoque
) {
}