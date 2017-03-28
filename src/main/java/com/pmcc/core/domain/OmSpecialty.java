package com.pmcc.core.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 专业信息
 * Created by H on 2016/2/19.
 */
@Entity
@Table(name = "OM_SPECIALTY")
public class OmSpecialty implements Serializable{

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")

    @Column(name ="SPECID")
    private  String specId;


    @Column(name ="SPECCODE")
    private String specCode;

    @Column(name = "SPECNAME")
    private String specName;

    @Column(name = "SPECPY")
    private String specCpy;

    @Column(name = "MANASPECID")
    private String maNaSpecId;

    @Column(name = "SPECLEVEL")
    private int specLevel;

    @Column(name = "SORTNO")
    private int sortNo;

    @Column(name = "SPECSEQ")
    private  String specSeq;

    @Column(name = "SPECSERIES")
    private String specSeries;

    @Column(name = "ISPTTITLE")
    private  String isPtTitle;

    @Column(name = "PTTRANK")
    private  String pttRank;

    @Column(name = "ISLEAF")
    private  String isLeaf;

    @Column(name ="SUBCOUNT")
    private Integer subCount;

    @Column(name = "REMARK")
    private String reMark;

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecCpy() {
        return specCpy;
    }

    public void setSpecCpy(String specCpy) {
        this.specCpy = specCpy;
    }

    public String getMaNaSpecId() {
        return maNaSpecId;
    }

    public void setMaNaSpecId(String maNaSpecId) {
        this.maNaSpecId = maNaSpecId;
    }

    public int getSpecLevel() {
        return specLevel;
    }

    public void setSpecLevel(int specLevel) {
        this.specLevel = specLevel;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getSpecSeq() {
        return specSeq;
    }

    public void setSpecSeq(String specSeq) {
        this.specSeq = specSeq;
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

    public Integer getSubCount() {
        return subCount;
    }

    public void setSubCount(Integer subCount) {
        this.subCount = subCount;
    }

    public String getReMark() {
        return reMark;
    }

    public void setReMark(String reMark) {
        this.reMark = reMark;
    }


    public String getSpecSeries() {
        return specSeries;
    }

    public void setSpecSeries(String specSeries) {
        this.specSeries = specSeries;
    }
}
