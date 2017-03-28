Ext.define('ExtFrame.view.main.gxbyszp.ptPreHiReEmpDeTails.PtPreHiReEmpDeTailsWndow',{
    extend:'Ext.panel.Panel',
    alias:'widget.ptPreHiReEmpDeTailsWndow',
    requires: ['ExtFrame.view.main.gxbyszp.ptPreHiReEmpDeTails.PtPreHireEmpFamMem'],
    controller: 'ptPreHiReEmpDeTailsManager',
    viewModel: { type: 'ptPreHiReEmpDeTailsViewModel' },
    title:'大中专毕业生基本信息明细表',
    layout: { type: 'border' },
    collapsible: true,
    collapsed:true,
    width: 600,
    closeAction: 'destroy',
    buttonAlign:'center',
    //renderTo:document.body,
    initComponent:function(){

        var me=this;
        me.items = [{
            xtype: 'form',
            id: 'ptPreHiReEmpDeTailsForm',
            region: 'center',
            autoScroll: true,
            bodyPadding: 5,
            fieldDefaults: {
                labelAlign: 'right',
                padding: 5
            },
            items:[ {
                xtype: 'fieldset',
                title: '人员信息',
                collapsible: true,
                defaults: {labelWidth: 89, anchor: '100%', layout: {type: 'hbox', defaultMargins: {top: 0, right: 5, bottom: 0, left: 0}}},
                items: [ {
                    xtype: 'hiddenfield',
                    itemId: 'hfOID',//注意，此itemId要写固定，functionjs中重置from有用到
                    name: 'oid',
                    bind: '{rec.oid}'
                },{
                    xtype:'textfield',
                    name:'empSeq',
                    bind: '{rec.empSeq}',
                    fieldLabel:'人员流水号'
                    },{
                    xtype:'textfield',
                    name:'workDate',
                    bind: '{rec.workDate}',
                    fieldLabel:'参加工作时间'
                },{
                    xtype:'combo',
                    name:'marry',
                    bind: '{rec.marry}',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['text', 'value'],
                        data: [{ 'value': '是', 'text': '是' },
                            { 'value': '否', 'text': '否' }]
                    }),
                    fieldLabel:'婚姻状况'
                },{
                    xtype:'textfield',
                    name:'empName',
                    bind: '{rec.empName}',
                    fieldLabel:'人员姓名'
                },{
                    xtype:'combo',
                    name:'genDer',
                    bind: '{rec.genDer}',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['text', 'value'],
                        data: [{ 'value': '男', 'text': '男' },
                            { 'value': '女', 'text': '女' }]
                    }),
                    fieldLabel:'性别'
                },{
                    xtype:'datefield',
                    name:'birthDate',
                    bind: '{rec.birthDate}',
                    fieldLabel:'出生日期',
                    allowBlank: false,
                    editable:false
                },{
                    xtype:'textfield',
                    name:'nation',
                    bind: '{rec.nation}',
                    fieldLabel:'民族'
                },{
                    xtype:'textfield',
                    name:'party',
                    bind: '{rec.party}',
                    fieldLabel:'政治面貌'
                },{
                    xtype:'textfield',
                    name:'gradDate',
                    bind: '{rec.gradDate}',
                    fieldLabel:'毕业时间'
                },{
                    xtype:'textfield',
                    name:'gradUniv',
                    bind: '{rec.gradUniv}',
                    fieldLabel:'毕业院校'
                },{
                    xtype:'textfield',
                    name:'realSpecName',
                    bind: '{rec.realSpecName}',
                    fieldLabel:'专业'
                },{
                    xtype:'textfield',
                    name:'schoolYears',
                    bind: '{rec.schoolYears}',
                    fieldLabel:'学制'
                },{
                    xtype:'textfield',
                    name:'eduBg',
                    bind: '{rec.eduBg}',
                    fieldLabel:'学历'
                },{
                    xtype:'textfield',
                    name:'degree',
                    bind: '{rec.degree}',
                    fieldLabel:'学位'
                },{
                    xtype:'textfield',
                    name:'trainMethod',
                    bind: '{rec.trainMethod}',
                    fieldLabel:'培养方式'
                },{
                    xtype:'textfield',
                    name:'unit',
                    bind: '{rec.unit}',
                    fieldLabel:'接收单位'
                },{
                    xtype:'textfield',
                    name:'posiName',
                    bind: '{rec.posiName}',
                    fieldLabel:'岗位名称'
                },{
                    xtype:'textfield',
                    name:'idEntityNo',
                    bind: '{rec.idEntityNo}',
                    fieldLabel:'身份证号'
                },{
                    xtype:'textfield',
                    name:'nppRov',
                    bind: '{rec.nppRov}',
                    fieldLabel:'籍贯省'
                },{
                    xtype:'textfield',
                    name:'npCity',
                    bind: '{rec.npCity}',
                    fieldLabel:'籍贯市县'
                },{
                    xtype:'textfield',
                    name:'npTown',
                    bind: '{rec.npTown}',
                    fieldLabel:'籍贯区乡'
                },{
                    xtype:'textarea',
                    name:'cuRadDrRootNo',
                    bind: '{rec.cuRadDrRootNo}',
                    fieldLabel:'现住址'
                },{
                    xtype:'textfield',
                    name:'linkTel',
                    bind: '{rec.linkTel}',
                    fieldLabel:'联系电话'
                },{
                    xtype:'combobox',
                    name:'isEmpChild',
                    bind: '{rec.isEmpChild}',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['text', 'value'],
                        data: [{ 'value': '是', 'text': '是' },
                            { 'value': '否', 'text': '否' }]
                    }),
                    fieldLabel:'是否职工子女'
                },{
                    xtype:'textfield',
                    name:'inGroupMethod',
                    bind: '{rec.inGroupMethod}',
                    fieldLabel:'进入集团方式'
                },{
                    xtype:'textfield',
                    name:'enRollLevel',
                    bind: '{rec.enRollLevel}',
                    fieldLabel:'录用级别'
                }]
            }, {
                xtype:'fieldset',
                title: '家庭关系',
                collapsible: true,
                defaults: {labelWidth: 89, anchor: '100%', layout: {type: 'hbox', defaultMargins: {top: 0, right: 5, bottom: 0, left: 0}}},
                items: [{
                    id: 'ptPreHireEmpFamMem',
                    xtype: 'ptPreHireEmpFamMem',
                    itemId: me.ename + 'window'
                    //ename: me.ename+"two",
                    //region: 'east'
                }]
            },{
                xtype:'fieldset',
                title: '用人单位意见',
                collapsible: true,
                defaults: {labelWidth: 89, anchor: '100%', layout: {type: 'hbox', defaultMargins: {top: 0, right: 5, bottom: 0, left: 0}}},
                items: [{
                    xtype: 'textarea',
                    name: 'unitOpinion',
                    bind: '{rec.unitOpinion}',
                    //emptyText: '',
                    fieldLabel: '用人单位意见',
                    allowBlank: false
                },{
                    xtype:'datefield',
                    name:'unitSignDate',
                    bind: '{rec.unitSignDate}',
                    fieldLabel:'用人单位审批时间'
                }]
            },{
                xtype:'fieldset',
                title: '主管部门意见',
                collapsible: true,
                defaults: {labelWidth: 89, anchor: '100%', layout: {type: 'hbox', defaultMargins: {top: 0, right: 5, bottom: 0, left: 0}}},
                items: [{
                    xtype: 'textarea',
                    name: 'buSiGroupOpinion',
                    bind: '{rec.buSiGroupOpinion}',
                    //emptyText: '',
                    fieldLabel: '主管部门审批意见',
                    allowBlank: false
                },{
                    xtype:'datefield',
                    name:'buSiGroupSignDate',
                    bind: '{rec.buSiGroupSignDate}',
                    fieldLabel:'主管部门审批时间'
                }]
            },{
                xtype:'fieldset',
                title: '集团审批意见',
                collapsible: true,
                defaults: {labelWidth: 89, anchor: '100%', layout: {type: 'hbox', defaultMargins: {top: 0, right: 5, bottom: 0, left: 0}}},
                items: [{
                    xtype: 'textarea',
                    name: 'groupOpinion',
                    bind: '{rec.groupOpinion}',
                    //emptyText: '',
                    fieldLabel: '集团审批意见',
                    allowBlank: false
                },{
                    xtype:'datefield',
                    name:'groupSignDate',
                    bind: '{rec.groupSignDate}',
                    fieldLabel:'集团审批时间'
                }]
            }
            ]
        }];
        me.buttons = [
            { xtype: "button", text: "保存", handler: 'onClickButtonSave' },
            { xtype: "button", text: "关闭", handler: function () { this.up("panel").collapse(); } }
        ];
        this.callParent();
    }
})