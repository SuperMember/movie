// 获取全局应用程序实例对象
const app = getApp()

// 创建页面实例对象
Page({
  /**
   * 页面的初始数据
   */
  data: {
    title: '',
    subtitle: '加载中...',
    type: 'in_theaters',
    loading: true,
    hasMore: true,
    page: 0,
    size: 20,
    movies: []
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh:function() {
    wx.stopPullDownRefresh();
    this.setData({hasMore: true })
    this.loadMore()
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad (params) {
    //类型
    var type=params.type;
    var title=params.title;
    this.setData({
      type:type,
      title:title
    })
    this.loadMore()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady () {
    wx.setNavigationBarTitle({ title: this.data.title })
  },

  

  onReachBottom () {
    this.loadMore()
  },

  loadMore() {
    var that = this;
    var pg=that.data.page+1;
    this.setData({ subtitle: '加载中...', loading: true, page:pg })
    wx.request({
      url: 'http://xugang.free.ngrok.cc/movie/' + that.data.type + '?start=' + that.data.page,
      method: 'GET',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        var result = that.data.movies.concat(res.data.subjects);
        if(res.data.subjects.length==0){
          that.setData({
            hasMore:false
          })
        }
        that.setData({
          loading: false,
          movies: result
        })
      },
      fail: function (e) {
        that.setData({
          loadingHidden: false
        })
      }
    })
  }
})
