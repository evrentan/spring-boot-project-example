package evrentan.examples.springbootprojectexample.dto;

import evrentan.examples.springbootprojectexample.dto.shared.AbstractGenericDtoType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Customer Class.
 * Its SuperClass can be reached from {@link evrentan.examples.springbootprojectexample.dto.shared.AbstractGenericDtoType} class.
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Customer extends AbstractGenericDtoType {
  /**
   * Customer Type in String type.
   */
  @Schema(description = "Customer Type")
  private String customerType;

  /**
   * Customer Full Name in String type.
   */
  @Schema(description = "Customer Full Name")
  private String customerFullName;
}
