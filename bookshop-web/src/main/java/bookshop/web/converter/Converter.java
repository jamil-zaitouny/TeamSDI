package bookshop.web.converter;

import bookshop.core.model.BaseEntity;
import bookshop.web.dto.BaseDto;

public interface Converter<Model extends BaseEntity<?>, Dto extends BaseDto> {
    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);


}
