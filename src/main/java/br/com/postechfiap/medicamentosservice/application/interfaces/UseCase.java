package br.com.postechfiap.medicamentosservice.application.interfaces;

public interface UseCase<Input,Output> {
    Output execute(Input input);
}
