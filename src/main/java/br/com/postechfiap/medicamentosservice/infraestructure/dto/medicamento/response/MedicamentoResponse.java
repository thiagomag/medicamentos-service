package br.com.postechfiap.medicamentosservice.infraestructure.dto.medicamento.response;

import br.com.postechfiap.medicamentosservice.infraestructure.dto.fornecedor.FornecedorResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicamentoResponse {

    private  Long id;
    private  String sku;
    private  String nome;
    private  String principioAtivo;
    private Long fornecedorId;
    private  String laboratorio;
    private  String dosagem;
    private  String descricao;
    private  Double preco;
    private int estoque;
}
