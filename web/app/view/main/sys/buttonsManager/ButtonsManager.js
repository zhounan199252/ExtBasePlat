Ext.define(
  'ExtFrame.view.main.sys.buttonsManager.ButtonsManager',
  {
      extend: 'Ext.panel.Panel',
      requires: ['ExtFrame.view.main.sys.buttonsManager.ButtonsManagerController', 'ExtFrame.view.main.sys.buttonsManager.ButtonsManagerModel', 'ExtFrame.view.main.sys.buttonsManager.ButtonsGrid'],//请求MainController类
      layout: { type: 'border' },
      controller: 'buttonsManager',
      viewModel: { type: 'buttonsManagerModel' },
      ename: '',//用于构造itemId，很重要，要和数据库存储的模块Ename对应
      initComponent: function () {
          var me = this;
          //alert(me.down('#RoleManagerGrid'));
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
                  items: [
                      {
                          xtype: 'hiddenfield',
                          itemId: 'hfOID',//注意，此itemId要写固定，functionjs中重置from有用到
                          name: 'oid',
                          bind: '{rec.oid}'
                      },
                      {
                          xtype: 'textfield',
                          name: 'name',
                          bind: '{rec.name}',
                          fieldLabel: '名称',
                          labelWidth: 60,
                          emptyText: '请输入名称',
                          allowBlank: false,
                          maxLength: 25,
                          labelWidth: 40
                      },
                      {
                          xtype: 'textfield',
                          name: 'ename',
                          bind: '{rec.ename}',
                          fieldLabel: '英文名称',
                          emptyText: '请输入英文名称',
                          allowBlank: false,
                          maxLength: 25
                      },
                      {
                          xtype: 'textfield',
                          name: 'sortCode',
                          bind: '{rec.sortCode}',
                          fieldLabel: '排序编号',
                          emptyText: '请输入排序编号',
                          allowBlank: false
                      }, {
                          xtype: 'combo',
                          name: 'state',
                          bind: '{rec.state}',
                          editable: false,// 是否允许输入
                          store: Ext.create('Ext.data.Store', {
                              fields: ['abbr', 'name'],
                              data: [{ 'abbr': '0', 'name': '正常' }, { 'abbr': '1', 'name': '停用' }]
                          }),
                          queryMode: 'local',
                          displayField: 'name',
                          valueField: 'abbr',
                          fieldLabel: '状态'
                      }
                  ]
              },
              {
                  layout: 'column',
                  items: [{
                      xtype: 'textareafield',
                      name: 'description',
                      bind: '{rec.description}',
                      fieldLabel: '描述',
                      emptyText: '请输入按钮描述',
                      maxLength: 100,
                      width: 350,
                      labelWidth: 40
                  }]
              }]
          },{
		      xtype: 'buttonsgrid',
		      itemId: me.ename + 'Grid',
		      ename: me.ename,
		      region: 'center'
		  }];
          me.callParent();
      }
  }

);