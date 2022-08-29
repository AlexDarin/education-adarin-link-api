package phoenixit.education.components;

import org.mapstruct.Mapper;
import phoenixit.education.models.ClassNode;
import phoenixit.education.models.ClassNodeDto;

@Mapper(componentModel = "spring")
public interface ClassNodeMapper {

    ClassNode toEntity(ClassNodeDto dto);

    ClassNodeDto toDto(ClassNode dto);
}
