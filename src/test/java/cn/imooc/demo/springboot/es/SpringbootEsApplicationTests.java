package cn.imooc.demo.springboot.es;

import cn.imooc.demo.springboot.es.entity.es.EsBlog;
import cn.imooc.demo.springboot.es.repository.es.EsBlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootEsApplicationTests {
    @Autowired
    EsBlogRepository esBlogRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testEs() {
        Iterable<EsBlog> iterable = esBlogRepository.findAll();
        Iterator<EsBlog> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            EsBlog blog = iterator.next();
            System.out.println("title" + blog.getTitle());
        }
    }
}