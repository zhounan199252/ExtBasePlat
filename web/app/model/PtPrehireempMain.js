Ext.define('ExtFrame.model.PtPrehireempMain', {
    extend: 'Ext.data.Model',
    idProperty: 'preHireeMpTabId',
    fields: [
        {name:'preHireeMpTabId', type: 'string'},
        {name:'empreqPlanId' ,type: 'Integer'},
        {name:'empreqPlanItemId' ,type: 'Integer'},
        {name:'letterSeq' ,type: 'string'},
        {name:'orgId' ,type: 'Integer'},
        {name:'orgCode' ,type: 'string'},
        {name:'unit' ,type: 'string'},
        {name:'busiGroupId' ,type: 'Integer'},
        {name:'busiGroupCode' ,type: 'string'},
        {name:'busiGroup' ,type: 'string'},
        {name:'rptDate' ,type: 'date'},
        {name:'rptPerson' ,type: 'string'},
        {name:'totalCount' ,type: 'Integer'},
        {name:'isAudit' ,type: 'string'},
        {name:'remark' ,type: 'string'}
    ]
});