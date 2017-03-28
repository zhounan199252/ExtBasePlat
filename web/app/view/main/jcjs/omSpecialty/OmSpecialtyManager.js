Ext.define(
    'ExtFrame.view.main.jcjs.omSpecialty.OmSpecialtyManager',
    {
        extend:'Ext.panel.Panel',
        requires:['ExtFrame.view.main.jcjs.omSpecialty.OmSpecialtyManagerController',
                        'ExtFrame.view.main.jcjs.omSpecialty.OmSpecialtyTreeGrid',
                        'ExtFrame.view.main.jcjs.omSpecialty.OmSpecialtyViewModel',
                        'ExtFrame.view.main.jcjs.omSpecialty.OmSpecialtyGrid',
                        'ExtFrame.view.extEncap.TreeCombo',
                        'Ext.tree.*'],//请求MainController类
        layout: { type: 'border' },
        controller: 'omSpecialtyManager',
        viewModel: { type: 'omSpecialtyViewModel' },
        eName: '',//用于构造itemId，很重要，要和数据库存储的模块Ename对应
        initComponent:function(){
            var me=this;
            me.items=[{
                    xtype:'form',
                    itemId:me.ename+'Form',
                    ename:me.ename,
                    region:'center',
                    bodyPadding:5,
                    padding:2,
                    defaults:{
                        bodyPadding:5
                    },
                    fieldDefaults:{
                        labelAlign:'right'
                    },
                   items:[{
                            layout:'column',
                            itemId:'column1',
                            items:[{
                                    xtype:'hiddenfield',
                                    itemId:'hfOID',//注意，此itemId要写固定，functionjs中重置from有用到
                                    name:'specId',
                                    bind:'{rec.specId}'
                                },{
                                    xtype:'hiddenfield',
                                    id:'specSeries',
                                    name:'specSeries',
                                    bind:'{rec.specSeries}'
                                },{
                                    xtype:'hiddenfield',
                                    id:'maNaSpecIdHidden',
                                    name:'maNaSpecIdHidden',
                                    bind:'{rec.maNaSpecId}'
                                },{
                                    xtype:'textfield',
                                    //nanText:"请输入正整数",
                                    allowDecimals:false,
                                    minValue:0,
                                    allowNegative: false,
                                    name:'specCode',
                                    bind:'{rec.specCode}',
                                    fieldLabel:'专业代码',
                                    emptyText:'请输入专业代码'
                                 },{
                                    xtype:'textfield',
                                    name:'specName',
                                    bind:'{rec.specName}',
                                    emptyText:'请输入专业名称',
                                    fieldLabel:'专业名称'
                                },{
                                    xtype:'treepicker',
                                    rootVisible: true,
                                    name:'maNaSpecId',
                                   store:Ext.create('ExtFrame.store.ModuleTree',{
                                       fields: ['oid','text'],
                                       root: {
                                           oid:"0",
                                           id: "0",
                                           text: ""
                                       },
                                       proxy:{
                                           type: 'ajax',
                                           url:Tools.Method.getAPiRootPath()+'/omSpecialty/omSpecialtyTree.do',
                                           extraParams:{'maNaSpecId':""},
                                           reader : new Ext.data.JsonReader({},
                                               ['oid','text' ])
                                       },
                                       listeners: {
                                           nodebeforeexpand: function (node, eOpts) {
                                               //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台
                                               this.proxy.extraParams.maNaSpecId = node.id;
                                           }
                                       }
                                   }),
                                    valueField : 'oid',
                                    displayField : 'text',
                                    emptyText: '请输入专业(系列)',
                                    fieldLabel: '专业(系列)',
                                    listeners:{
                                        select:function(me, record, eOpts){
                                            var specSeries = Ext.getCmp('specSeries');
                                            if(record.id=="0"){
                                                specSeries.setValue("");
                                            }else{
                                                specSeries.setValue(record.raw.text);
                                            }
                                            //Ext.MessageBox.alert('提示', this.value);
                                            var maNaSpecIdHidden = Ext.getCmp('maNaSpecIdHidden');
                                            maNaSpecIdHidden.setValue(this.value);
                                        }
                                    }
                                },{
                                    xtype:'textfield',
                                    name:'specCpy',
                                    bind:'{rec.specCpy}',
                                    emptyText:'请输入拼音',
                                    fieldLabel:'专业拼音'
                                }]
                        }, {
                           xtype:'omSpecialtyGrid',
                           id:'omSpecialtyGrid',
                           itemId:me.ename+'Grid',
                           ename:me.ename
                           //region:'center'
                       }]
                },{
                    xtype:'omSpecialtyTreeGrid',
                    id:'omSpecialtyTreeGrid',
                    itemId:me.ename+'Tree',
                    ename:me.ename,
                    region:'west'
                }
            ];
            me.callParent();
        }
    }

);