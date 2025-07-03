package br.com.postechfiap.medicamentosservice.infraestructure.task;

import br.com.postechfiap.medicamentosservice.infraestructure.dto.task.EstoqueAlertaDTO;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.EstoqueEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.EstoqueRepository;
import br.com.postechfiap.medicamentosservice.infraestructure.utils.EstoqueAlertaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EstoqueMonitorTask {

    private final EstoqueRepository estoqueRepository;
    private final EstoqueAlertaUtils alertaUtils;

    @Scheduled(fixedRate = 300000) // 300000 milissegundos = 5 minutos
    public List<EstoqueAlertaDTO> verificarEstoqueBaixo() {

        System.out.println("--- Executando verificação de estoque baixo ---");

       int limiteInferior = 5;
        List<EstoqueAlertaDTO> listaDeAlertas = Collections.emptyList();

       List<EstoqueEntity> produtosBaixoEstoque = estoqueRepository.findByQuantidadeLessThan(limiteInferior);

        if (produtosBaixoEstoque.isEmpty()) {
            System.out.println("Nenhum produto encontrado com estoque menor que " + limiteInferior);

        } else {

                 listaDeAlertas =  alertaUtils.
                        converterParaAlertaDTO(produtosBaixoEstoque);
        }

        System.out.println("Lista :" + listaDeAlertas);
        System.out.println("--- Fim da verificação de estoque ---");

        return listaDeAlertas;
    }
}



