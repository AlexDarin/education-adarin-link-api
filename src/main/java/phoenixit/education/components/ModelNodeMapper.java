package phoenixit.education.components;

import org.mapstruct.Mapper;
import phoenixit.education.models.ModelNode;
import phoenixit.education.models.ModelNodeDto;

@Mapper(componentModel = "spring")
public interface ModelNodeMapper {

    ModelNode toEntity(ModelNodeDto dto);

    ModelNodeDto toDto(ModelNode dto);
}
