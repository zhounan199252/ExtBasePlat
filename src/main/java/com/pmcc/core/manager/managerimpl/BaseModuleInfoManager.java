package com.pmcc.core.manager.managerimpl;

import com.pmcc.core.dao.daofacade.BaseModuleInfoDaoFacade;
import com.pmcc.core.dao.daofacade.BasePermissionInfoDaoFacade;
import com.pmcc.core.domain.BaseModuleInfo;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.BasePermissionInfo;
import com.pmcc.core.domain.BtnsUtil;
import com.pmcc.core.manager.managerfacade.BaseModuleInfoManagerFacade;
import com.pmcc.utils.ModuleTreeNode;
import com.pmcc.utils.OrgMeduleTreeNode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Repository("moduleInfoManager")
public class BaseModuleInfoManager implements BaseModuleInfoManagerFacade{

    @Resource
    private BaseModuleInfoDaoFacade baseModuleInfoDao;
    @Resource
    private BasePermissionInfoDaoFacade basePermissionInfoDao;
    @Override
    public List<BaseModuleInfo> queryUserModule(String userId, String orgId) {
        return baseModuleInfoDao.queryUserModule(userId,orgId);
    }

    @Override
    public List<BaseModuleInfo> queryUserButtons(String moduleEname) {
        return baseModuleInfoDao.queryUserButtons(moduleEname);
    }

    @Override
    public List<BaseModuleInfo> queryModuleByPid(String pid) {
        return baseModuleInfoDao.queryModuleByPid(pid);
    }


    @Override
    public ModuleTreeNode queryAllModule() {
        List<BaseModuleInfo> list=new ArrayList<BaseModuleInfo>();
        List<ModuleTreeNode> listTree1=new ArrayList<ModuleTreeNode>();
        ModuleTreeNode tn=new ModuleTreeNode();
        tn.setText("root");
        List<BaseModuleInfo> list1=baseModuleInfoDao.queryModule(1,null);
        tn.setChildren(getList(list1,1));
        return tn;
    }
    @Override
    public OrgMeduleTreeNode getOrgModulesList(String orgId) {
        List<BaseModuleInfo> list=new ArrayList<BaseModuleInfo>();
        List<OrgMeduleTreeNode> listTree1=new ArrayList<OrgMeduleTreeNode>();
        OrgMeduleTreeNode tn=new OrgMeduleTreeNode();
        tn.setText("root");
        List<BaseModuleInfo> list1=baseModuleInfoDao.queryModule(1,null);
        tn.setChildren(getOrgMoList(list1, 1,orgId));
        return tn;
    }

    @Override
    public Boolean updateRelaModuleOrg(String orgId, String moduleIds) {
       return  baseModuleInfoDao.updateRelaModuleOrg(orgId,moduleIds);
    }

    public List<OrgMeduleTreeNode> getOrgMoList(List<BaseModuleInfo> list,int treelevel,String orgId){
        List<OrgMeduleTreeNode> listnode=new ArrayList<OrgMeduleTreeNode>();
        for(BaseModuleInfo info:list){
            OrgMeduleTreeNode treeNode=covertOrgModule(info, treelevel,orgId);
            if(treeNode !=null){
                listnode.add(treeNode);
            }
        }
        return  listnode;
    }
    public OrgMeduleTreeNode covertOrgModule(BaseModuleInfo b,int treeLevel,String orgId){
        if(b.getFlag()==0){
            List<OrgMeduleTreeNode> list=new ArrayList<OrgMeduleTreeNode>();
            OrgMeduleTreeNode tn=new OrgMeduleTreeNode();
            tn.setOid(b.getOid());
            tn.setId(b.getOid());
            tn.setText(b.getName());
            tn.setName(b.getName());
            if(baseModuleInfoDao.isOrgRelaModule(b.getOid(),orgId)){
                tn.setChecked(true);
            }else {
                tn.setChecked(false);
            }
            List<BaseModuleInfo> list3=baseModuleInfoDao.queryModule(treeLevel+1,b.getOid());
            if(list3 !=null && list3.size()>0){
                tn.setExpanded(true);
                tn=getBtns(list3,tn,orgId);
                tn.setChildren(getOrgMoList(list3, b.getTreeLevel() + 1, orgId));
            }
            return tn;
        }else{
            return  null;
        }
    }

    private OrgMeduleTreeNode getBtns(List<BaseModuleInfo> list,OrgMeduleTreeNode tn,String orgId) {
       List<BtnsUtil> b1=new ArrayList<BtnsUtil>();
       List<BtnsUtil> b2=new ArrayList<BtnsUtil>();
       List<BtnsUtil> b3=new ArrayList<BtnsUtil>();
       List<BtnsUtil> b4=new ArrayList<BtnsUtil>();
        for(BaseModuleInfo info:list){
            if(info.getFlag()==1){
                b1.add(new BtnsUtil(baseModuleInfoDao.isOrgRelaModule(info.getOid(), orgId),info.getOid(),info.getName()));
            }else if(info.getFlag()==2){
                b2.add(new BtnsUtil(baseModuleInfoDao.isOrgRelaModule(info.getOid(), orgId),info.getOid(),info.getName()));
            }else if(info.getFlag()==3){
                b3.add(new BtnsUtil(baseModuleInfoDao.isOrgRelaModule(info.getOid(), orgId),info.getOid(),info.getName()));
            }else if (info.getFlag()==4){
                b4.add(new BtnsUtil(baseModuleInfoDao.isOrgRelaModule(info.getOid(), orgId),info.getOid(),info.getName()));
            }
        }
        tn.setToolbarBtns(b1);
        tn.setActions(b2);
        tn.setOperationBtns(b3);
        tn.setPageBtns(b4);
        return  tn;
    }

    @Override
    public List<BasePermissionInfo> getModulePermissionByOid(String oid) {
        return baseModuleInfoDao.getModulePermissionByOid(oid);
    }

    @Override
    public Boolean isExist(String oid, String val) {
        //根据用户名查询
        BaseModuleInfo b=baseModuleInfoDao.findByEname(val);
        if(b !=null){
            if("".equals(oid) || oid==null){
                return true;
            }else if(b.getOid().equals(oid)){
                return  false;
            }else{
                return  true;
            }
        }else{
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean save(BaseModuleInfo moduleInfo) {
        moduleInfo.setCreateTime(new Date());
        return baseModuleInfoDao.saveOrUpdate(moduleInfo);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> deleteById(String postData) {
        //分割postData
        String[] id=postData.split(",");
        int succount=0;
        int errorcount=0;
        for(int i=0;i<id.length;i++){
            if(baseModuleInfoDao.deleteById(id[i])){
                succount++;
            }else {
                errorcount++;
            };
        }
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("result",true);
        res.put("message","处理成功！");
        res.put("succount",succount);
        res.put("errorcount",errorcount);
        return res;
    }

    @Override
    public Boolean saveOrUpdatePermission(String oid, String permissionsId, String type) {
        //把oid分組
        String[] id=oid.split(",");
        String[] pid=permissionsId.split(",");
        List<BasePermissionInfo> listCurrent=new ArrayList<BasePermissionInfo>();
        List<BasePermissionInfo> list=new ArrayList<BasePermissionInfo>();
        //根據權限id查詢權限的對象信息
        for(int j=0;j<pid.length;j++){
            BasePermissionInfo basePermissionInfo=	basePermissionInfoDao.find(pid[j]);
            list.add(basePermissionInfo);
        }
        for(int i=0;i<id.length;i++){
            //根據id查出當前的最新的角色信息
            BaseModuleInfo baseModuleInfo=baseModuleInfoDao.find(id[i]);
            listCurrent=baseModuleInfo.getPermissions();
            if(type.equals("add")){
                for(int m=0;m<list.size();m++){
                    if(!listCurrent.contains(list.get(m))){
                        listCurrent.add(list.get(m));
                    }
                }
            }else{
                for(int m=0;m<list.size();m++){
                    if(listCurrent.contains(list.get(m))){
                        listCurrent.remove(list.get(m));
                    }
                }
            }
            baseModuleInfo.setPermissions(listCurrent);
            baseModuleInfoDao.saveOrUpdatePermission(baseModuleInfo);
        }
        return true;
    }



    @Override
    public Boolean moduleTreeMove(String oid, String moveType) {
        return baseModuleInfoDao.moduleTreeMove(oid, moveType);
    }

    public List<ModuleTreeNode> getList(List<BaseModuleInfo> list, int treelevel){
        List<ModuleTreeNode> listnode=new ArrayList<ModuleTreeNode>();
        for(BaseModuleInfo info:list){
         listnode.add(covert(info,treelevel));
        }
        return  listnode;
    }
    public ModuleTreeNode covert(BaseModuleInfo b, int treeLevel){
        List<ModuleTreeNode> list=new ArrayList<ModuleTreeNode>();
            ModuleTreeNode tn=new ModuleTreeNode();
            tn.setOid(b.getOid());
            tn.setCode(b.getCode());
            if(b.getIco() !=null && !"".equals(b.getIco())){
                String ico = b.getIco();
                String st = ico.substring(ico.lastIndexOf("|") + 1);
                String name="<i class=\"fa "+st+"\"></i> "+b.getName()+" ";
                tn.setText(name);
            }else{
                tn.setText(b.getName());
            }
            tn.setName(b.getName());
            tn.setEname(b.getEname());
            tn.setUrl(b.getUrl());
            tn.setIco(b.getIco());
            tn.setPathHandler(b.getPathHandler());
            tn.setDescription(b.getDescription());
            tn.setLT(b.getLT());
            tn.setRT(b.getRT());
            tn.setTreeLevel(b.getTreeLevel());
            tn.setSortCode(b.getSortCode());
            tn.setState(b.getState());
            tn.setFlag(b.getFlag());
            tn.setButtonId(b.getButtonId());
            tn.setParentOid(b.getParentOid());
            tn.setPermissions(b.getPermissions());
            tn.setChecked(false);
            List<BaseModuleInfo> list3=baseModuleInfoDao.queryModule(treeLevel+1,b.getOid());
            if(list3 !=null && list3.size()>0){
                if(tn.getTreeLevel()<2){
                    tn.setExpanded(true);
                }else{
                    tn.setExpanded(false);
                }
                tn.setChildren(getList(list3,tn.getTreeLevel()+1));
           }else{
                tn.setLeaf(true);
            }
        return tn;
    }
    @Override
    public List<BaseModuleInfo> getModuleList() {
//        for(BaseOrgInfo orgInfo:baseOrgInfoDao.getOrgList()){
//            orgInfo.setRoles(null);
//            orgInfo.setModules(null);
//        }
        return    baseModuleInfoDao.getModuleList();
    }
    @Override
    public List<ModuleTreeNode> findByPid(String orgId,String flag) {
        List<ModuleTreeNode> listTree1=new ArrayList<ModuleTreeNode>();
        List<BaseModuleInfo> list1=new ArrayList<BaseModuleInfo>();
        //根节点树
        if(orgId.equals("00000000-0000-0000-0000-000000000000")||orgId.equals("root")){
            list1=baseModuleInfoDao.query(1, null,flag);
        }else{
            //查出当前节点的对象
            BaseModuleInfo baseModuleInfo=baseModuleInfoDao.find(orgId);
            list1=baseModuleInfoDao.query(baseModuleInfo.getTreeLevel()+1, orgId,flag);
        }
        if(list1 !=null && list1.size()>0){
            for(BaseModuleInfo b:list1){
                ModuleTreeNode tn=new ModuleTreeNode();
                tn.setOid(b.getOid());
                tn.setId(b.getEname() + "|" + b.getPathHandler());
                tn.setCode(b.getCode());
                //tn.setText(b.getName());
                tn.setName(b.getName());
                tn.setParentOid(b.getParentOid());
                tn.setLT(b.getLT());
                tn.setRT(b.getRT());
                tn.setTreeLevel(b.getTreeLevel());
                tn.setSortCode(b.getSortCode());
                tn.setFlag(b.getFlag());
                tn.setPathHandler(b.getPathHandler());
                tn.setEname(b.getEname());
                tn.setIconCls("noIcon");
                tn.setUrl(b.getUrl());

                if (b.getIco() != null && !"".equals(b.getIco())) {
                    String ico = b.getIco();
                    String st = ico.substring(ico.lastIndexOf("|") + 1);
                    tn.setText("<i class=\"fa " + st + " \"></i> " + b.getName());
                } else {
                    tn.setText("" + b.getName() + "\"");
                }
                tn.setIco(b.getIco());
                tn.setDescription(b.getDescription());
                tn.setState(b.getState());
                List<BaseModuleInfo> li=baseModuleInfoDao.query(b.getTreeLevel()+1,b.getOid(),flag);
                if(li!=null && li.size()>0){
                    tn.setExpanded(false);
                }else{
                    tn.setLeaf(true);
                }
                listTree1.add(tn);
            }
            return listTree1;

        }else{
            return null;
        }
    }
}
