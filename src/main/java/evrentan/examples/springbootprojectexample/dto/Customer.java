package evrentan.examples.springbootprojectexample.dto;

import evrentan.examples.springbootprojectexample.dto.shared.AbstractGenericDtoType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Customer extends AbstractGenericDtoType {
  @Schema(description = "Customer Type")
  private String customerType;
  @Schema(description = "Customer Full Name")
  private String customerFullName;
}
