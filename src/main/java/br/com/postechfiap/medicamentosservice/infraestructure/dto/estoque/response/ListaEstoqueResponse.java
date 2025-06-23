package br.com.postechfiap.medicamentosservice.infraestructure.dto.estoque.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ListaEstoqueResponse(List<EstoqueResponse> estoques) {
}
