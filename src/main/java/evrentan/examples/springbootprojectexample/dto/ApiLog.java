package evrentan.examples.springbootprojectexample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ApiLog Class.
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@Builder
public class ApiLog {
  /**
   * Collection Id for ApiLog Class in String type.
   */
  @Schema(name = "Collection Id")
  private String id;

  /**
   * Source IP Address in String Type.
   */
  @Schema(name = "Source IP Address")
  private String sourceIpAddress;

  /**
   * HTTP Request Method in String type. This field cannot be null.
   * Allowed values are POST, PUT, PATCH, GET and DELETE.
   */
  @Schema(name = "HTTP Request Method", allowableValues = "POST, PUT, PATCH, GET, DELETE")
  @NotNull
  private String httpRequestMethod;

  /**
   * API End-Point value in String type. This field cannot be null.
   */
  @Schema(name = "API EndPoint")
  @NotNull
  private String endPoint;

  /**
   * API Request Payload in String type.
   */
  @Schema(name = "API Request Payload")
  private String requestPayload;

  /**
   * Response Payload in String type.
   */
  @Schema(name = "API Response Payload")
  private String responsePayload;

  /**
   * API HTTP Status Code in Integer type. Values can be checked from <a href="https://httpstatuses.com">HTTP Statuses Web Site</a>.
   */
  @Schema(name = "API HTTP Status Code; https://httpstatuses.com")
  private Integer httpStatusCode;

  /**
   * Total Duration of API call in MilliSeconds in Long type.
   */
  @Schema(name = "Total Duration of API Call in MilliSeconds")
  private Long totalDuration;

  /**
   * API Exception Message if there is any in String type.
   */
  @Schema(name = "API Exception Message")
  private String exceptionMessage;

  /**
   * API Call Date in Date type.
   */
  @Schema(name = "API Call Date")
  private Date callDate;
}
