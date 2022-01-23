package evrentan.examples.springbootprojectexample.dto.shared;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Abstract Generic Class
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractGenericDtoType implements Serializable {
  /**
   * ID of an Instance in String type.
   */
  @Schema(description = "ID of an Instance")
  private String id;
}
