Ext.define('ExtFrame.view.main.sys.moduleManager.ModuleManagerController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.moduleManager',

    onClickButtonLook: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var pnGrid = view.down('#' + view.ename + 'Grid');//获取当前grid控件
        var selectRecords =pnGrid.selModel.selected.items;//获取grid选中行records
        //根据oid取出module已配置的权限
        var oid = selectRecords[0].getData().oid;
        var permission = null;
        Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/baseModuleInfo/getModulePermissionByOid.do?oid=' + oid, 'GET', null, false, function (jsonData) {
            permission = jsonData;
        })
        selectRecords[0].data.permissions = permission;
        view.down('#ModuleManagerForm').down('#ename').oid = oid;
        //仅能选择一项数据
        if (Tools.Method.IsEditData(selectRecords)) {
            view.down('#' + view.ename + 'Form').getForm().loadRecord(selectRecords[0]);
        }
    },
    onClickButtonAdd: function () {
        Tools.GridSearchToolbar.AddEncap(this);
    },
    onClickButtonEdit: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        view.down('#' + view.ename + 'Form').getForm().reset();//表单清空
        var pnGrid = view.down('#' + view.ename + 'Grid');//获取当前grid控件
        var selectRecords = pnGrid.selModel.selected.items;//获取grid选中行record
        //根据oid取出module已配置的权限
        var oid = selectRecords[0].getData().oid;
        var permission = null;
        Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/baseModuleInfo/getModulePermissionByOid.do?oid=' + oid, 'GET', null, false, function (jsonData) {
            permission = jsonData;
        })
        selectRecords[0].data.permissions = permission;
        view.down('#ModuleManagerForm').down('#ename').oid = oid;
        //仅能选择一项数据
        if (Tools.Method.IsEditData(selectRecords)) {
            view.down('#' + view.ename + 'Form').getForm().loadRecord(selectRecords[0]);
        }
    },
    onClickButtonSave: function () {
        //Tools.GridSearchToolbar.SaveEncap(this, Tools.Method.getAPiRootPath() + '/api/ModuleManager/PostSave?ct=json');
        var ActionEdit = Tools.Method.getAPiRootPath() + '/baseModuleInfo/postSave.do';
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var form = view.down('#' + view.ename + 'Form');
        if (form.isValid()) {
            var record = view.getViewModel().getData().rec;
            if (record.buttonId == '')
                delete record['buttonId'];
            var pnGrid = view.down('#' + view.ename + 'Grid');
           // var data = Tools.Method.GetPostData(Ext.encode(record));
            //alert(record);
            if (record) {
                if (record.parentOid == undefined || record.parentOid == '')
                    Tools.Method.ShowTipsMsg('请选择上级菜单', 3000, 3, null);
                else {
                    Tools.Method.ExtAjaxRequestEncap(ActionEdit, 'POST', record, true, function (jsonData) {
                        if (jsonData) {
                            view.down('#' + view.ename + 'Form').getForm().reset();
                            view.getViewModel().getData().rec = null;
                            Tools.Method.ShowTipsMsg(Tools.Msg.MSG0006, '4000', '1', null);
                            pnGrid.store.reload()
                        } else {
                            Tools.Method.ShowTipsMsg(Tools.Msg.MSG0022, '4000', '2', null);//修改失败
                        }
                    });
               }
            } else {
                Ext.MessageBox.alert('提示', '请先填写数据！');
            }
        }
    },
    onClickButtonDel: function () {
        //Tools.GridSearchToolbar.DeleteEncap(this, Tools.Method.getAPiRootPath()+'/api/ModuleManager/Delete?ct=json');
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var ActionDelete = Tools.Method.getAPiRootPath() + '/baseModuleInfo/deleteById.do';
        var view = this.getView();//获取当前grid控件
        var pnGrid = view.down('#' + view.ename + 'Grid');
        var selectRows = pnGrid.selModel.selected.items;//获取grid选中行
        //至少选择一项数据
        if (Tools.Method.IsDelData(selectRows)) {
            var delOIDs = new Array(0);
            $.each(selectRows, function (index, row) {
                delOIDs.push(row.data.oid);
            })
            var data = { PostData:delOIDs };
            //用户确认删除操作-----点击“确认”并输入“删除”
            Ext.Msg.prompt('提醒', '确定要删除选中行？如果确定删除请输入“删除”，并点击确定', function (btn, text) {
                if (btn == 'ok' && text == '删除') {
                    Tools.Method.ExtAjaxRequestEncap(ActionDelete, 'POST', data, true, function (jsonData) {
                        if (jsonData.result) {
                            Tools.Method.ShowTipsMsg(Tools.Method.StrFormat(Tools.Msg.MSG00141, [jsonData.succount, jsonData.errorcount]), '4000', '1', null);
                            if (view.down('#' + view.ename + 'Form'))
                                view.down('#' + view.ename + 'Form').getForm().reset();
                            view.getViewModel().getData().rec = null;
                            pnGrid.store.reload();
                        }
                        else {
                            Tools.Method.ShowTipsMsg(Tools.Msg.MSG0022, '4000', '2', null);
                        }
                    });
                }
            });
        }
    },
    onOperationClickDelete:function(grid, rindex, cindex){
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var ActionDelete = Tools.Method.getAPiRootPath() + '/baseModuleInfo/deleteById.do';
        var view = grid.up('#tab-ModuleManager-grid').down('#ModuleManagerForm');
        var data = { PostData:grid.store.getAt(rindex).getData().oid };
            //用户确认删除操作-----点击“确认”并输入“删除”
            Ext.Msg.prompt('提醒', '确定要删除选中行？如果确定删除请输入“删除”，并点击确定', function (btn, text) {
                if (btn == 'ok' && text == '删除') {
                    Tools.Method.ExtAjaxRequestEncap(ActionDelete, 'POST', data, true, function (jsonData) {
                        if (jsonData.result) {
                            Tools.Method.ShowTipsMsg(Tools.Method.StrFormat(Tools.Msg.MSG00141, [jsonData.succount, jsonData.errorcount]), '4000', '1', null);
                            grid.store.reload();
                        }
                        else {
                            Tools.Method.ShowTipsMsg(Tools.Msg.MSG0022, '4000', '2', null);
                        }
                    });
                }
            });
    },
    onClickSearch: function () {
        Tools.GridSearchToolbar.SearchEncap(this);
    },
    onClickButtonIco: function () {
        Ext.create('ExtFrame.view.main.sys.moduleManager.MouduleIco')

    },
    onOperationClickButtonLook: function (grid, rindex, cindex) {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = grid.up('#tab-ModuleManager-grid').down('#ModuleManagerForm');
        var Record = grid.store.getAt(rindex);//获取grid选中行records
        //根据oid取出module已配置的权限
        var oid = Record.getData().oid;
        var permission = null;
        Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/baseModuleInfo/getModulePermissionByOid.do?oid=' + oid, 'GET', null, false, function (jsonData) {
            permission = jsonData;
        })
        Record.data.permissions = permission;
        grid.setSelection(Record);
        view.down('#ename').oid = oid;
        view.getForm().loadRecord(Record);
    },
    checkChild: function (node, state) {
        checkChild(node, state);
    },
    //权限分配工具栏按钮
    onClickForPermission: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var pnGrid = view.down('#' + view.ename + 'Grid');//获取当前grid控件
        var selectRecords = pnGrid.selModel.selected.items;//获取grid选中行records
        //至少选择一项数据
        if (Tools.Method.IsDelData(selectRecords)) {
            var selectRecordsOid = new Array();
            var nameList = new Array();
            $.each(selectRecords, function (index, row) {
                var PermissionsOid = new Array();
                $.each(row.data.permissions, function (i, per) {
                    PermissionsOid.push(per.oid);
                });
                selectRecordsOid.push({
                    oid: row.data.oid, permissionsId: PermissionsOid
                });
                nameList.push(row.data.name);
            });
            var nameDisplay = "";
            if (nameList.length > 10) {
                for (var i = 0; i < 10; i++) {
                    nameDisplay += nameList[i] + ',';
                }
                nameDisplay = nameDisplay.substr(0, nameDisplay.length - 1) + '...';
            } else {
                nameDisplay = nameList.toString();
            }
            Ext.create('ExtFrame.view.main.sys.roleManager.RolePermissionSet', {
                Operation: 'add',
                selectModules: selectRecordsOid,
                displayName: nameDisplay,
                controller: 'moduleManager',
                width: 560,
                height: 360
            });
        }
    },
    //移除权限工具栏按钮
    onClickRemovePermission: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var pnGrid = view.down('#' + view.ename + 'Grid');//获取当前grid控件
        var selectRecords = pnGrid.selModel.selected.items;//获取grid选中行records
        //至少选择一项数据
        if (Tools.Method.IsDelData(selectRecords)) {
            var selectRecordsOid = new Array();
            var nameList = new Array();
            $.each(selectRecords, function (index, row) {
                var PermissionsOid = new Array();
                $.each(row.data.permissions, function (i, per) {
                    PermissionsOid.push(per.oid);
                });
                selectRecordsOid.push({
                    oid: row.data.oid, permissionsId: PermissionsOid
                });
                nameList.push(row.data.name);
            });
            var nameDisplay = "";
            if (nameList.length > 10) {
                for (var i = 0; i < 10; i++) {
                    nameDisplay += nameList[i] + ',';
                }
                nameDisplay = nameDisplay.substr(0, nameDisplay.length - 1) + '...';
            } else {
                nameDisplay = nameList.toString();
            }
            Ext.create('ExtFrame.view.main.sys.roleManager.RolePermissionSet', {
                Operation: 'remove',
                selectModules: selectRecordsOid,
                displayName: nameDisplay,
                controller: 'moduleManager',
                width: 560,
                height: 360
            });
        }
    },
    //批量添加、移除权限窗体保存按钮
    onSavePermission: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var checkboxs = view.down("#lblName").getChecked();//获取选中的checkbox的值
        if (checkboxs.length > 0) {
            var selectModules = view.selectModules;
            var  ids="";
            var pids="";
            $.each(selectModules, function (index, row) {
                ids+=row.oid+",";
            });
            $.each(checkboxs, function (i, item) {
                pids+=item.inputValue+",";
            });
            ids = ids.substr(0, ids.length - 1);
            pids = pids.substr(0, pids.length - 1);
            var data={oid:ids,permissionsId:pids,type:view.Operation};
            Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/baseModuleInfo/modulePermissionSave.do', 'POST', data, true, function (jsonData) {
                if (jsonData) {
                    view.close();//修改成功
                    if (view.Operation == 'add') {
                        Tools.Method.ShowTipsMsg('批量添加权限是成功！', 3000, 1, null);
                    } else {
                        Tools.Method.ShowTipsMsg('批量移除权限成功！', 3000, 1, null);
                    }
                    //刷新grid
                    Ext.getCmp('ModuleManagerGrid').store.reload();
                } else {
                    if (view.Operation == 'add') {
                        Tools.Method.ShowTipsMsg('批量添加权限是失败！', 3000, 2, null);
                    } else {
                        Tools.Method.ShowTipsMsg('批量移除权限失败！', 3000, 2, null);
                    }
                }
            });
        } else {
            if (view.Operation == 'add') {
                Tools.Method.ShowTipsMsg('请选择要添加的权限', 3000, 3, null);
            } else {
                Tools.Method.ShowTipsMsg('请选择要移除的权限', 3000, 3, null);
            }
        }
    },
    onOperationClickUp: function (grid, rindex, cindex) {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var moduleId = grid.store.getAt(rindex).data.oid;//获取grid选中行orgid
       // var data = Tools.Method.GetPostData(moduleId + '☻up');
        Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/baseModuleInfo/moduleTreeMove.do?moveType=up&id='+moduleId, 'GET', null, true, function (jsonData) {
            if (jsonData) {
                Tools.Method.ShowTipsMsg('组织机构上移成功！', 3000, 1, null);
                grid.store.reload();
            } else {
                Tools.Method.ShowTipsMsg('组织机构上移失败！请联系管理员', 3000, 2, null);
            }
        });
    },
    //下移
    onOperationClickDown: function (grid, rindex, cindex) {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var moduleId = grid.store.getAt(rindex).data.oid;//获取grid选中行orgid
       // var data = Tools.Method.GetPostData(moduleId + '☻down');
        Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/baseModuleInfo/moduleTreeMove.do?moveType=down&id='+moduleId, 'GET', null, true, function (jsonData) {
            if (jsonData) {
                Tools.Method.ShowTipsMsg('组织机构下移成功！', 3000, 1, null);
                grid.store.reload();
            } else {
                Tools.Method.ShowTipsMsg('组织机构下移失败！请联系管理员', 3000, 2, null);
            }
        });
    }
});
function checkChild(node, state) {
    if (node.hasChildNodes()) {
        node.eachChild(function (child) {
            child.set('checked', state);
            checkChild(child, state);
        });
    }
}