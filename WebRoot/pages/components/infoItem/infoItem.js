// pages/components/infoItem/infoItem.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    infoData:{
      type:Object,
      value:{
        postId:"",
        photo: ".",
        name: "",
        tag: "",
        isTop: false,
        detail: "",
        phoneNum: "",
        images: [],
        address: "",
        pubTime: "",
        browse: 0,
        comments:0,
      }
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    imgNum: 0,
    moreStyle:"",
    showMore:"查看全文"
  },
  /*
    在组件实例进入页面节点树时执行
   */
  attached:function(){},
  /**
   * 组件的方法列表
   */
  methods: {
    // 拨打电话
    toCall:function(){
      wx.makePhoneCall({
        phoneNumber: this.properties.infoData.phoneNum 
      })
    },
    showMore:function(e){
      let msg=this.data.showMore;
      if(msg==="查看全文"){
        this.setData({
          moreStyle:"post-detail-more",
          showMore:"收起"
        });
      }else{
        this.setData({
          moreStyle:"",
          showMore: "查看全文"
        });
      }
    }
  }
})
