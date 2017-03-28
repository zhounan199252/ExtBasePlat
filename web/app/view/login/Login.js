Ext.define(
  'ExtFrame.view.login.Login',
  {
      requires: ['ExtFrame.view.login.LoginController'],
      extend: 'Ext.window.Window',
      controller: 'login',
      closable: false,
      resizable: false,
      modal: true,
      //draggable: false,
      autoShow: true,
      title: '用户登录---权限管理平台',
      glyph: 'xf007@FontAwesome',
      items: [{
          xtype: 'form',//父窗体
          reference: 'form',
          bodyPadding: 20,
          items: [{
              xtype: 'textfield',
              name: 'userName',
              labelWidth: 50,
              fieldLabel: '用户名',
              allowBlank: false,
              emptyText: '用户名或邮箱地址',
              value:'admin',
              enableKeyEvents: true,
              listeners: {
                  keyup: 'onKeyUp'
              }
          }, {
              xtype: 'textfield',
              name: 'userPwd',
              labelWidth: 50,
              inputType: 'password',
              fieldLabel: '密  码',
              allowBlank: false,
              emptyText: '请输入您的密码',
              value:'123456',
              enableKeyEvents: true,
              listeners: {
                  keyup: 'onKeyUp'
              }
          }]
      }],
      buttons: [{
          name: 'registbutton',
          text: '用户注册',
          glyph: 'xf118@FontAwesome'
      }, {
          name: 'loginbutton',
          text: '用户登录',
          glyph: 'xf110@FontAwesome',
          region: 'center',
          listeners: {
              click: 'onLoginbtnClick'//单击事件 调用LoginConroller.js中的onLoginbtnClick函数
          }
      }]
  }
);