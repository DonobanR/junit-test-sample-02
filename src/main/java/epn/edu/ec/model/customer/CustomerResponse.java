package epn.edu.ec.model.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
    private long id;
    private String name;
    private String phone;
}
