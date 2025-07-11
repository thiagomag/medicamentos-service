package br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response;

public record EstoqueResponse(
        Long id,
        String nome,
        String sku,
        Integer quantidade,
        Boolean reposicaoPendente
) {
}
