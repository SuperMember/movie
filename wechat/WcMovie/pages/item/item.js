// 获取全局应用程序实例对象
const app = getApp()
const key = "HISTORY_KEY"
const util = require("../../utils/util.js")
// 创建页面实例对象
Page({
  /**
   * 页面的初始数据
   */
  data: {
    title: '',
    loading: true,
    movie: {},
    comments: [],
    url: '',
    hiddenmodalput: true,
    movieContent: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(params) {
    var that = this
    that.setData({
      url: app.globalData.userInfo.avatarUrl
    })
    // 电影详情
    wx.request({
      url: 'http://xugang.free.ngrok.cc/movie/one/' + params.id + "?username=" + app.globalData.userInfo.nickName,
      method: 'GET',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        var result = res.data;
        that.setData({
          movie: res.data,
          loading: false,
          comments: res.data.comments
        })
        //存进缓存中
        wx.getStorage({
          key: key,
          success: function (res) {
            //判断是否进行增加操作
            var flag = true;
            //循环历史记录，如果重复则不进行增加
            var history = res.data.data;
            for (var i = 0; i < history.length; i++) {
              if (history[i].id === result.id) {
                flag = false;
                break;
              }
            }
            if (flag) {
              res.data.data.push(result);
              wx.setStorageSync(key, res.data);
            }
          },
          fail: function () { },
          complete: function () { }
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
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
  },
  ContentInput: function (e) {
    this.setData({
      movieContent: e.detail.value
    })
  },
  //点击按钮痰喘指定的hiddenmodalput弹出框  
  modalinput: function () {
    this.setData({
      hiddenmodalput: !this.data.hiddenmodalput
    })
  },
  //取消按钮  
  cancel: function () {
    this.setData({
      hiddenmodalput: true
    });
  },
  //确认  
  confirm: function () {
    var that=this;
    if (this.data.movieContent == '') {
      wx.showToast({
        title: '内容不能为空',
      });
      return;
    }
    this.setData({
      hiddenmodalput: true
    })
    wx.showLoading({
      title: '提交中...',
    })
    const username = app.globalData.userInfo.nickName;
    const movieId = this.data.movie.id;
    const movieContent = this.data.movieContent;
    //提交数据
    wx.request({
      url: 'http://xugang.free.ngrok.cc/movie/submit',
      method: 'POST',
      data: {
        username: username,
        movieid: movieId,
        moviecontent: movieContent,
        moviename:that.data.movie.title
      },
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        wx.showToast({
          title: '提交成功',
          success:function(res){
            that.getComment();
            that.setData({
              moviecontent:''
            })
          }
        })
      },
      fail: function (e) {
        wx.showToast({
          title: '提交失败,请重试',
        })
      },
      complete: function () {
        wx.hideLoading();
      }
    })
  },
  deleteBtn: function (e) {
    //删除评论
    var id = e.target.dataset.id;
    var that = this;
    wx.showModal({
      title: '提示',
      content: '确定要删除该评论?',
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')
          //删除评论
          wx.request({
            url: 'http://xugang.free.ngrok.cc/movie/comment?id=' + id,
            method: 'DELETE',
            data: {},
            header: {
              'Accept': 'application/json'
            },
            success: function (res) {
              that.getComment();
            },
            fail: function (e) {
              wx.showToast({
                title: '删除失败,请重试',
              })
            },
            complete: function () {
            }
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },
  getComment: function () {
    var that=this;
    var username = app.globalData.userInfo.nickName;
    var id=that.data.movie.id;
    wx.request({
      url: 'http://xugang.free.ngrok.cc/movie/comment/' + id + "?username=" + username,
      method: 'GET',
      data: {},
      header: {
        'Accept': 'application/json'
      },
      success: function (res) {
        that.setData({
          comments: res.data
        })
      },
      fail: function (e) {
      },
      complete: function () {
      }
    })
  }
})
