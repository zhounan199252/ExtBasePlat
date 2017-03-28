package com.pmcc.core.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhounan on 2016/2/25.
 */
@Entity
@Table(name = "PT_PASSPROBATIONAUDIT")
public class PtPassProbationAudit {

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="PASSPROBATIONTABID")
    private String  oid;
//    @Column(name ="PASSPROBSUMTABID")
//    private double passProbSumTabId;
//    @Column(name ="APPRAISALTABID")
//    private double  appRaIsAlTabID;
    @Column(name ="IDENTITYNO")
    private String identityNo;
    @Column(name ="EMPNAME")
    private String empName;
    @Column(name ="GENDER")
    private String gender;
    @Column(name ="BIRTHDATE")
    private Date birthDate;
    @Column(name ="NATION")
    private String nation;
    @Column(name ="GRADDATE")
    private Date gradDate;
    @Column(name ="GRADUNIV")
    private String gradUniv;
    @Column(name ="EDUBG")
    private String eduBg;
    @Column(name ="STUDYPERIOD")
    private String studyPeriod;
    @Column(name ="SPECNAME")
    private String specName;
    @Column(name ="WORKDATE")
    private Date workDate;
    @Column(name ="POSITION")
    private String position;
    @Column(name ="USEDPOSITION")
    private String usedPosition;
    @Column(name ="DUTY")
    private String duty;
    @Column(name ="UNITID")
    private double  unitId;
    @Column(name ="UNITCODE")
    private String unitCode;
    @Column(name ="UNIT")
    private String unit;
    @Column(name ="DEPARTMENT")
    private String department;
    @Column(name ="PRACTICEPOSITIONRANK")
    private String practicePositionRank;
    @Column(name ="PRACTICESALARYSTAND")
    private double practiceSalaryStand;
    @Column(name ="GRADPOSITIONRANK")
    private String gradPositionRank;
    @Column(name ="GRADSALARYSTAND")
    private double gradSalaryStand;
    @Column(name ="FILLTABDATE")
    private Date fillTabDate;
    @Column(name ="TECHLEVEL")
    private String techLevel;
    @Column(name ="UNITOPITION")
    private String unitOption;
    @Column(name ="UNITSIGNATURE")
    private String unitSignature;
    @Column(name ="UNITSIGNDATE")
    private Date unitSignDate;
    @Column(name ="ISUNITAPPROVED")
    private String isUnitApproved;
    @Column(name ="AUDITOPTION")
    private String auditOption;
    @Column(name ="AUDITSIGNATURE")
    private String auditSignature;
    @Column(name ="AUDITDATE")
    private Date auditDate;
    @Column(name ="ISAUDITDEPAPPROVED")
    private String isAuditDepApproved;
    @Column(name ="AUDITSTATUS")
    private String auditStatus;
//    @Column(name ="PROCESSINSTID")
//    private double  processInstId;
//    @Column(name ="WORKITEMID")
//    private double workItemId;
//    @Column(name ="ACTIVITYINSTID")
//    private double activityInstId;
    @Column(name ="ACTIVIDEFID")
    private String actIviDefId;
    @Column(name ="REMARK")
    private String remark;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Date getGradDate() {
        return gradDate;
    }

    public void setGradDate(Date gradDate) {
        this.gradDate = gradDate;
    }

    public String getGradUniv() {
        return gradUniv;
    }

    public void setGradUniv(String gradUniv) {
        this.gradUniv = gradUniv;
    }

    public String getEduBg() {
        return eduBg;
    }

    public void setEduBg(String eduBg) {
        this.eduBg = eduBg;
    }

    public String getStudyPeriod() {
        return studyPeriod;
    }

    public void setStudyPeriod(String studyPeriod) {
        this.studyPeriod = studyPeriod;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsedPosition() {
        return usedPosition;
    }

    public void setUsedPosition(String usedPosition) {
        this.usedPosition = usedPosition;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public double getUnitId() {
        return unitId;
    }

    public void setUnitId(double unitId) {
        this.unitId = unitId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPracticePositionRank() {
        return practicePositionRank;
    }

    public void setPracticePositionRank(String practicePositionRank) {
        this.practicePositionRank = practicePositionRank;
    }

    public double getPracticeSalaryStand() {
        return practiceSalaryStand;
    }

    public void setPracticeSalaryStand(double practiceSalaryStand) {
        this.practiceSalaryStand = practiceSalaryStand;
    }

    public String getGradPositionRank() {
        return gradPositionRank;
    }

    public void setGradPositionRank(String gradPositionRank) {
        this.gradPositionRank = gradPositionRank;
    }

    public double getGradSalaryStand() {
        return gradSalaryStand;
    }

    public void setGradSalaryStand(double gradSalaryStand) {
        this.gradSalaryStand = gradSalaryStand;
    }

    public Date getFillTabDate() {
        return fillTabDate;
    }

    public void setFillTabDate(Date fillTabDate) {
        this.fillTabDate = fillTabDate;
    }

    public String getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(String techLevel) {
        this.techLevel = techLevel;
    }

    public String getUnitOption() {
        return unitOption;
    }

    public void setUnitOption(String unitOption) {
        this.unitOption = unitOption;
    }

    public String getUnitSignature() {
        return unitSignature;
    }

    public void setUnitSignature(String unitSignature) {
        this.unitSignature = unitSignature;
    }

    public Date getUnitSignDate() {
        return unitSignDate;
    }

    public void setUnitSignDate(Date unitSignDate) {
        this.unitSignDate = unitSignDate;
    }

    public String getIsUnitApproved() {
        return isUnitApproved;
    }

    public void setIsUnitApproved(String isUnitApproved) {
        this.isUnitApproved = isUnitApproved;
    }

    public String getAuditOption() {
        return auditOption;
    }

    public void setAuditOption(String auditOption) {
        this.auditOption = auditOption;
    }

    public String getAuditSignature() {
        return auditSignature;
    }

    public void setAuditSignature(String auditSignature) {
        this.auditSignature = auditSignature;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getIsAuditDepApproved() {
        return isAuditDepApproved;
    }

    public void setIsAuditDepApproved(String isAuditDepApproved) {
        this.isAuditDepApproved = isAuditDepApproved;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }


    public String getActIviDefId() {
        return actIviDefId;
    }

    public void setActIviDefId(String actIviDefId) {
        this.actIviDefId = actIviDefId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}