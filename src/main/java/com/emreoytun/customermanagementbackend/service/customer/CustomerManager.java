package com.emreoytun.customermanagementbackend.service.customer;

import com.emreoytun.customermanagementbackend.service.post.PostService;
import com.emreoytun.customermanagementbackend.service.role.RoleService;
import com.emreoytun.customermanagementbackend.service.user.UserService;
import com.emreoytun.customermanagementdata.dto.IModelMapperService;
import com.emreoytun.customermanagementdata.dto.authentication.request.RegisterRequest;
import com.emreoytun.customermanagementdata.dto.customer.CustomerDto;
import com.emreoytun.customermanagementdata.dto.customer.CustomerWithPostsDto;
import com.emreoytun.customermanagementdata.dto.customer.requests.CustomerUpdateRequest;
import com.emreoytun.customermanagementdata.dto.post.PostDto;
import com.emreoytun.customermanagementdata.dto.user.UserDto;
import com.emreoytun.customermanagementdata.entities.Customer;
import com.emreoytun.customermanagementdata.entities.Role;
import com.emreoytun.customermanagementdata.entities.User;
import com.emreoytun.customermanagementdata.repository.CustomerDao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerDao customerDao;
    private final UserService userService;
    private final PostService postService;
    private final RoleService roleService;
    private final IModelMapperService modelMapperService;
    private final Logger logger = LoggerFactory.getLogger(CustomerManager.class);

    @Override
    public List<CustomerDto> getAllCustomers() {
        logger.info("Entering getAllCustomers method");

        List<Customer> customerList = customerDao.findAll();
        List<CustomerDto> resultList = customerList.stream()
                .map(customer -> modelMapperService.map(customer, CustomerDto.class)).toList();

        return resultList;
    }

    @Override
    public List<CustomerDto> getCustomersByPageNo(int pageSize, int pageNo) {
        logger.info("Entering getCustomersByPageNo method");

        // In Spring, pages start from 0.
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<Customer> customerList = customerDao.findAll(pageable).getContent();
        List<CustomerDto> resultList = customerList.stream()
                .map(customer -> modelMapperService.map(customer, CustomerDto.class)).toList();
        return resultList;
    }

    @Override
    public CustomerDto getCustomerById(int id) {
        logger.info("Entering getCustomerById method");

        Customer customer = customerDao.findById(id);
        if (customer == null) {
            return null;
        }

        CustomerDto customerGetDto = modelMapperService.map(customer, CustomerDto.class);
        return customerGetDto;
    }

    @Override
    public CustomerWithPostsDto getWithPosts(int customerId) {
        Customer customer = customerDao.findById(customerId);
        if (customer == null) {
            return null;
        }

        CustomerWithPostsDto customerDto = new CustomerWithPostsDto();
        modelMapperService.copyProperties(customer, customerDto, "posts");

        List<PostDto> posts = customer.getPosts().stream().map(post -> {
            PostDto postDto = new PostDto();
            BeanUtils.copyProperties(post, postDto);
            postDto.setCustomerId(customerId);
            return postDto;
        }).collect(Collectors.toList());

        customerDto.setPosts(posts);
        return customerDto;
    }

    @Override
    public void updateCustomer(CustomerUpdateRequest customerUpdateDto) {
        logger.info("Entering updateCustomer method : " + customerUpdateDto);

        Customer customer = customerDao.findById(customerUpdateDto.getId());

        modelMapperService.copyProperties(customerUpdateDto, customer);
        customerDao.save(customer);

        logger.info("Customer with id " + customer.getId() + " is updated");
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        logger.info("Entering deleteCustomer method");

        Customer customer = customerDao.findById(id);

        postService.deleteAllByCustomerId(id); // delete posts before deleting the customer
        customerDao.delete(customer);

        logger.info("Customer with id " + id + " is deleted");
    }

    @Override
    public boolean checkExists(String username) {
        logger.info("Entering checkExists method");
        return userService.checkExistenceByUsername(username);
    }

    @Override
    public void addCustomer(RegisterRequest request) {
        logger.info("Entering add customer method. request : " + request);

        Customer c = new Customer();
        c.setFirstName(request.getFirstName());
        c.setLastName(request.getLastName());

        c.setUsername(request.getUsername());
        c.setPassword(request.getPassword());

        Role role = roleService.findByName("CUSTOMER");
        c.setRoles(new HashSet<>());
        c.getRoles().add(role);

        customerDao.save(c);
    }

    @Override
    public CustomerDto getCustomerByUsername(String username) {
        UserDto user = userService.getByUsername(username);
        if (user == null) {
            return null;
        }

        Customer customer = customerDao.findById(user.getId());
        if (customer == null) {
            return null;
        }
        return modelMapperService.mapComposed(customer, CustomerDto.class);
    }

}