package evrentan.examples.springbootprojectexample.service;

import evrentan.examples.springbootprojectexample.dto.Customer;
import evrentan.examples.springbootprojectexample.dto.CustomerRef;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICustomerService {

  /**
   * create a customer instance in the database
   *
   * @param customer customer to be created
   * @return Customer
   */
  Customer createCustomer(Customer customer);

  /**
   * return all customer ref instances in the database
   *
   * @return List<CustomerRef>
   */
  List<CustomerRef> getAllCustomerRefs();

  /**
   * return a customer instance by using its id in the database
   *
   * @param id customer id to be filtered
   * @return Customer
   */
  Customer getCustomerById(String id);

  /**
   * update a customer instance in the database
   *
   * @param id customer id to be patched
   * @param customer customer instance to be updated
   * @return Customer
   */
  ResponseEntity<Customer> updateCustomer(String id, Customer customer);

  /**
   * delete a customer instance in the database
   *
   * @param id customer id to be deleted
   * @return Customer
   */
  ResponseEntity<Customer> deleteCustomer(String id);
}
