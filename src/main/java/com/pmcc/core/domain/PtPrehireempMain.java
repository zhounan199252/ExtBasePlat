package com.pmcc.core.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 拟录入人员审核信息表
 * Created by {zhangsongwei} on 2016/2/24.
 */
@Entity
@Table(name="PT_PREHIREEMP_MAIN")
public class PtPrehireempMain implements Serializable{

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="PREHIREEMPTABID")
    private String preHireeMpTabId;

    @Column(name ="EMPREQPLANID")
    private Integer empreqPlanId;

    @Column(name ="EMPREQPLANITEMID")
    private Integer empreqPlanItemId;

    @Column(name="LETTERSEQ")
    private String letterSeq;

    @Column(name="ORGID")
    private Integer orgId;

    @Column(name="ORGCODE")
    private String orgCode;

    @Column(name="UNIT")
    private String unit;

    @Column(name="BUSIGROUPID")
    private Integer busiGroupId;

    @Column(name="BUSIGROUPCODE")
    private String busiGroupCode;

    @Column(name="BUSIGROUP")
    private String busiGroup;

    @Column(name="RPTDATE")
    private Date rptDate;

    @Column(name="RPTPERSON")
    private String rptPerson;

    @Column(name="TOTALCOUNT")
    private Integer totalCount;

    @Column(name="ISAUDIT")
    private String isAudit;

    @Column(name="REMARK")
    private String remark;

    public String getPreHireeMpTabId() {
        return preHireeMpTabId;
    }

    public void setPreHireeMpTabId(String preHireeMpTabId) {
        this.preHireeMpTabId = preHireeMpTabId;
    }

    public Integer getEmpreqPlanId() {
        return empreqPlanId;
    }

    public void setEmpreqPlanId(Integer empreqPlanId) {
        this.empreqPlanId = empreqPlanId;
    }

    public Integer getEmpreqPlanItemId() {
        return empreqPlanItemId;
    }

    public void setEmpreqPlanItemId(Integer empreqPlanItemId) {
        this.empreqPlanItemId = empreqPlanItemId;
    }

    public String getLetterSeq() {
        return letterSeq;
    }

    public void setLetterSeq(String letterSeq) {
        this.letterSeq = letterSeq;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
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

    public Integer getBusiGroupId() {
        return busiGroupId;
    }

    public void setBusiGroupId(Integer busiGroupId) {
        this.busiGroupId = busiGroupId;
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

    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }

    public String getRptPerson() {
        return rptPerson;
    }

    public void setRptPerson(String rptPerson) {
        this.rptPerson = rptPerson;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
