package com.miven.springboot.dds.jpa.dynamic.repository;

import com.miven.springboot.dds.jpa.dynamic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户持久层
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    /**
     * 根据用户名查询用户
     * @param username  用户名
     * @return 用户
     */
    User findByUsername(String username);
}