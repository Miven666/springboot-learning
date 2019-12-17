package com.miven.springboot.dds.jpa.master.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 租户
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@Data
@Entity
@Table(name = "tenant")
@NoArgsConstructor
@AllArgsConstructor
public class Tenant implements Serializable {

    private static final long serialVersionUID = -9165640000604788356L;

    @Id
    @Column(name="id")
    private String id;

    @Column(name = "identifier")
    @NotEmpty(message = "Tenant identifier must be provided")
    private String identifier;

    @Column(name = "url")
    @Size(max = 256)
    @NotEmpty(message = "Tenant jdbc url must be provided")
    private String url;

    @Column(name = "driverClassName")
    @NotEmpty(message = "Tenant db driverClassName must be provided")
    private String driverClassName;

    @Column(name = "username")
    @Size(min = 4,max = 30,message = "db username length must between 4 and 30")
    @NotEmpty(message = "Tenant db username must be provided")
    private String username;

    @Column(name = "password")
    @Size(min = 4,max = 30)
    @NotEmpty(message = "Tenant db password must be provided")
    private String password;

    @Version
    private int version = 0;
}