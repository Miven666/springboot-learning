package com.miven.springboot.dds.jpa.master.service;

import com.miven.springboot.dds.jpa.master.model.Tenant;

/**
 * 租户业务层
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
public interface TenantService {
    /**
     * 根据身份标识查询租户
     * @param name 身份
     * @return 租户
     */
    Tenant findByName(String name);
}