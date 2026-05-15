package epn.edu.ec.service;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;

import org.springframework.stereotype.Service;

import epn.edu.ec.model.customer.CustomerResponse;
import epn.edu.ec.model.customer.CustomersResponse;
import epn.edu.ec.repository.CustomerRepository;
import epn.edu.ec.repository.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomersResponse getCustomers() {
        return new CustomersResponse(customerRepository.findAll().stream()
                .map(this::customerResponse)
                .sorted(Comparator.comparing(CustomerResponse::getName))
                .collect(toList()));
    }

    public CustomerResponse getCustomer(long customerId) {
        return customerResponse(findExistingCustomer(customerId));
    }

    private Customer findExistingCustomer(long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> {
            log.error("customer with id not found {}", customerId);
            throw new RuntimeException("Customer not found");
        });
    }

    private CustomerResponse customerResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getPhone());
    }
}
