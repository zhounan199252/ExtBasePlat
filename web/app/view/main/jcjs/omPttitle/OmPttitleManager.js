Ext.define(
  'ExtFrame.view.main.jcjs.omPttitle.OmPttitleManager',
  {
      extend: 'Ext.panel.Panel',
      requires: ['ExtFrame.view.main.jcjs.omPttitle.OmPttitleManagerController','ExtFrame.view.main.jcjs.omPttitle.OmPttitleTree', 'ExtFrame.view.main.jcjs.omPttitle.OmPttitleManagerModel', 'ExtFrame.view.main.jcjs.omPttitle.OmPttitleGrid', 'ExtFrame.view.extEncap.TreeCombo', 'Ext.tree.*',],//请求MainController类
      layout: { type: 'border' },
      controller: 'omPttitleManager',
      viewModel: { type: 'omPttitleManagerModel' },
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
                      name: 'oid',
                      bind: '{rec.oid}'
                  },{
                      xtype: 'hiddenfield',
                      id: 'pttSeries',
                      name: 'pttSeries',
                      bind: '{rec.pttSeries}'
                  },{
                      xtype: 'hiddenfield',
                      id: 'maNaPttIdHidden',
                      name:'maNaPttIdHidden',
                      bind: '{rec.maNaPttId}'
                  },{
                      xtype: 'textfield',
                      nanText:"请输入正整数",
                      allowDecimals:false,
                      minValue:0,
                      allowNegative: false,
                      name: 'pttCode',
                      bind: '{rec.pttCode}',
                      fieldLabel: '职称代码',
                      emptyText: '请输入职称代码',
                      allowBlank: false
                  }, {
                      xtype: 'textfield',
                      name: 'pttName',
                      bind: '{rec.pttName}',
                      emptyText: '请输入职称名称',
                      fieldLabel: '职称名称',
                      allowBlank: false
                  }, {
                      xtype: 'treepicker',
                      itemId:'treepicker',
                      rootVisible: true,
                      name: 'maNaPttId',
                      store :Ext.create('ExtFrame.store.ModuleTree', {
                          fields: ['oid','text'],
                          root: {
                              oid:"0",
                              id: "0",
                              text: "所有职称系列"
                          },
                          proxy: {
                              type: 'ajax',
                              url: Tools.Method.getAPiRootPath() + "/omPttitle/getOmPttitleTree.do",
                              extraParams: {'maNaPttId': ""},
                              reader : new Ext.data.JsonReader({},
                                  ['oid','text' ])
                          },
                          listeners : {
                              nodebeforeexpand: function (node, eOpts) {
                                  //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台
                                  this.proxy.extraParams.maNaPttId = node.id;
                              }
                          }
                      }),
                      valueField : 'oid',
                      displayField : 'text',
                      emptyText: '请输入职称系列',
                      allowBlank: false,
                      fieldLabel: '职称系列',
                      listeners:{
                          select:function(me, record, eOpts){
                              var pttSeriesName = Ext.getCmp('pttSeries');
                              if(record.id=="0"){
                                  pttSeriesName.setValue("所有职称系列");
                              }else{
                                  pttSeriesName.setValue(record.raw.text);
                              }
                              var maNaPttIdHidden = Ext.getCmp('maNaPttIdHidden');
                              maNaPttIdHidden.setValue(this.value);
                          }}
                     }, {
                      xtype: 'textfield',
                      name: 'pttPy',
                      bind: '{rec.pttPy}',
                      emptyText: '请输入拼音',
                      fieldLabel: '职称拼音',
                      allowBlank: false
                  }]
              }, {
                  id: 'omPttitlegrid',
                  xtype: 'omPttitlegrid',
                  itemId: me.ename + 'Grid',
                  ename: me.ename
              }]
          }, {
              xtype: 'omPttitleTree',
                  id:'omPttitleTree',
              itemId: me.ename + 'Tree',
              ename: me.ename,
              region: 'west',
              split:true
          }
          ];
          me.callParent();
      }
  }

);