
Ext.define('ExtFrame.view.main.gxbyszp.ptPreHiReEmpDeTails.PtPreHireEmpFamMem', {
    extend: 'Ext.grid.GridPanel',
    alias: 'widget.ptPreHireEmpFamMem',
    fit: true,
    stripeRows: true,

    initComponent: function () {
        var me = this;
        var need_select = false;
        /********************** 根据具体业务需要适当修改 ***********************/
        var pageSize = 12;//分页条数
        var OrderField = 'title';//默认排序字段
        var OrderType = 'ASC';//默认排序类型 ASC/DESC
        var OrderField2 = 'memName';
        /*
         ** grid控件绑定列
         ** text: 前台显示文字, dataIndex: 数据绑定字段, sortable: 能否排序（缺省值为true）
         ** searchable: 能否查询（缺省值为false）
         ** fieldType: 字段类型（用户查询控件拼接where字句，目前仅支持 string、int、datetime
         其中string类型使用'like'关键字查询，其余的使用'='关键字查询）
         */
        me.columns = [
            { text: 'oid', dataIndex: 'oid',hidden:true },
            { text: '称谓', dataIndex: 'title', searchable: true,fieldType: 'string',width:50, editor: {allowBlank: false}},
            { text: '姓名', dataIndex: 'memName', searchable: true, fieldType: 'string',width:80 ,editor: {allowBlank: false}},
            { text: '年龄', dataIndex: 'age' ,fieldType: 'int',width:50},
            { text: '工作单位', dataIndex: 'workOrg',fieldType: 'string',width:120,editor: {allowBlank: false}},
            { text: '职务', dataIndex: 'reqCount',duty: 'string', width:120,editor: {allowBlank: false}},
            { text: '备注', dataIndex: 'remark',fieldType: 'string' ,editor: {}},
        ];


        var rowEditing = Ext.create('Ext.grid.plugin.RowEditing',{
            pluginId:'rowEditing',
            saveBtnText: '保存',
            cancelBtnText: "取消",
            autoCancel: false,
            clicksToEdit:2   //双击进行修改  1-单击   2-双击    0-可取消双击/单击事件
        });

        me.plugins= [rowEditing],

            me.listeners={
                edit:'onClickSave'
            } ;
        //构造grid store
        me.store = Ext.create('ExtFrame.store.PtPreHiReEmpDeTails', {
            pageSize: pageSize,
            remoteSort: true,
            sortOnLoad: true,
            sorters: [{ property: OrderField, direction: OrderType },{ property: OrderField2, direction: "ASC" }],
            proxy: {
                type: 'ajax',
                url: Tools.Method.getAPiRootPath()+"/ptPreHiReEmpFamMem/pagedQueryByBean.do",
                reader: {
                    type: 'json',
                    rootProperty: 'extData',//数据根节点名称
                    totalProerty: 'total',//数据总数节点名称
                    idProperty: 'oid'//id标示节点名称
                },
                listeners: {
                    //捕捉异常处理
                    exception: function (theproxy, response, operation, options) {
                        Tools.Method.ExceptionEncap(response);
                    }
                }
            },
            listeners: {
                beforeload: function (store, operation, eOpts) {
                    var aaa= store;
                }
            }
        });
        //grid 停靠item
        me.dockedItems = [ {
            xtype: 'pagingtoolbar',
            store: me.store,//分页控件数据（同grid的数据保持一致）
            dock: 'bottom',
            displayInfo: true,
            items: [
                '-', {cls: 'x-btn-text-icon details'}
            ]
        }];
        me.selModel = Ext.create('Ext.selection.CheckboxModel', {
            mode: 'simple',//multi,simple,single；默认为多选multi
            injectCheckbox: 0//checkbox位于哪一列，默认值为0
            //checkOnly: true//如果值为true，则只用点击checkbox列才能选中此条记录,
        });
        me.callParent();
    }
});
