package com.springboot.test;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends TestLauncherTest {

    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

    private RequestBuilder request;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    /**
     * 查询 User 列表，应该为空
     */
    @Test
    public void test001() throws Exception {
        request = get("/users/");
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    /**
     * 新增 User，应该成功
     */
    @Test
    public void test002() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("测试大师");
        user.setAge(20);
        request = post("/users/")
                .contentType(APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(user));
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(equalTo("success")));
    }

    /**
     * 查询 User，应该不为空
     */
    @Test
    public void test003() throws Exception {
        request = get("/users/");
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));
    }

    /**
     * 修改 User，应该成功
     */
    @Test
    public void test004() throws Exception {
        request = put("/users/1")
                .param("name", "测试终极大师")
                .param("age", "30");
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(equalTo("success")));
    }

    /**
     * 删除 User，应该成功
     */
    @Test
    public void test005() throws Exception {
        request = delete("/users/1");
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(equalTo("success")));
    }
}