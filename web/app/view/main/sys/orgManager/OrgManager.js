﻿Ext.define(
  'ExtFrame.view.main.sys.orgManager.OrgManager',
  {
      extend: 'Ext.panel.Panel',
      requires: ['ExtFrame.view.main.sys.orgManager.OrgManagerController', 'ExtFrame.view.main.sys.orgManager.OrgManagerModel', 'ExtFrame.view.main.sys.orgManager.OrgGrid'],//请求MainController类
      layout: { type: 'border' },
      controller: 'orgManager',
      viewModel: { type: 'orgManagerModel' },
      ename: '',//用于构造itemId，很重要，要和数据库存储的模块Ename对应
      initComponent: function () {
          var me = this;
          me.items = [{
              xtype: 'form',
              itemId: me.ename + 'Form',
              ename: me.ename,
              region: 'north',
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
                          name: 'treeLevel',
                          bind: '{rec.treeLevel}'
                      },
                     {
                      xtype: 'hiddenfield',
                      itemId: 'hfLT',//注意，此itemId要写固定，functionjs中重置from有用到
                      name: 'lt'
                  }, {
                      xtype: 'hiddenfield',
                      itemId: 'hfRT',//注意，此itemId要写固定，functionjs中重置from有用到
                      name: 'rt'
                  }, {
                      xtype: 'textfield',
                      name: 'name',
                      bind: '{rec.name}',
                      fieldLabel: '机构名称',
                      emptyText: '请输入机构名称',
                      allowBlank: false,
                      labelWidth: 60
                  }, {
                      xtype: 'textfield',
                      name: 'englishName',
                      bind: '{rec.englishName}',
                      emptyText: '请输入英文名称',
                      allowBlank: false,
                      fieldLabel: '英文名称'
                  }, {
                      xtype: 'textfield',
                      name: 'shortName',
                      bind: '{rec.shortName}',
                      emptyText: '请输入机构简称',
                      fieldLabel: '机构简称'
                  }, {
                      xtype: 'hiddenfield',
                      itemId: 'hfOrg',
                      name: 'parentOid',
                      bind: '{rec.parentOid}',
                      listeners: {
                          change: function (me, newValue, oldValue, eOpts) {
                              me.up('#column1').down('#orgPicker').setValue(newValue);
                          }
                      }
                  }, {
                      xtype: 'treepicker',
                      itemId: 'orgPicker',
                      fieldLabel: '上级机构',
                      displayField: 'name',
                      valueField: 'oid',
                      forceSelection: true,// 只能选择下拉框里面的内容
                      emptyText: '请选择',
                      blankText: '请选择',// 该项如果没有选择，则提示错误信息
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
                      listeners: {
                          select: function (me, record, eOpts) {
                              me.up('#column1').down('#hfOrg').setValue(record.data.oid);
                              var rt = Number(me.up('#column1').down('#hfRT').getValue());
                              var lt = Number(me.up('#column1').down('#hfLT').getValue());
                              if (Number(record.data.lt) >= lt && Number(record.data.lt) <= rt) {
                                  me.valid = 'false';
                                  me.setFieldStyle({
                                      backgroundColor: '#fc7c7c'
                                  });
                              } else {
                                  me.valid = 'true';
                                  me.setFieldStyle({
                                      backgroundColor: '#fff'
                                  });
                              }

                          }
                      }
                  }]
              }, {
                  layout: 'column',
                  itemId: 'column2',
                  items: [{
                      xtype: 'textfield',
                      name: 'code',
                      bind: '{rec.code}',
                      emptyText: '请输入机构编号',
                      allowBlank: false,
                      fieldLabel: '机构编号',
                      labelWidth: 60
                  }, {
                      xtype: 'textfield',
                      name: 'orgNo',
                      bind: '{rec.orgNo}',
                      emptyText: '请输入机构号',
                      fieldLabel: '机构号'
                  },
                  //    {
                  //    xtype: 'combo',
                  //    name: 'level',
                  //    bind: '{rec.level}',
                  //    itemId: 'level',
                  //    editable: false,// 是否允许输入
                  //    emptyText: '请选择',
                  //    allowBlank: false,// 不允许为空
                  //    blankText: '请选择',// 该项如果没有选择，则提示错误信息,
                  //    store: Ext.create('Ext.data.Store', {
                  //        fields: ['abbr', 'name'],
                  //        data: [{ 'abbr': '0', 'name': '省' }, { 'abbr': '1', 'name': '市' }, { 'abbr': '2', 'name': '县' }]
                  //    }),
                  //    queryMode: 'local',
                  //    displayField: 'name',
                  //    valueField: 'abbr',
                  //    fieldLabel: '机构级别',
                  //    listeners: {
                  //        afterRender: function (combo) {
                  //            combo.setValue(combo.getStore().getAt(0).data.abbr);
                  //        }
                  //    }
                  //},
                      {
                      xtype: 'combo',
                      name: 'state',
                      bind: '{rec.state}',
                      itemId: 'state',
                      editable: false,// 是否允许输入
                      emptyText: '请选择',
                      allowBlank: false,// 不允许为空
                      blankText: '请选择',// 该项如果没有选择，则提示错误信息,
                      store: Ext.create('Ext.data.Store', {
                          fields: ['abbr', 'name'],
                          data: [{ 'abbr': '0', 'name': '正常' }, { 'abbr': '1', 'name': '停用' }]
                      }),
                      queryMode: 'local',
                      displayField: 'name',
                      valueField: 'abbr',
                      fieldLabel: '状态',
                      listeners: {
                          afterRender: function (combo) {
                              combo.setValue(combo.getStore().getAt(0).data.abbr);
                          }
                      }
                  }]
              }, {
                  layout: 'column',
                  itemId: 'column3',
                  items: [{
                      xtype: 'textfield',
                      name: 'sortCode',
                      bind: '{rec.sortCode}',
                      emptyText: '请输入机构排序标号',
                      fieldLabel: '排序',
                      labelWidth: 60
                  }, {
                      xtype: 'textareafield',
                      name: 'description',
                      bind: '{rec.description}',
                      fieldLabel: '描述',
                      emptyText: '请书写描述内容',
                      width: 450
                  }]
              }]
          }, {
              xtype: 'orggrid',
              itemId: me.ename + 'Grid',
              ename: me.ename,
              region: 'center'
          }];
          me.callParent();
      }
  }

);