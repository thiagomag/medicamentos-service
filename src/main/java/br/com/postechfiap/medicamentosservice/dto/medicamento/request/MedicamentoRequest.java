package br.com.postechfiap.medicamentosservice.dto.medicamento.request;

public record MedicamentoRequest(
        String nome,
        String laboratorio,
        String dosagem,
        String descrição,
        Double preço,
        Integer quantidadeEmEstoque
) {
}
