// pages/components/iconItem/iconItem.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    bgColor:{
      type:String,
      value:"#000"
    },
    iconTitle:{
      type:String,
      value:"房产租售"
    }
  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    toJump:function(){
      this.triggerEvent('toJump');
    }
  }
})
