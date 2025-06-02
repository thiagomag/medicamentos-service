package br.com.postechfiap.medicamentosservice.interfaces;

public interface Adapter<Source, Destination> {

    Destination adapt(Source source);

    Destination adapt(Source source, Destination destination);

}
