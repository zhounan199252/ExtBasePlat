/**
 * Created by Administrator on 2016/2/23.
 */

    Ext.define('ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanDetailsWindow', {
        extend: 'Ext.panel.Panel',
        requires: ['ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanDetailsGrid'],
        alias: 'widget.ptEmpreqPlanDetailsWindow',
        controller: 'ptEmpreqPlanMainManager',
        viewModel: { type: 'ptEmpreqPlanMainManagerModel' },
        layout: { type: 'border' },
        collapsible: true,
        collapsed:true,
        width: 800,
        closeAction: 'destroy',
        title : '人才需求计划主表录入',
        buttonAlign:'center',
        initComponent: function () {
            var me = this;
            me.items = [{
                xtype: 'form',
                id: 'ptEmpreqPlanDetailsForm',
                region: 'center',
                autoScroll: true,
                bodyPadding: 5,
                fieldDefaults: {
                    labelAlign: 'right',
                    padding:2
                },
                items:[ {
                    xtype: 'fieldset',
                    title: '基本信息',
                    collapsible: true,
                    defaults: {labelWidth: 89, anchor: '100%', layout: {type: 'hbox', defaultMargins: {top: 0, right: 5, bottom: 0, left: 0}}},
                    items: [ {
                        xtype: 'hiddenfield',
                        itemId: 'hfOID',//注意，此itemId要写固定，functionjs中重置from有用到
                        name: 'oid',
                        bind: '{rec.oid}'
                    },{
                        xtype: 'numberfield',
                        nanText:"请输入正整数",
                        allowDecimals:false,
                        minValue:0,
                        allowNegative: false,
                        name: 'nyear',
                        bind: '{rec.nyear}',
                        fieldLabel: '请输入年度',
                        emptyText: '年度',
                        allowBlank: false
                    }, {
                        xtype: 'textfield',
                        name: 'unit',
                        bind: '{rec.unit}',
                        emptyText: '请输入单位名称',
                        fieldLabel: '单位名称',
                        allowBlank: false
                    },  {
                        xtype: 'textfield',
                        name: 'rptPerson',
                        bind: '{rec.rptPerson}',
                        emptyText: '请输入填报人',
                        fieldLabel: '填报人',
                        allowBlank: false
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
                    }]},
                    {   id: 'ptEmpreqPlanDetailsgrid',
                        xtype: 'ptEmpreqPlanDetailsgrid',
                        itemId: me.ename + 'window',
                        ename: me.ename+"two"
                    }]
            }];
            me.buttons = [
                {    xtype: "button",
                    text: '增加',
                    handler : function() {
                        var grid= Ext.getCmp("ptEmpreqPlanDetailsgrid")
                         var rowEditing =grid.plugins[0];
                        rowEditing.cancelEdit();
                        var r = Ext.create( 'ExtFrame.model.OmPttitle',{
                            empreqPlanId:grid.empreqPlanId+"",
                            unit: "",
                            busiGroup: '平煤股份',
                            specName: "",
                            eduBg: "",
                            remark: ""
                        });
                        grid.store.insert(0, r);
                        rowEditing.startEdit(0, 0);
                    }
                },
                {xtype: "button", text: '删除', handler: 'onClickDel'},
                { xtype: "button", text: "关闭", handler: function () { this.up("panel").collapse(); } }
            ];
            this.callParent();
        }
    });
