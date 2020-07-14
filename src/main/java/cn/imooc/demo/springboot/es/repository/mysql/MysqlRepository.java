package cn.imooc.demo.springboot.es.repository.mysql;

import cn.imooc.demo.springboot.es.entity.mysql.MysqlBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/7/14 5:10 下午
 */
public interface MysqlRepository extends JpaRepository<MysqlBlog, Integer>, JpaSpecificationExecutor<MysqlBlog> {
    /**
     * 查询所有
     */
    @Query("select e from MysqlBlog e order by e.createTime desc")
    List<MysqlBlog> queryAll();

    /**
     * 搜索博客
     *
     * @param keyword 搜索关键字
     */
    @Query("select e from MysqlBlog e where e.title like concat('%',:keyword,'%') " +
            "or e.content like concat('%',:keyword,'%') ")
    List<MysqlBlog> queryBlogs(@Param("keyword") String keyword);
}