Ext.define('ExtFrame.view.main.sys.roleManager.RoleManagerController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.roleManager',
    
    onClickButtonLook: function () {
        Tools.GridSearchToolbar.LookEncap(this);
    },
    onClickButtonAdd: function () {
        Tools.GridSearchToolbar.AddEncap(this);
    },
    onClickButtonEdit: function () {
        Tools.GridSearchToolbar.EditEncap(this);
    },
    onClickButtonSave: function () {
        Tools.GridSearchToolbar.SaveEncap(this, Tools.Method.getAPiRootPath()+'/roleInfo/postSave.do');
    },
    onClickButtonDel: function () {
        Tools.GridSearchToolbar.DeleteByOIDEncap(this, Tools.Method.getAPiRootPath()+'/roleInfo/deleteById.do');
    },
    onClickSearch: function () {
        Tools.GridSearchToolbar.SearchEncap(this);
    },
    //分配权限
    onClickForPermission: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView().ownerCt.down("#RoleManagerGrid");
        var records = new Array();//角色OID
        var nameList = new Array();
        var permissionList = new Array();//权限列表 --用来加载原角色对应的权限OID
        var selectRows = view.getSelectionModel().getSelection();//获取grid选中行
        //至少选择一项数据
        if (Tools.Method.IsDelData(selectRows)) {
            $.each(selectRows, function (index, row) {                
                $.each(row.data.permissions, function (index, permissrow) {
                     permissionList.push(permissrow.oid);
                })
                var hh = { oid: row.data.oid, permissionsId: permissionList };
                records.push(hh);//构造的对象
                nameList.push(row.data.name);
            });
            if (Ext.encode(nameList.length) > 15)
            {
                nameList = nameList.toString().substring(0, 15) + "..";
            }
            var aaa = Ext.create('ExtFrame.view.main.sys.roleManager.RolePermissionSet', {
                    Operation:'add',
                    selectOIDs: records,
                    displayName: nameList,
                    controller: 'roleManager'
            });
        }
    },
    //移除权限
    onClickRemovePermission: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView().ownerCt.down("#RoleManagerGrid");
        var records = new Array();//角色OID
        var nameList = new Array();
        var permissionList = new Array();//权限列表 --用来加载原角色对应的权限OID
        var selectRows = view.getSelectionModel().getSelection();//获取grid选中行
        //至少选择一项数据
        if (Tools.Method.IsDelData(selectRows)) {
            $.each(selectRows, function (index, row) {
                $.each(row.data.permissions, function (index, permissrow) {
                    permissionList.push(permissrow.oid);
                })
                var hh = { oid: row.data.oid, permissionsId: permissionList };
                records.push(hh);//构造的对象
                nameList.push(row.data.name);
            });
            if (Ext.encode(nameList.length) > 15) {
                nameList = nameList.toString().substring(0, 15) + "..";
            }
            var aaa = Ext.create('ExtFrame.view.main.sys.roleManager.RolePermissionSet', {
                Operation: 'remove',
                selectOIDs: records,
                displayName: nameList,
                controller: 'roleManager'
            });
        }
    },
    onSavePermission: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var checkboxValue = view.down("#lblName").getChecked();//获取选中的checkbox的值
        if (checkboxValue != "") {
            var selectRow = view.down("#lblName").selectOIDs;//选中的grid中的记录
            var checkValue = new Array();//选中的权限
            var  ids="";
            var pids="";
            var selectOIDs = view.selectOIDs;//得到构造的对象[{OID:'',Permission:[]}]
            $.each(selectOIDs, function (index, row) {
                ids+=row.oid+",";
            });
            $.each(checkboxValue, function (i, item) {
                pids+=item.inputValue+",";
            });
            ids = ids.substr(0, ids.length - 1);
            pids = pids.substr(0, pids.length - 1);
            var data={oid:ids,permissionsId:pids,type:view.Operation};
          //  var data = Tools.Method.GetPostData(Ext.encode(selectOIDs));
            Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + "/roleInfo/rolePermissionSave.do", 'POST',data, true, function (jsonData) {
                if (jsonData) {
                    Tools.Method.ShowTipsMsg(Tools.Msg.MSG0006, '4000', '1', null);
                    Ext.getCmp("RoleManagerGrid").store.reload();
                    view.close();
                } else {
                    Tools.Method.ShowTipsMsg(Tools.Msg.MSG0022, '4000', '2', null);//修改失败
                }
            });
        }
        else {
            Tools.Method.ShowTipsMsg("请选择要配置的权限信息!", '4000', '1', null);
        }
        
    }
});