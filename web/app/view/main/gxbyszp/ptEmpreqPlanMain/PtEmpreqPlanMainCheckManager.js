/**
 * Created by Administrator on 2016/2/23.
 */
Ext.define(
    'ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainCheckManager',
    {
        extend: 'Ext.panel.Panel',
        requires: ['ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainManagerController', 'ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainManagerModel', 'ExtFrame.view.main.gxbyszp.ptEmpreqPlanMain.PtEmpreqPlanMainCheckGrid', 'ExtFrame.view.extEncap.TreeCombo', 'Ext.tree.*'],//请求MainController类
        layout: { type: 'border' },
        controller: 'ptEmpreqPlanMainManager',
        viewModel: { type: 'ptEmpreqPlanMainManagerModel' },
        eName: '',//用于构造itemId，很重要，要和数据库存储的模块Ename对应
        initComponent: function () {
            var me = this;
            me.items = [{
                id: 'ptEmpreqPlanMainCheckgrid',
                xtype: 'ptEmpreqPlanMainCheckgrid',
                itemId: me.ename + 'Grid',
                ename: me.ename,
                region: 'center'
            }];
            me.callParent();
        }
    }

);