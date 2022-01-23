package evrentan.examples.springbootprojectexample.controller;

import evrentan.examples.springbootprojectexample.dto.CustomerRef;
import evrentan.examples.springbootprojectexample.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for provided customer reference related API end-points.
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/refCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Customer Reference Related APIs")
public class CustomerRefController {

  private final ICustomerService customerService;

  public CustomerRefController(ICustomerService customerService) {
    this.customerService = customerService;
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
}
