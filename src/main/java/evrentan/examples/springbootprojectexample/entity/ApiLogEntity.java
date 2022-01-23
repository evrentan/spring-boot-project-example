package evrentan.examples.springbootprojectexample.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ApiLong Entity Class in order for the related collection in the MongoDB
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@Data
@Document(collection = "apiLog")
public class ApiLogEntity {
  /**
   * Collection Id for ApiLog Class in String type.
   */
  @Id
  private String id;

  /**
   * Source IP Address in String Type.
   */
  private String sourceIpAddress;

  /**
   * HTTP Request Method in String type. This field cannot be null.
   * Allowed values are POST, PUT, PATCH, GET and DELETE.
   */
  @NotNull
  private String httpRequestMethod;

  /**
   * API End-Point value in String type. This field cannot be null.
   */
  @NotNull private String endPoint;

  /**
   * API Request Payload in String type.
   */
  private String requestPayload;

  /**
   * Response Payload in String type.
   */
  private String responsePayload;

  /**
   * API HTTP Status Code in Integer type. Values can be checked from <a href="https://httpstatuses.com">HTTP Statuses Web Site</a>.
   */
  private Integer httpStatusCode;

  /**
   * Total Duration of API call in MilliSeconds in Long type.
   */
  private Long totalDuration;

  /**
   * API Exception Message if there is any in String type.
   */
  private String exceptionMessage;

  /**
   * API Call Date in Date type.
   */
  private Date callDate;
}
