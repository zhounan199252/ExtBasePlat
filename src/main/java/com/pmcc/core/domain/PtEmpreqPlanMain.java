package com.pmcc.core.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhounan on 2016/2/23.
 */
@Entity
@Table(name = "PT_EMPREQPLAN_MAIN")
public class PtEmpreqPlanMain {

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="EMPREQPLANID")
    private      String    oid;
    @Column(name ="NYEAR")
    private      int     nyear;
    @Column(name ="ORGID")
    private      String     orgId;
    @Column(name ="ORGCODE")
    private      String     orgCode;
    @Column(name ="UNIT")
    private      String     unit;
    @Column(name ="BUSIGROUPID")
    private      String    busiGroupId;
    @Column(name ="BUSIGROUPCODE")
    private      String     busiGroupCode;
    @Column(name ="BUSIGROUP")
    private      String     busiGroup;
    @Column(name ="RPTDATE")
    private       Date rptDate;
    @Column(name ="RPTPERSON")
    private      String     rptPerson;
    @Column(name ="TOTALCOUNT")
    private       String   totalCount;
    @Column(name ="ISAUDIT")
    private      String     isAudit;
    @Column(name ="PLANSTAGE")
    private      String     planStage;
    @Column(name ="REMARK")
    private      String     remark;


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getNyear() {
        return nyear;
    }

    public void setNyear(int nyear) {
        this.nyear = nyear;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
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

    public String getBusiGroupId() {
        return busiGroupId;
    }

    public void setBusiGroupId(String busiGroupId) {
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

    @Transient
    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }


    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }

    public String getPlanStage() {
        return planStage;
    }

    public void setPlanStage(String planStage) {
        this.planStage = planStage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
