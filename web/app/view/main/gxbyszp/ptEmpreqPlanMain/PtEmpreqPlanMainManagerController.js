/**
 * Created by Administrator on 2016/2/23.
 */
Ext.define('ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainManagerController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.ptEmpreqPlanMainManager',

     onClickButtonLook: function () {
        Tools.GridSearchToolbar.LookEncap(this);
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
        var selectRecords = pnGrid.getSelection();//获取grid选中行records

        //仅能选择一项数据
        if (Tools.Method.IsEditData(selectRecords)) {
            view.down('#column1').down('#treepicker').setRawValue(selectRecords[0].data.unit);
            view.down('#' + view.ename + 'Form').getForm().loadRecord(selectRecords[0]);
        }
    },
    onClickButtonSave: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var form = view.down('#' + view.ename + 'Form');
        if (form.isValid()) {
            var    nyear=  view.down('#nyear').getValue();
            var    rptPerson=  view.down('#rptPerson').getValue();
            view.down('#nyear').setValue("");
            view.down('#nyear').setValue(nyear);
            view.down('#rptPerson').setValue("");
            view.down('#rptPerson').setValue(rptPerson);
            var record = view.getViewModel().getData().rec;
            var pnGrid = view.down('#' + view.ename + 'Grid');
            if (record) {
                Tools.Method.ExtAjaxRequestEncap( Tools.Method.getAPiRootPath()+'/ptEmpreqPlanMain/save.do', 'POST',record, true, function (jsonData) {
                    if (jsonData) {
                        view.down('#' + view.ename + 'Form').getForm().reset();
                        view.getViewModel().getData().rec = null;
                        Tools.Method.ShowTipsMsg(Tools.Msg.MSG0006, '4000', '1', null);
                        pnGrid.store.reload();
                    } else {
                        Tools.Method.ShowTipsMsg(Tools.Msg.MSG0022, '4000', '2', null);//修改失败
                    }
                });
            } else {
                Ext.MessageBox.alert('提示', '请先填写数据！');
            }
        }


    },
    onClickButtonDel: function () {
        Tools.GridSearchToolbar.DeleteByOIDEncap(this, Tools.Method.getAPiRootPath()+'/ptEmpreqPlanMain/delete.do');
    },
    onClickSearch: function () {
        Tools.GridSearchToolbar.SearchEncap(this);
    },
    onClickAddSearch:function(){
        Tools.GridSearchToolbar.ClickAddSearch(this);
    },
    onClickClear:function(){
        Tools.GridSearchToolbar.ClickClear(this);
    },

//人员需求计划审核页面
    onClickCheck: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var pnGrid=Ext.getCmp("ptEmpreqPlanMainCheckgrid");//获取当前grid控件
        var selectRecords = pnGrid.getSelection();//获取grid选中行records
        //仅能选择一项数据
        if (Tools.Method.IsEditData(selectRecords)) {
            var records = new Array();
            $.each(selectRecords, function (index, row) {
                records.push(row.data);
            });
            var aaa = Ext.create('ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainWindow', {
                selectData:  records,
                controller: 'ptEmpreqPlanMainManager',
                viewModel: { type: 'ptEmpreqPlanMainManagerModel' }
            });
        }
    },


//人员需求计划的审核保存
    onClickCheckSave: function () {
        if (!Tools.Method.IsLogin())
            return;
            var view = this.getView();
            var    oid=  view.down('form>hiddenfield').getValue();
            var    isAudit=   view.down('form>combo').getValue();
            var  form= view.down('form').getForm();
            var record = {oid:oid,isAudit:isAudit};
            var pnGrid=Ext.getCmp("ptEmpreqPlanMainCheckgrid");
        if (form.isValid()) {
            if (record) {
                Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath()+'/ptEmpreqPlanMain/save.do', 'POST',record, true, function (jsonData) {
                    if (jsonData) {
                        view.down('form').getForm().reset();
                        Tools.Method.ShowTipsMsg(Tools.Msg.MSG0006, '4000', '1', null);
                        pnGrid.store.reload();
                        view.close();
                    } else {
                        Tools.Method.ShowTipsMsg(Tools.Msg.MSG0022, '4000', '2', null);//修改失败
                    }
                });
            } else {
                Ext.MessageBox.alert('提示', '请先填写数据！');
            }

        } else {
            Ext.MessageBox.alert('提示', '请先填写数据！');
        }
    },

//人员需求计划主表录入
    onClickManage: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var pnGrid=Ext.getCmp("ptEmpreqPlanMaingrid");//获取当前grid控件
        var selectRecords = pnGrid.getSelection();//获取grid选中行records
        //仅能选择一项数据
        if (Tools.Method.IsEditData(selectRecords)) {
            Ext.getCmp("ptEmpreqPlanDetailsWindow").expand();
            var  form= Ext.getCmp("ptEmpreqPlanDetailsForm");
            form.getForm().reset();//表单清空
            form.getForm().loadRecord(selectRecords[0]);
            var pnGridTwo=Ext.getCmp("ptEmpreqPlanDetailsgrid");
            var records = new Array();
            $.each(selectRecords, function (index, row) {
                records.push(row.data);
            });
            pnGridTwo.store.getProxy().extraParams = {
                "swhere": 'empreqPlanId|String|'+ records[0].oid
            }
            pnGridTwo.empreqPlanId=records[0].oid;
            pnGridTwo.store.reload();
        }

    },

//人员需求计划主表录入   grid修改后的保存
    onClickSave: function (e) {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var pnGrid=Ext.getCmp("ptEmpreqPlanDetailsgrid");
        var records = e.context.newValues;
        if (records) {
                   Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath()+'/ptEmpreqPlanDetails/save.do', 'POST',records, true, function (jsonData) {
                    if (jsonData) {
                        Tools.Method.ShowTipsMsg(Tools.Msg.MSG0006, '4000', '1', null);
                        pnGrid.store.reload();
                    } else {
                        Tools.Method.ShowTipsMsg(Tools.Msg.MSG0022, '4000', '2', null);//修改失败
                    }
                });
            } else {
                Ext.MessageBox.alert('提示', '请先填写数据！');
            }
    },


    //人员需求计划主表录入   grid修改后的保存
    onClickDel: function (e) {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var pnGrid  =Ext.getCmp("ptEmpreqPlanDetailsgrid") ;
        var selectRows = pnGrid.getSelectionModel().getSelection();//获取grid选中行
        //至少选择一项数据
        if (Tools.Method.IsDelData(selectRows)) {
            var delOIDs = '';
            $.each(selectRows, function (index, row) {
                if(row.data.oid==undefined){
                    return;
                }
                delOIDs += row.data.oid + ',';
            });
            delOIDs = delOIDs.substr(0, delOIDs.length - 1);
            var data = { ids: delOIDs };
            //用户确认删除操作-----点击“是”
            Ext.MessageBox.confirm('提醒', '确定要删除选中行？', function (btn) {
                if (btn == 'yes') {
                    Tools.Method.ExtAjaxRequestEncap( Tools.Method.getAPiRootPath()+'/ptEmpreqPlanDetails/delete.do', 'POST', data, true, function (jsonData) {
                        if (jsonData.result) {
                            Tools.Method.ShowTipsMsg(Tools.Method.StrFormat(Tools.Msg.MSG00141, [jsonData.sCount, jsonData.fCount]), '4000', '1', null);
                            pnGrid.store.reload();
                        }
                        else {
                            Tools.Method.ShowTipsMsg(Tools.Msg.MSG0022, '4000', '2', null);
                        }
                    });
                }
            });
        }
    }
});