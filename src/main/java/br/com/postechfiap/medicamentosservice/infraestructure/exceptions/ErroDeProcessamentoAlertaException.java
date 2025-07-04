package br.com.postechfiap.medicamentosservice.infraestructure.exceptions;

public class ErroDeProcessamentoAlertaException extends RuntimeException {

    public ErroDeProcessamentoAlertaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public ErroDeProcessamentoAlertaException(String mensagem) {
        super(mensagem);
    }
}