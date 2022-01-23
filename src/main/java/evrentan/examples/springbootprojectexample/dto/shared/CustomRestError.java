package evrentan.examples.springbootprojectexample.dto.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Custom Rest Error class in order to handle returned error objects
 *
 * @author <a href="https://github.com/ybqdren">Zhao Wen</a>, <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomRestError {
  /**
   * Status of the customized REST error in Integer type.
   */
  private Integer status;

  /**
   * Message of the customized REST error in String type.
   */
  private String message;
}
