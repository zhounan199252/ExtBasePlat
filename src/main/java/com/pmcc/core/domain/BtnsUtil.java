package com.pmcc.core.domain;

/**
 * 按钮工具类
 * Created by yaonan on 2015/12/18.
 */
public class BtnsUtil {
    private Boolean checked;
    private String oid;
    private String name;

    public Boolean getChecked() {
        return checked;
    }

    public BtnsUtil() {
    }

    public BtnsUtil(Boolean checked, String oid, String name) {
        this.checked = checked;
        this.oid = oid;
        this.name = name;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
