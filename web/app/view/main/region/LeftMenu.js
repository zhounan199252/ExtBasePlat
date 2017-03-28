﻿Ext.define('ExtFrame.view.main.region.LeftMenu', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.mainleftmenu',
    title: '系统菜单',
    glyph: 0xf0c9,
    split: true,
    collapsible: false,
    floatable: false,
    viewModel: 'main',//指定后可获取MainModel中data数据块
    rootVisible: false,
    //useArrows: true,
    listeners: {
        itemclick: 'onTreeMenuClick'
    },
    initComponent: function () {
        var me = this;
        var userId = Ext.decode($.cookie('CurUser'))[0];
        var orgId = Ext.decode($.cookie('CurUser'))[2];
        me.store = Ext.create('ExtFrame.store.ModuleTree', {
            proxy: {
                type: 'ajax',
                url: Tools.Method.getAPiRootPath()+'/baseModuleInfo/queryUserModule.do',
                //扩展参数
                reader: {
                    type: 'json',
                    rootproperty: 'children'//数据根节点名称
                },
                extraParams: {
                    'userId': userId,
                    'orgId': orgId,
                    'moduleId':'00000000-0000-0000-0000-000000000000'
                }
            },listeners : {
                nodebeforeexpand:function(node,eOpts){
                    //点击父亲节点的菜单会将节点的id通过ajax请求，
                    if(node.id !="root"){
                        this.proxy.extraParams.moduleId = node.raw.oid;
                    }else{
                        this.proxy.extraParams.moduleId=node.oid;
                    }
                }
            }

        });
        this.callParent(arguments);
    }
})