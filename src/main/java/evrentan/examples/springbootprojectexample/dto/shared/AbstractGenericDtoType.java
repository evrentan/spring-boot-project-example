package evrentan.examples.springbootprojectexample.dto.shared;

import io.swagger.annotations.ApiModelProperty;
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
  @ApiModelProperty(notes = "ID of the Instance")
  private String id;
}
