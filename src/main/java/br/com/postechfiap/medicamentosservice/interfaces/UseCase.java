package br.com.postechfiap.medicamentosservice.interfaces;

public interface UseCase<Input,Output> {
    Output execute(Input input);
}
