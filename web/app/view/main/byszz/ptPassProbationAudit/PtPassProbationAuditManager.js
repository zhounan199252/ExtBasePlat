/**
 * Created by Administrator on 2016/2/25.
 */
Ext.define(
    'ExtFrame.view.main.byszz.ptPassProbationAudit.PtPassProbationAuditManager',
    {
        extend: 'Ext.panel.Panel',
        requires: ['ExtFrame.view.main.byszz.ptPassProbationAudit.PtPassProbationAuditManagerController',
                    'ExtFrame.view.main.byszz.ptPassProbationAudit.PtPassProbationAuditManagerModel',
                    'ExtFrame.view.main.byszz.ptPassProbationAudit.PtPassProbationAuditGrid',
                    'ExtFrame.view.main.byszz.ptPassProbationAudit.PtPassProbationAuditWindow',
                    'ExtFrame.view.extEncap.TreeCombo', 'Ext.tree.*'],//请求MainController类
        layout: { type: 'border' },
        controller: 'ptPassProbationAuditManager',
        viewModel: { type: 'ptPassProbationAuditManagerModel' },
        eName: '',//用于构造itemId，很重要，要和数据库存储的模块Ename对应
        initComponent: function () {
            var me = this;
            me.items = [{
                id: 'ptPassProbationAuditGrid',
                xtype: 'ptPassProbationAuditGrid',
                itemId: me.ename + 'Grid',
                ename: me.ename,
                region: 'center'
                },{
                id: 'ptPassProbationAuditWindow',
                xtype: 'ptPassProbationAuditWindow',
                itemId: me.ename + 'window',
                ename: me.ename,
                region: 'east',
                split:true
            }];
            me.callParent();
        }
    }

);
