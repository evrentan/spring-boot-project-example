package evrentan.examples.springbootprojectexample.controller;

import evrentan.examples.springbootprojectexample.dto.CustomerRef;
import evrentan.examples.springbootprojectexample.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/refCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Customer Reference Related APIs")
@ResponseBody
public class CustomerRefController {

  private final ICustomerService customerService;

  public CustomerRefController(ICustomerService customerService) {
    this.customerService = customerService;
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
}
