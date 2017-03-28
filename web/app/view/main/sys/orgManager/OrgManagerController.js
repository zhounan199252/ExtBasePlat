Ext.define('ExtFrame.view.main.sys.orgManager.OrgManagerController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.orgManager',

    onClickButtonLook: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var pnGrid = view.down('#' + view.ename + 'Grid');//获取当前grid控件
        var selectRecords = pnGrid.selModel.selected.items;//获取grid选中行records
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
        var selectRecords =  pnGrid.selModel.selected.items;//获取grid选中行records
        //仅能选择一项数据
        if (Tools.Method.IsEditData(selectRecords)) {
            view.down('#' + view.ename + 'Form').getForm().loadRecord(selectRecords[0]);
        }
    },
    onClickButtonSave: function () {
        //Tools.GridSearchToolbar.SaveEncap(this, Tools.Method.getAPiRootPath()+'/api/OrgManager/PostSave?ct=json');
        var ActionEdit = Tools.Method.getAPiRootPath() + '/baseOrgInfo/postSave.do';
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var form = view.down('#' + view.ename + 'Form');
        if (form.down('#orgPicker').valid) {
            if (form.isValid()) {
                var record = view.getViewModel().getData().rec;
                var pnGrid = view.down('#' + view.ename + 'Grid');
               // var data = Tools.Method.GetPostData(Ext.encode(record));
                //alert(record);
                if (record) {
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
                } else {
                    Ext.MessageBox.alert('提示', '请先填写数据！');
                }
            }
        } else {
            Tools.Method.ShowTipsMsg('上级机构选择错误！', '3000', '2', null);
        }
    },
    onClickButtonDel: function () {
       // Tools.GridSearchToolbar.DeleteEncap(this, Tools.Method.getAPiRootPath() + '/baseOrgInfo/deleteById.do');
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var ActionDelete = Tools.Method.getAPiRootPath() + '/baseOrgInfo/deleteById.do';
        var view = this.getView();//获取当前grid控件
        var pnGrid = view.down('#' + view.ename + 'Grid');
        var selectRows = pnGrid.selModel.selected.items;//获取grid选中行
        //至少选择一项数据
        if (Tools.Method.IsDelData(selectRows)) {
            var delOIDs = new Array(0);
            $.each(selectRows, function (index, row) {
                delOIDs.push(row.data.oid);
            })
            var data = { PostData: delOIDs };
            //alert($.toJSON(data));
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
            ////用户确认删除操作-----点击“是”
            //Ext.MessageBox.confirm('提醒', '确定要删除选中行？', function (btn) {
            //    if (btn == 'yes') {
            //        Tools.Method.ExtAjaxRequestEncap(ActionUrl, 'DELETE', selectRows, true, function (jsonData) {
            //            if (jsonData) {
            //                Tools.Method.ShowTipsMsg(Tools.Method.StrFormat(Tools.Msg.MSG0014, [count]), '4000', '1', null);
            //                view.down('#' + view.eName + 'Form').getForm().reset();
            //                pnGrid.store.reload();
            //            }
            //            else {
            //                Tools.Method.ShowTipsMsg(Tools.Msg.MSG0022, '4000', '2', null);
            //            }
            //        });
            //    }
            //});
        }
    },
    onOperationClickButtonLook: function (grid, rindex, cindex) {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = grid.up('#tab-OrgManager-grid').down('#OrgManagerForm');
        var Record = grid.store.getAt(rindex);//获取grid选中行records
        grid.setSelection(Record);
        view.getForm().loadRecord(Record);
    },
    onClickSearch: function () {
        Tools.GridSearchToolbar.SearchEncap(this);
    },
    checkChild: function (node, state) {
        checkChild(node, state);
    },
    //上移
    onOperationClickUp: function (grid, rindex, cindex) {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var orgId = grid.store.getAt(rindex).data.oid;//获取grid选中行orgid
      //  var data = Tools.Method.GetPostData(orgId + '☻up');
        Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/baseOrgInfo/orgTreeMove.do?moveType=up&id='+orgId, 'GET', null, true, function (jsonData) {
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
        var orgId = grid.store.getAt(rindex).data.oid;//获取grid选中行orgid
       // var data = Tools.Method.GetPostData(orgId + '☻down');
        Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/baseOrgInfo/orgTreeMove.do?moveType=down&id='+orgId, 'GET', null, true, function (jsonData) {
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