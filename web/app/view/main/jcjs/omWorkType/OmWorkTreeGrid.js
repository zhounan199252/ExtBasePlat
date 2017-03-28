Ext.define('ExtFrame.view.main.jcjs.omWorkType.OmWorkTreeGrid', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.omWorkTreeGrid',
    title: '人员工种树',
    width: 300,
    height: 200,
    renderTo: document.body,
    initComponent: function () {
        var me = this;
        //构造treegrid store
        me.store = Ext.create('ExtFrame.store.ModuleTree', {
            root: {
                oid:"0",
                id: "0",
                text: "全部工种"
            },
            proxy: {
                type: 'ajax',
                url: Tools.Method.getAPiRootPath() + "/workType/getWorkTree.do",
                extraParams: {
                    'manaworktypeId ': ""
                }
            },
            listeners : {
                nodebeforeexpand: function (node, eOpts) {
                    //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台
                    this.proxy.extraParams.manaworktypeId = node.id;
                }
            },
            folderSort: true
        });

        me.on('celldblclick',function(sender, td, cellIndex, record, tr, rowIndex, e){
            //带附加参数重构grid store数据
            //获取当前grid控件
            if(record.id != 0){
                var pnGrid = Ext.getCmp("omWorkTypeGrid");
                pnGrid.store.getProxy().extraParams = {
                    "swhere": 'worktypeId|String|'+record.id+'<<worktypeName|String|'+record.raw.text
                };
                //重新加载grid
                pnGrid.store.reload();
            }
            return true;
        });

        me.on('beforecellclick', function (sender, td, cellIndex, record, tr, rowIndex, e) {
            if (cellIndex == 0) {
                need_select = true;
                e.stopEvent();
                if (me.selModel.isSelected(record))
                    me.selModel.doDeselect(record, false);
                else
                    me.selModel.doSelect(record, true);
            }
            return true;
        });
        function select_deselect(model, record, index) {
            index = me.store.indexOf(record);

            me.getView().focusRow(index);
            if (need_select) {
                if (need_select != 'all' || (need_select == 'all' && me.view.all.count - 1 == index)) {
                    need_select = false;
                }
                return true;
            }
            return false;
        };
        me.on('beforeselect', select_deselect);
        me.on('beforedeselect', select_deselect);
        me.callParent();
    }
});
