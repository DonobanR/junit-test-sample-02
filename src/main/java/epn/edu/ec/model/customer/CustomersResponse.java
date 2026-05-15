package epn.edu.ec.model.customer;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomersResponse {
    private List<CustomerResponse> customers;
}
