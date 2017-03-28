Ext.define(
  'ExtFrame.view.main.test.testManager.TestManager',
  {
      extend: 'Ext.panel.Panel',
      requires: ['ExtFrame.view.main.test.testManager.TestManagerController', 'ExtFrame.view.main.test.testManager.TestManagerModel', 'ExtFrame.view.main.test.testManager.TestGrid', 'ExtFrame.view.extEncap.TreeCombo'],//请求MainController类
      layout: { type: 'border' },
      controller: 'testManager',
      viewModel: { type: 'testManagerModel' },
      eName: '',//用于构造itemId，很重要，要和数据库存储的模块Ename对应
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
                  }, {
                      xtype: 'textfield',
                      name: 'name',
                      bind: '{rec.name}',
                      fieldLabel: '姓名',
                      emptyText: '请输入姓名',
                      allowBlank: false,
                      labelWidth: 60
                  }, {
                      xtype: 'numberfield',
                      name: 'age',
                      bind: '{rec.age}',
                      emptyText: '请输入年龄',
                      fieldLabel: '年龄'
                  }, {
                      xtype: 'textfield',
                      name: 'address',
                      bind: '{rec.address}',
                      emptyText: '请输入地址',
                      allowBlank: false,
                      fieldLabel: '地址'
                  }
                      , {
                      xtype: 'datefield',
                      name: 'createTime',
                      bind: '{rec.createTime}',
                      emptyText: '请输入创建时间',
                      //format : 'Y-m-d',
                      fieldLabel: '时间'
                  }
                  ]
              }]
          }, {
              xtype: 'testgrid',
              itemId: me.ename + 'Grid',
              ename: me.ename,
              region: 'center'
          }];
          me.callParent();
      }
  }

);