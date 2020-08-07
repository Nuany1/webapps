package com.lucky.nacos.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "ts_model_page")
public class ModelPage implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "model_id")
    private Integer modelId;

    private String name;

    private String nameEn;

    @Column(name = "page_type")
    private Byte pageType;

    @Column(name = "page_sub_name")
    private String pageSubName;

    @Column(name = "create_time")
    private Date createTime;

    private String content;

    @Column(name = "page_id")
    private Integer pageId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Byte getPageType() {
        return pageType;
    }

    public void setPageType(Byte pageType) {
        this.pageType = pageType;
    }

    public String getPageSubName() {
        return pageSubName;
    }

    public void setPageSubName(String pageSubName) {
        this.pageSubName = pageSubName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }
}
