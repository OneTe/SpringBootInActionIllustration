package com.manning.readinglist;

import com.manning.readinglist.entity.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadingListApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
    private RedisTemplate<String,Book> redisTemplate;
//	@Test
//	public void test() {
//		stringRedisTemplate.opsForValue().set("aaa","111");
//		Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
//	}

	@Test
    public void test1() throws Exception{
	    Book book = new Book();
	    book.setId(1);
	    book.setIsbn("1231");
	    book.setDescription("seek something deep!");
	    book.setAuthor("wangcheng");
	    book.setTitle("Hi");
	    book.setReader("wang");
	    redisTemplate.opsForValue().set("wangcheng",book);
	    Book book1 = (Book)redisTemplate.opsForValue().get("wangcheng");
	    System.out.println(book1.getTitle());
        //Assert.assertEquals("wangcheng",book1.getAuthor());
    }


}
