package br.com.postechfiap.medicamentosservice.infraestructure.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record ResponseError(
        LocalDateTime timestamp,
        int status,
        String httpError,
        List<String> message
) {
    public ResponseError(int status, String error,  List<String> messages) {
        this(LocalDateTime.now(), status, error,  messages != null ? messages : new ArrayList<>());
    }

    public ResponseError(int status, String error,  String messages) {
        this(LocalDateTime.now(), status, error,List.of(messages));
    }
}

