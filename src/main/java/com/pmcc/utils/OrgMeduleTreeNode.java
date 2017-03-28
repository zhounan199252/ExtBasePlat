package com.pmcc.utils;

import com.pmcc.core.domain.BtnsUtil;

import java.util.List;

/**
 * 组织菜单树
 * Created by yaonan on 2016/1/4.
 */
public class OrgMeduleTreeNode{
    private String text;
    private String oid;
    private String id;
    private String name;
    private Boolean checked;
    private List<OrgMeduleTreeNode> children;
    private List<BtnsUtil> toolbarBtns;
    private List<BtnsUtil> operationBtns;
    private List<BtnsUtil> pageBtns;
    private List<BtnsUtil> actions;
    private Boolean expanded;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<OrgMeduleTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<OrgMeduleTreeNode> children) {
        this.children = children;
    }

    public List<BtnsUtil> getToolbarBtns() {
        return toolbarBtns;
    }

    public void setToolbarBtns(List<BtnsUtil> toolbarBtns) {
        this.toolbarBtns = toolbarBtns;
    }

    public List<BtnsUtil> getOperationBtns() {
        return operationBtns;
    }

    public void setOperationBtns(List<BtnsUtil> operationBtns) {
        this.operationBtns = operationBtns;
    }

    public List<BtnsUtil> getPageBtns() {
        return pageBtns;
    }

    public void setPageBtns(List<BtnsUtil> pageBtns) {
        this.pageBtns = pageBtns;
    }

    public List<BtnsUtil> getActions() {
        return actions;
    }

    public void setActions(List<BtnsUtil> actions) {
        this.actions = actions;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }
}
