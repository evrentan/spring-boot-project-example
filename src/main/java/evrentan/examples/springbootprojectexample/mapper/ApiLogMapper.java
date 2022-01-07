package evrentan.examples.springbootprojectexample.mapper;

import evrentan.examples.springbootprojectexample.dto.ApiLog;
import evrentan.examples.springbootprojectexample.entity.ApiLogEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface ApiLogMapper {

  ApiLogMapper INSTANCE = Mappers.getMapper(ApiLogMapper.class);

  @Mappings({
      @Mapping(target = "id", ignore = true)
  })
  ApiLogEntity toEntity(ApiLog apiLog);

  ApiLog toDto(ApiLogEntity apiLogEntity);

  List<ApiLogEntity> toEntityList(List<ApiLog> apiLogList);

  List<ApiLog> toDtoList(List<ApiLogEntity> apiLogEntityList);

  @AfterMapping
  default void setEntityId(ApiLog apiLog, @MappingTarget ApiLogEntity apiLogEntity) {
    if(Objects.nonNull(apiLog.getId()))
      apiLogEntity.setId(apiLog.getId());
  }
}
