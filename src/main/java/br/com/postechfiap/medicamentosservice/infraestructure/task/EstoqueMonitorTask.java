package br.com.postechfiap.medicamentosservice.infraestructure.task;

import br.com.postechfiap.medicamentosservice.application.gateways.EstoqueGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.task.EstoqueAlertaDTO;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.EstoqueEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.producer.NotificacaoProducer;
import br.com.postechfiap.medicamentosservice.infraestructure.utils.EstoqueAlertaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EstoqueMonitorTask {

    private final EstoqueGateway estoqueGateway;
    private final EstoqueAlertaUtils alertaUtils;
    private final NotificacaoProducer notificacaoProducer;

    @Scheduled(cron = "0 */5 * * * *")
    public List<EstoqueAlertaDTO> verificarEstoqueBaixo() {

        System.out.println("--- Executando verificação de estoque baixo ---");

       int limiteInferior = 5;
        List<EstoqueAlertaDTO> listaDeAlertas = Collections.emptyList();

       List<EstoqueEntity> produtosBaixoEstoque = estoqueGateway.findByQuantidadeLessThan(limiteInferior);

        if (produtosBaixoEstoque.isEmpty()) {
            System.out.println("Nenhum produto encontrado com estoque menor que " + limiteInferior);
        } else {
            listaDeAlertas =  alertaUtils.converterParaAlertaDTO(produtosBaixoEstoque);
            listaDeAlertas.forEach(notificacaoProducer::enviarNotificacao);
        }

        System.out.println("Lista :" + listaDeAlertas);
        System.out.println("--- Fim da verificação de estoque ---");

        return listaDeAlertas;
    }
}



