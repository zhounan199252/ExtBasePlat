package com.pmcc.core.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 字典表
 * Created by yaonan on 2015/12/18.
 */
@Entity
@Table(name = "BASE_DICTIONARY")
public class BaseDictionary implements Serializable {
    /**
     * 序列化
     */
    private static  final  long serialVersionUID= 1L;
    @Id()
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="OID")
    private String oid;
    //说明标题
    @Column(name ="TITLE")
    private String title;
    //内容
    @Column(name ="CONTENT")
    private String content;
    //对应字段名称
    @Column(name ="FILENAME")
    private String filename;
    //备注
    @Column(name ="REMARK")
    private String remark;
    //状态0启用1禁用
    @Column(name ="STATE")
    private Integer state;


    public BaseDictionary() {
    }
    public BaseDictionary(String oid) {
        this.oid=oid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
