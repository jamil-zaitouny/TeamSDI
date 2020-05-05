package web.converter;

import core.model.BaseEntity;
import web.dto.BaseDto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<ID extends Serializable,Model extends BaseEntity<ID>,Dto extends BaseDto<ID>>
        implements Converter<ID,Model,Dto>
{
    public List<ID> convertModelsToDIDs(List<Model> models)
    {
        return models.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

    public List<ID> convertDTOsToIDs(List<Dto> dtos)
    {
        return dtos.stream().map(BaseDto::getId).collect(Collectors.toList());
    }

    public List<Dto> convertModelsToDtos(Collection<Model> models)
    {
        return  models.stream().map(this::convertModelToDto).collect(Collectors.toList());
    }

    public List<Model> convertDtosToModels(Collection<Dto> dtos)
    {
        return dtos.stream().map(this::convertDtoToModel).collect(Collectors.toList());
    }
}
