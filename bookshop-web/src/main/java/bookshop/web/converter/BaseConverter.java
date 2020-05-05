package bookshop.web.converter;

import bookshop.core.model.BaseEntity;
import bookshop.web.dto.BaseDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model extends BaseEntity<?>, Dto extends BaseDto<?>>
        implements Converter<Model, Dto>{
    public Set<?> convertModelsToIds(Set<Model> models){
        return models.stream()
                .map(model -> model.getId())
                .collect(Collectors.toSet());
    }

    public Set<?> convertDTOsToIDs(Set<Dto> dtos){
        return dtos.stream()
                .map(BaseDto::getId)
                .collect(Collectors.toSet());
    }

    public Set<?> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toSet());
    }
}
