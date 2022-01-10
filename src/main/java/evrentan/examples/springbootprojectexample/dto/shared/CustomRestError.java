package evrentan.examples.springbootprojectexample.dto.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Custom Rest Error class in order to handle returned error objects
 *
 * @author zhao wen, evren tan
 * @since 1.0.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomRestError {
  private Integer status;
  private String msg;
}
