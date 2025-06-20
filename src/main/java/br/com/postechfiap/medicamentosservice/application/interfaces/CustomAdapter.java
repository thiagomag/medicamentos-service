package br.com.postechfiap.medicamentosservice.application.interfaces;

public interface CustomAdapter<Source, Destination> {

    Destination adapt(Source source);

    default Destination adapt(Source source, Object... args) {
        return adapt(source);
    }

}
