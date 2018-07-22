// pages/components/searchBox/searchBox.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    keywords:""
  },

  /**
   * 组件的方法列表
   */
  methods: {
    getValue:function(e){
      this.setData({
        keywords:e.detail.value
      });
    },
    goSearch:function(){
      var detail={
        keywords:this.data.keywords
      };
      var option={};
      this.triggerEvent('goSearch',detail,option);
    }
  }
})
