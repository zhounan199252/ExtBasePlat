Ext.define(
    'ExtFrame.view.main.jcjs.omSpecialty.OmSpecialtyTreeGrid', {
        extend:'Ext.tree.Panel',
        alias:'widget.omSpecialtyTreeGrid',
        title:'专业(系列)',
        width:220,
        split:true,
        renderTo:document.body,
        initComponent:function(){
            var me=this;
            me.store=Ext.create('ExtFrame.store.ModuleTree',
                {root:{
                    oid:"0",
                    id:"0",
                    text:'所有专业(系列)'
                },
                    proxy:{
                        type:'ajax',
                        url:Tools.Method.getAPiRootPath()+'/omSpecialty/omSpecialtyTree.do',
                        extraParams:{
                            'maNaSpecId':''
                        }
                    },
                    listeners: {
                        nodebeforeexpand: function (node, eOpts) {
                            //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台
                            this.proxy.extraParams.maNaSpecId = node.id;
                        }
                    },
                    folderSort:true
                })
            me.on('celldblclick',function(sender, td, cellIndex, record, tr, rowIndex, e){
                //带附加参数重构grid store数据
                //获取当前grid控件
                if(record.id!=0){
                    var pnGrid=Ext.getCmp("omSpecialtyGrid");
                    pnGrid.store.getProxy().extraParams={
                        "swhere":'specId|String|'+record.id+'<<specName|String|' +record.raw.text
                    };
                    //重新加载grid
                    pnGrid.store.reload();
                }
                return true;
            });
            me.callParent();
        }
    }
)