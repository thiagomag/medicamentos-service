package br.com.postechfiap.medicamentosservice.infraestructure.dto.task;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueAlertaDTO {
    private String nomeProduto;
    private String sku;
    private String laboratorio;
    private Integer quantidade;
    private LocalDateTime dataAnalise;
    private String tipoNotificacao;

}
