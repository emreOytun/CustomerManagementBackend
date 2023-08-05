package com.emreoytun.customermanagementbackend.service.post;

import com.emreoytun.customermanagementdata.dto.post.PostDto;
import com.emreoytun.customermanagementdata.dto.post.request.PostAddRequest;

import java.util.List;

public interface PostService {

    List<PostDto> getPostsByCustomerId(int id);

    void addPost(PostAddRequest postAddRequest);

    public void deleteAllByCustomerId(int customerID);
}
