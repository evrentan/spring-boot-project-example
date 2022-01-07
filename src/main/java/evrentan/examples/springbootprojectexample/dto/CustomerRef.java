package evrentan.examples.springbootprojectexample.dto;

import evrentan.examples.springbootprojectexample.dto.shared.AbstractGenericDtoType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CustomerRef extends AbstractGenericDtoType {
}
