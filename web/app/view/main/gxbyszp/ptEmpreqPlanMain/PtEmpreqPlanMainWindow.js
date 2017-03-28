/**
 * Created by Administrator on 2016/2/24.
 */

Ext.define('ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainWindow', {
    extend: 'Ext.window.Window',
    closable: true,
    layout: { type: 'border' },
    resizable: false,
    autoShow: true,
    modal: true,
    width: 400,
    height: 300,
    closeAction: 'destroy',
    title : '人才需求计划审核',
    buttonAlign:'center',

    initComponent: function () {
        var me = this;
        me.items = [{
            xtype: 'form',
            itemId: 'PtEmWindowForm',
            region: 'center',
            autoScroll: true,
            bodyPadding: 5,
            padding: 2,
            defaults: {
                bodyPadding: 5
            },
            fieldDefaults: {
                labelAlign: 'right'
            },
            items:[{
                xtype: 'hiddenfield',
                itemId: 'hfOID',//注意，此itemId要写固定，functionjs中重置from有用到
                value:me.selectData[0].oid,
                name: 'oid'
            },{
                xtype: 'numberfield',
                nanText:"请输入正整数",
                allowDecimals:false,
                minValue:0,
                allowNegative: false,
                name: 'nyear',
                value:me.selectData[0].nyear,
                fieldLabel: '年度',
                disabled:true
            }, {
                xtype: 'textfield',
                name: 'unit',
                value:me.selectData[0].unit,
                fieldLabel: '单位名称',
                disabled:true
            },  {
                xtype: 'textfield',
                name: 'rptPerson',
                value:me.selectData[0].rptPerson,
                fieldLabel: '填报人',
                disabled:true
            }, {
                xtype: 'datefield',
                name: 'rptDate',
                value:me.selectData[0].rptDate,
                fieldLabel: '填报时间',
                disabled:true
            }, {
                xtype: 'combo',
                name: 'isAudit',
                value:me.selectData[0].isAudit,
                bind: '{rec.isAudit}',
                store: Ext.create('Ext.data.Store', {
                    fields: ['text', 'value'],
                    data: [{ 'value': '未审批', 'text': '未审批' },
                        { 'value': '审批通过', 'text': '审批通过' },{ 'value': '审批未通过', 'text': '审批未通过' }]
                }),
                emptyText: '请输入审批状态',
                fieldLabel: '审批状态',
                allowBlank: false,
                editable:false
            },{
                xtype: 'textfield',
                name: 'remark',
                value:me.selectData[0].remark,
                fieldLabel: '备注',
                disabled:true
            }]
        }];
        me.buttons = [
            { xtype: "button", text: "保存", handler: 'onClickCheckSave' },
            { xtype: "button", text: "关闭", handler: function () { this.up("window").close(); } }
        ];
        this.callParent();
    }
});
