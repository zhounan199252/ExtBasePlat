﻿/**  
 * 提取了一个停靠组件的类，方便维护 
 */
Ext.define('ExtFrame.view.main.region.GridSearchToolbar', {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.gridsearchtoolbar',
    hasSearch: true,
    autoScroll: true,
    searchCols: [],//可查询字段数据
    hasBtn: true,
    searchEx: false,//扩展查询，当为true时，查询控件会自动构造sql语句的整个语句，当为false时，查询控件构造查询语句的“字段名|值”的“键|值”对传到后台处理
    initComponent: function () {
        var me = this;
        if (me.hasSearch) {
            var columns = me.searchCols;
            /*
            ** 构造combo数据
            ** searchable: 能否查询（缺省值为false）
            ** fieldType: 字段类型（用户查询控件拼接where字句，目前仅支持 string、int、datetime
               其中string类型使用'like'关键字查询，其余的使用'='关键字查询，缺省类型使用'like'关键字查询）
            */
            var ComboData = [];
            $.each(columns, function (i, n) {
                var dataIndex;
                if (n.searchable) {
                    var abbr = "";
                    if (me.searchEx) {
                        if (n.dataIndexEx == undefined) {
                            dataIndex = n.dataIndex;
                        }
                        else {
                            dataIndex = n.dataIndexEx;
                        }
                        if (n.fieldType == 'string') {
                            abbr = dataIndex + " like '{0}%'";
                        } else if (n.fieldType == 'int') {
                            abbr = dataIndex + " ={0}";
                        } else if (n.fieldType == 'datetime') {
                            abbr = dataIndex + " ='{0}'";
                        } else {
                            //缺省类型为 like 链接
                            abbr = dataIndex + " like '%{0}%'";
                        }
                    }
                    else {
                        if (n.dataIndexEx == undefined) {
                            dataIndex = n.dataIndex;
                        }
                        else {
                            dataIndex = n.dataIndexEx;
                        }
                        abbr = dataIndex + "|" + n.fieldType;
                    }
                    var comboItem = { "abbr": abbr, "name": n.text };
                    ComboData.push(comboItem);
                }
            });
            if (ComboData.length > 0) {
                me.items.push({
                    xtype: 'combo',
                    itemId: 'lastComboID',
                    emptyText: '请选择查询项',
                    editable: false,// 是否允许输入
                    store: Ext.create('Ext.data.Store', {
                        fields: ['abbr', 'name'],
                        data: ComboData
                    }),
                    queryMode: 'local',
                    displayField: 'name',
                    valueField: 'abbr'
                });
                me.items.push({
                    xtype: 'textfield',
                    itemId: 'lastSearchField',
                    name: 'searchField',
                    emptyText: '输入您的搜索关键词'
                });
            }
            me.items.push({
                text: '搜索',
                glyph: 0xf00e,
                handler: 'onClickSearch'
            });
            me.items.push({
                text: '增加条件',
                glyph: 0xf0a9,
                handler: 'onClickAddSearch'
            });
            me.items.push({
                text: '重置查询',
                glyph: 0xf122,
                handler: 'onClickClear'
            });
            me.items.push({
                fieldLabel: 'addSearchField',
                name: 'addSearchField',
                itemId:'addSearchField',
                text:''
            });
            me.items.push({
                xtype: 'hiddenfield',
                name: 'hiddenSearchField',
                itemId:'hiddenSearchField'
            });

        }
        if (me.hasBtn) {
            if (me.hasSearch) {
                me.items.push('->');
            }

            var userId = Ext.decode($.cookie('CurUser'))[0];
            var orgId = Ext.decode($.cookie('CurUser'))[2];
            /* 请求本页面按钮数据 */
            if ($.data(ExtBtns, me.ename + userId + orgId) == undefined) {
                Tools.Method.ExtAjaxRequestEncap(Tools.Method.getAPiRootPath() + '/baseModuleInfo/queryUserModuleButtons.do', 'GET', { 'userId': userId, 'orgId': orgId, 'moduleEname': me.ename }, false, function (jsonData) {
                    //写入缓存
                    $.data(ExtBtns, me.ename + userId + orgId, jsonData);
                });
            }
            //取出页面按钮缓存
            var btns = $.data(ExtBtns, me.ename + userId + orgId);
            $.each(btns, function (i, n) {
                if (n.flag == 1) {
                    if (n.ico != undefined && n.ico != '') {
                        me.items.push({
                            text: n.name,
                            glyph: Ext.decode(n.ico.split('|')[0]),//将字符串 转换为符合font-awesome规范的  16进制
                            handler: n.pathHandler
                        });
                    } else {
                        me.items.push({
                            text: n.name,
                            handler: n.pathHandler
                        });
                    }
                }
            });
        }
        this.callParent();
    }
});