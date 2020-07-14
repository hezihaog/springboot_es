package cn.imooc.demo.springboot.es.entity.es;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/7/14 5:26 下午
 * <p>
 * Es中实体
 */
@NoArgsConstructor
@Data
/**
 * 注解@Document
 * 1.useServerConfiguration属性：是否使用线上的配置，一般我们设置为true
 * 2.createIndex属性：是否在项目启动时创建index，一般我们通常在kibana中已经配置过了，所以就不需要每次启动重新创建
 */
@Document(indexName = "blog", type = "_doc",
        useServerConfiguration = true, createIndex = false)
public class EsBlog {
    /**
     * 主键，告知对应Es中的_id字段
     */
    @Id
    private Integer id;
    /**
     * 博客标题
     * 注解@Field
     * 1.type属性：表示在Es中对应的属性
     * 2.analyzer属性，表示需要分词，指定分词器
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
    /**
     * 博客作者
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String author;
    /**
     * 博客内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;
    /**
     * 博客创建时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date createTime;
    /**
     * 博客更新时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date updateTime;
}