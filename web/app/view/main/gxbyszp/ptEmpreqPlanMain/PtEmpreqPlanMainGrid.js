/**
 * Created by Administrator on 2016/2/23.
 */
Ext.define('ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainGrid', {
    extend: 'Ext.grid.GridPanel',
    alias: 'widget.ptEmpreqPlanMaingrid',
    fit: true,
    stripeRows: true,
    initComponent: function () {
        var me = this;
        var need_select = false;
        /********************** 根据具体业务需要适当修改 ***********************/
        var pageSize = 12;//分页条数
        var OrderField = 'nyear';//默认排序字段
        var OrderType = 'ASC';//默认排序类型 ASC/DESC
        var OrderField2 = 'unit';
        /*
         ** grid控件绑定列
         ** text: 前台显示文字, dataIndex: 数据绑定字段, sortable: 能否排序（缺省值为true）
         ** searchable: 能否查询（缺省值为false）
         ** fieldType: 字段类型（用户查询控件拼接where字句，目前仅支持 string、int、datetime
         其中string类型使用'like'关键字查询，其余的使用'='关键字查询）
         */
        me.columns = [
            { text: '计划oid', dataIndex: 'oid',hidden:true },
            { text: '年度', dataIndex: 'nyear',  searchable: true,fieldType: 'string'},
            { text: '单位名称', dataIndex: 'unit', searchable: true,fieldType: 'string',width:150},
            { text: '业务群名称', dataIndex: 'busiGroup', searchable: true, fieldType: 'string',width:150 },
            { text: '填报人', dataIndex: 'rptPerson' ,fieldType: 'string'},
            { text: '填报时间', dataIndex: 'rptDate',fieldType: 'string'},
            { text: '阶段计划', dataIndex: 'planStage',fieldType: 'string' },
            { text: '审批状态', dataIndex: 'isAudit',fieldType: 'string'},
            { text: '备注', dataIndex: 'remark',fieldType: 'string' },
        ];
        //构造grid store
        me.store = Ext.create('ExtFrame.store.OmPttitle', {
            pageSize: pageSize,
            remoteSort: true,
            sortOnLoad: true,
            sorters: [{ property: OrderField, direction: OrderType },{ property: OrderField2, direction: "ASC" }],
            proxy: {
                type: 'ajax',
                url: Tools.Method.getAPiRootPath()+"/ptEmpreqPlanMain/pagedQueryByBean.do",
                reader: {
                    type: 'json',
                    rootProperty: 'extData',//数据根节点名称
                    totalProerty: 'total',//数据总数节点名称
                    idProperty: 'pttId'//id标示节点名称
                },
                //扩展参数
                extraParams: {
                    'swhere': ""
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
        me.dockedItems = [{
            xtype: 'gridsearchtoolbar',
            itemId: 'searchtoolbar',
            ename: me.ename,//搜索栏父级grid 对应类名称，用于GridSearchToolbar查找父级grid对象
            searchEx: false,//扩展查询，当为true时，查询控件会自动构造sql语句的整个语句
            searchCols: me.columns.filter(function (col) {
                return col.searchable;
            }),
            dock: 'top',
            items: []
        }, {
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

