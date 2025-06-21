package br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.request;

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
