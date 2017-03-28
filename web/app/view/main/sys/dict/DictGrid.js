/**
 * Created by LvXL on 2016/2/2.
 */
Ext.define('ExtFrame.view.main.sys.dict.DictGrid', {
    extend: 'Ext.tree.Panel',
    requires: [
        'Ext.data.*',
        'Ext.grid.*',
        'Ext.tree.*',
        'Ext.ux.CheckColumn'
    ],
    alias: 'widget.dictGrid',
    fit: true,
    stripeRows: true,
    reserveScrollbar: true,
    rootVisible: false,
    initComponent: function () {
        var me = this;
        /********************** 根据具体业务需要适当修改 ***********************/
        /*
         ** grid控件绑定列
         ** text: 前台显示文字, dataIndex: 数据绑定字段, sortable: 能否排序（缺省值为true）
         ** searchable: 能否查询（缺省值为false）
         ** fieldType: 字段类型（用户查询控件拼接where字句，目前仅支持 string、int、datetime
         其中string类型使用'like'关键字查询，其余的使用'='关键字查询）
         */
        me.columns = [
            {text: 'OID', dataIndex: 'id', hidden: true},
            {text: '字典名称', dataIndex: 'dictName', xtype: 'treecolumn', width:150},
            {text: '字典编码', dataIndex: 'dictCode', width:100},
            {text: '所属编码', dataIndex: 'parentCode', width:100},
            {text: '排序', dataIndex: 'sortno'},
            {text: '状态', dataIndex: 'status',
                renderer: function (v) {
                    if (v == 1) {
                        return '停用';
                    }
                    else if (v == 0) {
                        return '正常';
                    }
                }
            }
        ];
        // 创建按钮
        Tools.Grid.CreateOperationBtn(me, me.ename);

        //构造grid store
        me.store = Ext.create('ExtFrame.store.DictTree', {
            proxy: {
                type: 'ajax',
                url: Tools.Method.getAPiRootPath() + "/dict/queryToTreePicker.do?type=1",
                folderSort: true
            }
        });
        //grid 停靠item
        me.dockedItems = [{
            xtype: 'gridsearchtoolbar',
            itemId: 'gridtoolbar',
            hasSearch: false,
            ename: me.ename,//搜索栏父级grid 对应类名称，用于GridSearchToolbar查找父级grid对象
            dock: 'top',
            items: []
        }];
        me.callParent();
    },
    listeners: {
        checkchange: 'checkChild'
    }
});
