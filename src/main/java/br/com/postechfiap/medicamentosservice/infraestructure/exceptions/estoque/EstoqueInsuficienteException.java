package br.com.postechfiap.medicamentosservice.infraestructure.exceptions.estoque;

public class EstoqueInsuficienteException extends RuntimeException {

  public EstoqueInsuficienteException() {
    super("Não há quantidade suficiente em estoque para realizar esta operação.");
  }

  public EstoqueInsuficienteException(String message) {
    super(message);
  }
}
