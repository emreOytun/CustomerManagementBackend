package com.emreoytun.customermanagementbackend.service.post;

import com.emreoytun.customermanagementdata.dto.IModelMapperService;
import com.emreoytun.customermanagementdata.dto.post.PostDto;
import com.emreoytun.customermanagementdata.dto.post.request.PostAddRequest;
import com.emreoytun.customermanagementbackend.entities.Customer;
import com.emreoytun.customermanagementbackend.entities.Post;
import com.emreoytun.customermanagementdata.exceptions.EntityNotFoundException;
import com.emreoytun.customermanagementbackend.repository.CustomerDao;
import com.emreoytun.customermanagementbackend.repository.PostDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostManager implements PostService {
    private final PostDao postDao;
    private final CustomerDao customerDao;
    private final IModelMapperService modelMapperService;

    @Override
    public List<PostDto> getPostsByCustomerId(int id) {
        return postDao.getPostsByCustomer_id(id).stream().map(post -> {
            PostDto postDto = modelMapperService.map(post, PostDto.class);
            postDto.setCustomerId(post.getCustomer().getId());
            return postDto;
        }).collect(Collectors.toList());
    }
    @Override
    public void addPost(PostAddRequest postAddRequest) {
        Post post = new Post();
        BeanUtils.copyProperties(postAddRequest, post);

        Customer customer = customerDao.findById(postAddRequest.getCustomerID());
        if (customer == null) {
            throw new EntityNotFoundException("Customer not found for the given post.");
        }

        post.setCustomer(customer);
        postDao.save(post);
    }

    @Override
    @Transactional
    public void deleteAllByCustomerId(int customerID) {
        postDao.deleteAllByCustomer_id(customerID);
    }
}
