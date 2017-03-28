﻿Ext.define(
  'ExtFrame.view.main.sys.moduleManager.ModuleManager',
  {
      extend: 'Ext.panel.Panel',
      requires: ['ExtFrame.view.main.sys.moduleManager.ModuleManagerController', 'ExtFrame.view.main.sys.moduleManager.ModuleManagerModel', 'ExtFrame.view.main.sys.moduleManager.ModuleGrid'],//请求MainController类
      layout: { type: 'border' },
      controller: 'moduleManager',
      viewModel: { type: 'moduleManagerModel' },
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
                  xtype: 'hiddenfield',
                  itemId: 'hfOID',
                  name: 'oid',
                  bind: '{rec.oid}'
              }, {
                  xtype: 'hiddenfield',
                  name: 'pathHandler',
                  bind: '{rec.pathHandler}'
              }, {
                  xtype: 'hiddenfield',
                  name: 'LT',
                  bind: '{rec.LT}'
              }, {
                  xtype: 'hiddenfield',
                  name: 'RT',
                  bind: '{rec.RT}'
              }, {
                  xtype: 'hiddenfield',
                  name: 'treeLevel',
                  bind: '{rec.treeLevel}'
              }, {
                  layout: 'column',
                  itemId: 'column1',
                  items: [{
                      xtype: 'combo',
                      name: 'flag',
                      bind: '{rec.flag}',
                      itemId: 'moduleManagerFlag',
                      editable: false,// 是否允许输入
                      emptyText: '请选择',
                      allowBlank: false,// 不允许为空
                      blankText: '请选择',// 该项如果没有选择，则提示错误信息,
                      store: Ext.create('ExtFrame.store.Org', {
                          pageSize: 0,
                          proxy: {
                              type: 'ajax',
                              url: Tools.Method.getAPiRootPath() + "/baseUtil/getDictList.do?fileName=MODULETYPE",
                              reader: {
                                  type: 'json'
                                  //rootProperty: 'Orgs'//数据根节点名称
                              }
                          }
                      }),
                      queryMode: 'local',
                      displayField: 'text',
                      valueField: 'value',
                      fieldLabel: '类型',
                      labelWidth: 40,
                      listeners: {
                          change: function (me, newValue, oldValue, eOpts) {
                              var EnameTextFiled = me.up('#ModuleManagerForm').down('#ename');
                              EnameTextFiled.setValue('');
                          }
                      }
                  },
                  {
                      xtype: 'hiddenfield',
                      itemId: 'hfModule',
                      name: 'parentOid',
                      bind: '{rec.parentOid}',
                      listeners: {
                          change: function (me, newValue, oldValue, eOpts) {
                              me.up('#column1').down('#modulePicker').setValue(newValue);
                          }
                      }
                  },

                      {
                          xtype: 'treepicker',
                          itemId: 'modulePicker',
                          fieldLabel: '上级菜单',
                          displayField: 'name',
                          valueField: 'oid',
                          forceSelection: true,// 只能选择下拉框里面的内容
                          emptyText: '请选择',
                          blankText: '请选择',// 该项如果没有选择，则提示错误信息
                          labelWidth: 60,
                          rootVisible: false,
                          store: Ext.create('ExtFrame.store.ModuleTree', {
                              root: {
                                  oid: '00000000-0000-0000-0000-000000000000',
                                  name: '',
                                  id: '00000000-0000-0000-0000-000000000000',
                                  expanded: true
                              },
                              proxy: {
                                  type: 'ajax',
                                  url: Tools.Method.getAPiRootPath() + '/baseModuleInfo/getModuleListForTreegrid.do?type=false',
                                  reader: {
                                      type: 'json',
                                      rootproperty: 'children'//数据根节点名称
                                  },
                                  extraParams: {
                                      'moduleId': '00000000-0000-0000-0000-000000000000'
                                  }
                              },
                              listeners : {
                                  nodebeforeexpand:function(node,eOpts){
                                      //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台
                                      if(node.id =='00000000-0000-0000-0000-000000000000'){
                                          this.proxy.extraParams.moduleId = node.oid;
                                      }else{
                                          this.proxy.extraParams.moduleId = node.raw.oid;

                                      }
                                  }
                              },
                              clearOnLoad: true,
                              nodeParam: 'PID'
                          }),
                          listeners : {
                              select: function (me, record, eOpts) {
                                  me.up('#column1').down('#hfModule').setValue(record.data.oid);
                              }
                          }
                      },
                  {
                      xtype: 'textfield',
                      name: 'pathHandler',
                      bind: '{rec.pathHandler}',
                      fieldLabel: '类名/事件',
                      maxLength: 100
                  },
                  {
                      xtype: 'combo',
                      name: 'buttonId',
                      itemId: 'buttonId',
                      bind: '{rec.buttonId}',
                      editable: false,// 是否允许输入
                      emptyText: '请选择',
                      queryMode: 'local',
                      displayField: 'name',
                      valueField: 'oid',
                      fieldLabel: '所属按钮',
                      store: Ext.create('ExtFrame.store.Module', {
                          proxy: {
                              type: 'ajax',
                              url: Tools.Method.getAPiRootPath() + "/buttons/getList.do",
                              reader: {
                                  type: 'json',
                                  rootProperty: 'buttons'//数据根节点名称
                              }
                          },
                          listeners: {
                              load: function (store, records, successful, eOpts) {
                                  if (records.length > 0) {
                                      var record = { name: '无', oid: '' };
                                      store.insert(0, record)
                                  }
                              }
                          }
                      }),
                      listeners: {
                          //捕捉异常处理
                          select: function (combo, record, index) {
                              var data = record.getData();
                              if (data.oid != '') {
                                  var json = this.up("#ModuleManagerForm").getForm().getValues();
                                  json.name = data.name;
                                  json.ico = data.ico;
                                  json.ename = data.ename;
                                  json.description = data.description;
                                  json.sortCode = data.sortCode;
                                  json.pathHandler = data.eventMethod;
                                  json.permissions = null;
                                  this.up("#ModuleManagerForm").getForm().setValues(json);
                              }
                          },
                          exception: function (theproxy, response, operation, options) {
                              Tools.Method.ExceptionEncap(response);
                          }
                      }
                  }]
              }, {
                  layout: 'column',
                  items: [{
                      xtype: 'textfield',
                      name: 'name',
                      itemId: 'name',
                      bind: '{rec.name}',
                      fieldLabel: '名称',
                      labelWidth: 40,
                      allowBlank: false,// 不允许为空
                      maxLength: 25
                  }, {
                      xtype: 'textfield',
                      name: 'ename',
                      itemId: 'ename',
                      bind: '{rec.ename}',
                      fieldLabel: '英文名称',
                      vtype: 'OnlyEnglishAndNum',
                      allowBlank: false,// 不允许为空
                      maxLength: 50,
                      listeners: {
                          change: function (me, newValue, oldValue, eOpts) {
                              var value = me.getValue();
                              if (value == '')
                                  valid = "请输入用户名";
                              else {
                                  var FlagValue = me.up('#ModuleManagerForm').down('#moduleManagerFlag').getValue();
                                  if (FlagValue == 0) {
                                      var OID;
                                      if (me.oid != undefined && me.oid != '') {
                                          OID = me.oid;
                                          me.oid = '';
                                      } else {
                                          OID = me.up('#ModuleManagerForm').down('#hfOID').getValue();
                                      }
                                      Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/baseModuleInfo/getIsExist.do?oid=' + OID + '&col=ename&val=' + value, 'GET', null, false, function (jsonData) {
                                          if (jsonData) {
                                              valid = "英文名称已存在，请更换";
                                          }
                                          else {
                                              valid = true;
                                          }
                                      });
                                  } else {
                                      valid = true;
                                  }
                              }
                              me.validation = valid;
                          }
                      }
                  }, {
                      xtype: 'displayfield',
                      name: 'ico',
                      itemId: 'ico',
                      id: 'MemuIco',
                      bind: '{rec.ico}',
                      fieldLabel: '图  标',
                      width: 194,
                      maxLength: 50

                  }, {
                      xtype: 'button',
                      scale: 'small',
                      text: '选择图标',
                      glyph: 0xf03e,
                      ename: 'ChoiceIco',
                      //style: {background:'#FFFFFF'},
                      handler: 'onClickButtonIco',
                      listeners: {
                          beforerender: function (btn, eOpts) {
                              if (Tools.Method.getPageBtnPower(btn.ename, me.ename))
                                  btn.hidden = false;
                              else {
                                  btn.hidden = true;
                              }
                          }
                      }
                  }, {
                      xtype: 'textfield',
                      name: 'url',
                      itemId: 'url',
                      bind: '{rec.url}',
                      fieldLabel: 'action路径',
                      maxLength: 200
                  }]
              },
              {
                  layout: 'column',
                  items: [{
                      xtype: 'textfield',
                      name: 'code',
                      itemId: 'code',
                      bind: '{rec.code}',
                      fieldLabel: '编号',
                      allowBlank: false,// 不允许为空
                      maxLength: 25,
                      labelWidth: 40
                  }, {
                      xtype: 'textfield',
                      name: 'sortCode',
                      itemId: 'sortCode',
                      bind: '{rec.sortCode}',
                      fieldLabel: '排序编号'

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
                     // value: 0,//默认值
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

              },
              {
                  layout: 'column',
                  items: [{
                      xtype: 'textareafield',
                      name: 'description',
                      bind: '{rec.description}',
                      fieldLabel: '描述',
                      emptyText: '请书写描述内容',
                      width: 450,
                      labelWidth: 40,
                      maxLength: 100
                  }]
              }]
          }, {
              xtype: 'modulegrid',
              itemId: me.ename + 'Grid',
              id: 'ModuleManagerGrid',
              ename: me.ename,
              region: 'center'
          }];
          me.callParent();
      }
  }

);
