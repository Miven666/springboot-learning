package com.miven.springboot.dds.jpa.dynamic.service.impl;

import com.miven.springboot.dds.jpa.dynamic.model.User;
import com.miven.springboot.dds.jpa.dynamic.repository.UserRepository;
import com.miven.springboot.dds.jpa.dynamic.service.UserService;
import com.miven.springboot.dds.jpa.support.TenantContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.UUID;

/**
 * 用户服务
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;


    @Override
    public void save(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setTenant(TenantContextHolder.getTenant());
        userRepository.save(user);
    }

    @Override
    public User findById(String userId) {
        Optional<User> optional = userRepository.findById(userId);
        return optional.orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}