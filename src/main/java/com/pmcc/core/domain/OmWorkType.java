package com.pmcc.core.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 工种信息表
 * Created by {zhangsongwei} on 2016/2/19.
 */
@Entity
@Table(name = "OM_WORKTYPE")
public class OmWorkType implements Serializable {

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="WORKTYPEID")
    private  String  worktypeId;
    //工种代码
    @Column(name ="WORKTYPECODE")
    private    String   worktypeCode;
    //工种名称
    @Column(name ="WORKTYPENAME")
    private    String   worktypeName;
    //工种拼音
    @Column(name ="WORKTYPEPY")
    private    String   worktypePy;
    //工种大类
    @Column(name ="WORKTYPEBIGCAT")
    private    String   worktypeBigcat;
    //上级工种编号
    @Column(name ="MANAWORKTYPEID")
    private    String  manaworktypeId;
    //工种层次
    @Column(name ="WORKTYPELEVEL")
    private    Integer  worktypeLevel;
    //排列序号
    @Column(name ="SORTNO")
    private    Integer  sortNo;
    //工种序列号
    @Column(name ="WORKTYPESEQ")
    private    String  worktypeSeq;
    //工种分类
    @Column(name ="WORKTYPECATALOG")
    private    String  worktypeCatalog;
    //是否子节点
    @Column(name ="ISLEAF")
    private    String  isLeaf;
    //子节点数
    @Column(name ="SUBCOUNT")
    private    Integer  subCount;
    //备注
    @Column(name ="REMARK")
    private    String  remark;


    public String getWorktypeId() {
        return worktypeId;
    }

    public void setWorktypeId(String worktypeId) {
        this.worktypeId = worktypeId;
    }

    public String getWorktypeCode() {
        return worktypeCode;
    }

    public void setWorktypeCode(String worktypeCode) {
        this.worktypeCode = worktypeCode;
    }

    public String getWorktypeName() {
        return worktypeName;
    }

    public void setWorktypeName(String worktypeName) {
        this.worktypeName = worktypeName;
    }

    public String getWorktypePy() {
        return worktypePy;
    }

    public void setWorktypePy(String worktypePy) {
        this.worktypePy = worktypePy;
    }

    public String getWorktypeBigcat() {
        return worktypeBigcat;
    }

    public void setWorktypeBigcat(String worktypeBigcat) {
        this.worktypeBigcat = worktypeBigcat;
    }

    public String getManaworktypeId() {
        return manaworktypeId;
    }

    public void setManaworktypeId(String manaworktypeId) {
        this.manaworktypeId = manaworktypeId;
    }

    public Integer getWorktypeLevel() {
        return worktypeLevel;
    }

    public void setWorktypeLevel(Integer worktypeLevel) {
        this.worktypeLevel = worktypeLevel;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getWorktypeSeq() {
        return worktypeSeq;
    }

    public void setWorktypeSeq(String worktypeSeq) {
        this.worktypeSeq = worktypeSeq;
    }

    public String getWorktypeCatalog() {
        return worktypeCatalog;
    }

    public void setWorktypeCatalog(String worktypeCatalog) {
        this.worktypeCatalog = worktypeCatalog;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Integer getSubCount() {
        return subCount;
    }

    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
