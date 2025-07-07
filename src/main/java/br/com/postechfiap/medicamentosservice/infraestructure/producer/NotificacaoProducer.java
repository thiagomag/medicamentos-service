package br.com.postechfiap.medicamentosservice.infraestructure.producer;

import br.com.postechfiap.medicamentosservice.infraestructure.dto.task.EstoqueAlertaDTO;
import br.com.postechfiap.medicamentosservice.infraestructure.exceptions.ErroDeProcessamentoAlertaException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificacaoProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Value("${kafka.topic.notificacao}")
    private String topico;

    public NotificacaoProducer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void enviarNotificacao(EstoqueAlertaDTO estoqueAlertaDTO) {
        try {
            estoqueAlertaDTO.setTipoNotificacao("MEDICAMENTO_FIM");
            String pedidoJson = objectMapper.writeValueAsString(estoqueAlertaDTO);
            log.info("➡️ Enviando JSON para o Kafka: " + pedidoJson);

            kafkaTemplate.send(topico, estoqueAlertaDTO.getSku(), pedidoJson)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.info("alpha ao enviar notificacao para o Kafka: " + ex.getMessage());
                            throw new ErroDeProcessamentoAlertaException("Erro ao enviar notificacao para o Kafka", ex);
                        }

                        log.info("✅ Notificacao enviado com sucesso para o Kafka. Offset: "
                                + result.getRecordMetadata().offset());
                    });
        } catch (Exception e) {
            log.error("Erro ao serializar a Notificação para JSON: " + e.getMessage());
            throw new ErroDeProcessamentoAlertaException("Erro ao serializar a Notificacao", e);
        }
    }
}