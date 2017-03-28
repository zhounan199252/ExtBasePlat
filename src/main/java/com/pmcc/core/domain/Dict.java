package com.pmcc.core.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典实体
 * Created by LvXL on 2016/2/2.
 */
@Entity
@Table(name = "DICT")
public class Dict implements Serializable {

    private String id; // 主键
    private String parentId; // 父ID
    private String dictName; // 字典名称
    private String dictCode; // 字典编码
    private String parentCode; // 所属编码
    private Integer sortno; // 排序
    private Integer status; // 状态 0：正常；1：停用
    private Integer rank; // 等级
    private Timestamp createTime; // 创建时间
    private String creator; // 创建人

    private Boolean checked; // 是否选中
    private Boolean expanded; // 是否展开
    private Boolean leaf; // 是否最终节点
    private List<Dict> children = new ArrayList<Dict>();

    private String text; // 下拉框隐藏值
    private String value; // 下拉框显示值

    @Transient
    public String getText() {
        return dictName;
    }

    public void setText(String text) {
        this.text = text;
    }
    @Transient
    public String getValue() {
        return id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Transient
    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Transient
    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    @Transient
    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    @Transient
    public List<Dict> getChildren() {
        return children;
    }

    public void setChildren(List<Dict> children) {
        this.children = children;
    }

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="OID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name ="PARENT_ID")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Column(name ="DICT_NAME")
    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    @Column(name ="DICT_CODE")
    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    @Column(name ="PARENT_CODE")
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Column(name ="SORTNO")
    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }

    @Column(name ="STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name ="RANK")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Column(name ="CREATE_TIME")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column(name ="CREATOR")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
