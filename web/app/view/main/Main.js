Ext.define(
  'ExtFrame.view.main.Main',
  {
      extend: 'Ext.container.Viewport',
      requires: ['ExtFrame.view.main.MainController', 'ExtFrame.view.main.region.LeftMenu'],//请求MainController类
      layout: { type: 'border' },
      xtype: 'app-main',
      controller: 'main',
      viewModel: { type: 'main' },
      items: [
	  	  {
	  	      xtype: 'maintop',
	  	      region: 'north'
	  	  },
		  {
		      xtype: 'mainbottom',
		      region: 'south',
		      bind: '你好，{currentUser}'
		  },
		  {
		      xtype: 'mainleftmenu',// 'mainleft','mainleftmenu'
		      region: 'west', // 左边面板    
		      width: 220,
		      split: true,
		      collapsible: true,
		      floatable: false
		  },
		  {
		      xtype: 'tabpanel',
		      id: 'main-tabpanel',
		      region: 'center',
		      items: [{
		          title: '首页',
		          itemId: 'tab-index',
		          html: '<h2>Content appropriate for the current navigation.</h2>',
                  height:500
		      }
		      ]
		  }
      ],

      initComponent: function () {
          //设置图标文件，设置后可以使用glyph属性
          Ext.setGlyphFontFamily('FontAwesome');
          this.callParent();
      }
  }

);
