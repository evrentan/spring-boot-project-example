package evrentan.examples.springbootprojectexample.dto.shared;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractGenericDtoType implements Serializable {
  @Schema(description = "ID of the Instance")
  private String id;
}
