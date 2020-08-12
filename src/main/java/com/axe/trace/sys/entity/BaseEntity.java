package com.axe.trace.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键", name="id")
    protected String id;

    @ApiModelProperty(value="页号", name="pageNum")
    protected Integer pageNum = 1;

    @ApiModelProperty(value="页面大小", name="pageSize")
    protected Integer pageSize = 10;

    @ApiModelProperty(value="创建者", name="createBy")
    protected String createBy;

    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @ApiModelProperty(value="创建时间", name="createTime")
    protected Date createTime;

    @ApiModelProperty(value="更新者", name="updateBy")
    protected String updateBy;

    @ApiModelProperty(value="更新时间", name="updateTime")
    protected Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public Integer getPageNum() {
        return pageNum;
    }

    @JsonProperty
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @JsonIgnore
    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
