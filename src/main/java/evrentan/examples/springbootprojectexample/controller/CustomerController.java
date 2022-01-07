package evrentan.examples.springbootprojectexample.controller;

import evrentan.examples.springbootprojectexample.annotation.ApiLogger;
import evrentan.examples.springbootprojectexample.dto.Customer;
import evrentan.examples.springbootprojectexample.dto.CustomerRef;
import evrentan.examples.springbootprojectexample.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Customer Related APIs")
@ResponseBody
public class CustomerController {

  private final ICustomerService customerService;

  public CustomerController(ICustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  @ApiOperation(value = "Create a Customer")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully Customer Created"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @ApiLogger
  ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    return ResponseEntity.ok(this.customerService.createCustomer(customer));
  }

  @GetMapping
  @ApiOperation(value = "Get All Customer Instances Reference IDs")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully Get All Customer Reference IDs"),
      @ApiResponse(code = 404, message = "No Customer Reference IDs Found"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  ResponseEntity<List<CustomerRef>> getAllCustomerRefs() {
    final List<CustomerRef> customerRefList = this.customerService.getAllCustomerRefs();

    if (CollectionUtils.isNotEmpty(customerRefList))
      return ResponseEntity.ok(customerRefList);

    return ResponseEntity.notFound().build();
  }

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Get a Specific Customer Instance by ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully Get Customer By ID"),
      @ApiResponse(code = 404, message = "No Customer Found With ID"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  ResponseEntity<Customer> getCustomerById(@PathVariable final String id) {
    final Customer customer = this.customerService.getCustomerById(id);

    if (Objects.nonNull(customer))
      return ResponseEntity.ok(customer);

    return ResponseEntity.notFound().build();
  }

  @PatchMapping(value = "/{id}")
  @ApiOperation(value = "Patch a Specific Customer Instance by ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully Patch the Customer By ID"),
      @ApiResponse(code = 400, message = "Bad Request for Patching the Customer"),
      @ApiResponse(code = 404, message = "No Customer Found With ID"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  ResponseEntity<Customer> patchCustomer(@PathVariable final String id, @RequestBody Customer customer) {
    return this.customerService.updateCustomer(id, customer);
  }

  @PutMapping(value = "/{id}")
  @ApiOperation(value = "Put a Specific Customer Instance by ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully Put the Customer By ID"),
      @ApiResponse(code = 400, message = "Bad Request for Putting the Customer"),
      @ApiResponse(code = 404, message = "No Customer Found With ID"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  ResponseEntity<Customer> putCustomer(@PathVariable final String id, @RequestBody Customer customer) {
    return this.customerService.updateCustomer(id, customer);
  }

  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Delete a Specific Customer Instance by ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully Put the Customer By ID"),
      @ApiResponse(code = 400, message = "Bad Request for Putting the Customer"),
      @ApiResponse(code = 404, message = "No Customer Found With ID"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  ResponseEntity<Customer> deleteCustomer(@PathVariable final String id) {
    return this.customerService.deleteCustomer(id);
  }
}
