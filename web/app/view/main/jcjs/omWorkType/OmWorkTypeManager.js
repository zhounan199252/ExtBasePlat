Ext.define(
    'ExtFrame.view.main.jcjs.omWorkType.OmWorkTypeManager',
    {
        extend: 'Ext.panel.Panel',
        requires: ['ExtFrame.view.main.jcjs.omWorkType.OmWorkTypeManagerController', 'ExtFrame.view.main.jcjs.omWorkType.OmWorkTypeManagerModel', 'ExtFrame.view.main.jcjs.omWorkType.OmWorkTypeGrid', 'ExtFrame.view.main.jcjs.omWorkType.OmWorkTreeGrid', 'ExtFrame.view.extEncap.TreeCombo'],//请求MainController类
        layout: { type: 'border' },
        controller: 'omWorkTypeManager',
        viewModel: { type: 'omWorkTypeManagerModel' },
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
                        name: 'worktypeId',
                        bind: '{rec.worktypeId}'
                    },{
                        xtype: 'textfield',
                        name: 'worktypeCode',
                        bind: '{rec.worktypeCode}',
                        fieldLabel: '工种代码',
                        emptyText: '请输入工种代码',
                        allowBlank: false,

                    }, {
                        xtype: 'textfield',
                        name: 'worktypeName',
                        bind: '{rec.worktypeName}',
                        fieldLabel: '工种名称',
                        emptyText: '请输入工种名称',
                        allowBlank: false,

                    }, {
                        xtype: 'textfield',
                        name: 'worktypePy',
                        bind: '{rec.worktypePy}',
                        emptyText: '请输入工种拼音',
                        fieldLabel: '工种拼音'
                    }, {
                        xtype: 'textfield',
                        name: 'worktypeBigcat',
                        bind: '{rec.worktypeBigcat}',
                        emptyText: '请输入工种大类',
                        allowBlank: false,
                        fieldLabel: '工种大类'
                    }, {
                        xtype: 'textfield',
                        name: 'manaworktypeId',
                        bind: '{rec.manaworktypeId}',
                        emptyText: '请输入工种编号',
                        allowBlank: false,
                        fieldLabel: '工种编号'
                    }, {
                        xtype: 'numberfield',
                        name: 'worktypeLevel',
                        bind: '{rec.worktypeLevel}',
                        emptyText: '请输入工种层次',
                        allowBlank: false,
                        fieldLabel: '工种层次'
                    }, {
                        xtype: 'numberfield',
                        name: 'sortNo',
                        bind: '{rec.sortNo}',
                        emptyText: '请输入排列序号',
                        allowBlank: false,
                        fieldLabel: '排列序号'
                    },{
                        xtype: 'textfield',
                        name: 'worktypeSeq',
                        bind: '{rec.worktypeSeq}',
                        emptyText: '请输入工种序列号',
                        allowBlank: false,
                        fieldLabel: '工种序列号'
                    },{
                        xtype: 'textfield',
                        name: 'subCount',
                        bind: '{rec.subCount}',
                        emptyText: '请输入子节点数',
                        allowBlank: false,
                        fieldLabel: '子节点数'
                    }, {
                        xtype: 'textfield',
                        name: 'worktypeCatalog',
                        bind: '{rec.worktypeCatalog}',
                        emptyText: '请输入工种分类',
                        fieldLabel: '工种分类'
                    },{
                        xtype: 'textfield',
                        name: 'remark',
                        bind: '{rec.remark}',
                        emptyText: '请输入备注',
                        fieldLabel: '备注'
                    }
                    ]
                }, {
                    xtype: 'omWorkTypeGrid',
                       id: 'omWorkTypeGrid',
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
                xtype: 'omWorkTreeGrid',
                    id:'omWorkTreeGrid',
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
