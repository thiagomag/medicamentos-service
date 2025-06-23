package br.com.postechfiap.medicamentosservice.application.interfaces;

public interface Adapter<Source, Destination> {

    Destination adapt(Source source);

    Destination adapt(Source source, Destination destination);

}
