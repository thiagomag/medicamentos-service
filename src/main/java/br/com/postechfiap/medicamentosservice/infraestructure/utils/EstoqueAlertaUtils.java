package br.com.postechfiap.medicamentosservice.infraestructure.utils;

import br.com.postechfiap.medicamentosservice.application.gateways.MedicamentoGateway;
import br.com.postechfiap.medicamentosservice.infraestructure.dto.task.EstoqueAlertaDTO;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.EstoqueEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.MedicamentoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EstoqueAlertaUtils {

    private final MedicamentoGateway medicamentoGateway;

    public  List<EstoqueAlertaDTO> converterParaAlertaDTO(List<EstoqueEntity> estoqueEntities) {

        return estoqueEntities.stream()
                .map(entity -> {

                    Optional<MedicamentoEntity> medicamentoOptional = medicamentoGateway.findBySku(entity.getSku());

                    return EstoqueAlertaDTO.builder()
                            .nomeProduto(entity.getNome())
                            .sku(entity.getSku())
                            .laboratorio(medicamentoOptional.map(MedicamentoEntity::getLaboratorio).orElse(null))
                            .laboratorioId(medicamentoOptional.map(MedicamentoEntity::getFornecedorId).orElse(null))
                            .quantidade(entity.getQuantidade())
                            .dataAnalise(LocalDateTime.now())
                            .build();
                })
                .collect(Collectors.toList());
    }


}