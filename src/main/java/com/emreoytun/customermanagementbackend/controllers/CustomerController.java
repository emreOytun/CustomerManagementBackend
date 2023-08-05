package com.emreoytun.customermanagementbackend.controllers;

import com.emreoytun.customermanagementbackend.service.customer.CustomerService;
import com.emreoytun.customermanagementdata.dto.authentication.request.RegisterRequest;
import com.emreoytun.customermanagementdata.dto.customer.CustomerDto;
import com.emreoytun.customermanagementdata.dto.customer.CustomerWithPostsDto;
import com.emreoytun.customermanagementdata.dto.customer.requests.CustomerUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getCustomersByPage(@RequestParam(value = "pageSize") int pageSize,
                                                @RequestParam(value = "pageNo") int pageNo) {

        return customerService.getCustomersByPageNo(pageSize, pageNo);
    }

    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@RequestParam(value = "id") int id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/{id}/withPosts")
    @ResponseStatus(HttpStatus.OK)
    public CustomerWithPostsDto getWithPosts(@PathVariable(value = "id") int customerId) {
        return customerService.getWithPosts(customerId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody CustomerUpdateRequest customerUpdateDto) {
        customerService.updateCustomer(customerUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/{username}/checkExistence")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkExists(@PathVariable("username") String username) {
        return customerService.checkExists(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody RegisterRequest request) {
       customerService.addCustomer(request);
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerByUsername(@PathVariable("username") String username) {
        return customerService.getCustomerByUsername(username);
    }
}
