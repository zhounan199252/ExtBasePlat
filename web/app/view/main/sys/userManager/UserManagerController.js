Ext.define('ExtFrame.view.main.sys.userManager.UserManagerController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.userManager',

    onClickButtonLook: function () {
        var view = this.getView();
        var pnGrid = view.down('#userManagerGrid');//获取当前grid控件
        var selectRecords = pnGrid.getSelection();//获取grid选中行records
        if(Tools.Method.IsEditData(selectRecords)) {
            view.down('#UserManagerForm').down('#userName').oid = selectRecords[0].getData().oid;
            Tools.GridSearchToolbar.LookEncap(this);
        }
    },
    onClickButtonAdd: function () {
        Tools.GridSearchToolbar.AddEncap(this);
        //恢复用户名可修改
        //this.getView().down('#textUserName').setConfig('readOnly', false);
    },
    onClickButtonEdit: function () {
        var view = this.getView();
        var pnGrid = view.down('#UserManagerGrid');//获取当前grid控件
        var selectRecords = pnGrid.getSelection();//获取grid选中行records
        if(Tools.Method.IsEditData(selectRecords)){
            view.down('#UserManagerForm').down('#userName').oid = selectRecords[0].getData().oid;
            Tools.GridSearchToolbar.EditEncap(this);
        }
    },
    onClickButtonSave: function () {
        Tools.GridSearchToolbar.SaveEncap(this, Tools.Method.getAPiRootPath() + '/baseUserInfo/postSave.do');
    },
    onClickButtonDel: function () {
        Tools.GridSearchToolbar.DeleteByOIDEncap(this, Tools.Method.getAPiRootPath() + '/baseUserInfo/deleteById.do');
    },
    onClickSearch: function () {
        Tools.GridSearchToolbar.SearchEncap(this);
    },
    onClickExport: function () {
        window.open(Tools.Method.getAPiRootPath() + '/api/UserManager/Export');
        //Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/api/UserManager/Export', 'GET', null, true, function (jsonData) {

        //});
    },
    onClickAddSearch:function(){
        Tools.GridSearchToolbar.ClickAddSearch(this);
    },
    onClickClear:function(){
        Tools.GridSearchToolbar.ClickClear(this);
    }
});