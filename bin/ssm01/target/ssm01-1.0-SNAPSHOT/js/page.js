(function ($, window, document, undefined) {
    // 定义类
    function paging(element, option) {
        this.element = element;
        this.option = {
            currentPage: option.currPage || 1,
            classStyle: option.classStyle,
            backClass: option.backClass,
            isFirst: ((option.isFirst == undefined) ? true : option.isFirst), // 是否显示首页和尾页
            isPre: ((option.isPre == undefined) ? true : option.isPre), // 是否显示下一页和上一页按钮
            isShow: ((option.isShow == undefined) ? true : option.isShow),//是否显示总页数和总记录数
            showRecordNum: option.showRecordNum || 5,
            totalNum: 0,
            // totalPage: ((option.totalNum % option.showRecordNum == 0) ? option.totalNum / option.showRecordNum : option.totalNum / option.showRecordNum + 1) ,
            totalPage: 0,
            showNum: option.showNum
        }
        this.init();
    }

    paging.prototype = {
        init: function () {
            const {currentPage, showRecordNum} = this.option;
            //调用这个方法
            this.option.showNum(currentPage, showRecordNum).then(totalSize=>{
                this.initPage(totalSize);
                this.createPage();
                this.changePage();
            }).catch(err=>console.log(err));
        },
        initPage: function(totalSize){
            this.option.totalNum = totalSize;
            this.option.totalPage = ((this.option.totalNum % this.option.showRecordNum == 0) ? this.option.totalNum / this.option.showRecordNum : parseInt(this.option.totalNum / this.option.showRecordNum + 1));
        },
        //生成分页
        createPage: function () {
            let ele = this;
            let arr = new Array();
            let str = "";
            let isFirst = ele.option.isFirst;
            let isPre = ele.option.isPre;
            let isShow = ele.option.isShow;

            let classStyle = ele.option.classStyle;
            let string1 = "";
            for (let ss in classStyle) {
                string1 += ss + ':' + classStyle[ss] + ';';
            }

            let backClass = ele.option.backClass;
            let string2 = "";
            for (let ss in backClass) {
                string2 += ss + ':' + backClass[ss] + ';';
            }

            let currentPage = Number(ele.option.currentPage);
            let totalPage = Number(ele.option.totalPage);
            let totalNum = Number(ele.option.totalNum);
            if (isNaN(currentPage) || isNaN(totalPage) || isNaN(totalNum)) {
                alert("分页插件不能正常工作，请输入正确的数字");
            } else {
                if (isFirst) {
                    if (currentPage == 1) {
                        str = '<li class="disabled"><span style="' + string1 + '">首页</span></li>';
                        console.debug(str);
                        arr.push(str);
                    } else {
                        str = '<li><a href="javascript:void(0)" style="' + string1 + '">首页</a></li>';
                        console.debug(str);
                        arr.push(str);
                    }
                }
                if (isPre) {
                    if (currentPage == 1) {
                        str = '<li class="disabled"><span style="' + string1 + '">上一页</span></li>';
                        arr.push(str);
                    } else {
                        str = '<li><a href="javascript:void(0)" style="' + string1 + '">上一页</a></li>';
                        arr.push(str);
                    }

                }
                // 只显示5个页号
                if (totalPage <= 5) {
                    for (let i = 0; i < totalPage; i++) {
                        if ((i + 1) == currentPage) {
                            str = '<li class="active"><a  href="javascript:void(0)" style="' + string1 + string2 + '">' + (i + 1) + '</a></li>';
                            arr.push(str);
                        } else {
                            str = '<li><a href="javascript:void(0)" style="' + string1 + '">' + (i + 1) + '</a></li>';
                            arr.push(str);
                        }
                    }
                } else {
                    if ((totalPage - 7) > currentPage) {
                        if (currentPage == 1) {
                            for (let i = 1; i <= 3; i++) {
                                if (i == currentPage) {
                                    str = '<li class="active"><a  href="javascript:void(0)" style="' + string1 + string2 + '">' + (i) + '</a></li>';
                                } else {
                                    str = '<li><a  href="javascript:void(0)" style="' + string1 + '">' + (i) + '</a></li>';
                                }
                                arr.push(str);
                            }
                        } else {
                            for (let i = currentPage - 1; i <= currentPage + 1; i++) {
                                if (i == currentPage) {
                                    str = '<li class="active"><a  href="javascript:void(0)" style="' + string1 + string2 + '">' + (i) + '</a></li>';
                                } else {
                                    str = '<li><a  href="javascript:void(0)" style="' + string1 + '">' + (i) + '</a></li>';
                                }
                                arr.push(str);
                            }
                        }
                        str = '<li><span style="' + string1 + '">...</span></li>';
                        arr.push(str);
                        str = '<li><a  href="javascript:void(0)" style="' + string1 + '">' + (currentPage + 6) + '</a></li>';
                        arr.push(str);
                        str = '<li><a  href="javascript:void(0)" style="' + string1 + '">' + (currentPage + 7) + '</a></li>';
                        arr.push(str);
                    } else {
                        if ((totalPage - 1) <= currentPage) {
                            str = '<li><a  href="javascript:void(0)" style="' + string1 + '">' + (totalPage - 8) + '</a></li>';
                            arr.push(str);
                            str = '<li><a  href="javascript:void(0)" style="' + string1 + '">' + (totalPage - 7) + '</a></li>';
                            arr.push(str);
                            str = '<li><span style="' + string1 + '">...</span></li>';
                            arr.push(str);
                            for (let i = totalPage - 2; i <= totalPage; i++) {
                                if (i == currentPage) {
                                    str = '<li class="active"><a  href="javascript:void(0)" style="' + string1 + string2 + '">' + (i) + '</a></li>';
                                } else {
                                    str = '<li><a  href="javascript:void(0)" style="' + string1 + '">' + (i) + '</a></li>';
                                }
                                arr.push(str);
                            }
                        } else {
                            if (currentPage - 8 <= 0) {
                                str = '<li><a  href="javascript:void(0)" style="' + string1 + '">' + 1 + '</a></li>';
                                arr.push(str)
                            } else {
                                str = '<li><a  href="javascript:void(0)" style="' + string1 + '">' + (currentPage - 8) + '</a></li>';
                                arr.push(str);
                                str = '<li><a  href="javascript:void(0)" style="' + string1 + '">' + (currentPage - 7) + '</a></li>';
                                arr.push(str);
                            }
                            str = '<li><span style="' + string1 + '">...</span></li>';
                            arr.push(str);
                            for (let i = currentPage - 1; i <= currentPage + 1; i++) {
                                if (i == currentPage) {
                                    str = '<li class="active"><a  href="javascript:void(0)" style="' + string1 + string2 + '">' + (i) + '</a></li>';
                                } else {
                                    str = '<li><a  href="javascript:void(0)" style="' + string1 + '">' + (i) + '</a></li>';
                                }
                                arr.push(str);
                            }
                        }
                    }

                }
                if (isPre) {
                    if (currentPage == totalPage) {
                        str = '<li class="disabled"><span style="' + string1 + '">下一页</span></li>';
                        arr.push(str);
                    } else {
                        str = '<li><a href="javascript:void(0)" style="' + string1 + '">下一页</a></li>';
                        arr.push(str);
                    }
                }
                if (isFirst) {
                    if (currentPage == totalPage) {
                        str = '<li class="disabled"><span style="' + string1 + '">尾页</span></li>';
                        arr.push(str);
                    } else {
                        str = '<li><a href="javascript:void(0)" style="' + string1 + '">尾页</a></li>';
                        arr.push(str);
                    }
                }
                if (isShow) {
                    str = '<li><span class="' + classStyle + '" style="' + string1 + '">共' + totalPage + '页</span></li>';
                    arr.push(str);
                    str = '<li><span class="' + classStyle + '" style="' + string1 + '">共' + totalNum + '条记录</span></li>';
                    arr.push(str);
                }
                str = arr.join("");
                ele.element.html(str);
            }
        },
        //添加点击事件
        changePage: function () {
            let ele = this;
            console.debug(ele);
            ele.element.off('click','a')
            ele.element.on('click', 'a', function () {
                let currentPage = parseInt(ele.option.currentPage);
                let totalPage = parseInt(ele.option.totalPage);
                let ss = $(this).html();
                if (ss == "上一页" && currentPage != 1) {
                    ele.option.currentPage = ele.option.currentPage - 1;
                } else if (ss == "上一页" && currentPage == 1) {
                    ele.option.currentPage = ele.option.currentPage;
                }
                if (ss == '首页') {
                    ele.option.currentPage = 1;
                }
                if (ss == "尾页") {
                    ele.option.currentPage = totalPage;
                }
                if (ss == "下一页" && currentPage != totalPage) {
                    ele.option.currentPage = ele.option.currentPage + 1;
                } else if (ss == "下一页" && currentPage == totalPage) {
                    ele.option.currentPage = totalPage;
                }
                if (ss != "首页" && ss != "上一页" && ss != "下一页" && ss != "尾页") {
                    ele.option.currentPage = parseInt(ss);
                }
                ele.createPage();
                if (ele.option.showNum) {
                    ele.option.showNum(ele.option.currentPage, ele.option.showRecordNum).then(size=>{
                        ele.initPage(size);
                        ele.createPage();
                    }).catch(error=>console.log(error));
                }
            });
        },
        setPage:function(totalNum){
            this.option.totalNum=totalNum;
            this.option.totalPage =((this.option.totalNum % this.option.showRecordNum == 0) ? this.option.totalNum / this.option.showRecordNum :parseInt(this.option.totalNum / this.option.showRecordNum + 1));
            //this.init()
        },
        getCurr(){
          return this.option.currentPage
        },
        getRecord(){
            return this.option.showRecordNum
        },
        setRecord(num){
            this.option.showRecordNum = num
            //this.init()
        }

    };

    $.fn.Paging = function (option) {
        return new paging(this, option);
    }

})(jQuery, window, document);
