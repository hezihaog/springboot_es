package cn.imooc.demo.springboot.es.controller;

import cn.imooc.demo.springboot.es.entity.mysql.MysqlBlog;
import cn.imooc.demo.springboot.es.repository.mysql.MysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/7/14 5:11 下午
 */
@Controller
public class IndexController {
    @Autowired
    private MysqlRepository mysqlRepository;

    /**
     * 首页
     */
    @RequestMapping("/")
    public String index() {
        List<MysqlBlog> all = mysqlRepository.findAll();
        System.out.println(all.size());
        return "index.html";
    }
}