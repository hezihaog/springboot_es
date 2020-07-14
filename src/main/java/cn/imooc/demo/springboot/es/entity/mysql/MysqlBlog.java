package cn.imooc.demo.springboot.es.entity.mysql;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/7/14 4:59 下午
 * <p>
 * MySQL中blog表的实体
 *
 * CREATE TABLE `t_blog` (
 * `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
 * `title` varchar(60) DEFAULT NULL COMMENT '博客标题',
 * `author` varchar(60) DEFAULT NULL COMMENT '博客作者',
 * `content` mediumtext COMMENT '博客内容',
 * `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 * `update_time` datetime DEFAULT NULL COMMENT '更新时间',
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4
 */
@Entity
@Table(name = "t_blog")
@Data
@NoArgsConstructor
public class MysqlBlog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 博客标题
     */
    @Column(name = "title")
    private String title;
    /**
     * 博客作者
     */
    @Column(name = "author")
    private String author;
    /**
     * 博客内容
     * columnDefinition指定数据库中的数据类型，博客内容比较大，用的是mediumtext
     */
    @Column(name = "content", columnDefinition = "mediumtext")
    private String content;
    /**
     * 博客创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 博客更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}