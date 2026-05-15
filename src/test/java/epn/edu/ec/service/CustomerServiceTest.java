package epn.edu.ec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import epn.edu.ec.model.customer.CustomerResponse;
import epn.edu.ec.model.customer.CustomersResponse;
import epn.edu.ec.repository.CustomerRepository;
import epn.edu.ec.repository.model.Customer;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customerA;
    private Customer customerB;

    @BeforeEach
    public void setUp() {
        customerA = Customer.builder()
                .id(1L)
                .name("Juan Pérez")
                .phone("0987654321")
                .build();
        customerB = Customer.builder()
                .id(2L)
                .name("María García")
                .phone("0912345678")
                .build();
    }

    @Test
    public void getCustomers_ShouldReturnAllCustomersSortedByName() {
        // ARRANGE
        List<Customer> customers = Arrays.asList(customerB, customerA);
        when(customerRepository.findAll()).thenReturn(customers);

        // ACT
        CustomersResponse customersResponse = customerService.getCustomers();

        // ASSERT
        assertNotNull(customersResponse);
        assertEquals(2, customersResponse.getCustomers().size());

        assertEquals("Juan Pérez", customersResponse.getCustomers().get(0).getName());
        assertEquals("María García", customersResponse.getCustomers().get(1).getName());

        verify(customerRepository, times(1)).findAll();
    }

}
