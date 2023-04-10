package com.emreoytun.customermanagementbackend.api.controllers;

import com.emreoytun.customermanagementbackend.business.abstracts.CustomerService;
import com.emreoytun.customermanagementdata.dto.customer.requests.CustomerAddRequest;
import com.emreoytun.customermanagementdata.dto.customer.requests.CustomerUpdateRequest;
import com.emreoytun.customermanagementdata.dto.customer.responses.CustomerGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//localhost:8082/api/customers/
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    // SOLID
    @Autowired
    private CustomerService customerService;

    // JWT - HEADER -> "Authorization": Bearer .....

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid CustomerAddRequest customerAddDto) {
        customerService.addCustomer(customerAddDto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody @Valid CustomerUpdateRequest customerUpdateDto) {
        customerService.updateCustomer(customerUpdateDto);
    }

    // TODO: ONLY-ADMIN
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
    }

    // /customers/3
    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public CustomerGetResponse getCustomerById(@RequestParam(value = "id", required = true) int id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerGetResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerGetResponse> getCustomersByPage(@RequestParam(value = "pageSize", required = true) int pageSize,
                                                        @RequestParam(value = "pageNo", required = true) int pageNo) {

        return customerService.getCustomersByPageNo(pageSize, pageNo);
    }
}