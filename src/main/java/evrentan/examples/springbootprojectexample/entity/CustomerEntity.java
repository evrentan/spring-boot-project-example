package evrentan.examples.springbootprojectexample.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Customer Entity Class in order for the related collection in the MongoDB
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "customer")
public class CustomerEntity implements Serializable {
  /**
   * ID of an Instance in String type.
   */
  @Id
  private String id;

  /**
   * Customer Type in String type.
   */
  private String customerType;

  /**
   * Customer Full Name in String type.
   */
  private String customerFullName;
}
