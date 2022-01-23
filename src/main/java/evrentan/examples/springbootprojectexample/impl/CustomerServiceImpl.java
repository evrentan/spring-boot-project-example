package evrentan.examples.springbootprojectexample.impl;

import evrentan.examples.springbootprojectexample.dto.Customer;
import evrentan.examples.springbootprojectexample.dto.CustomerRef;
import evrentan.examples.springbootprojectexample.entity.CustomerEntity;
import evrentan.examples.springbootprojectexample.mapper.CustomerMapper;
import evrentan.examples.springbootprojectexample.repository.CustomerRepository;
import evrentan.examples.springbootprojectexample.service.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Customer Service Implementation class implementing {@link evrentan.examples.springbootprojectexample.service.ICustomerService} interface class.
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

  private final CustomerMapper customerMapper;
  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
    this.customerMapper = customerMapper;
    this.customerRepository = customerRepository;
  }

  /**
   * create a customer instance in the database
   *
   * @param customer customer to be created. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   * @return Customer. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Customer createCustomer(Customer customer) {
    return this.save(customer);
  }

  /**
   * return all customer ref instances in the database
   *
   * @return List<CustomerRef>. Please, see the {@link evrentan.examples.springbootprojectexample.dto.CustomerRef} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @Override
  @Transactional(propagation =  Propagation.REQUIRED)
  public List<CustomerRef> getAllCustomerRefs() {
    return this.customerMapper.toDtoRefList(this.customerRepository.findAll());
  }

  /**
   * return a customer instance by using its id in the database
   *
   * @param id customer id to be filtered
   * @return Customer. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public Customer getCustomerById(String id) {
    Optional<CustomerEntity> customerEntity = this.customerRepository.findById(id);

    return customerEntity.map(this.customerMapper::toDto).orElse(null);
  }

  /**
   * update a customer instance in the database
   *
   * @param id customer id to be patched.
   * @param customer customer instance to be updated. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   * @return Customer. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @Override
  public ResponseEntity<Customer> updateCustomer(String id, Customer customer) {
    if (Objects.isNull(id) || Objects.isNull(customer.getId()) || !Objects.equals(id, customer.getId()))
      return  ResponseEntity.badRequest().build();

    Optional<CustomerEntity> checkCustomer = this.customerRepository.findById(id);
    if (checkCustomer.isEmpty())
      return ResponseEntity.notFound().build();;

    final Customer updatedCustomer = this.save(customer);

    if (Objects.nonNull(updatedCustomer))
      return ResponseEntity.ok(updatedCustomer);

    return ResponseEntity.internalServerError().build();
  }

  /**
   * delete a customer instance in the database
   *
   * @param id customer id to be deleted.
   * @return Customer. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @Override
  public ResponseEntity<Customer> deleteCustomer(String id) {
    if (Objects.isNull(id))
      return  ResponseEntity.badRequest().build();

    Optional<CustomerEntity> deletedCustomer = this.customerRepository.findById(id);
    if (deletedCustomer.isEmpty())
      return ResponseEntity.notFound().build();

    this.customerRepository.delete(deletedCustomer.get());

    return ResponseEntity.ok(this.customerMapper.toDto(deletedCustomer.get()));
  }

  /**
   *
   * @param customer object that is going to be saved in {@link evrentan.examples.springbootprojectexample.repository.CustomerRepository}. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   * @return Customer object. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  private Customer save(Customer customer) {
    CustomerEntity customerEntity = this.customerMapper.toEntity(customer);
    customerEntity = this.customerRepository.save(customerEntity);
    return this.customerMapper.toDto(customerEntity);
  }
}
