// pages/user-center/change-info/change-info.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo:{},
    genderList:[
      {text:"男",value:1,checked:false},
      {text:"女",value:2,checked:false}
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      userInfo:app.globalData.userInfo
    });  
  },
  // 选头像
  chooseAvatar(){
    let tempFilePaths;
    wx.chooseImage({
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        tempFilePaths = res.tempFilePaths
        app.globalData.userInfo.avatarUrl = tempFilePaths;
      }
    })
  },
  // 改性别
  radioChange(e){
    console.log(e);
  }
})