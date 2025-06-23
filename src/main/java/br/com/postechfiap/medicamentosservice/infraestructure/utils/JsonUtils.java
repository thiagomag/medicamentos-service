package br.com.postechfiap.medicamentosservice.infraestructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonUtils {

    private static final String LOG_PREFIX = "[JSON_UTILS]";

    private final ObjectMapper objectMapper;

    public String writeValueAsStringOrNull(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error( LOG_PREFIX + " " + e.getMessage(), e );
        }
        return null;
    }

}
