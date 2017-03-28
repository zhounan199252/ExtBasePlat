Ext.define(
  'ExtFrame.view.main.sys.userManager.UserManager',
  {
      extend: 'Ext.panel.Panel',
      requires: ['ExtFrame.view.main.sys.userManager.UserManagerController', 'ExtFrame.view.main.sys.userManager.UserManagerModel', 'ExtFrame.view.main.sys.userManager.UserGrid', 'ExtFrame.view.extEncap.TreeCombo'],//请求MainController类
      layout: { type: 'border' },
      controller: 'userManager',
      viewModel: { type: 'userManagerModel' },
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
                      xtype: 'textfield',
                      itemId: 'userName',
                      name: 'userName',
                      bind: '{rec.userName}',
                      fieldLabel: '用户名',
                      allowBlank: false,// 不允许为空
                      listeners: {
                          change: function (me, newValue, oldValue, eOpts) {
                              var value = me.getValue();
                              if (value == '')
                                  valid = "请输入用户名";
                              else {
                                  var OID;
                                  if (me.oid != undefined && me.oid != '') {
                                      OID = me.oid;
                                      me.oid = '';
                                  } else {
                                      OID = me.up('#column1').down('#hfOID').getValue();
                                  }
                                  Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/baseUserInfo/getIsExist.do?oid=' + OID + '&col=userName&val=' + value, 'GET', null, false, function (jsonData) {
                                      if (jsonData) {
                                          valid = "用户名已存在，请更换";
                                      }
                                      else {
                                          valid = true;
                                      }
                                  });
                              }
                              me.validation = valid;
                          }
                      }
                  }, {
                      xtype: 'textfield',
                      name: 'userPwd',
                      bind: '{rec.userPwd}',
                      emptyText: '请输入密码',
                      allowBlank: false,
                      //inputType: 'password',
                      fieldLabel: '密码'
                  }]
              }, {
                  layout: 'column',
                  itemId: 'column2',
                  items: [{
                      xtype: 'combo',
                      name: 'state',
                      bind: '{rec.state}',
                      emptyText: '请选择状态',
                      labelWidth: 60,
                      editable: false,// 是否允许输入
                      allowBlank: false,
                      queryMode: 'local',
                      displayField: 'name',
                      valueField: 'abbr',
                      fieldLabel: '状态',
                      store: Ext.create('Ext.data.Store', {
                          fields: ['abbr', 'name'],
                          data: [{ 'abbr': '0', 'name': '正常' }, { 'abbr': '1', 'name': '停用' }]
                      })
                  }, {
                      xtype: 'treepicker',
                      itemId: 'orgPicker',
                      fieldLabel: '组织机构',
                      name: 'orgsId',
                      bind: '{rec.orgsId}',
                      displayField: 'name',
                      valueField: 'oid',
                      forceSelection: true,// 只能选择下拉框里面的内容
                      emptyText: '请选择组织机构',
                      allowBlank: false,// 不允许为空
                      blankText: '请选择组织机构',// 该项如果没有选择，则提示错误信息
                      rootVisible: false,
                      store: Ext.create('ExtFrame.store.OrgTree', {
                          root: {
                              oid: '00000000-0000-0000-0000-000000000000',
                              name: '',
                              id: '00000000-0000-0000-0000-000000000000',
                              expanded: true
                          },
                          proxy: {
                              type: 'ajax',
                              url: Tools.Method.getAPiRootPath() + '/baseOrgInfo/getOrgListForTreegrid.do',
                              reader: {
                                  type: 'json',
                                  rootproperty: 'children'//数据根节点名称
                              },
                              extraParams: {
                                  'orgId': '00000000-0000-0000-0000-000000000000'
                              }
                          },
                          listeners : {
                              nodebeforeexpand: function (node, eOpts) {
                                  //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台
                                  this.proxy.extraParams.orgId = node.id;
                              }
                          },
                          clearOnLoad: true,
                          nodeParam: 'PID'
                      }),
                      listeners: {
                          select: function (me, record, eOpts) {
                              //选择的组织机构id
                              var orgid = record.getData().oid;
                              var type = "org";
                              var rtype = "org";
                              var swhere = "oid|string|" + orgid + "|=";
                              var rwhere = swhere;
                              //alert(orgid);
                              //if (orgid == "4ED0562765A34692919995D21CB86650") { //如果选择的是公司机构) {
                              //    swhere = rwhere = "";
                              //}
                              var userGrid = me.up("#UserRoleManagerGrid");
                              var roleGrid = userGrid.up("#tab-UserRoleManager-grid").down("#RoleUserGrid");
                              //带附加参数重构grid store数据
                              userGrid.store.getProxy().extraParams = {
                                  "swhere": swhere,
                                  'xtype': type
                              };
                              //重新加载grid
                              userGrid.store.reload();
                              roleGrid.store.getProxy().extraParams = {
                                  "swhere": rwhere,
                                  'xtype': rtype
                              };
                              roleGrid.store.reload();
                          }
                      }
                  }]
              }]
          }, {
              xtype: 'usergrid',
              itemId: me.ename + 'Grid',
              ename: me.ename,
              region: 'center'
          }];
          me.callParent();
      }
  }

);