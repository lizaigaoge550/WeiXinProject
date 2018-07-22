// pages/publish/publish.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    iconList: [
      {
        iconIcon: "bank",
        iconTitle: "房产租售",
        bgColor: "#F53B3E",
        num: 0
      },
      {
        iconIcon: "taxi",
        iconTitle: "拼车出行",
        bgColor: "#16ACF0",
        num: 1
      },
      {
        iconIcon: "black-tie",
        iconTitle: "招聘求职",
        bgColor: "#FEA52D",
        num: 2
      },
      {
        iconIcon: "car",
        iconTitle: "车辆交易",
        bgColor: "#FF5115",
        num: 3
      },
      {
        iconIcon: "handshake-o",
        iconTitle: "二手买卖",
        bgColor: "#FCA62D",
        num: 4
      },
      {
        iconIcon: "user",
        iconTitle: "生活服务",
        bgColor: "#FF4F14",
        num: 5
      },
      {
        iconIcon: "paw",
        iconTitle: "宠物",
        bgColor: "#FB5007",
        num: 6
      },
      {
        iconIcon: "yen",
        iconTitle: "优惠信息",
        bgColor: "#FEA723",
        num: 7
      },
      {
        iconIcon: "search",
        iconTitle: "寻人寻物",
        bgColor: "#15ADEF",
        num: 8
      },
      {
        iconIcon: "truck",
        iconTitle: "装修建材",
        bgColor: "#37BA9B",
        num: 9
      },
      {
        iconIcon: "leaf",
        iconTitle: "农林牧渔",
        bgColor: "#37BA9B",
        num: 10
      },
      {
        iconIcon: "suitcase",
        iconTitle: "商务服务",
        bgColor: "#1BAFF0",
        num: 11
      },
      {
        iconIcon: "mortar-board",
        iconTitle: "教育培训",
        bgColor: "#FF490D",
        num: 12
      }
    ]
  },
  toJump:function(e){
    console.log(e.target);
    wx.navigateTo({
      url: 'create-post/create-post?index='+ e.target.dataset.num
    })
  }
})