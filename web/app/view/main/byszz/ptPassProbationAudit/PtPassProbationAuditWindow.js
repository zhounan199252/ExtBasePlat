/**
 * Created by Administrator on 2016/2/25.
 */

Ext.define('ExtFrame.view.main.byszz.ptPassProbationAudit.PtPassProbationAuditWindow', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.ptPassProbationAuditWindow',
    controller: 'ptPassProbationAuditManager',
    viewModel: { type: 'ptPassProbationAuditManagerModel'},
    layout: { type: 'border' },
    collapsible: true,
    collapsed:true,
    width: 300,
    closeAction: 'destroy',
    title : '人才需求计划审核',
    buttonAlign:'center',
    initComponent: function () {
        var me = this;
        me.items = [{
            xtype: 'form',
            id: 'ptPassProbationAuditForm',
            region: 'center',
            autoScroll: true,
            bodyPadding: 5,
            fieldDefaults: {
                labelAlign: 'right',
                padding:5
            },
            items:[ {
                xtype: 'fieldset',
                itemId:'fieldset',
                    title: '人员基本信息',
                collapsible: true,
                defaults: {labelWidth: 89, anchor: '100%', layout: {type: 'hbox', defaultMargins: {top: 0, right: 5, bottom: 0, left: 0}}},
                items: [ {
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
                    itemId: 'unitId',
                    name:'unitId',
                    bind: '{rec.unitId}'
                },{
                    xtype: 'hiddenfield',
                    itemId: 'unitCode',
                    name:'unitCode',
                    bind: '{rec.unitCode}'
                }, {
                    xtype: 'textfield',
                    name: 'empName',
                    bind: '{rec.empName}',
                    emptyText: '请输入姓名',
                    fieldLabel: '姓名',
                    allowBlank: false
                },  {
                    xtype: 'combo',
                    name: 'gender',
                    bind: '{rec.gender}',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['text', 'value'],
                        data: [{ 'value': '男', 'text': '男' },
                            { 'value': '女', 'text': '女' }]
                    }),
                    emptyText: '请输入性别',
                    fieldLabel: '性别',
                    allowBlank: false,
                    editable:false
                }, {
                    xtype: 'datefield',
                    name: 'birthDate',
                    bind: '{rec.birthDate}',
                    emptyText: '请输入出生年月',
                    fieldLabel: '出生年月',
                    allowBlank: false,
                    editable:false
                }, {
                    xtype: 'combo',
                    name: 'nation',
                    bind: '{rec.nation}',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['text', 'value'],
                        data: [{ 'value': '汉', 'text': '汉' },
                            { 'value': '苗', 'text': '苗' }]
                    }),
                    emptyText: '请输入民族',
                    fieldLabel: '民族',
                    allowBlank: false,
                    editable:false
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
                            me.up('#fieldset').down('#unitId').setValue(this.value);
                            me.up('#fieldset').down('#unitCode').setValue(record.raw.code);
                            me.up('#fieldset').down('#unit').setValue(record.raw.text);
                        }}

                }, {
                    xtype: 'textfield',
                    name: 'department',
                    bind: '{rec.department}',
                    emptyText: '请输入部门',
                    fieldLabel: '部门',
                    allowBlank: false
                }, {
                    xtype: 'textfield',
                    name: 'duty',
                    bind: '{rec.duty}',
                    emptyText: '请输入职务',
                    fieldLabel: '职务',
                    allowBlank: false
                }, {
                    xtype: 'combo',
                    name: 'eduBg',
                    bind: '{rec.eduBg}',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['text', 'value'],
                        data: [{ 'value': '本科', 'text': '本科' },
                            { 'value': '博士', 'text': '博士' }]
                    }),
                    emptyText: '请输入学历',
                    fieldLabel: '学历',
                    allowBlank: false,
                    editable:false
                }, {
                    xtype: 'datefield',
                    name: 'gradDate',
                    bind: '{rec.gradDate}',
                    emptyText: '请输入毕业时间',
                    fieldLabel: '出生毕业时间',
                    allowBlank: false,
                    editable:false
                },{
                    xtype: 'textfield',
                    name: 'gradUniv',
                    bind: '{rec.gradUniv}',
                    emptyText: '请输入毕业院校',
                    fieldLabel: '毕业院校',
                    allowBlank: false
                }, {
                    xtype: 'textfield',
                    name: 'specName',
                    bind: '{rec.specName}',
                    emptyText: '请输入所学专业',
                    fieldLabel: '所学专业',
                    allowBlank: false
                }, {
                    xtype: 'textfield',
                    name: 'studyPeriod',
                    bind: '{rec.studyPeriod}',
                    emptyText: '请输入修业年限',
                    fieldLabel: '修业年限'
                } ,{
                    xtype: 'datefield',
                    name: 'workDate',
                    bind: '{rec.workDate}',
                    emptyText: '请输入参加工作时间',
                    fieldLabel: '参加工作时间',
                    allowBlank: false,
                    editable:false
                },{
                    xtype: 'textarea',
                    name: 'remark',
                    bind: '{rec.remark}',
                    emptyText: '请输入备注',
                    fieldLabel: '备注'
                }]},
                {   xtype: 'fieldset',
                    title: '定级信息',
                    collapsible: true,
                    defaults: {labelWidth: 89, anchor: '100%', layout: {type: 'hbox', defaultMargins: {top: 0, right: 5, bottom: 0, left: 0}}},
                    items: [{
                        xtype: 'combo',
                        name: 'practicePositionRank',
                        bind: '{rec.practicePositionRank}',
                        store: Ext.create('Ext.data.Store', {
                            fields: ['text', 'value'],
                            data: [{ 'value': '三岗副A|750', 'text': '三岗副A|750' },
                                { 'value': '三岗副B|690', 'text': '三岗副A|690' }]
                        }),
                        emptyText: '请输入见习岗次',
                        fieldLabel: '见习岗次',
                        allowBlank: false,
                        editable:false
                    },{
                        xtype: 'textfield',
                        name: 'practiceSalaryStand',
                        bind: '{rec.practiceSalaryStand}',
                        emptyText: '请输入见习标准',
                        fieldLabel: '见习标准',
                        allowBlank: false
                    }, {
                        xtype: 'combo',
                        name: 'gradPositionRank',
                        bind: '{rec.gradPositionRank}',
                        store: Ext.create('Ext.data.Store', {
                            fields: ['text', 'value'],
                            data: [{ 'value': '三岗副A|750', 'text': '三岗副A|750' },
                                { 'value': '三岗副B|690', 'text': '三岗副A|690' }]
                        }),
                        emptyText: '请输入定级岗次',
                        fieldLabel: '定级岗次',
                        allowBlank: false,
                        editable:false
                    },{
                        xtype: 'textfield',
                        name: 'gradSalaryStand',
                        bind: '{rec.gradSalaryStand}',
                        emptyText: '请输入定级标准',
                        fieldLabel: '定级标准',
                        allowBlank: false
                    },{
                        xtype: 'textarea',
                        name: 'techLevel',
                        bind: '{rec.techLevel}',
                        emptyText: '请输入见习期业务技术水平',
                        fieldLabel: '见习期业务技术水平',
                        allowBlank: false
                    }]},
                {   xtype: 'fieldset',
                    title: '单位意见',
                    collapsible: true,
                    defaults: {
                        labelWidth: 89,
                        anchor: '100%',
                        layout: {type: 'hbox', defaultMargins: {top: 0, right: 5, bottom: 0, left: 0}}},
                    items: [{
                        xtype: 'combo',
                        name: 'unitOption',
                        bind: '{rec.unitOption}',
                        store: Ext.create('Ext.data.Store', {
                            fields: ['text', 'value'],
                            data: [{ 'value': '同意按期转正', 'text': '同意按期转正' },
                                { 'value': '延期转正', 'text': '延期转正' }]
                        }),
                        emptyText: '请输入呈报单位意见',
                        fieldLabel: '呈报单位意见',
                        allowBlank: false,
                        editable:false
                    },{
                        xtype: 'textfield',
                        name: 'unitSignature',
                        bind: '{rec.unitSignature}',
                        emptyText: '请输入呈报单位负责人',
                        fieldLabel: '呈报单位负责人',
                        allowBlank: false
                    }, {
                        xtype: 'datefield',
                        name: 'unitSignDate',
                        bind: '{rec.unitSignDate}',
                        emptyText: '请输入呈报单位签署时间',
                        fieldLabel: '呈报单位签署时间',
                        allowBlank: false,
                        editable:false
                    }]} ]
        }];
        me.buttons = [
            { xtype: "button", text: "保存", handler: 'onClickButtonSave' },
            { xtype: "button", text: "关闭", handler: function () { this.up("panel").collapse(); } }
        ];
        this.callParent();
    }
});
