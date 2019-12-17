package com.miven.springboot.dds.jpa.dynamic.service;

import com.miven.springboot.dds.jpa.dynamic.model.User;

/**
 * 用户业务层
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
public interface UserService {
    /**
     * 保存用户
     * @param user 用户
     */
    void save(User user);

    /**
     * 根据用户 ID 查询用户
     * @param userId 用户 ID
     * @return 用户
     */
    User findById(String userId);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    User findByUsername(String username);
}