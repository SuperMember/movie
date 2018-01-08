var app = getApp()
Page( {
  data: {
    userInfo: {},
    projectSource: 'https://github.com/liuxuanqiang/wechat-weapp-mall',
    userListInfo: [{
        icon: '../../images/iconfont-card.png',
        text: '我的评论',
        isunread: false,
        unreadNum: 2,
        url:'../../pages/my/comment/index'
      }, {
        icon: '../../images/iconfont-icontuan.png',
        text: '历史记录',
        isunread: true,
        unreadNum: 1,
        url:'../../pages/my/history/index'
      }, {
        icon: '../../images/iconfont-kefu.png',
        text: '联系客服',
        url:'../../pages/my/kefu/index'
      }]
  },

  onLoad: function() {
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfo( function( userInfo ) {
      //更新数据
      that.setData( {
        userInfo: userInfo
      })
    })
    
  }
})