Ext.define('ExtFrame.view.main.jcjs.omPttitle.OmPttitleTree', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.omPttitleTree',
    title: '职称系列',
    width: 220,
    height: 200,
    renderTo: document.body,
    initComponent: function () {
        var me = this;
        //构造treegrid store
        me.store = Ext.create('ExtFrame.store.ModuleTree', {
            root: {
                oid:"0",
                id: "0",
                text: "所有职称系列"
             },
            proxy: {
                type: 'ajax',
                url: Tools.Method.getAPiRootPath() + "/omPttitle/getOmPttitleTree.do",
                extraParams: {
                    'maNaPttId': ""
                }
            },
            listeners : {
                nodebeforeexpand: function (node, eOpts) {
                    //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台
                    this.proxy.extraParams.maNaPttId = node.id;
                }
            },
            folderSort: true
        })
        me.on('celldblclick', function (sender, td, cellIndex, record, tr, rowIndex, e) {
                //带附加参数重构grid store数据
                 //获取当前grid控件
            if(record.id!=0){
                var pnGrid=Ext.getCmp("omPttitlegrid");
                pnGrid.store.getProxy().extraParams = {
                    "swhere": 'oid|String|'+record.id+'<<pttName|String|'+record.raw.text
                };
                //重新加载grid
                pnGrid.store.reload();
            }
            return true;
        });

        me.callParent();
    }
});
