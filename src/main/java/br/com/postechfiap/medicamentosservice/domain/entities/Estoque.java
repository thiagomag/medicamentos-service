package br.com.postechfiap.medicamentosservice.domain.entities;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Estoque  {

    private Long id;
    private String nome;
    private String sku;
    private int quantidade;

}