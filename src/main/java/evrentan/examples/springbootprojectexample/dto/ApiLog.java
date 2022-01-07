package evrentan.examples.springbootprojectexample.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ApiLog {
  @ApiModelProperty(value = "Collection Id")
  private String id;

  @ApiModelProperty(value = "Source IP Address")
  private String sourceIpAddress;

  @ApiModelProperty(value = "HTTP Request Method", allowableValues = "POST, PUT, PATCH, GET, DELETE")
  @NotNull
  private String httpRequestMethod;

  @ApiModelProperty(value = "API EndPoint")
  @NotNull
  private String endPoint;

  @ApiModelProperty(value = "API Request Payload")
  private String requestPayload;

  @ApiModelProperty(value = "API Response Payload")
  private String responsePayload;

  @ApiModelProperty(value = "API HTTP Status Code; https://httpstatuses.com")
  private Integer httpStatusCode;

  @ApiModelProperty(value = "Total Duration of the API Call in MilliSeconds")
  private Long totalDuration;

  @ApiModelProperty(value = "API Exception Message")
  private String exceptionMessage;

  @ApiModelProperty(value = "API Call Date")
  private Date callDate;
}
