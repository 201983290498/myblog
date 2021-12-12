/*
 * Author: Abdullah A Almsaeed
 * Date: 2014-01-04
 * Description:
 *      该文件仅用于主仪表盘的演示 （index.html）
 **/

/* global moment:false, Chart:false, Sparkline:false */

$(function () {
  'use strict'

  // 使用 jQuery UI 使仪表盘小部件可排序
  $('.connectedSortable').sortable({
    placeholder: 'sort-highlight',
    connectWith: '.connectedSortable',
    handle: '.card-header, .nav-tabs',
    forcePlaceholderSize: true,
    zIndex: 999999
  })
  $('.connectedSortable .card-header').css('cursor', 'move')

  // jQuery UI 可排序待办事项列表
  $('.todo-list').sortable({
    placeholder: 'sort-highlight',
    handle: '.handle',
    forcePlaceholderSize: true,
    zIndex: 999999
  })

  // bootstrap WYSIHTML5 - 文本编辑器
  $('.textarea').summernote()

  $('.daterange').daterangepicker({locale:{applyLabel: '确定',cancelLabel: '取消',customRangeLabel: '自定义范围',},locale:{applyLabel: '确定',cancelLabel: '取消',customRangeLabel: '自定义范围',},
    ranges: {
      '今天': [moment(), moment()],
      '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
      '最近7天': [moment().subtract(6, 'days'), moment()],
      '最近30天': [moment().subtract(29, 'days'), moment()],
      '本月': [moment().startOf('month'), moment().endOf('month')],
      '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
    },
    startDate: moment().subtract(29, 'days'),
    endDate: moment()
  }, function (start, end) {
    // eslint-disable-next-line no-alert
    alert('你选择了： ' + start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
  })

  /* jQueryKnob */
  $('.knob').knob()

  // jvectormap 数据
  var visitorsData = {
    US: 398, // 美国
    SA: 400, // 沙特阿拉伯
    CA: 1000, // 加拿大
    DE: 500, // 德国
    FR: 760, // 法国
    CN: 300, // 中国
    AU: 700, // 澳大利亚
    BR: 600, // 巴西
    IN: 800, // 印度
    GB: 320, // 大不列颠
    RU: 3000 // 俄罗斯
  }
  // jvectormap 世界地图
  $('#world-map').vectorMap({
    map: 'usa_en',
    backgroundColor: 'transparent',
    regionStyle: {
      initial: {
        fill: 'rgba(255, 255, 255, 0.7)',
        'fill-opacity': 1,
        stroke: 'rgba(0,0,0,.2)',
        'stroke-width': 1,
        'stroke-opacity': 1
      }
    },
    series: {
      regions: [{
        values: visitorsData,
        scale: ['#ffffff', '#0154ad'],
        normalizeFunction: 'polynomial'
      }]
    },
    onRegionLabelShow: function (e, el, code) {
      if (typeof visitorsData[code] !== 'undefined') {
        el.html(el.html() + ': ' + visitorsData[code] + ' 新访客')
      }
    }
  })

  // Sparkline 图表
  var sparkline1 = new Sparkline($('#sparkline-1')[0], { width: 80, height: 50, lineColor: '#92c1dc', endColor: '#ebf4f9' })
  var sparkline2 = new Sparkline($('#sparkline-2')[0], { width: 80, height: 50, lineColor: '#92c1dc', endColor: '#ebf4f9' })
  var sparkline3 = new Sparkline($('#sparkline-3')[0], { width: 80, height: 50, lineColor: '#92c1dc', endColor: '#ebf4f9' })

  sparkline1.draw([1000, 1200, 920, 927, 931, 1027, 819, 930, 1021])
  sparkline2.draw([515, 519, 520, 522, 652, 810, 370, 627, 319, 630, 921])
  sparkline3.draw([15, 19, 20, 22, 33, 27, 31, 27, 19, 30, 21])

  // 日历
  $('#calendar').datetimepicker({
    format: 'L',
    inline: true
  })

  // SLIMSCROLL 聊天小工具
  $('#chat-box').overlayScrollbars({
    height: '250px'
  })

  /* Chart.js Charts */




})
