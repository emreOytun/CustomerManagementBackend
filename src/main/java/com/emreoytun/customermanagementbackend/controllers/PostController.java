package com.emreoytun.customermanagementbackend.controllers;

import com.emreoytun.customermanagementbackend.service.post.PostService;
import com.emreoytun.customermanagementdata.dto.post.PostDto;
import com.emreoytun.customermanagementdata.dto.post.request.PostAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/getCustomerPosts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getPostsByCustomerId(@RequestParam("customerId") int customerId) {
        return postService.getPostsByCustomerId(customerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost(@RequestBody PostAddRequest postAddRequest) {
        postService.addPost(postAddRequest);
    }

    @DeleteMapping("/deleteAllCustomerPosts")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllByCustomerId(@RequestParam("customerId") int customerId) {
        postService.deleteAllByCustomerId(customerId);
    }

}
