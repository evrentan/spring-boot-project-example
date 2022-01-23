package evrentan.examples.springbootprojectexample.service;

import evrentan.examples.springbootprojectexample.dto.Customer;
import evrentan.examples.springbootprojectexample.dto.CustomerRef;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Interface Class for Customer Related service implementations
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
public interface ICustomerService {

  /**
   * create a customer instance in the database
   *
   * @param customer customer to be created. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   * @return Customer. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  Customer createCustomer(Customer customer);

  /**
   * return all customer ref instances in the database
   *
   * @return List<CustomerRef>. Please, see the {@link evrentan.examples.springbootprojectexample.dto.CustomerRef} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  List<CustomerRef> getAllCustomerRefs();

  /**
   * return a customer instance by using its id in the database
   *
   * @param id customer id to be filtered
   * @return Customer. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  Customer getCustomerById(String id);

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
  ResponseEntity<Customer> updateCustomer(String id, Customer customer);

  /**
   * delete a customer instance in the database
   *
   * @param id customer id to be deleted.
   * @return Customer. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  ResponseEntity<Customer> deleteCustomer(String id);
}
