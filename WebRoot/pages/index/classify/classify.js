// pages/index/classify/classify.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    this.getInfoData();
  },
  // 获取数据
  getInfoData: function () {
    this.setData({
      infoData: {
        postId: "1",
        photo: "../../../static/image/city.png",
        name: "魏永刚",
        tag: "全职招聘-销售",
        isTop: false,
        detail: "详情：招内勤1名。业务主任3名。业务员20名。要求要有团队精神，有上进心，每月15号发工资。工作时间8个小时，名额有限，报名电话，15690018617......13483409787",
        phoneNum: "18880010008",
        images: [
          "../../../static/image/post-img.jpg",
          "../../../static/image/post-img.jpg",
          "../../../static/image/post-img.jpg",
          "../../../static/image/post-img.jpg",
          "../../../static/image/post-img.jpg",
          "../../../static/image/post-img.jpg"
        ],
        address: "河北省临漳县",
        pubTime: "1小时前",
        browse: 0,
        comments: 0
      }
    });
  },
  /*跳转到发布界面*/
  toPublish:function(){
    wx.switchTab({
      url:"../../publish/index"
    });
  }
})