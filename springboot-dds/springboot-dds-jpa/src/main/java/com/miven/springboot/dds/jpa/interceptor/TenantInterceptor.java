package com.miven.springboot.dds.jpa.interceptor;

import com.miven.springboot.dds.jpa.support.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 租户信息的拦截器
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@Slf4j
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenant = request.getParameter("tenant");
        if(StringUtils.isBlank(tenant)) {
            response.sendRedirect("/login.html");
            return false;
        } else {
            TenantContextHolder.setTenant(tenant);
            return true;
        }
    }
}