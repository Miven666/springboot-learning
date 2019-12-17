package com.miven.springboot.dds.jpa.dynamic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户
 * @author mingzhi.xie
 * @date 2019/12/17
 * @since 1.0
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -156890917814957041L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @Size(min = 6,max = 22,message = "User password must be provided and length between 6 and 22.")
    private String password;

    @Column(name = "tenant")
    private String tenant;
}