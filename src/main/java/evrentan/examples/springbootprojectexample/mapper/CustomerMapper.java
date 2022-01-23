package evrentan.examples.springbootprojectexample.mapper;

import evrentan.examples.springbootprojectexample.dto.Customer;
import evrentan.examples.springbootprojectexample.dto.CustomerRef;
import evrentan.examples.springbootprojectexample.entity.CustomerEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

/**
 * Customer Mapper Interface class by using <a href="https://mapstruct.org">MapStruct</a>.
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface CustomerMapper {

  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  /**
   * Mapper method in order to map from {@link evrentan.examples.springbootprojectexample.dto.Customer} to {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity}.
   *
   * @param customer object that is going to be mapped. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   * @return Returns CustomerEntity. Please, see the {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @Mappings({
      @Mapping(target = "id", ignore = true)
  })
  CustomerEntity toEntity(Customer customer);

  /**
   * Mapper method in order to map from {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity} to {@link evrentan.examples.springbootprojectexample.dto.Customer}.
   *
   * @param customerEntity object that is going to be mapped. Please, see the {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity} class for details.
   * @return Returns Customer. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  Customer toDto(CustomerEntity customerEntity);

  /**
   * Mapper method in order to map from list of {@link evrentan.examples.springbootprojectexample.dto.Customer} to list of {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity}.
   *
   * @param customerList list that is going to be mapped. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   * @return Returns List of CustomerEntity. Please, see the {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  List<CustomerEntity> toEntityList(List<Customer> customerList);

  /**
   * Mapper method in order to map from list of {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity} to list of {@link evrentan.examples.springbootprojectexample.dto.Customer}.
   *
   * @param customerEntityList list that is going to be mapped. Please, see the {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity} class for details.
   * @return Returns List of Customer. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  List<Customer> toDtoList(List<CustomerEntity> customerEntityList);

  /**
   * Mapper method in order to map from {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity} to {@link evrentan.examples.springbootprojectexample.dto.CustomerRef}.
   * This method uses a bean mapping in order to ignore specified fields mappings. For this case, customerType & fullName fields in CustomerEntity are ignored in the mapping implementation.
   *
   * @param customerEntity object that is going to be mapped. Please, see the {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity} class for details.
   * @return Returns CustomerRef. Please, see the {@link evrentan.examples.springbootprojectexample.dto.CustomerRef} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @BeanMapping(ignoreUnmappedSourceProperties = {"customerType", "fullName"})
  CustomerRef toDtoRef(CustomerEntity customerEntity);

  /**
   * Mapper method in order to map from list of {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity} to list of {@link evrentan.examples.springbootprojectexample.dto.CustomerRef}.
   *
   * @param customerEntityList list that is going to be mapped. Please, see the {@link evrentan.examples.springbootprojectexample.entity.CustomerEntity} class for details.
   * @return Returns List of Customerref. Please, see the {@link evrentan.examples.springbootprojectexample.dto.CustomerRef} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  List<CustomerRef> toDtoRefList(List<CustomerEntity> customerEntityList);

  /**
   * Method to be used for setting the CustomerEntity from Customer object if it exists.
   * This method calls after mapping all fields within {@link evrentan.examples.springbootprojectexample.mapper.CustomerMapper#toEntity(Customer)}.
   *
   * @param customer object
   * @param customerEntity is used for mapping target.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @AfterMapping
  default void setEntityId(Customer customer, @MappingTarget CustomerEntity customerEntity) {
    if(Objects.nonNull(customer.getId()))
      customerEntity.setId(customer.getId());
  }
}
