// pages/publish/create-post/create-post.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    multiArray: [['房产租售', '招聘求职'], ['出租', '出售'], ['整租', '合租']],
    multiIndex: [0, 0, 0]
  },
  bindMultiPickerChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      multiIndex: e.detail.value
    })
  },
  bindMultiPickerColumnChange: function (e) {
    console.log('修改的列为', e.detail.column, '，值为', e.detail.value);
    var data = {
      multiArray: this.data.multiArray,
      multiIndex: this.data.multiIndex
    };
    data.multiIndex[e.detail.column] = e.detail.value;
    switch (e.detail.column) {
      case 0:
        switch (data.multiIndex[0]) {
          case 0:
            data.multiArray[1] = ['出租', '出售'];
            data.multiArray[2] = ['整租', '合租'];
            break;
          case 1:
            data.multiArray[1] = ['招聘', '求职'];
            data.multiArray[2] = ['全职', '兼职'];
            break;
        }
        data.multiIndex[1] = 0;
        data.multiIndex[2] = 0;
        break;
      case 1:
        switch (data.multiIndex[0]) {
          case 0:
            switch (data.multiIndex[1]) {
              case 0:
                data.multiArray[2] = ['整租', '合租'];
                break;
              case 1:
                data.multiArray[2] = ['新房','二手房'];
                break;
            }
            break;
          case 1:
            switch (data.multiIndex[1]) {
              case 0:
                data.multiArray[2] = ['全职', '兼职'];
                break;
              case 1:
                data.multiArray[2] = ['全职', '兼职'];
                break;
            }
            break;
        }
        data.multiIndex[2] = 0;
        console.log(data.multiIndex);
        break;
    }
    this.setData(data);
  },
})