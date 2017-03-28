package com.pmcc.core.domain;

import com.lowagie.text.pdf.PRIndirectReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 父母关系
 * Created by Haoco on 2016/2/29.
 */
@Entity
@Table(name = "PT_PREHIREEMPFAMMEM")
public class PtPreHireEmpFamMem implements Serializable{
    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "FAMMEMID")
    private String oid;

    @Column(name = "PREHIREEMPRECID")
    private Integer preHireEmpReCid;//录入人员id

    @Column(name = "EMPID")
    private Integer empId;

    @Column(name = "SORTNO")
    private Integer sortNo;

    @Column(name = "TITLE")
    private String title;//父/母

    @Column(name = "MEMNAME")
    private String memName;//父母名字

    @Column(name = "AGE")
    private Integer age;//年龄

    @Column(name = "BIRTHDATE")
    private Date birthDate;

    @Column(name = "WORKORG")
    private String workOrg;//工作单位

    @Column(name = "DUTY")
    private String duty;//职位

    @Column(name = "RELATION")
    private String relation;

    @Column(name = "PARTY")
    private String party;

    @Column(name = "FAMMEMEMPID")
    private Integer famMemEmpId;

    @Column (name = "REMARK")
    private  String remark;//备注

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getPreHireEmpReCid() {
        return preHireEmpReCid;
    }

    public void setPreHireEmpReCid(Integer preHireEmpReCid) {
        this.preHireEmpReCid = preHireEmpReCid;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getWorkOrg() {
        return workOrg;
    }

    public void setWorkOrg(String workOrg) {
        this.workOrg = workOrg;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Integer getFamMemEmpId() {
        return famMemEmpId;
    }

    public void setFamMemEmpId(Integer famMemEmpId) {
        this.famMemEmpId = famMemEmpId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
