package evrentan.examples.springbootprojectexample.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "apiLog")
public class ApiLogEntity {
  @Id
  private String id;
  private String sourceIpAddress;
  @NotNull
  private String httpRequestMethod;
  @NotNull private String endPoint;
  private String requestPayload;
  private String responsePayload;
  private Integer httpStatusCode;
  private Long totalDuration;
  private String exceptionMessage;
  private Date callDate;
}
