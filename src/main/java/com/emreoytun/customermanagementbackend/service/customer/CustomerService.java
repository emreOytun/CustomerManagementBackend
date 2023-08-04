package com.emreoytun.customermanagementbackend.service.customer;


import com.emreoytun.customermanagementdata.dto.authentication.request.RegisterRequest;
import com.emreoytun.customermanagementdata.dto.customer.CustomerDto;
import com.emreoytun.customermanagementdata.dto.customer.CustomerWithPostsDto;
import com.emreoytun.customermanagementdata.dto.customer.requests.CustomerUpdateRequest;

import java.util.List;

public interface CustomerService {
    void deleteCustomer(int id);
    void updateCustomer(CustomerUpdateRequest customerUpdateDto);
    CustomerDto getCustomerById(int id);
    List<CustomerDto> getAllCustomers();
    List<CustomerDto> getCustomersByPageNo(int pageSize, int pageNo);
    CustomerWithPostsDto getWithPosts(int customerId);

    boolean checkExists(String username);

    void addCustomer(RegisterRequest request);
    CustomerDto getCustomerByUsername(String username);
}