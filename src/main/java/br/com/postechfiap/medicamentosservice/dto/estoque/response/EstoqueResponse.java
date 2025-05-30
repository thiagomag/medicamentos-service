package br.com.postechfiap.medicamentosservice.dto.estoque.response;

public record EstoqueResponse(
        Long id,
        String nome,
        String sku,
        Long quantidade
) {
}
