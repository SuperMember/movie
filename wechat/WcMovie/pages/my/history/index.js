// pages/my/collection/index.js
const key = "HISTORY_KEY"
Page({

  /**
   * 页面的初始数据
   */
  data: {
    collections: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //缓存获取历史记录
    var that = this;
    wx.getStorage({
      key: key,
      success: function (res) {
        that.setData({
          collections: res.data.data
        })
      },
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  onclear: function () {
    var that = this;
    wx.showModal({
      title: '清除',
      content: '确定清除历史记录?',
      success: function (res) {
        if (res.confirm) {
          //清除缓存
          wx.removeStorage({
            key: key,
            success: function (res) {
              console.log("缓存清除成功!");
              wx.showToast({
                title: '清除成功',
                icon: 'success',
                duration: 1000,
                success: function (res) {
                  //缓存获取历史记录
                  that.setData({
                    collections:[]
                  })
                }
              })
            },
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }

    })

  }
})