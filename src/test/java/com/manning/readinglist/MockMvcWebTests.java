package com.manning.readinglist;

import com.manning.readinglist.entity.Book;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


//添加一些静态类，简化代码
/*
    简化前：
    mockMvc.perform(MockMvcRequestBuilders.get("/readingList/this"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("readingList"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
                .andExpect(MockMvcResultMatchers.model().attribute("books",
                Matchers.is(Matchers.empty())));
    简化后：见下面
 */
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by wangcheng  on 2018/6/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MockMvcWebTests {
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void homePage() throws Exception{
        mockMvc.perform(get("/readingList/craig"))
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books",
                Matchers.is(Matchers.empty())));
    }

    @Test
    public void postBook() throws Exception{
        //实际是两个测试放在一起了。第一部分提交图书并检查了请求结果，第二部分执行了一次对主页的请求，检查新建的图书是否在模型中。
        mockMvc.perform(post("/readingList/craig")       //执行post请求
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)   //application/x-www-form-urlencoded
                .param("title","Book Title")
                .param("author","Book Author")
                .param("isbn","1234567890")
                .param("description","Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/readingList/craig"));

        Book expectedBook = new Book();         //配置期望的图书
        expectedBook.setId(1L);
        expectedBook.setReader("craig");
        expectedBook.setTitle("Book Title");
        expectedBook.setAuthor("Book Author");
        expectedBook.setIsbn("1234567890");
        expectedBook.setDescription("Description");

        mockMvc.perform(get("/readingList/craig"))          //执行get请求
                .andExpect(status().isOk())
                .andExpect(view().name("readingList"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books",hasSize(1)))
                .andExpect(model().attribute("books",contains(samePropertyValuesAs(expectedBook))));

    }
}
