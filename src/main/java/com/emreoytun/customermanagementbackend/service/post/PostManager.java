package com.emreoytun.customermanagementbackend.service.post;

import com.emreoytun.customermanagementdata.repository.PostDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostManager implements PostService {

    private final PostDao postDao;

    @Override
    public void deleteAllByCustomerId(int customerID) {
        postDao.deleteAllByCustomerId(customerID);
    }
}
