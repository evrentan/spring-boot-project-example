package evrentan.examples.springbootprojectexample;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import evrentan.examples.springbootprojectexample.dto.Customer;
import evrentan.examples.springbootprojectexample.dto.CustomerRef;
import evrentan.examples.springbootprojectexample.entity.CustomerEntity;
import evrentan.examples.springbootprojectexample.impl.CustomerServiceImpl;
import evrentan.examples.springbootprojectexample.mapper.CustomerMapper;
import evrentan.examples.springbootprojectexample.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ICustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer() {
        //given
        Customer customer = new Customer();
        customer.setId("1");
        customer.setCustomerFullName("John");
        customer.setCustomerType("ACTIVE");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId("1");
        customerEntity.setCustomerType("ACTIVE");
        customerEntity.setCustomerFullName("John");

        //when
        when(customerMapper.toEntity(customer)).thenReturn(customerEntity);
        when(customerRepository.save(any())).thenReturn(customerEntity);
        when(customerMapper.toDto(customerEntity)).thenReturn(customer);

        Customer customer2 = customerService.createCustomer(customer);

        //then
        verify(customerRepository, times(1)).save(any());
        assertEquals(customer, customer2);
    }

    @Test
    void getAllCustomerRefs() {
        //given
        Customer customer = new Customer();
        customer.setId("1");
        customer.setCustomerFullName("John");
        customer.setCustomerType("ACTIVE");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId("1");
        customerEntity.setCustomerType("ACTIVE");
        customerEntity.setCustomerFullName("John");

        //when
        when(customerMapper.toEntity(customer)).thenReturn(customerEntity);
        when(customerRepository.save(any())).thenReturn(customerEntity);
        when(customerMapper.toDto(customerEntity)).thenReturn(customer);

        //given
        CustomerRef customerRef = new CustomerRef();
        customerRef.setId("1");

        List<CustomerRef> customerRefList = new ArrayList<>();
        customerRefList.add(customerRef);

        List<CustomerEntity> entityList = new ArrayList<>();
        entityList.add(customerEntity);

        when(customerMapper.toDtoRefList(any())).thenReturn(customerRefList);
        when(customerRepository.findAll()).thenReturn(entityList);

        List<CustomerRef> customerRefs = customerService.getAllCustomerRefs();

        verify(customerMapper, times(1)).toDtoRefList(any());
        verify(customerRepository, times(1)).findAll();

        assertEquals(customerRefs, customerRefList);
    }

    @Test
    void getCustomerById() {
        //given
        Customer customer = new Customer();
        customer.setId("1");
        customer.setCustomerFullName("John");
        customer.setCustomerType("ACTIVE");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId("1");
        customerEntity.setCustomerType("ACTIVE");
        customerEntity.setCustomerFullName("John");

        //when
        when(customerMapper.toEntity(customer)).thenReturn(customerEntity);
        when(customerRepository.save(any())).thenReturn(customerEntity);
        when(customerMapper.toDto(customerEntity)).thenReturn(customer);

        Customer customer2 = customerService.createCustomer(customer);

        when(customerMapper.toDto(any())).thenReturn(customer);
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customerEntity));

        Customer customerExample = customerService.getCustomerById("1");

        verify(customerRepository, times(1)).findById(anyString());

        assertEquals(customerExample, customer2);
    }
}
