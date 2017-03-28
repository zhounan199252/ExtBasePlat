Ext.define('ExtFrame.view.main.gxbyszp.ptPreHiReEmpDeTails.PtPreHiReEmpDeTailsManager',{
    extend:'Ext.panel.Panel',
    requires:['ExtFrame.view.main.gxbyszp.ptPreHiReEmpDeTails.PtPreHiReEmpDeTailsManagerController',
        'ExtFrame.view.main.gxbyszp.ptPreHiReEmpDeTails.PtPreHiReEmpDeTailsViewModel',
        'ExtFrame.view.main.gxbyszp.ptPreHiReEmpDeTails.PtPreHiReEmpDeTailsGridLeft',
        'ExtFrame.view.main.gxbyszp.ptPreHiReEmpDeTails.PtPreHiReEmpDeTailsGrid',
        'ExtFrame.view.main.gxbyszp.ptPreHiReEmpDeTails.PtPreHiReEmpDeTailsWndow',
        'ExtFrame.view.extEncap.TreeCombo','Ext.tree.*'],//请求MainController类
    layout: { type: 'border' },
    controller: 'ptPreHiReEmpDeTailsManager',
    viewModel: { type: 'ptPreHiReEmpDeTailsViewModel' },
    eName: '',//用于构造itemId，很重要，要和数据库存储的模块Ename对应
    initComponent:function(){
        var me=this;
        me.items=[{
            xtype:'ptPreHiReEmpDeTailsGrid',
            id:'ptPreHiReEmpDeTailsGrid',
            itemId:me.ename+'Grid',
            ename:me.ename,
            region:'center'
        },{
            xtype:'ptPreHiReEmpDeTailsGridLeft',
            id:'ptPreHiReEmpDeTailsGridLeft',
            itemId:me.ename+'Tree',
            ename:me.ename,
            region:'west',
            split:true
        },{
            xtype:'ptPreHiReEmpDeTailsWndow',
            id:'ptPreHiReEmpDeTailsWndow',
            itemId:me.ename+'window',
            ename:me.ename,
            region:'east',
            split:true
        }];
        me.callParent();
    }
})