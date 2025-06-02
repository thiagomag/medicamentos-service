package br.com.postechfiap.medicamentosservice.dto.medicamento.request;

public record MedicamentoRequest(
        String nome,
        String principioAtivo,
        String laboratorio,
        String dosagem,
        String descricao,
        Double preco,
        Integer quantidadeEmEstoque
) {
}
