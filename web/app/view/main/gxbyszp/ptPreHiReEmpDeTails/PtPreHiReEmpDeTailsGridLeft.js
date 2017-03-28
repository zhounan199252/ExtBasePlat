Ext.define('ExtFrame.view.main.gxbyszp.ptPreHiReEmpDeTails.PtPreHiReEmpDeTailsGridLeft',{
    extend:'Ext.grid.GridPanel',
    alias:'widget.ptPreHiReEmpDeTailsGridLeft',
    title:'人才需求计划信息',
    width:220,
    floatable: false,
    fit:true,
    stripeRows:true,
    //rootVisible: false,
    collapsible: true,
    //renderTo: document.body,
    initComponent:function(){
        var me=this;
        var need_select = false;
        /********************** 根据具体业务需要适当修改 ***********************/
        var pageSize = 12;//分页条数
        var OrderField = 'nyear';//默认排序字段
        var OrderType = 'DESC';//默认排序类型 ASC/DESC
        var OrderField2 = 'rptDate';
        /*
         ** grid控件绑定列
         ** text: 前台显示文字, dataIndex: 数据绑定字段, sortable: 能否排序（缺省值为true）
         ** searchable: 能否查询（缺省值为false）
         ** fieldType: 字段类型（用户查询控件拼接where字句，目前仅支持 string、int、datetime
         其中string类型使用'like'关键字查询，其余的使用'='关键字查询）
         */
        me.columns = [
            { text: 'id', dataIndex: 'oid',hidden:true },
            { text: '单位名称', dataIndex: 'unit',fieldType: 'string',width:150},
            { text: '年度', dataIndex: 'nyear',fieldType: 'string'},
            { text: '填报时间', dataIndex: 'rptDate',fieldType: 'string'},
            { text: '业务群名称', dataIndex: 'busiGroup', fieldType: 'string'},
            { text: '填报人', dataIndex: 'rptPerson' ,fieldType: 'string'},
            { text: '审批状态', dataIndex: 'planStage',fieldType: 'string'},
            { text: '阶段计划', dataIndex: 'isAudit',fieldType: 'string' },
            { text: '备注', dataIndex: 'remark',fieldType: 'string' }
        ];
        //构造grid store
        me.store = Ext.create('ExtFrame.store.OmSpecialty', {
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
                    idProperty: 'oid'//id标示节点名称
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
        me.on('celldblclick', function (sender, td, cellIndex, record, tr, rowIndex, e) {
            //带附加参数重构grid store数据
            //获取当前grid控件
            //debugger;
            if(record.id!=0){
                var pnGrid=Ext.getCmp("ptPreHiReEmpDeTailsGrid");
                pnGrid.store.getProxy().extraParams = {
                    "empReqPlanId": record.raw.oid
                };
                //重新加载grid
                pnGrid.store.reload();
            }
            return true;
        });
        //grid 停靠item
        me.dockedItems = [{

            items: [ {
                xtype: 'form',
                frame: true,
                bodyPadding: '5 5 0',
                layout: 'form',

                //defaultType: 'datefield',

                items: [{
                    items: [
                        {   xtype: 'datefield',
                            name: 'rptDate',
                            emptyText:'开始时间',
                            itemId: 'startdt',
                            vtype: 'daterange',
                            endDateField: 'enddt'
                        } // id of the end date field }
                    ]
                }, {
                    items: [
                        {
                            xtype: 'datefield',
                            name: 'rptDate',
                            emptyText:'结束时间',
                            itemId: 'enddt',
                            vtype: 'daterange',
                            startDateField: 'startdt' }// id of the start date field}
                    ]
                }, {
                    items: [
                        { xtype: 'button',
                            text: '查询',
                            handler:'onClickButtonFind'}
                    ]
                }]
            } ]
        },
            {
                xtype: 'pagingtoolbar',
                store: me.store,//分页控件数据（同grid的数据保持一致）
                dock: 'bottom',
                displayInfo: true
            }];
        me.callParent();
    }
})