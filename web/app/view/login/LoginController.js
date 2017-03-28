Ext.define(
  'ExtFrame.view.login.LoginController',
  {
      extend: 'Ext.app.ViewController',
      alias: 'controller.login',
      onKeyUp: function (textField, e) {
          if (e.getKey() == 13) {
              this.onLoginbtnClick();
          }

      },
      //用户登录按钮事件处理
      onLoginbtnClick: function () {
          var form = this.lookupReference('form');
          if (form.isValid()) {
              this.login({
                  data: form.getValues(),
                  scope: this,
                  success: 'onLoginSuccess',
                  failure: 'onLoginFailure'
              })
          }
      },
      onLoginFailure: function () {
          Ext.getBody().unmask();
      },
      onLoginSuccess: function (userId, userName, userOrg) {
          this.fireViewEvent('login', userId, userName, userOrg);
      },
      login: function (options) {
          var curController = this;
          var t1 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正在请求页面...&nbsp;&nbsp;";
          Ext.getBody().mask(t1, 'page-loading');
          var t2 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正在登录系统...&nbsp;&nbsp;";
          curController.getView().mask(t2, 'page-loading');
          Ext.Ajax.request({
              url: Tools.Method.getAPiRootPath()+'/baseUserInfo/login.do',
              method: 'POST',
              params: options.data,
              dataType: 'json',
              contentType: 'application/json',
              success: function (response) {
                  curController.getView().unmask();
                  var jsonData = Ext.decode(response.responseText);
                  curController.onLoginReturn(options, jsonData.result, jsonData);
              },
              failure: function (response, opts) {
                  console.log('server-side failure with status code ' + response.status);
              }
          });
      },
      onLoginReturn: function (options, success, jsonData) {
          if (success) {
              Tools.Method.AddCookie("CurUser", "['" + jsonData.user.oid + "','" + jsonData.user.name + "','"+jsonData.user.orgs[0].oid+"','']",20);
              Ext.callback(options.success, options.scope, [jsonData.user.oid, jsonData.user.name, jsonData.user.orgs]);
              return;
          }
          else {
              Ext.MessageBox.alert('登录失败', '用户名密码不正确！');
          }
      }
  }
);