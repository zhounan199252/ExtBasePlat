package com.pmcc.core.domain;

import com.pmcc.utils.TreeNode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 职称信息表
 * Created by ${zhounan} on 2016/2/18.
 */

@Entity
@Table(name = "OM_PTTITLE")
public class OmPttitle  implements Serializable {

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="PTTID")
  private      String  oid;

    @Column(name ="PTTCODE")
    private    String  pttCode;
    @Column(name ="PTTNAME")
    private    String  pttName;
    @Column(name ="PTTPY")
    private    String  pttPy;

    @Column(name ="MANAPTTID")
    private    String  maNaPttId;

    @Column(name ="PTTLEVEL")
    private    int  pttLevel;
    @Column(name ="SORTNO")
    private    int  sortNo;
    @Column(name ="PTTSEQ")
    private    String  pttSeq;
    @Column(name ="PTTSERIES")
    private    String  pttSeries;
    @Column(name ="ISPTTITLE")
    private    String  isPtTitle;
    @Column(name ="PTTRANK")
    private    String  pttRank;
    @Column(name ="ISLEAF")
    private    String  isLeaf;
    @Column(name ="SUBCOUNT")
    private    double  subCount;
    @Column(name ="REMARK")
    private    String  remark;

    private    String  pttSeriesName;


    @Transient
    public String getPttSeriesName() {
        return pttSeriesName;
    }

    public void setPttSeriesName(String pttSeriesName) {
        this.pttSeriesName = pttSeriesName;
    }

    public String getMaNaPttId() {
        return maNaPttId;
    }

    public void setMaNaPttId(String maNaPttId) {
        this.maNaPttId = maNaPttId;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getPttCode() {
        return pttCode;
    }


    public void setPttCode(String pttCode) {
        this.pttCode = pttCode;
    }

    public String getPttName() {
        return pttName;
    }

    public void setPttName(String pttName) {
        this.pttName = pttName;
    }


    public String getPttPy() {
        return pttPy;
    }

    public void setPttPy(String pttPy) {
        this.pttPy = pttPy;
    }

    public int getPttLevel() {
        return pttLevel;
    }

    public void setPttLevel(int pttLevel) {
        this.pttLevel = pttLevel;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getPttSeq() {
        return pttSeq;
    }

    public void setPttSeq(String pttSeq) {
        this.pttSeq = pttSeq;
    }

    public String getPttSeries() {
        return pttSeries;
    }

    public void setPttSeries(String pttSeries) {
        this.pttSeries = pttSeries;
    }

    public String getIsPtTitle() {
        return isPtTitle;
    }

    public void setIsPtTitle(String isPtTitle) {
        this.isPtTitle = isPtTitle;
    }

    public String getPttRank() {
        return pttRank;
    }

    public void setPttRank(String pttRank) {
        this.pttRank = pttRank;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getSubCount() {
        return subCount;
    }

    public void setSubCount(double subCount) {
        this.subCount = subCount;
    }

}
