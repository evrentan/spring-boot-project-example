package evrentan.examples.springbootprojectexample.controller;

import evrentan.examples.springbootprojectexample.annotation.ApiLogger;
import evrentan.examples.springbootprojectexample.dto.Customer;
import evrentan.examples.springbootprojectexample.dto.CustomerRef;
import evrentan.examples.springbootprojectexample.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * REST Controller for provided customer related API end-points.
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Customer Related APIs")
public class CustomerController {

  private final ICustomerService customerService;

  public CustomerController(ICustomerService customerService) {
    this.customerService = customerService;
  }

  /**
   * REST end-point in order to create a customer.
   * Details related to API specs can be found in the API Documentation which can be reached as described in README file.
   *
   * @param customer object that is going to be created. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   * @return Customer Object within ResponseEntity.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @PostMapping
  @Operation(summary = "Create a Customer")
  @ApiResponses(value = {
      @ApiResponse(responseCode  = "200", description  = "Successfully Customer Created"),
      @ApiResponse(responseCode  = "404", description  = "Not Found"),
      @ApiResponse(responseCode  = "500", description  = "Internal Server Error")
  })
  @ApiLogger
  ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    return ResponseEntity.ok(this.customerService.createCustomer(customer));
  }

  /**
   * REST end-point in order to get all customer reference objects.
   * Details related to API specs can be found in the API Documentation which can be reached as described in README file.
   *
   * @return List of CustomerRef Object within ResponseEntity. Please, see the {@link evrentan.examples.springbootprojectexample.dto.CustomerRef} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @GetMapping
  @Operation(summary = "Get All Customer Instances Reference IDs")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully Get All Customer Reference IDs"),
      @ApiResponse(responseCode = "404", description = "No Customer Reference IDs Found"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  ResponseEntity<List<CustomerRef>> getAllCustomerRefs() {
    final List<CustomerRef> customerRefList = this.customerService.getAllCustomerRefs();

    if (CollectionUtils.isNotEmpty(customerRefList))
      return ResponseEntity.ok(customerRefList);

    return ResponseEntity.notFound().build();
  }

  /**
   * REST end-point in order to retrieve a specific customer object by customer ID.
   * Details related to API specs can be found in the API Documentation which can be reached as described in README file.
   *
   * @param id is the customer id that is going to be retrieved.
   * @return Customer Object within ResponseEntity. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @GetMapping(value = "/{id}")
  @Operation(summary = "Get a Specific Customer Instance by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully Get Customer By ID"),
      @ApiResponse(responseCode = "404", description = "No Customer Found With ID"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  ResponseEntity<Customer> getCustomerById(@NotNull @PathVariable final String id) {
    final Customer customer = this.customerService.getCustomerById(id);

    if (Objects.nonNull(customer))
      return ResponseEntity.ok(customer);

    return ResponseEntity.notFound().build();
  }

  /**
   * REST end-point in order to patch a specific customer object by customer ID.
   * Details related to API specs can be found in the API Documentation which can be reached as described in README file.
   *
   * @param id is the customer id that is going to be patched.
   * @param customer is the new object that is going to be patched within the existing one. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   * @return Customer Object within ResponseEntity. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @PatchMapping(value = "/{id}")
  @Operation(summary = "Patch a Specific Customer Instance by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully Patch the Customer By ID"),
      @ApiResponse(responseCode = "400", description = "Bad Request for Patching the Customer"),
      @ApiResponse(responseCode = "404", description = "No Customer Found With ID"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  ResponseEntity<Customer> patchCustomer(@Validated @NotNull @PathVariable final String id, @RequestBody Customer customer) {
    return this.customerService.updateCustomer(id, customer);
  }

  /**
   * REST end-point in order to put a specific customer object by customer ID.
   * Details related to API specs can be found in the API Documentation which can be reached as described in README file.
   *
   * @param id is the customer id that is going to be put.
   * @param customer is the new object that is going to be put instead of the existing one. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   * @return Customer Object within ResponseEntity. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @PutMapping(value = "/{id}")
  @Operation(summary = "Put a Specific Customer Instance by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully Put the Customer By ID"),
      @ApiResponse(responseCode = "400", description = "Bad Request for Putting the Customer"),
      @ApiResponse(responseCode = "404", description = "No Customer Found With ID"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  ResponseEntity<Customer> putCustomer(@Validated @NotNull @PathVariable final String id, @RequestBody Customer customer) {
    return this.customerService.updateCustomer(id, customer);
  }

  /**
   * REST end-point in order to delete a specific customer object by customer ID.
   * Details related to API specs can be found in the API Documentation which can be reached as described in README file.
   *
   * @param id is the customer id that is going to be deleted.
   * @return Customer Object within ResponseEntity. Please, see the {@link evrentan.examples.springbootprojectexample.dto.Customer} class for details.
   *
   * @author <a href="https://github.com/evrentan">Evren Tan</a>
   * @since 1.0.0
   */
  @DeleteMapping(value = "/{id}")
  @Operation(summary = "Delete a Specific Customer Instance by ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully Put the Customer By ID"),
      @ApiResponse(responseCode = "400", description = "Bad Request for Putting the Customer"),
      @ApiResponse(responseCode = "404", description = "No Customer Found With ID"),
      @ApiResponse(responseCode = "500", description = "Internal Server Error")
  })
  ResponseEntity<Customer> deleteCustomer(@PathVariable final String id) {
    return this.customerService.deleteCustomer(id);
  }
}
