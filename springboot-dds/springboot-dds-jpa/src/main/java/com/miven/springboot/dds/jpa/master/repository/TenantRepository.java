package com.miven.springboot.dds.jpa.master.repository;

import com.miven.springboot.dds.jpa.master.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 租户持久层
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@Repository
public interface TenantRepository extends JpaRepository<Tenant, String> {
    /**
     * 根据身份标识查询租户
     * @param identifier 身份
     * @return 租户
     */
    @Query("select t from Tenant t where t.identifier = :identifier")
    Tenant findByIdentifier(@Param("identifier") String identifier);
}