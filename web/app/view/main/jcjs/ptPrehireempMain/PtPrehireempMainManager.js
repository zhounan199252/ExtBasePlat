Ext.define(
    'ExtFrame.view.main.jcjs.ptPrehireempMain.PtPrehireempMainManager',
    {
        extend: 'Ext.panel.Panel',
        requires: ['ExtFrame.view.main.jcjs.ptPrehireempMain.PtPrehireempMainManagerController', 'ExtFrame.view.main.jcjs.ptPrehireempMain.PtPrehireempMainModel', 'ExtFrame.view.main.jcjs.ptPrehireempMain.PtPrehireempMainGrid', 'ExtFrame.view.main.jcjs.ptPrehireempMain.OrgTreeGrid', 'ExtFrame.view.extEncap.TreeCombo'],//请求MainController类
        layout: { type: 'border' },
        controller: 'ptPrehireempMainManager',
        viewModel: { type: 'ptPrehireempMainModel' },
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
                        name: 'preHireeMpTabId',
                        bind: '{rec.preHireeMpTabId}'
                    },{
                        xtype: 'textfield',
                        name: 'unit',
                        bind: '{rec.unit}',
                        fieldLabel: '单位名称',
                        emptyText: '请输入单位名称',
                        allowBlank: false,

                    }, {
                        xtype: 'textfield',
                        name: 'busiGroup',
                        bind: '{rec.busiGroup}',
                        fieldLabel: '业务群名称',
                        emptyText: '请输入业务群名称',
                    }, {
                        xtype: 'datefield',
                        name: 'rptDate',
                        bind: '{rec.rptDate}',
                        emptyText: '请输入填报时间',
                        fieldLabel: '填报时间'
                    }, {
                        xtype: 'textfield',
                        name: 'rptPerson',
                        bind: '{rec.rptPerson}',
                        emptyText: '请输入填报人',
                        allowBlank: false,
                        fieldLabel: '填报人'
                    }, {
                        xtype: 'textfield',
                        name: 'isAudit',
                        bind: '{rec.isAudit}',
                        allowBlank: false,
                        fieldLabel: '审批状态'
                    }, {
                        xtype: 'textfield',
                        name: 'remark',
                        bind: '{rec.remark}',
                        allowBlank: false,
                        fieldLabel: '备注'
                    }
                    ]
                }, {
                    xtype: 'ptPrehireempMainGrid',
                    id: 'ptPrehireempMainGrid',
                    itemId: me.ename + 'Grid',
                    ename: me.ename,
                    //region: 'south'
                }]
            },
                //    {
                //    xtype: 'workTypeGrid',
                //    itemId: me.ename + 'Grid',
                //    ename: me.ename,
                //    //region: 'south'
                //},
                {
                    xtype: 'OrgTreeGrid',
                    id:'orgTreeGrid',
                    itemId: me.ename + 'Tree',
                    ename: me.ename,
                    region: 'west',
                    width: 230,
                    split: true
                }
            ];
            me.callParent();
        }
    }

);
