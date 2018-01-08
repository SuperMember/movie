// 获取全局应用程序实例对象
const app = getApp()
const key="HISTORY_KEY"
// 创建页面实例对象
Page({
  /**
   * 页面的初始数据
   */
  data: {
    index:{},
    loading: true,
    swiper:[],
    boards:[
      {
        title:"正在上映",
        tag:"showing"
      },
      {
        title:"即将上映",
        tag:"coming"
      },
      {
        title:"top250",
        tag:"top250"
      },
      {
        title:"口碑榜",
        tag:"weekly"
      },
      {
        title:"北美票房榜",
        tag:"usmovie"
      },
      {
        title:"新片榜",
        tag:"newmovie"
      }

    ],
    history: {
      "message": "缓存",
      data: []
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    var that = this

    //保存缓存
    wx.setStorageSync(key, that.data.history);

    wx.request({
      url: 'http://xugang.free.ngrok.cc/movie/index',
      method: 'GET',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        var result=res.data;
        if(result.code=='200'){
            var swiperImg = [];
            //获取轮播图
            for (var i = 0; i < result.swiper.count; i++) {
              swiperImg.push(result.swiper.subjects[i].images.small);
            }
            that.setData({
              //navLeftItems: res.data,
              index: res.data,
              loading: false,
              swiper: swiperImg
            })
        }else{
            that.setData({
              loading: false
            })
        }
        
      },
      fail: function (e) {
        that.setData({
          loadingHidden: false
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    // TODO: onReady
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    // TODO: onShow
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {
    // TODO: onHide
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {
    // TODO: onUnload
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    // TODO: onPullDownRefresh
  }
})
