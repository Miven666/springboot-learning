package com.miven.springboot.jooq.controller;

import com.miven.springboot.jooq.repository.tables.User;
import org.jooq.DSLContext;
import org.jooq.types.UInteger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.miven.springboot.jooq.repository.Tables.USER;

/**
 * 控制器
 *
 * @author mingzhi.xie
 * @since 2021/3/5 1.0.0
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Resource
    private DSLContext dslContext;

    @GetMapping("select/user/{id}")
    public User selectUser(@PathVariable Integer id) {
        return dslContext
                .select(USER.ID, USER.NAME, USER.AGE)
                .from(USER)
                .where(USER.ID.eq(UInteger.valueOf(id)))
                .fetchOneInto(User.class);
    }

    @GetMapping("insert/user/{name}/{age}")
    public void insertUser(@PathVariable String name, @PathVariable Short age) {
        dslContext
                .insertInto(USER)
                .columns(USER.NAME, USER.AGE)
                .values(name, age)
                .execute();
    }

    @GetMapping("update/user/{id}/{name}/{age}")
    public void updateUser(@PathVariable Integer id, @PathVariable String name, @PathVariable Short age) {
        dslContext
                .update(USER)
                .set(USER.NAME, name)
                .set(USER.AGE, age)
                .where(USER.ID.eq(UInteger.valueOf(id)))
                .execute();
    }

    @GetMapping("delete/user/{id}")
    public void updateUser(@PathVariable Integer id) {
        dslContext
                .delete(USER)
                .where(USER.ID.eq(UInteger.valueOf(id)))
                .execute();
    }
}
