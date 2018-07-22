const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    infoList:[
      {text:'我的发布',target:'user-history'},
      {text:'修改个人信息',target:'change-info'},
    ]
  },
  //事件处理函数
  onLoad: function () {},
  getUserInfo: function (e) {
    console.log(e);
    app.globalData.userInfo = e.detail.userInfo;
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  toJump:function(e){
    let target=e.target.dataset.target;
    wx.navigateTo({
      url: target+"/"+target,
    })
  }
})
