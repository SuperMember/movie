var app = getApp()
Page({
    data: {
        navLeftItems: [
          {
            tag:"喜剧",
            id:1
          },
          {
            tag:"恐怖",
            id:2
          },
          {
            tag:"爱情",
            id:3
          },
          {
            tag:"动作",
            id:4
          },
          {
            tag:"科幻",
            id:5
          },
          {
            tag:"武侠",
            id:6
          },
          {
            tag:"战争",
            id:7
          },
          {
            tag:"犯罪",
            id:8
          },
          {
            tag:"惊悚",
            id:9
          },
          {
            tag:"剧情",
            id:10
          }],
        navRightItems: {},
        curNav: 1,
		    curIndex: 0,
        loadingHidden:false
    },
    onLoad: function() {

        var that = this
        
        wx.request({
            url: 'http://xugang.free.ngrok.cc/movie/search?tag=喜剧',
            method: 'GET',
            data: {},
            header: {
                'Accept': 'application/json'
            },
            success: function(res) {
                that.setData({
                    //navLeftItems: res.data,
                    navRightItems: res.data,
                    loadingHidden: true
                })
            },
            fail: function(e){
                that.setData({
                    loadingHidden: true
                })
            }
        })
    },

    //事件处理函数
    switchRightTab: function(e) {
      let id = e.target.dataset.id,
			index = parseInt(e.target.dataset.index);
		  this.setData({
			  curNav: id,
			  curIndex: index
		  })
      this.getData(e);
    },

    //请求数据
    getData: function(e){
      var that=this;
      that.setData({
          loadingHidden:false
      });
      let tag=e.target.dataset.tag;
      wx.request({
        url: 'http://xugang.free.ngrok.cc/movie/search?tag='+tag,
        method: 'GET',
        data: {},
        header: {
          'Accept': 'application/json'
        },
        success: function (res) {
          that.setData({
            //navLeftItems: res.data,
            navRightItems: res.data,
            loadingHidden: true
          })
        }
      })
    }

})