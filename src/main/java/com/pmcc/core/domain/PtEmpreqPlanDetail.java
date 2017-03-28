package com.pmcc.core.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zhounan on 2016/2/29.
 */
@Entity
@Table(name = "PT_EMPREQPLAN_DETAILS")
public class PtEmpreqPlanDetail {

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="EMPREQPLANITEMID")
    private  String oid;
    @Column(name ="EMPREQPLANID")
    private  String  empreqPlanId;
    private  double   orgId;
    @Column(name ="ORGCODE")
    private   String    orgCode;
    @Column(name ="UNIT")
    private   String    unit;
//    private   double   busiGroupID;
    @Column(name ="BUSIGROUPCODE")
    private   String    busiGroupCode;
    @Column(name ="BUSIGROUP")
    private   String    busiGroup;
    @Column(name ="SPECSEQ")
    private   String   specSeq;
    @Column(name ="SPECID")
    private   String    specId;
    @Column(name ="SPECNAME")
    private   String    specName;
    @Column(name ="EDUBG")
    private   String    eduBg;
    @Column(name ="REQCOUNT")
    private   int     reqCount;
    @Column(name ="REMARK")
    private   String   remark;


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }


    public String getEmpreqPlanId() {
        return empreqPlanId;
    }

    public void setEmpreqPlanId(String empreqPlanId) {
        this.empreqPlanId = empreqPlanId;
    }

    public double getOrgId() {
        return orgId;
    }

    public void setOrgId(double orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public String getBusiGroupCode() {
        return busiGroupCode;
    }

    public void setBusiGroupCode(String busiGroupCode) {
        this.busiGroupCode = busiGroupCode;
    }

    public String getBusiGroup() {
        return busiGroup;
    }

    public void setBusiGroup(String busiGroup) {
        this.busiGroup = busiGroup;
    }

    public String getSpecSeq() {
        return specSeq;
    }

    public void setSpecSeq(String specSeq) {
        this.specSeq = specSeq;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getEduBg() {
        return eduBg;
    }

    public void setEduBg(String eduBg) {
        this.eduBg = eduBg;
    }

    public int getReqCount() {
        return reqCount;
    }

    public void setReqCount(int reqCount) {
        this.reqCount = reqCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
