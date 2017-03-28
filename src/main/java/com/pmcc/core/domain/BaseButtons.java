package com.pmcc.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 功能按钮表
 * Created by yaonan on 2015/12/18.
 */
@Entity
@Table(name = "BASE_BUTTONS")
public class BaseButtons  implements Serializable {
    /**
     * 序列化
     */
    private static  final  long serialVersionUID= 1L;
    @Id()
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="OID")
    private String oid;
//    名称
@Column(name ="NAME")
    private String name;
    //    英文名称
    @Column(name ="ENAME")
    private String ename;
//    事件
@Column(name ="EVENTMETHOD")
    private String eventMethod;
//    图标
@Column(name ="ICO")
    private String ico;
//    状态
@Column(name ="STATE")
    private Integer state;
//    排序编号
@Column(name ="SORTCODE")
    private Integer sortCode;
//    描述
@Column(name ="DESCRIPTION")
    private String description;
//    创建时间
@Column(name ="CREATETIME")
    private Date createTime;

    public BaseButtons() {
    }
    public BaseButtons(String oid) {
        this.oid=oid;
    }


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEventMethod() {
        return eventMethod;
    }

    public void setEventMethod(String eventMethod) {
        this.eventMethod = eventMethod;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonSerialize(using = com.pmcc.utils.CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
