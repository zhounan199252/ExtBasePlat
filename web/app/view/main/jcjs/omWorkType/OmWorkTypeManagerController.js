Ext.define('ExtFrame.view.main.jcjs.omWorkType.OmWorkTypeManagerController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.omWorkTypeManager',

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
        Tools.GridSearchToolbar.SaveEncap(this, Tools.Method.getAPiRootPath()+'/workType/save.do');
    },
    onClickButtonDel: function () {
        Tools.GridSearchToolbar.DeleteByOIDEncap(this, Tools.Method.getAPiRootPath()+'/workType/delete.do');
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