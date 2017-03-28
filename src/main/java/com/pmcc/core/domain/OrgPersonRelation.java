package com.pmcc.core.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Admin on 2016/1/27.
 */
@Entity
@Table(name = "ORG_PERSON_RELATION")
@Cache(region = "com.pmcc.core.domain.OrgPersonRelation", usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrgPersonRelation implements Serializable {

    private String id;

    private Org org;

    private Person person;

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name ="OID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY )
    @JoinColumn(name="ORG_OID")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} , fetch = FetchType.LAZY)
    @JoinColumn(name="PERSON_OID")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
