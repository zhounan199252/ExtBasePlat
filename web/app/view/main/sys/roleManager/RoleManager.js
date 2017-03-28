
Ext.define(
  'ExtFrame.view.main.sys.roleManager.RoleManager',
  {
      extend: 'Ext.panel.Panel',
      requires: ['ExtFrame.view.main.sys.roleManager.RoleManagerController', 'ExtFrame.view.main.sys.roleManager.RoleManagerModel', 'ExtFrame.view.main.sys.roleManager.RoleGrid'],//请求MainController类
      layout: { type: 'border' },
      controller: 'roleManager',
      viewModel: { type: 'roleManagerModel' },
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
                  items: [{
                      xtype: 'hiddenfield',
                      name: 'oid',
                      itemId: 'hfOID',//注意，此itemId要写固定，functionjs中重置from有用到
                      bind: '{rec.oid}'
                  }, {
                      xtype: 'textfield',
                      name: 'code',
                      bind: '{rec.code}',
                      fieldLabel: '编号',
                      emptyText: '请输入编号',
                      labelWidth: 40,
                      maxLength: 25
                  }, {
                      xtype: 'textfield',
                      name: 'name',
                      bind: '{rec.name}',
                      fieldLabel: '角色名称',
                      emptyText: '请输入角色名称',
                      allowBlank: false,
                      maxLength: 25
                  }, {
                      xtype: 'combo',
                      name: 'permissions',
                      bind: '{rec.permissionsId}',
                      editable: false,// 是否允许输入
                      emptyText: '请选择拥有的权限',
                      allowBlank: false,// 不允许为空
                      blankText: '请选择拥有的权限',// 该项如果没有选择，则提示错误信息,
                      multiSelect: true,
                      queryMode: 'local',
                      displayField: 'name',
                      valueField: 'oid',
                      fieldLabel: '拥有权限',
                      store: Ext.create('ExtFrame.store.Permission', {
                          proxy: {
                              type: 'ajax',
                              url: Tools.Method.getAPiRootPath() + "/permissonInfo/getPermissionList.do?type=true",
                              reader: {
                                  type: 'json',
                                  rootProperty: 'permissions'//数据根节点名称
                              }
                          }
                      }),
                      listeners: {
                          change: function (combo, newValue, oldValue, eOpts) {
                              if ((typeof (newValue[0])).toLocaleLowerCase() == 'object') {
                                  //加载记录数据
                                  var v = [];
                                  $.each(newValue, function (index, o) {
                                      v.push(o.oid);
                                  });
                                  combo.setValue(v);
                              }
                          }
                      }
                  }, {
                      xtype: 'combo',
                      name: 'state',
                      bind: '{rec.state}',
                    //  value: 0,//默认值
                      editable: false,// 是否允许输入
                      store: Ext.create('Ext.data.Store', {
                          fields: ['text', 'value'],
                          data: [{ 'value': '0', 'text': '正常' }, { 'value': '1', 'text': '停用' }]
                      }),
                      queryMode: 'local',
                      displayField: 'text',
                      valueField: 'value',
                      fieldLabel: '状态'
                  }]
              }]
          }, {
              xtype: 'rolegrid',
              itemId: me.ename + 'Grid',
              id: 'RoleManagerGrid',
              ename: me.ename,
              region: 'center'
          }];
          me.callParent();
      }
  }
);


