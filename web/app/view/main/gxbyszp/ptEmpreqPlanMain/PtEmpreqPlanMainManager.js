/**
 * Created by Administrator on 2016/2/23.
 */
Ext.define(
    'ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainManager',
    {
        extend: 'Ext.panel.Panel',
        requires: ['ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainManagerController',
            'ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainManagerModel',
            'ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainGrid',
            'ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanDetailsWindow',
            'ExtFrame.view.extEncap.TreeCombo', 'Ext.tree.*'],//请求MainController类
        layout: { type: 'border' },
        controller: 'ptEmpreqPlanMainManager',
        viewModel: { type: 'ptEmpreqPlanMainManagerModel' },
        eName: '',//用于构造itemId，很重要，要和数据库存储的模块Ename对应
        initComponent: function () {
            var me = this;
            me.items = [{
                xtype: 'form',
                itemId: me.ename + 'Form',
                ename: me.ename,
                region: 'center',
                bodyPadding: 5,
                padding: 2,
                defaults: {
                    bodyPadding: 5
                },
                fieldDefaults: {
                    labelAlign: 'right'
                },
                items: [{
                    layout: 'column',
                    itemId: 'column1',
                    items: [{
                        xtype: 'hiddenfield',
                        itemId: 'hfOID',//注意，此itemId要写固定，functionjs中重置from有用到
                        name: 'oid',
                        bind: '{rec.oid}'
                    },{
                        xtype: 'hiddenfield',
                        itemId: 'unit',
                        name:'unit',
                        bind: '{rec.unit}'
                    },{
                        xtype: 'hiddenfield',
                        itemId: 'orgId',
                        name:'orgId',
                        bind: '{rec.orgId}'
                    },{
                        xtype: 'hiddenfield',
                        itemId: 'orgCode',
                        name:'orgCode',
                        bind: '{rec.orgCode}'
                    },{
                        xtype: 'numberfield',
                        nanText:"请输入正整数",
                        allowDecimals:false,
                        itemId:'nyear',
                        minValue:0,
                        allowNegative: false,
                        value:Ext.Date.format(new Date(), 'Y'),
                        name: 'nyear',
                        bind: '{rec.nyear}',
                        fieldLabel: '年度',
                        emptyText: '请输入年度',
                        allowBlank: false
                    },{
                        xtype: 'treepicker',
                        itemId: 'treepicker',
                        name: 'unit',
                        fieldLabel: '单位名称',
                        displayField: 'name',
                        valueField: 'oid',
                        forceSelection: true,// 只能选择下拉框里面的内容
                        emptyText: '请输入单位名称',
                        blankText: '请输入单位名称',// 该项如果没有选择，则提示错误信息
                        rootVisible: false,
                        initComponent: function () {
                            var treepicker = this;
                            treepicker.store = Ext.create('ExtFrame.store.OrgTree', {
                                root: {
                                    oid: '00000000-0000-0000-0000-000000000000',
                                    name: '',
                                    id: '00000000-0000-0000-0000-000000000000',
                                    expanded: true
                                },
                                proxy: {
                                    type: 'ajax',
                                    timeout: 100000000,
                                    url: Tools.Method.getAPiRootPath() + '/baseOrgInfo/getOrgListForTreegrid.do?type=false',
                                    extraParams: {
                                        'orgId': '00000000-0000-0000-0000-000000000000'
                                    },
                                    reader: {
                                        type: 'json',
                                        rootproperty: 'children'//数据根节点名称
                                    }
                                },
                                listeners : {
                                    nodebeforeexpand:function(node,eOpts){
                                        //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台
                                        this.proxy.extraParams.orgId = node.id;
                                    }
                                },
                                clearOnLoad: true
                            });
                            treepicker.callParent();
                        },
                        listeners:{
                            select:function(me, record, eOpts){
                                    me.up('#column1').down('#orgId').setValue(this.value);
                                    me.up('#column1').down('#orgCode').setValue(record.raw.code);
                                    me.up('#column1').down('#unit').setValue(record.raw.text);
                            }}

                    },  {
                        xtype: 'textfield',
                        name: 'rptPerson',
                        itemId:'rptPerson',
                        bind: '{rec.rptPerson}',
                        emptyText: '请输入填报人',
                        fieldLabel: '填报人',
                        allowBlank: false,
                        value:Ext.decode($.cookie('CurUser'))[1]

                    }, {
                        xtype: 'datefield',
                        name: 'rptDate',
                        bind: '{rec.rptDate}',
                        emptyText: '请输入填报时间',
                        fieldLabel: '填报时间',
                        editable:false,
                        allowBlank: false

                    },{
                        xtype: 'combo',
                        name: 'planStage',
                        bind: '{rec.planStage}',
                        store: Ext.create('Ext.data.Store', {
                            fields: ['text', 'value'],
                            data: [{ 'value': '基本计划', 'text': '基本计划' }, { 'value': '增报计划', 'text': '增报计划' }]
                        }),
                        emptyText: '请输入阶段计划',
                        fieldLabel: '阶段计划',
                        editable:false,
                       allowBlank: false
                    },{
                    xtype: 'textfield',
                     name: 'remark',
                    bind: '{rec.remark}',
                    emptyText: '请输入备注',
                    fieldLabel: '备注',
                     allowBlank: false
                 }]
                },
                    {  id: 'ptEmpreqPlanMaingrid',
                    xtype: 'ptEmpreqPlanMaingrid',
                    itemId: me.ename + 'Grid',
                    ename: me.ename
                }]
            }, {
                id: 'ptEmpreqPlanDetailsWindow',
                xtype: 'ptEmpreqPlanDetailsWindow',
                itemId: me.ename + 'window',
                ename: me.ename+"two",
                region: 'east',
                split:true
            }];
            me.callParent();
        }
    }

);