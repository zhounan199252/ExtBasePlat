/**
 * Created by Administrator on 2016/2/25.
 */

Ext.define('ExtFrame.view.main.byszz.ptPassProbationAudit.PtPassProbationAuditManagerController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.ptPassProbationAuditManager',


    onClickButtonAdd: function () {
            Ext.getCmp("ptPassProbationAuditWindow").expand();
           var  form= Ext.getCmp("ptPassProbationAuditForm");
                form.getForm().reset();//表单清空
    },

    onClickButtonEdit: function () {
        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var pnGrid=Ext.getCmp("ptPassProbationAuditGrid");//获取当前grid控件
        var selectRecords = pnGrid.getSelection();//获取grid选中行records
        //仅能选择一项数据
        if (Tools.Method.IsEditData(selectRecords)) {
           Ext.getCmp("ptPassProbationAuditWindow").expand();
            var  form= Ext.getCmp("ptPassProbationAuditForm");
               form.getForm().reset();//表单清空
            form.down('#treepicker').setRawValue(selectRecords[0].data.unit);
              form.getForm().loadRecord(selectRecords[0]);
        }
    },

    //大中专生转正定级申请保存
    onClickButtonSave: function () {

        //登录状态判断
        if (!Tools.Method.IsLogin())
            return;
        var view = this.getView();
        var  form= view.down('form').getForm();
        var record = view.getViewModel().getData().rec;
        var pnGrid=Ext.getCmp("ptPassProbationAuditGrid");
        if (form.isValid()) {
            if (record) {
                Tools.Method.ExtAjaxRequestEncap( Tools.Method.getAPiRootPath()+'/ptPassProbationAudit/save.do', 'POST',record, true, function (jsonData) {
                    if (jsonData) {
                        view.down('form').getForm().reset();
                        Tools.Method.ShowTipsMsg(Tools.Msg.MSG0006, '4000', '1', null);
                        pnGrid.store.reload();
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

    onClickButtonDel: function () {
        Tools.GridSearchToolbar.DeleteByOIDEncap(this, Tools.Method.getAPiRootPath()+'/ptPassProbationAudit/delete.do');
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

});
