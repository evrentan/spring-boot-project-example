package evrentan.examples.springbootprojectexample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ApiLog {
  @Schema(name = "Collection Id")
  private String id;

  @Schema(name = "Source IP Address")
  private String sourceIpAddress;

  @Schema(name = "HTTP Request Method", allowableValues = "POST, PUT, PATCH, GET, DELETE")
  @NotNull
  private String httpRequestMethod;

  @Schema(name = "API EndPoint")
  @NotNull
  private String endPoint;

  @Schema(name = "API Request Payload")
  private String requestPayload;

  @Schema(name = "API Response Payload")
  private String responsePayload;

  @Schema(name = "API HTTP Status Code; https://httpstatuses.com")
  private Integer httpStatusCode;

  @Schema(name = "Total Duration of the API Call in MilliSeconds")
  private Long totalDuration;

  @Schema(name = "API Exception Message")
  private String exceptionMessage;

  @Schema(name = "API Call Date")
  private Date callDate;
}
