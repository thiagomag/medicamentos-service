package br.com.postechfiap.medicamentosservice.infraestructure.adapter;


import br.com.postechfiap.medicamentosservice.application.interfaces.Adapter;
import br.com.postechfiap.medicamentosservice.infraestructure.utils.JsonUtils;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Optional;

public abstract class AbstractAdapter<Source, Destination> implements Adapter<Source, Destination> {

    private final Class<Destination> destinationClass;
    protected final JsonUtils jsonUtils;
    protected ModelMapper modelMapper;

    public AbstractAdapter(Class<Destination> destinationClass,
                           JsonUtils jsonUtils) {
        this.destinationClass = destinationClass;
        this.jsonUtils = jsonUtils;
    }

    protected ModelMapper getModelMapper() {
            this.modelMapper = new ModelMapper();
            this.modelMapper.getConfiguration()
                    .setPropertyCondition(Conditions.isNotNull())
                    .setSkipNullEnabled(true)
                    .setAmbiguityIgnored(true)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
        return this.modelMapper;
    }

    @Override
    public Destination adapt(Source source) {
        return Optional.ofNullable(source)
                .map(sourceObject -> this.getModelMapper().map(sourceObject, destinationClass))
                .orElse(null);
    }

    @Override
    public Destination adapt(Source source, Destination destination) {
        return Optional.ofNullable(source)
                .map(sourceObject -> {
                    this.getModelMapper().map(sourceObject, destination);
                    return destination;
                })
                .orElse(null);
    }

}
