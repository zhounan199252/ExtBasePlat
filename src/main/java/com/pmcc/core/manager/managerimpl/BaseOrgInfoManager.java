package com.pmcc.core.manager.managerimpl;

import com.pmcc.core.dao.daofacade.BaseOrgInfoDaoFacade;
import com.pmcc.core.domain.BaseModuleInfo;
import com.pmcc.core.domain.BaseOrgInfo;
import com.pmcc.core.domain.BaseRoleInfo;
import com.pmcc.core.manager.managerfacade.BaseOrgInfoManagerFacade;
import com.pmcc.utils.OrgTreeNode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Repository("orgInfoManager")
public class BaseOrgInfoManager implements BaseOrgInfoManagerFacade{

    @Resource
    private BaseOrgInfoDaoFacade baseOrgInfoDao;


    @Override
    public List<BaseOrgInfo> getOrgList() {
//        for(BaseOrgInfo orgInfo:baseOrgInfoDao.getOrgList()){
//            orgInfo.setRoles(null);
//            orgInfo.setModules(null);
//        }
        return    baseOrgInfoDao.getOrgList();
    }

    @Override
    public List<OrgTreeNode> findByPid(String orgId) {
        List<OrgTreeNode> listTree1=new ArrayList<OrgTreeNode>();
        List<BaseOrgInfo> list1=new ArrayList<BaseOrgInfo>();
        //根节点树
        if(orgId.equals("00000000-0000-0000-0000-000000000000")||orgId.equals("root")){
           list1=baseOrgInfoDao.query(1, null);
        }else{
            //查出当前节点的对象
            BaseOrgInfo baseOrgInfo=baseOrgInfoDao.find(orgId);
            list1=baseOrgInfoDao.query(baseOrgInfo.getTreeLevel()+1, orgId);
        }
        if(list1 !=null && list1.size()>0){
            for(BaseOrgInfo b:list1){
                OrgTreeNode tn=new OrgTreeNode();
                tn.setOid(b.getOid());
                tn.setId(b.getOid());
                tn.setCode(b.getCode());
                tn.setText(b.getName());
                tn.setName(b.getName());
                tn.setParentOid(b.getParentOid());
                tn.setLT(b.getLT());
                tn.setRT(b.getRT());
                tn.setTreeLevel(b.getTreeLevel());
                tn.setOrgLevel(b.getOrgLevel());
                tn.setOrgNo(b.getOrgNo());
                tn.setEnglishName(b.getEnglishName());
                tn.setSortCode(b.getSortCode());
                tn.setShortName(b.getShortName());
                tn.setDescription(b.getDescription());
                tn.setState(b.getState());
                List<BaseOrgInfo> li=baseOrgInfoDao.query(b.getTreeLevel()+1,b.getOid());
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

    /**
     * 保存
     * @param orgInfo
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean save(BaseOrgInfo orgInfo) {
        orgInfo.setCreateTime(new Date());
        return baseOrgInfoDao.saveOrUpdate(orgInfo);
    }

    @Override
    public Boolean orgTreeMove(String id, String moveType) {
        return baseOrgInfoDao.orgTreeMove(id, moveType);
    }

    @Override
    public Map<String, Object> deleteById(String postData) {
        //分割postData
        String[] id=postData.split(",");
        int succount=0;
        int errorcount=0;
        for(int i=0;i<id.length;i++){
            if(baseOrgInfoDao.deleteById(id[i])){
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
    public BaseOrgInfo getOrg(String orgId) {

        BaseOrgInfo baseOrgInfo= baseOrgInfoDao.find(orgId);
       // baseOrgInfo.setRoles(null);
        for(BaseRoleInfo roleInfo:baseOrgInfo.getRoles()){
            roleInfo.setPermissions(null);
            roleInfo.setOrgs(null);
        }
        baseOrgInfo.setModules(null);
        return  baseOrgInfo;
    }
    @Override
    public BaseOrgInfo getOrgInfo(String orgId) {
        BaseOrgInfo baseOrgInfo= baseOrgInfoDao.getOrgInfo(orgId);
        return  baseOrgInfo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean orgRoleSave(String orgs, String roles) {
        return baseOrgInfoDao.orgRoleSave(orgs, roles);
    }

    /**
     * 递归方法父类调用，传入当前集合，和节点等级，返回树集合
     * @param list
     * @param treelevel
     * @return
     */
    public List<OrgTreeNode> getList(List<BaseOrgInfo> list,int treelevel,String  type){
        List<OrgTreeNode> listnode=new ArrayList<OrgTreeNode>();
        for(BaseOrgInfo info:list){
            listnode.add(covert(info,treelevel,type));
        }
        return  listnode;
    }

    /**
     * 递归方法子类调用，传入当前对象和节点等级，返回树对象
     * @param b
     * @param treeLevel
     * @return
     */
    public OrgTreeNode covert(BaseOrgInfo b,int treeLevel,String type){
        List<OrgTreeNode> list=new ArrayList<OrgTreeNode>();
        OrgTreeNode tn=new OrgTreeNode();
        tn.setOid(b.getOid());
        tn.setId(b.getOid());
        tn.setCode(b.getCode());
        tn.setText(b.getName());
        tn.setName(b.getName());
        tn.setParentOid(b.getParentOid());
        tn.setLT(b.getLT());
        tn.setRT(b.getRT());
        tn.setTreeLevel(b.getTreeLevel());
        tn.setOrgLevel(b.getOrgLevel());
        tn.setOrgNo(b.getOrgNo());
        tn.setEnglishName(b.getEnglishName());
        tn.setSortCode(b.getSortCode());
        tn.setShortName(b.getShortName());
        tn.setDescription(b.getDescription());
        tn.setState(b.getState());
        if(!"false".equals(type)){
            tn.setChecked(false);
        }
        List<BaseOrgInfo> list3=baseOrgInfoDao.query(treeLevel + 1, b.getOid());
        if(list3 !=null && list3.size()>0){
            if(tn.getTreeLevel()<2){
                tn.setExpanded(true);
            }else{
                tn.setExpanded(false);
            }
            tn.setChildren(getList(list3,tn.getTreeLevel()+1,type));
        }else{
            tn.setLeaf(true);
        }
        return tn;
    }
}
