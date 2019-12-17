package com.miven.springboot.dds.jpa.support;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * 当前租户标识符解析器
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
public class SimpleCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    /**
     * 默认的租户身份
     */
    private static final String DEFAULT_TENANT = "tenant_1";

    /**
     * 解析当前租户身份
     * @return 租户身份
     */
    @Override
    public String resolveCurrentTenantIdentifier() {
        // 通过租户上下文获取租户身份，此身份是用户登录时在header中进行设置的
        String tenant = TenantContextHolder.getTenant();
        // 如果上下文中没有找到该租户身份，则使用默认的租户身份，或者直接报异常信息
        return StringUtils.isNotBlank(tenant)?tenant:DEFAULT_TENANT;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}