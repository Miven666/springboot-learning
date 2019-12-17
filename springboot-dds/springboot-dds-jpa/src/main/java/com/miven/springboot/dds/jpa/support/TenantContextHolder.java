package com.miven.springboot.dds.jpa.support;

/**
 * 租户上下文持有者
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
public class TenantContextHolder {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setTenant(String tenant){
        CONTEXT.set(tenant);
    }

    public static String getTenant(){
        return CONTEXT.get();
    }

    public static void clear(){
        CONTEXT.remove();
    }
}