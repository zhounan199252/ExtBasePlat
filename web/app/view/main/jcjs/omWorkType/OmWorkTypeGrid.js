Ext.define('ExtFrame.view.main.jcjs.omWorkType.OmWorkTypeGrid', {
    extend: 'Ext.grid.GridPanel',
    alias: 'widget.omWorkTypeGrid',
    fit: true,
    stripeRows: true,
    initComponent: function () {
        var me = this;
        var need_select = false;
        /********************** 根据具体业务需要适当修改 ***********************/
        var pageSize = 15;//分页条数
        var OrderField = 'worktypeCode';//默认排序字段
        var OrderField2 = 'worktypeName';//第二排序字段
        var OrderType = 'ASC';//默认排序类型 ASC/DESC
        /*
         ** grid控件绑定列
         ** text: 前台显示文字, dataIndex: 数据绑定字段, sortable: 能否排序（缺省值为true）
         ** searchable: 能否查询（缺省值为false）
         ** fieldType: 字段类型（用户查询控件拼接where字句，目前仅支持 string、int、datetime
         其中string类型使用'like'关键字查询，其余的使用'='关键字查询）
         */
        me.columns = [
            { text: 'ID', dataIndex: 'worktypeId', hidden: true },
            { text: '工种代码', dataIndex: 'worktypeCode',    searchable: true, fieldType: 'string' },
            { text: '工种名称', dataIndex: 'worktypeName',  width:160,  searchable: true, fieldType: 'string' },
            { text: '工种拼音', dataIndex: 'worktypePy' },
            { text: '工种大类', dataIndex: 'worktypeBigcat',  fieldType: 'string' },
            { text: '上级工种编号', dataIndex: 'manaworktypeId',    fieldType: 'String' },
            { text: '工种层次', dataIndex: 'worktypeLevel',   fieldType: 'Integer' },
            { text: '排列序号', dataIndex: 'sortNo',   fieldType: 'Integer' },
            { text: '工种序列号', dataIndex: 'worktypeSeq',   fieldType: 'string' },
            { text: '工种分类', dataIndex: 'worktypeCatalog',    fieldType: 'string' },
            { text: '是否子节点', dataIndex: 'isLeaf',   fieldType: 'string' },
            { text: '字节点数', dataIndex: 'subCount',  fieldType: 'string' },
            { text: '备注', dataIndex: 'remark',   fieldType: 'string' }
        ];

        //构造grid store
        me.store = Ext.create('ExtFrame.store.OmWorkType', {
            pageSize: pageSize,
            remoteSort: true,
            sortOnLoad: true,
            sorters: [{ property: OrderField, direction: OrderType },{ property: OrderField2, direction: "ASC" }],
            proxy: {
                type: 'ajax',
                url: Tools.Method.getAPiRootPath()+"/workType/pagedQueryByBean.do",
                reader: {
                    type: 'json',
                    rootProperty: 'extData',//数据根节点名称
                    totalProerty: 'total',//数据总数节点名称
                    idProperty: 'worktypeId'//id标示节点名称
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
        },{
            xtype: 'pagingtoolbar',
            store: me.store,//分页控件数据（同grid的数据保持一致）
            dock: 'bottom',
            displayInfo: true,
            items: [
                '-', {
                    cls: 'x-btn-text-icon details'
                }
            ]
        }];
        me.selModel = Ext.create('Ext.selection.CheckboxModel', {
            mode: 'SIMPLE',//multi,simple,single；默认为多选multi
            injectCheckbox: 0,//checkbox位于哪一列，默认值为0
            checkOnly: true,//如果值为true，则只用点击checkbox列才能选中此条记录
            enableKeyNav: true,
            onKeySpace: function (e) {
                need_select = true;

                var record = e.record || this.lastFocused;
                if (record) {
                    this.afterKeyNavigate(e, record);
                }
            },
            onHeaderClick: function (headerCt, header, e) {
                need_select = 'all';
                if (header.isCheckerHd) {
                    e.stopEvent();
                    var me = this,
                        isChecked = header.el.hasCls(Ext.baseCSSPrefix + 'grid-hd-checker-on');

                    me.preventFocus = true;
                    if (isChecked) {
                        me.deselectAll();
                    } else {
                        me.selectAll();
                    }
                    delete me.preventFocus;
                }
            }
        });//添加复选框列  如果不想有复选框是需要把selModel换成Ext.create('Ext.selection.RowModel',{mode:"SIMPLE"})就ok了
        me.callParent();
    }
});

