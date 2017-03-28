package com.pmcc.core.web;

import com.pmcc.core.domain.BaseModuleInfo;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.manager.managerfacade.BaseModuleInfoManagerFacade;
import com.pmcc.core.manager.managerfacade.BaseOrgInfoManagerFacade;
import com.pmcc.utils.ModuleTreeNode;
import com.pmcc.utils.OrgMeduleTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pmcc.utils.ModuleTreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("baseModuleInfo")
public class BaseModuleInfoController {

    @Autowired
    BaseModuleInfoManagerFacade moduleInfoManager;
    @Autowired
    BaseOrgInfoManagerFacade orgInfoManager;

    /**
     * 根据人的id和组织机构获取菜单
     *
     * @param
     */
    @RequestMapping(value = "/getModuleList", method = RequestMethod.GET)
    @ResponseBody
    //   @JsonView(BaseView.OrgSummary.class)
    public Map<String, Object> getModelList() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("orgs", moduleInfoManager.getModuleList());
        return model;
    }

    /**
     * 获取菜单树
     */
    @RequestMapping(value = "/getModuleListForTreegrid", method = RequestMethod.GET)
    @ResponseBody
    public List<ModuleTreeNode> getModuleListForTreegrid(String moduleId, String type, String flag) {
        if (moduleId == null || "".equals(moduleId) || "00000000-0000-0000-0000-000000000000".equals(moduleId)) {
            moduleId = "00000000-0000-0000-0000-000000000000";
        }
        return moduleInfoManager.findByPid(moduleId, flag);
    }

    /*
       获得页面左侧菜单
     */
    @RequestMapping(value = "/queryUserModule", method = RequestMethod.GET)
    @ResponseBody
    public List<ModuleTreeNode> queryUserModule(String userId, String orgId, String moduleEname, String moduleId) {
        Map<String, Object> model = new HashMap<String, Object>();
        // StringBuffer sb = new StringBuffer();
        //如果moduleRname为空值，代表请求整个菜单权限
        List<BaseModuleInfo> list = new ArrayList<BaseModuleInfo>();
        List<ModuleTreeNode> list1 = new ArrayList<ModuleTreeNode>();
        if (moduleId == null || "".equals(moduleId) || "00000000-0000-0000-0000-000000000000".equals(moduleId)) {
            moduleId = "00000000-0000-0000-0000-000000000000";
        }
        // String s = "";
        list = moduleInfoManager.queryUserModule(userId, orgId);
        for (int i = 0; i < list.size(); i++) {
            if (moduleId.equals(list.get(i).getParentOid())) {
                ModuleTreeNode tn = new ModuleTreeNode();
                tn.setOid(list.get(i).getOid());
                tn.setId(list.get(i).getEname() + "|" + list.get(i).getPathHandler());
                tn.setCode(list.get(i).getCode());
                //tn.setText(b.getName());
                tn.setName(list.get(i).getName());
                tn.setParentOid(list.get(i).getParentOid());
                tn.setLT(list.get(i).getLT());
                tn.setRT(list.get(i).getRT());
                tn.setTreeLevel(list.get(i).getTreeLevel());
                tn.setSortCode(list.get(i).getSortCode());
                tn.setFlag(list.get(i).getFlag());
                tn.setIconCls("noIcon");
                tn.setPathHandler(list.get(i).getPathHandler());
                tn.setEname(list.get(i).getEname());
                tn.setUrl(list.get(i).getUrl());

                if (list.get(i).getIco() != null && !"".equals(list.get(i).getIco())) {
                    String ico = list.get(i).getIco();
                    String st = ico.substring(ico.lastIndexOf("|") + 1);
                    tn.setText("<i class=\"fa " + st + " \"></i> " + list.get(i).getName());
                } else {
                    tn.setText("" + list.get(i).getName() + "\"");
                }
                tn.setIco(list.get(i).getIco());
                tn.setDescription(list.get(i).getDescription());
                tn.setState(list.get(i).getState());
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(i).getOid().equals(list.get(j).getParentOid())) {
                        tn.setExpanded(false);
                        break;
                    }
                }
                if (tn.getExpanded() == null || tn.getExpanded() != false) {
                    tn.setLeaf(true);
                }
                list1.add(tn);
            }
        }
        return list1;
    }

    @RequestMapping(value = "/queryUserModuleButtons", method = RequestMethod.GET)
    @ResponseBody
    public List<BaseModuleInfo> queryUserModuleButtons(String userId, String orgId, String moduleEname) {
        //查询该组织下的菜单
        BaseOrgInfo orgInfo = orgInfoManager.getOrgInfo(orgId);
        List<BaseModuleInfo> li = new ArrayList<BaseModuleInfo>();
        List<BaseModuleInfo> list = moduleInfoManager.queryUserButtons(moduleEname);
        for (BaseModuleInfo baseModuleInfo : list) {
            if (baseModuleInfo.getOrgs().contains(orgInfo)) {
                li.add(baseModuleInfo);
            }
        }
        //请求某菜单下的按钮
        return li;
    }

    /**
     * 辅助queryUserModule
     *
     * @param b
     * @return
     */
    public StringBuffer getTreeJson(List<BaseModuleInfo> b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.size(); i++) {
            //最后一个
            if (i + 1 == b.size()) {
                sb.append(getCurrent(b.get(i), null));
            } else {
                sb.append(getCurrent(b.get(i), b.get(i + 1)));
            }
        }
        return sb;
    }

    public StringBuffer getTreeJsonNoIco(List<BaseModuleInfo> b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.size(); i++) {
            //最后一个
            if (i + 1 == b.size()) {
                sb.append(getCurrentNoIco(b.get(i), null));
            } else {
                sb.append(getCurrentNoIco(b.get(i), b.get(i + 1)));
            }
        }
        return sb;
    }

    public StringBuffer getCurrentNoIco(BaseModuleInfo current, BaseModuleInfo next) {
        StringBuffer s = new StringBuffer();
        s.append("{\"oid\":\"" + current.getOid() + "\",\"id\":\"" + current.getOid() + "\"");
        if (next == null) {

            s.append(",\"name\":\"" + current.getName() + "\",\"leaf\":true}]}");
        } else {
            //当前的是下一个的父级
            if (current.getOid().equals(next.getParentOid())) {
                s.append(",\"name\":\"" + current.getName() + "\",\"expanded\":false,\"children\":[");
            } else {
                if (current.getParentOid().equals(next.getParentOid())) {
                    s.append(",\"name\":\"" + current.getName() + "\",\"leaf\":true},");
                } else {
                    s.append(",\"name\":\"" + current.getName() + "\",\"leaf\":true}]},");
                }
            }
        }
        return s;
    }

    /**
     * 当前对象和下一个对象，返回当前对象的字符串
     *
     * @param current
     * @param next
     * @return
     */
    public StringBuffer getCurrent(BaseModuleInfo current, BaseModuleInfo next) {
        StringBuffer s = new StringBuffer();
        s.append("{\"text\":\"");
        if (current.getIco() != null && !"".equals(current.getIco())) {
            String ico = current.getIco();
            String st = ico.substring(ico.lastIndexOf("|") + 1);
            s.append("<i class=\\\"fa " + st + " \\\"></i> " + current.getName() + "\"");
        } else {
            s.append("" + current.getName() + "\"");
        }
        String path = "";
        if (current.getPathHandler() != null && !"".equals(current.getPathHandler())) {
            path = current.getPathHandler();
        }
        if (next == null) {

            s.append(",\"iconCls\":\"noIcon\",\"id\":\"" + current.getEname() + "|" + path + "\",\"leaf\":true}]}");
        } else {
            //当前的是下一个的父级
            if (current.getOid().equals(next.getParentOid())) {
                s.append(",\"iconCls\":\"noIcon\",\"id\":\"" + current.getEname() + "|" + path + "\",\"expanded\":false,\"children\":[");
            } else {
                if (current.getParentOid().equals(next.getParentOid())) {
                    s.append(",\"iconCls\":\"noIcon\",\"id\":\"" + current.getEname() + "|" + path + "\",\"leaf\":true},");
                } else {
                    s.append(",\"iconCls\":\"noIcon\",\"id\":\"" + current.getEname() + "|" + path + "\",\"leaf\":true}]},");
                }
            }
        }
        return s;
    }

    @RequestMapping(value = "/getModulesList", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getModulesList(String PID) {
        Map<String, Object> model = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        List<BaseModuleInfo> list = new ArrayList<BaseModuleInfo>();
        String s = "";
        list = moduleInfoManager.queryModuleByPid(PID);
        sb.append("{\"text\":\"root\",\"children\":[");
        sb.append(getTreeJsonNoIco(list));
        s = sb.toString();
        return s;
    }

    /**
     * 完全展开异步树
     *
     * @return
     */
    @RequestMapping(value = "/getAllModulesForTree", method = RequestMethod.GET)
    @ResponseBody
    public ModuleTreeNode getAllModulesForTree() {
        return moduleInfoManager.queryAllModule();
    }

    /**
     * 组织菜单版完全展开异步树
     *
     * @return
     */
    @RequestMapping(value = "/getOrgModulesList", method = RequestMethod.GET)
    @ResponseBody
    public OrgMeduleTreeNode getOrgModulesList(String orgId) {
        return moduleInfoManager.getOrgModulesList(orgId);
    }

    /**
     * 根据菜单id找到对应的权限
     *
     * @param oid
     * @return
     */
    @RequestMapping(value = "/getModulePermissionByOid", method = RequestMethod.GET)
    @ResponseBody
    public List<BasePermissionInfo> getModulePermissionByOid(String oid) {
        List<BasePermissionInfo> list = moduleInfoManager.getModulePermissionByOid(oid);
        return list;
    }

    public String getCurrentJson(BaseModuleInfo b) {
        String s = "{\"oid\":\"" + b.getOid() + "\",\"code\":\"" + b.getCode() + "\",\"text\":";
        if (b.getIco() != null && !"".equals(b.getIco())) {
            String ico = b.getIco();
            String st = ico.substring(ico.lastIndexOf("|") + 1);
            s = s + "\"<i class=\\\"fa " + st + " \\\"></i> " + b.getName() + "\"";
        } else {
            s = s + "\"" + b.getName() + "\"";
        }
        s = s + ",\"name\":\"" + b.getName() + "\",\"ename\":\"" + b.getEname() + "\",\"url\":\"" + b.getUrl() + "\",\"ico\":\"" + b.getIco() + "\",\"pathHandler\":"
                + "\"" + b.getPathHandler() + "\",\"description\":\"" + b.getDescription() + "\",\"LT\":" + b.getLT() + ",\"RT\":" + b.getRT() + ",\"treeLevel\":" + b.getTreeLevel() + ","
                + "\"sortCode\":" + b.getSortCode() + ",\"state\":" + b.getState() + ",\"flag\":" + b.getFlag() + ",\"buttonId\":\"" + b.getButtonId() + "\",\"createTime\":\"" + b.getCreateTime() + "\","
                + "\"parentOid\":\"" + b.getParentOid() + "\",\"check\":false,\"iconCls\":\"noIcon\",";
        return s;
    }

    /**
     * 验证英文名称重复
     */
    @RequestMapping(value = "/getIsExist", method = RequestMethod.GET)
    @ResponseBody
    public Boolean getIsExist(String oid, String col, String val) {
        Boolean isExist = false;
        if (col.equals("ename")) {
            isExist = moduleInfoManager.isExist(oid, val);
        }
        return isExist;
    }

    /**
     * 保存
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/postSave", method = RequestMethod.POST)
    @ResponseBody
    public Boolean postSave(BaseModuleInfo moduleInfo) {
        Boolean isExist = false;
        isExist = moduleInfoManager.save(moduleInfo);
        return isExist;
    }

    @RequestMapping(value = "/updateRelaModuleOrg", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateRelaModuleOrg(String orgId, String moduleIds) {
        Boolean isExist = false;
        isExist = moduleInfoManager.updateRelaModuleOrg(orgId, moduleIds);
        return isExist;
    }

    @RequestMapping(value = "/moduleTreeMove", method = RequestMethod.GET)
    @ResponseBody
    public Boolean moduleTreeMove(String id, String moveType) {
        Boolean isExist = false;
        isExist = moduleInfoManager.moduleTreeMove(id, moveType);
        return isExist;
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteById(String PostData) {
        return moduleInfoManager.deleteById(PostData);
    }

    @RequestMapping(value = "/modulePermissionSave", method = RequestMethod.POST)
    @ResponseBody
    public Boolean modulePermissionSave(String oid, String permissionsId, String type) {
        //批量增加和批量刪除
        Boolean isExist = false;
        isExist = moduleInfoManager.saveOrUpdatePermission(oid, permissionsId, type);
        return isExist;
    }


}
