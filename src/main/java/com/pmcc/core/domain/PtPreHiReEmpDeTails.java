package com.pmcc.core.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by H on 2016/2/24.
 */
@Entity
@Table(name = "PT_PREHIREEMP_DETAILS")
public class PtPreHiReEmpDeTails implements Serializable{

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")

    @Column(name = "PREHIREEMPRECID")
    private String oid;//ID

    @Column(name = "PREHIREEMPTABID")
    private Integer preHireEmpTabId;

    @Column(name ="EMPREQPLANID" )
    private Integer empReqPlanId;

    @Column(name = "EMPREQPLANITEMID")
    private Integer empReqPlanItemId;

    @Column(name = "ORGID")
    private Integer orgId;

    @Column(name = "ORGCODE")
    private String orgCode;

    @Column(name = "UNIT")
    private  String unit;//接收单位

    @Column(name = "BUSIGROUPID")
    private Integer buSiGroupId;

    @Column(name = "BUSIGROUPCODE")
    private  String buSiGroupCode;

    @Column(name = "BUSIGROUP")
    private String buSiGroup;

    @Column(name = "SORTNO")
    private Integer sorTno;

    @Column(name = "EMPSEQ")
    private  String empSeq;//人员流水号

    @Column(name = "EMPNAME")
    private String empName;//人员姓名

    @Column(name = "GENDER")
    private String genDer;//性别

    @Column(name = "SPECID")
    private String speCid;

    @Column(name = "SPECNAME")
    private String specName;

    @Column(name = "SPECSEQ")
    private String specSeq;

    @Column(name = "REALSPECID")
    private String realSpecId;

    @Column(name = "REALSPECNAME")
    private String realSpecName;//专业名称

    @Column(name = "REALSPECSEQ")
    private String realSpecSeq;

    @Column(name = "WORKDATE")
    private Date workDate;//参加工作时间

    @Column(name = "MARRY")
    private String marry;//婚姻状况

    @Column(name = "EDUBG")
    private String eduBg;//学历

    @Column(name = "GRADDATE")
    private Date gradDate;//毕业时间

    @Column(name = "GRADUNIV")
    private String gradUniv;//毕业院校

    @Column(name = "TRAINMETHOD")
    private String trainMethod;//培养方式

    @Column(name = "CONTRACTNO")
    private String contraction;

    @Column(name = "ISEMPLOYED")
    private String isEmployed;

    @Column(name = "ISAUDIT")
    private String isAudit;

    @Column(name = "BIRTHDATE")
    private Date birthDate;//出生日期

    @Column(name = "NATION")
    private String nation;//民族

    @Column(name = "PARTY")
    private String party;//政治面貌

    @Column(name = "SCHOOLYEARS")
    private String schoolYears;//年制

    @Column(name = "DEGREE")
    private String degree;//学位

    @Column(name = "POSITIONID")
    private Integer positionId;

    @Column(name = "POSICODE")
    private String posiCode;

    @Column(name = "POSINAME")
    private String posiName;//岗位名称

    @Column(name = "NPPROV")
    private String nppRov;//籍贯省

    @Column(name = "NPCITY")
    private String npCity;//籍贯市县

    @Column(name = "NPTOWN")
    private String npTown;//籍贯区乡

    @Column(name = "IDENTITYNO")
    private String idEntityNo;//身份证号

    @Column(name = "CURADDRPROV")
    private String cuRaDdRpRov;

    @Column(name = "CURADDRCITY")
    private String cuRaDdRCity;

    @Column(name = "CURADDRTOWN")
    private String cuRadDrTown;

    @Column(name = "CURADDRROOTNO")
    private String cuRadDrRootNo;//现住址

    @Column(name = "LINKTEL")
    private String linkTel;//联系电话

    @Column(name = "FATHERUNIT")
    private String fatherUnit;

    @Column(name = "FATHERDUTY")
    private String fatherDuty;

    @Column(name = "MOTHERUNIT")
    private String motherUnit;

    @Column(name = "MOTHERDUTY")
    private String motherDuty;

    @Column(name = "ISEMPCHILD")
    private String isEmpChild;//是否职工子女

    @Column(name = "GRADINSURANCE")
    private String gradInSuRance;

    @Column(name = "UNITOPINION")
    private String unitOpinion;//单位意见

    @Column(name = "UNITSIGNDATE")
    private Date unitSignDate;//单位审批时间

    @Column(name = "BUSIGROUPOPINION")
    private String buSiGroupOpinion;//主管部门意见

    @Column(name = "BUSIGROUPSIGNDATE")
    private Date buSiGroupSignDate;//主管部门审批时间

    @Column(name = "GROUPOPTION")
    private String groupOpinion;//集团公司审批意见

    @Column(name = "GROUPSIGNDATE")
    private Date groupSignDate;//集团公司审批时间

    @Column(name = "RPTPERSON")
    private String rptPerson;

    @Column(name = "RPTDATE")
    private Date rptDate;

    @Column(name = "UPDATEFLAG")
    private Integer upDateFlag;

    @Column(name = "AUDIT")
    private String auDit;

    @Column(name = "AUDITSIGNDATE")
    private Date auDitSignDate;

    @Column(name = "AUDITFLAG")
    private String auDitFlag;

    @Column(name = "REMARK")
    private String reMark;

    @Column(name = "ISBLACK")
    private String isBlack;

    @Column(name = "INGRUOPMETHOD")
    private String inGroupMethod;//进入集团方式

    @Column(name = "ENROLLLEVEL")
    private String enRollLevel;//录用级别

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getPreHireEmpTabId() {
        return preHireEmpTabId;
    }

    public void setPreHireEmpTabId(Integer preHireEmpTabId) {
        this.preHireEmpTabId = preHireEmpTabId;
    }

    public Integer getEmpReqPlanId() {
        return empReqPlanId;
    }

    public void setEmpReqPlanId(Integer empReqPlanId) {
        this.empReqPlanId = empReqPlanId;
    }

    public Integer getEmpReqPlanItemId() {
        return empReqPlanItemId;
    }

    public void setEmpReqPlanItemId(Integer empReqPlanItemId) {
        this.empReqPlanItemId = empReqPlanItemId;
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

    public Integer getBuSiGroupId() {
        return buSiGroupId;
    }

    public void setBuSiGroupId(Integer buSiGroupId) {
        this.buSiGroupId = buSiGroupId;
    }

    public String getBuSiGroupCode() {
        return buSiGroupCode;
    }

    public void setBuSiGroupCode(String buSiGroupCode) {
        this.buSiGroupCode = buSiGroupCode;
    }

    public String getBuSiGroup() {
        return buSiGroup;
    }

    public void setBuSiGroup(String buSiGroup) {
        this.buSiGroup = buSiGroup;
    }

    public Integer getSorTno() {
        return sorTno;
    }

    public void setSorTno(Integer sorTno) {
        this.sorTno = sorTno;
    }

    public String getEmpSeq() {
        return empSeq;
    }

    public void setEmpSeq(String empSeq) {
        this.empSeq = empSeq;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGenDer() {
        return genDer;
    }

    public void setGenDer(String genDer) {
        this.genDer = genDer;
    }

    public String getSpeCid() {
        return speCid;
    }

    public void setSpeCid(String speCid) {
        this.speCid = speCid;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecSeq() {
        return specSeq;
    }

    public void setSpecSeq(String specSeq) {
        this.specSeq = specSeq;
    }

    public String getRealSpecId() {
        return realSpecId;
    }

    public void setRealSpecId(String realSpecId) {
        this.realSpecId = realSpecId;
    }

    public String getRealSpecName() {
        return realSpecName;
    }

    public void setRealSpecName(String realSpecName) {
        this.realSpecName = realSpecName;
    }

    public String getRealSpecSeq() {
        return realSpecSeq;
    }

    public void setRealSpecSeq(String realSpecSeq) {
        this.realSpecSeq = realSpecSeq;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public String getEduBg() {
        return eduBg;
    }

    public void setEduBg(String eduBg) {
        this.eduBg = eduBg;
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

    public String getTrainMethod() {
        return trainMethod;
    }

    public void setTrainMethod(String trainMethod) {
        this.trainMethod = trainMethod;
    }

    public String getContraction() {
        return contraction;
    }

    public void setContraction(String contraction) {
        this.contraction = contraction;
    }

    public String getIsEmployed() {
        return isEmployed;
    }

    public void setIsEmployed(String isEmployed) {
        this.isEmployed = isEmployed;
    }

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
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

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getSchoolYears() {
        return schoolYears;
    }

    public void setSchoolYears(String schoolYears) {
        this.schoolYears = schoolYears;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPosiCode() {
        return posiCode;
    }

    public void setPosiCode(String posiCode) {
        this.posiCode = posiCode;
    }

    public String getPosiName() {
        return posiName;
    }

    public void setPosiName(String posiName) {
        this.posiName = posiName;
    }

    public String getNppRov() {
        return nppRov;
    }

    public void setNppRov(String nppRov) {
        this.nppRov = nppRov;
    }

    public String getNpCity() {
        return npCity;
    }

    public void setNpCity(String npCity) {
        this.npCity = npCity;
    }

    public String getNpTown() {
        return npTown;
    }

    public void setNpTown(String npTown) {
        this.npTown = npTown;
    }

    public String getIdEntityNo() {
        return idEntityNo;
    }

    public void setIdEntityNo(String idEntityNo) {
        this.idEntityNo = idEntityNo;
    }

    public String getCuRaDdRpRov() {
        return cuRaDdRpRov;
    }

    public void setCuRaDdRpRov(String cuRaDdRpRov) {
        this.cuRaDdRpRov = cuRaDdRpRov;
    }

    public String getCuRaDdRCity() {
        return cuRaDdRCity;
    }

    public void setCuRaDdRCity(String cuRaDdRCity) {
        this.cuRaDdRCity = cuRaDdRCity;
    }

    public String getCuRadDrTown() {
        return cuRadDrTown;
    }

    public void setCuRadDrTown(String cuRadDrTown) {
        this.cuRadDrTown = cuRadDrTown;
    }

    public String getCuRadDrRootNo() {
        return cuRadDrRootNo;
    }

    public void setCuRadDrRootNo(String cuRadDrRootNo) {
        this.cuRadDrRootNo = cuRadDrRootNo;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getFatherUnit() {
        return fatherUnit;
    }

    public void setFatherUnit(String fatherUnit) {
        this.fatherUnit = fatherUnit;
    }

    public String getFatherDuty() {
        return fatherDuty;
    }

    public void setFatherDuty(String fatherDuty) {
        this.fatherDuty = fatherDuty;
    }

    public String getMotherUnit() {
        return motherUnit;
    }

    public void setMotherUnit(String motherUnit) {
        this.motherUnit = motherUnit;
    }

    public String getMotherDuty() {
        return motherDuty;
    }

    public void setMotherDuty(String motherDuty) {
        this.motherDuty = motherDuty;
    }

    public String getIsEmpChild() {
        return isEmpChild;
    }

    public void setIsEmpChild(String isEmpChild) {
        this.isEmpChild = isEmpChild;
    }

    public String getGradInSuRance() {
        return gradInSuRance;
    }

    public void setGradInSuRance(String gradInSuRance) {
        this.gradInSuRance = gradInSuRance;
    }

    public String getUnitOpinion() {
        return unitOpinion;
    }

    public void setUnitOpinion(String unitOpinion) {
        this.unitOpinion = unitOpinion;
    }

    public Date getUnitSignDate() {
        return unitSignDate;
    }

    public void setUnitSignDate(Date unitSignDate) {
        this.unitSignDate = unitSignDate;
    }

    public String getBuSiGroupOpinion() {
        return buSiGroupOpinion;
    }

    public void setBuSiGroupOpinion(String buSiGroupOpinion) {
        this.buSiGroupOpinion = buSiGroupOpinion;
    }

    public Date getBuSiGroupSignDate() {
        return buSiGroupSignDate;
    }

    public void setBuSiGroupSignDate(Date buSiGroupSignDate) {
        this.buSiGroupSignDate = buSiGroupSignDate;
    }

    public String getGroupOpinion() {
        return groupOpinion;
    }

    public void setGroupOpinion(String groupOpinion) {
        this.groupOpinion = groupOpinion;
    }

    public Date getGroupSignDate() {
        return groupSignDate;
    }

    public void setGroupSignDate(Date groupSignDate) {
        this.groupSignDate = groupSignDate;
    }

    public String getRptPerson() {
        return rptPerson;
    }

    public void setRptPerson(String rptPerson) {
        this.rptPerson = rptPerson;
    }

    public Date getRptDate() {
        return rptDate;
    }

    public void setRptDate(Date rptDate) {
        this.rptDate = rptDate;
    }

    public Integer getUpDateFlag() {
        return upDateFlag;
    }

    public void setUpDateFlag(Integer upDateFlag) {
        this.upDateFlag = upDateFlag;
    }

    public String getAuDit() {
        return auDit;
    }

    public void setAuDit(String auDit) {
        this.auDit = auDit;
    }

    public Date getAuDitSignDate() {
        return auDitSignDate;
    }

    public void setAuDitSignDate(Date auDitSignDate) {
        this.auDitSignDate = auDitSignDate;
    }

    public String getAuDitFlag() {
        return auDitFlag;
    }

    public void setAuDitFlag(String auDitFlag) {
        this.auDitFlag = auDitFlag;
    }

    public String getReMark() {
        return reMark;
    }

    public void setReMark(String reMark) {
        this.reMark = reMark;
    }

    public String getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(String isBlack) {
        this.isBlack = isBlack;
    }

    public String getInGroupMethod() {
        return inGroupMethod;
    }

    public void setInGroupMethod(String inGroupMethod) {
        this.inGroupMethod = inGroupMethod;
    }

    public String getEnRollLevel() {
        return enRollLevel;
    }

    public void setEnRollLevel(String enRollLevel) {
        this.enRollLevel = enRollLevel;
    }
}
