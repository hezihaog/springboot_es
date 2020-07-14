package cn.imooc.demo.springboot.es.controller;

import cn.imooc.demo.springboot.es.entity.es.EsBlog;
import cn.imooc.demo.springboot.es.entity.mysql.MysqlBlog;
import cn.imooc.demo.springboot.es.repository.es.EsBlogRepository;
import cn.imooc.demo.springboot.es.repository.mysql.MysqlRepository;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/7/14 5:51 下午
 */
@RestController
public class DataController {
    private static final String MYSQL = "mysql";
    private static final String ES = "es";

    @Autowired
    private MysqlRepository mysqlRepository;
    @Autowired
    private EsBlogRepository esBlogRepository;

    /**
     * 获取所有博客
     */
    @GetMapping("/blogs")
    public Object blogs() {
        return mysqlRepository.queryAll();
    }

    /**
     * 搜索
     */
    @PostMapping("/search")
    public Object search(@RequestBody Param param) {
        String keyword = param.getKeyword();
        StopWatch watch = new StopWatch();
        watch.start();
        HashMap<String, Object> map = new HashMap<>();
        String type = param.getType();
        if (type.equalsIgnoreCase(MYSQL)) {
            //mysql实现
            List<MysqlBlog> queryBlogs = mysqlRepository.queryBlogs(keyword);
            map.put("list", queryBlogs);
        } else if (type.equalsIgnoreCase(ES)) {
            //es实现
            List<EsBlog> queryBlogs = queryByEs(keyword);
            map.put("list", queryBlogs);
        } else {
            return "传入的type有误";
        }
        watch.stop();
        //获取耗时
        long totalTimeMillis = watch.getTotalTimeMillis();
        map.put("duration", totalTimeMillis);
        return map;
    }

    /**
     * 使用Es进行搜索
     */
    private List<EsBlog> queryByEs(String keyword) {
        BoolQueryBuilder builder =
                QueryBuilders.boolQuery()
                        //从标题搜索
                        .should(QueryBuilders.matchPhraseQuery("title", keyword))
                        //从内容搜索
                        .should(QueryBuilders.matchPhraseQuery("content", keyword));
        String queryStr = builder.toString();
        System.out.println(queryStr);
        Page<EsBlog> search = (Page<EsBlog>) esBlogRepository.search(builder);
        return search.getContent();
    }

    /**
     * 请求参数
     */
    @Data
    public static class Param {
        /**
         * 查询方式，mysql、es
         */
        private String type;
        /**
         * 搜索关键字
         */
        private String keyword;
    }
}