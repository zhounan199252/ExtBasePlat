/**
 * Created by LvXL on 2016/2/2.
 */
Ext.define('ExtFrame.view.main.sys.dict.DictManager', {
        extend: 'Ext.panel.Panel',
        requires: ['ExtFrame.view.main.sys.dict.DictController', 'ExtFrame.view.main.sys.dict.DictModel', 'ExtFrame.view.main.sys.dict.DictGrid'],//请求MainController类
        layout: {type: 'border'},
        controller: 'dictController',
        viewModel: {type: 'dictModel'},
        ename: '',//用于构造itemId，很重要，要和数据库存储的模块Ename对应
        initComponent: function () {
            var me = this;
            me.items = [{
                xtype: 'form',
                itemId: me.ename + 'Form',
                ename: me.ename,
                region: 'north',
                bodyPadding: 3,
                padding: 2,
                defaults: {
                    bodyPadding: 5,
                    labelWidth: 50,
                },
                fieldDefaults: {
                    labelAlign: 'right'
                },
                items: [{
                    layout: 'column',
                    itemId : 'dict_column',
                    items: [{
                        xtype: 'hiddenfield',
                        itemId: 'dict_hidden_oid',//注意，此itemId要写固定，functionjs中重置from有用到
                        name: 'id',
                        bind: '{rec.id}'
                    }, {
                        xtype: 'textfield',
                        name: 'dictName',
                        bind: '{rec.dictName}',
                        fieldLabel: '字典名称',
                        emptyText: '请输入内容',
                        allowBlank: false,
                        maxLength: 100
                    }, {
                        xtype: 'textfield',
                        name: 'dictCode',
                        bind: '{rec.dictCode}',
                        fieldLabel: '字典编码',
                        emptyText: '请输入内容',
                        allowBlank: false,
                        maxLength: 50
                    },{
                        xtype: 'treepicker',
                        itemId : 'dict_parent_picker',
                        fieldLabel: '所属字典',
                        emptyText: '请选择',
                        blankText: '请选择',
                        displayField : 'dictName',
                        valueField: 'id',
                        forceSelection: true,
                        rootVisible: false,
                        allowBlank: false,
                        initComponent: function () {
                            var treepicker = this;
                            treepicker.store = Ext.create('ExtFrame.store.DictTree', {
                                root: {
                                    oid: '0000',
                                    name: '字典值',
                                    id: '0000',
                                    expanded: true
                                },
                                proxy: {
                                    type: 'ajax',
                                    url: Tools.Method.getAPiRootPath() + '/dict/queryToTreePicker.do',
                                    reader: {
                                        type: 'json',
                                        rootproperty: 'children'//数据根节点名称
                                    }
                                },
                                clearOnLoad: true
                            });
                            treepicker.callParent();
                        },
                        listeners: {
                            select: function (me, record, eOpts) {
                                me.up('#dict_column').down('#hidd_parentId').setValue(record.data.id);
                                me.up('#dict_column').down('#hidd_parentCode').setValue(record.data.dictCode);
                            }
                        }
                    }, {
                        // 父节点ID
                        xtype: 'hiddenfield',
                        itemId: 'hidd_parentId',
                        name: 'parentId',
                        bind: '{rec.parentId}',
                        listeners: {
                            change: function (me, newValue, oldValue, eOpts) {
                                me.up('#dict_column').down('#dict_parent_picker').setValue(newValue);
                            }
                        }
                    },{
                        // 父节点编码
                        xtype: 'hiddenfield',
                        itemId: 'hidd_parentCode',
                        name: 'parentCode',
                        bind: '{rec.parentCode}'
                    }]
                },{
                    layout: 'column',
                    items: [{
                        xtype: 'textfield',
                        name: 'sortno',
                        bind: '{rec.sortno}',
                        fieldLabel: '排序',
                        emptyText: '请输入内容',
                        allowBlank: false
                    }, {
                        xtype: 'combo',
                        name: 'status',
                        bind: '{rec.status}',
                        //value: '0', //默认值
                        editable: false,// 是否允许输入
                        store: Ext.create('Ext.data.Store', {
                            fields: ['text', 'value'],
                            data: [{'value': '0', 'text': '启用'}, {'value': '1', 'text': '停用'}]
                        }),
                        queryMode: 'local',
                        displayField: 'text',
                        valueField: 'value',
                        fieldLabel: '状态',
                        emptyText : '请选择'
                    }
                    //{
                    //    xtype: 'combo',
                    //    name: 'flag',
                    //    bind: '{rec.flag}',
                    //    editable: false,// 是否允许输入
                    //    emptyText: '请选择',
                    //    allowBlank: false,// 不允许为空
                    //    blankText: '请选择',// 该项如果没有选择，则提示错误信息,
                    //    store: Ext.create('ExtFrame.store.Dict', {
                    //        pageSize: 0,
                    //        proxy: {
                    //            type: 'ajax',
                    //            url: Tools.Method.getAPiRootPath() + "/dict/queryToCombo.do?parentCode=LX",
                    //            reader: {
                    //                type: 'json'
                    //                //rootProperty: 'Orgs'//数据根节点名称
                    //            }
                    //        }
                    //    }),
                    //    queryMode: 'local',
                    //    displayField: 'text',
                    //    valueField: 'value',
                    //    fieldLabel: '测试'
                    //}
                    ]
                }]
            }, {
                xtype: 'dictGrid',
                itemId: me.ename + 'Grid',
                ename: me.ename,
                region: 'center'
            }];
            me.callParent();
        }
    }
);