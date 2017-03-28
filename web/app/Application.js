/**
 * The main application class. An instance of this class is created by app.js when it calls
 * Ext.application(). This is the ideal place to handle application launch and initialization
 * details.
 */
Ext.define('ExtFrame.Application', {
    extend: 'Ext.app.Application',

    name: 'ExtFrame',
    uses: ['ExtFrame.SimData', 'Ext.ux.ajax.*'],

    views: [
        // TODO: add views here  
    ],
    controllers: [
        'Root'//������ڿ����� 
        // TODO: add controllers here  
    ],
    stores: [
        // TODO: add global / shared stores here
    ],

    launch: function () {
        // TODO - Launch the application  
        //���������ܣ�ģ����ʵ�����з���������  
        //oaSystem.SimData.init();  
        //console.log('launch begin');  
        //this.callParent()  
        //Ext.ux.ajax.SimManager.init().register({  
        //  '/authenticate':  
        //  {  
        //    type: 'json',  
        //    data: function(ctx){  
        //        return Ext.apply({}, true);
        //    }  
        //  }  
        //});  
    }
});
