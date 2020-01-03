package com.javaee6.cgret.test;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 通过注解配置，与Repository联用时，可以直接将数据结构映射到ElasticSearch上
 * index：是否设置索引， store是否存储数据，type:数据类型，analyzer：分词粒度选择，searchAnalyzer：查询进行分词处理
 * ik_smart：进行最小粒度分词，ik_max_word进行最大粒度分词
 * @author Yellowyao
 *
 */
@Document(indexName = "client", type = "doc", useServerConfiguration = true, createIndex = false)
@Table(name = "client")
@Entity

public class Client implements Serializable {

    private  static final long serialVersionUID = 1L;

    @Id
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer id;

    @Field(index = true, analyzer = "ik_max_word",  type = FieldType.Text)
    private String name;

    @Field(index = true, store = true, type = FieldType.Text)
    private String password;

    @Field(index = true, store = true, type = FieldType.Text)
    private String email;

    @Field(index = true, store = true, type = FieldType.Text)
    private Long telephone;

    /**
     * 需要格式化format时间
     */
    @Field(index = true, store = true, type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date createTime;

    @Field(index = true, store = true, type = FieldType.Text)
    private String articleCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }
}