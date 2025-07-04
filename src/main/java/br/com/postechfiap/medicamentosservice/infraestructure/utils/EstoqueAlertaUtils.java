package br.com.postechfiap.medicamentosservice.infraestructure.utils;

import br.com.postechfiap.medicamentosservice.infraestructure.dto.task.EstoqueAlertaDTO;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.EstoqueEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.entities.MedicamentoEntity;
import br.com.postechfiap.medicamentosservice.infraestructure.persistance.repository.MedicamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EstoqueAlertaUtils {

    private final MedicamentoRepository repository;

    public  List<EstoqueAlertaDTO> converterParaAlertaDTO(List<EstoqueEntity> estoqueEntities) {

        return estoqueEntities.stream()
                .map(entity -> {
                    String laboratorio = "Laboratório não informado"; // Valor padrão seguro


                    Optional<MedicamentoEntity> medicamentoOptional = repository.findBySku(entity.getSku());


                    if (medicamentoOptional.isPresent()) {
                        laboratorio = medicamentoOptional.get().getLaboratorio();
                    }

                    // Constrói e retorna o DTO
                    return EstoqueAlertaDTO.builder()
                            .nomeProduto(entity.getNome())
                            .sku(entity.getSku())
                            .laboratorio(laboratorio)
                            .quantidade(entity.getQuantidade())
                            .dataAnalise(LocalDateTime.now())
                            .build();
                })
                .collect(Collectors.toList());
    }


}