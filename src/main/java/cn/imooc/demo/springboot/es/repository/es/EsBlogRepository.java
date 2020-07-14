package cn.imooc.demo.springboot.es.repository.es;

import cn.imooc.demo.springboot.es.entity.es.EsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/7/14 5:41 下午
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, Integer> {
}