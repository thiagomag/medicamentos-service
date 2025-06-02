package br.com.postechfiap.medicamentosservice.dto.medicamento.response;

public record MedicamentoResponse(
        Long id,
        String nome,
        String principioAtivo,
        String laboratorio,
        String dosagem,
        String descricao,
        Double preco,
        Integer quantidadeEmEstoque) {
}
