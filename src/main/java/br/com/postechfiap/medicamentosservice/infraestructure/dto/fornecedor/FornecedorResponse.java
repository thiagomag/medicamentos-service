package br.com.postechfiap.medicamentosservice.infraestructure.dto.fornecedor;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FornecedorResponse {
    private Long id;
    private String nome;
    private String cnpj;
}
