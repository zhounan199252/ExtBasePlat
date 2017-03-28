Ext.define('ExtFrame.view.main.gxbyszp.ptPreHiReEmpDeTails.PtPreHiReEmpDeTailsManagerController',{
    extend:'Ext.app.ViewController',
    alias:'controller.ptPreHiReEmpDeTailsManager',

    onClickButtonLook: function () {},
    onClickButtonFind: function () {
        //Ext.MessageBox.alert('提示', '测试');
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var  form= view.down('form').getForm();
        var startDate = view.down('#startdt').getValue();//获取搜索字段
        var endDate = view.down('#enddt').getValue();//获取搜索字段
        var pnGrid=Ext.getCmp("ptPreHiReEmpDeTailsGridLeft");

        //Ext.MessageBox.alert('提示', endDate);
        //var dateTime=new Array();
        //dateTime[0]=startDate;
        //dateTime[1]=endDate;
       // Ext.MessageBox.alert('提示', data);
        pnGrid.store.getProxy().extraParams = {
                startDate:startDate,
                endDate:endDate
        };
        //重新加载grid
        pnGrid.store.reload();
        //if(form.isValid()){
        //    if(data){
        //        Tools.Method.ExtAjaxRequestEncap( Tools.Method.getAPiRootPath()+'/ptEmpreqPlanMain/pagedQueryByBean.do', 'GET' ,data,true, function (jsonData) {
        //            if (jsonData) {
        //                //view.down('form').getForm().reset();
        //               // pnGrid.store.reload();
        //            }
        //        });
        //    }
        //    }
    },
    onClickButtonAdd: function () {
        Tools.GridSearchToolbar.AddEncap(this);
    },
    onClickButtonEdit: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var pnGrid=Ext.getCmp("ptPreHiReEmpDeTailsGrid");//获取当前grid控件
        var selectRecords = pnGrid.getSelection();//获取grid选中行records
        //仅能选择一项数据
        if (Tools.Method.IsEditData(selectRecords)) {
            Ext.getCmp("ptPreHiReEmpDeTailsWndow").expand();
            var  form= Ext.getCmp("ptPreHiReEmpDeTailsForm");
            form.getForm().reset();//表单清空
            form.getForm().loadRecord(selectRecords[0]);
            var pnGrids=Ext.getCmp("ptPreHireEmpFamMem");
            var records = new Array();
            $.each(selectRecords, function (index, row) {
                records.push(row.data);
            });
          //  Ext.MessageBox.alert('提示', records[0].oid);
            pnGrids.store.getProxy().extraParams = {
                //"swhere": 'empreqPlanId|String|'+ records[0].oid
                "preHireEmpReCid": records[0].oid
            }
              Ext.MessageBox.alert('提示', records[0].oid);
            pnGrids.store.reload();
        }
    },
    onClickButtonSave: function () {
        //Tools.GridSearchToolbar.SaveEncap(this, Tools.Method.getAPiRootPath()+'/ptPreHiReEmpDeTails/save.do');
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var  form= view.down('form').getForm();
        var record = view.getViewModel().getData().rec;
        var pnGrid=Ext.getCmp("ptPreHiReEmpDeTailsGrid");
        if (form.isValid()) {
            if (record) {
                Tools.Method.ExtAjaxRequestEncap( Tools.Method.getAPiRootPath()+'/ptPreHiReEmpDeTails/save.do', 'POST' ,record, true, function (jsonData) {
                    if (jsonData) {
                        view.down('form').getForm().reset();
                        Tools.Method.ShowTipsMsg(Tools.Msg.MSG0006, '4000', '1', null);
                        pnGrid.store.reload();
                    } else {
                        Tools.Method.ShowTipsMsg(Tools.Msg.MSG0022, '4000', '2', null);//修改失败
                    }
                });
            }else {
                Ext.MessageBox.alert('提示', '请先填写数据！');
            }
        }else {
            Ext.MessageBox.alert('提示', '请先填写数据！');
        }
    },
    onClickButtonDel: function () {
        Tools.GridSearchToolbar.DeleteByOIDEncap(this, Tools.Method.getAPiRootPath()+'/ptPreHiReEmpDeTails/delete.do');
    },
    onClickSearch: function () {
        Tools.GridSearchToolbar.SearchEncap(this);
    },
    onClickAddSearch:function(){
        Tools.GridSearchToolbar.ClickAddSearch(this);
    },
    onClickClear:function(){
        Tools.GridSearchToolbar.ClickClear(this);
    }
})