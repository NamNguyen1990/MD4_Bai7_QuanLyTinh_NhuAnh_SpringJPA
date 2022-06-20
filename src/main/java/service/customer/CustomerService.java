package service.customer;

import model.Customer;
import model.Province;
import service.IGeneralService;

public interface CustomerService extends IGeneralService<Customer> {
    Iterable<Customer> findAllByProvince (Province province);
}
