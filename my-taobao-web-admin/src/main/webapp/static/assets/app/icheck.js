/**
 *
 *函数对象
 * @constructor
 *
 * 不直接写function,为了安全
 * JS闭包
 * 整个icheck就是个函数对象
 * handlerInitIcheck就是正常的函数
 */
var Icheck=function () {
    /**
     * 私有的属性 成员变量
     * @type {jQuery|HTMLElement}
     * @private
     */
    var _masterCheckbox;
    var _checkbox;
    /**
     *  定义一个存放ID的数组
     */
    var _idArray;
    /**
     * Dropzone
     * 定义一个对象
     * @type {{addRemoveLinks: boolean, dictFallbackMessage: string, paramName: string, dictFileTooBig: string, url: string, dictInvalidFileType: string, dictRemoveLinks: string, acceptedFiles: string, parallelUploads: number, maxFilesize: number, dictResponseError: string, dictCancelUpload: string, dictMaxFilesExceeded: string, maxFiles: number, dictDefaultMessage: string}}
     */
    var defaultDropzoneOpts={
        url: "",
        paramName: "dropzFile", // 传到后台的参数名称
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',// 设置默认的提示语句
        dictMaxFilesExceeded: "您最多只能上传"+this.maxFiles+"个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消"
    };

    /**
     * 私有方法，初始化Icheck
     */
    var handlerInitCheckbox=function () {
        //激活Icheck
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        })
        //获取控制器Checkbox
        _masterCheckbox=$('input[type="checkbox"].minimal.icheck_master');
        //获取全的Checkbox集合
        _checkbox=$('input[type="checkbox"].minimal');
    };
;
    /**
     * Checkbox全选功能
     */
    var handlerCheckboxAll=function () {
        _masterCheckbox.on("ifClicked",function (e) {
            //返回true表示未选中
            if(e.target.checked){
                _checkbox.iCheck('uncheck');
            }else{
                _checkbox.iCheck('check');
            }
        });
    };



    /**
     * 批量删除数据
     */
    var handleDeleteMulti= function (url) {
        _idArray = new Array();
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefined" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });
        //判断用户是否选择了数据项
        if (_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何数据，请至少选择一条数据。")
        } else {
            $("#modal-message").html("您确定要删除所选数据吗？")
        }
        //点击删除按钮时弹出模态框
        $("#modal-default").modal("show");

        //如果用户选择了数据项则调用删除方法
        $("#btnModalClick").bind("click", function () {
            handleDeleteData(url);
        });
    };

    /**
     * 批量删除数据
     */
    var handleDeleteSingle= function (url,id,msg) {
        //可选参数
        if(!msg){msg=null}
        //将ID放入数组中，以便与批量删除通用
        _idArray = new Array();
        _idArray.push(id);
        //判断用户是否选择了数据项
        $("#modal-message").html(msg==null?"您确定要删除所选数据吗？":msg)
        //点击删除按钮时弹出模态框
        $("#modal-default").modal("show");

        //如果用户选择了数据项则调用删除方法
        $("#btnModalClick").bind("click", function () {
            handleDeleteData(url);
        });
    };

    /**
     * AJAX异步
     * @param url
     */
    var handleDeleteData= function (url) {
            $("#modal-default").modal("hide");
            //如果没有选择数据项的处理
            if(_idArray.length > 0) {
                //【500毫秒】延迟事件,为加载动态效果提高用户体验
                setTimeout(function () {
                    $.ajax({
                        "url":url,
                        "type":"POST",
                        "data":{"ids": _idArray.toString()},
                        "dataType":"JSON",
                        "success":function (data) {
                            //请求成功后，无论是成功失败都需要弹出模态框进行提示，所以这里需要先进行原来Click事件的解绑
                            $("#btnModalClick").unbind("click");
                            if(data.status ===200){
                                $("#btnModalClick").bind("click",function () {
                                    window.location.reload();
                                });
                            }else{
                                $("#btnModalClick").bind("click",function () {
                                    $("#modal-default").modal("hide");
                                });
                            }
                            $("#modal-message").html(data.message);
                            $("#modal-default").modal("show");
                        }
                    });
                },500);
            }
        };


    /**
     * 数据分页
     */
    var handlerDataTables=function (url,columns) {
       var _dataTable = $('#dataTable').DataTable({
            "padding":true,
            "info":true,
            "lengthChange":false,
            "ordering": false,
            "processing":true,
            "searching" : false,
            //服务器模式
            "serverSide": true,
            "deferRender":true,
            "ajax": {
                "url": url,
            },
            "columns": columns,
            "language":{
                "sProcessing":   "处理中...",
                "sLengthMenu":   "显示 _MENU_ 项结果",
                "sZeroRecords":  "没有匹配结果",
                "sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix":  "",
                "sSearch":       "搜索:",
                "sUrl":          "",
                "sEmptyTable":     "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands":  ",",
                "oPaginate": {
                    "sFirst":    "首页",
                    "sPrevious": "上页",
                    "sNext":     "下页",
                    "sLast":     "末页"
                },
                "oAria": {
                    "sSortAscending":  ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }

            },
            "drawCallback": function( settings ) {
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        });
       return  _dataTable;
    };
    /**
     * DataTable时间格式化
     * @param time
     * @returns {string}
     */
    var handlerFormatDate=function(time){
        if(typeof(time)=="undefined"){
            return "未定义";
        }
        var oDate = new Date(time),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth()+1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
        return oTime;

        //补0操作,当时间数据小于10的时候，给该数据前面加一个0
        function getzf(num){
            if(parseInt(num) < 10){
                num = '0'+num;
            }
            return num;
        }
    };
    /**
     * 模态框显示数据
     * @param url
     */
    var handlerSHowDetail=function (url) {
        //这里是通过Ajax请求HTML的方式将jsp装进模态框
        $.ajax({
            url:url,
            type:"get",
            dataType:"html",
            success:function (data) {
                $("#modal-detail-body").html(data)
                $("#modal-detail").modal("show");
            }
        })
    };
    /**
     * 初始Ztree
     * @param url
     * @param autoParam
     * @param callback
     */
    var handleInitZtree=function (url,autoParam,callback) {
        var setting = {
            view: {
                // 禁止多选
                selectedMulti: false
            },
            async: {
                // 开启异步加载功能
                enable: true,
                // 远程访问地址
                url:url,
                // 选择父节点时会自动将节点中的参数传回服务器再重新取结果
                autoParam:autoParam,
                otherParam:{"otherParam":"zTreeAsyncTest"},
            }
        };
        // 初始化 zTree 控件
        $.fn.zTree.init($("#myTree"), setting);

        $("#btnModalClick").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();
            if (nodes.length == 0) {
                alert("请先选择一个节点");
            }else{
                callback(nodes);
            }
        });
    }
    /**使用JS的对象的继承实现
     * Dropzone
     * @param elementId
     * @param url
     */
    var handlerInitDropzone=function (opts) {
        //关闭Dropzone的自动发现功能
        Dropzone.autoDiscover=false;
        //继承：defaultDropzoneOpts来继承opts
        $.extend(defaultDropzoneOpts,opts)
        var myDropzone = new Dropzone(defaultDropzoneOpts.id,defaultDropzoneOpts);
    }
   /* var handlerInitDropzone=function (opts) {
        var myDropzone = new Dropzone("#"+elementId,{
            url: url,
            dictDefaultMessage: '拖动文件至此或者点击上传', // 设置默认的提示语句
            paramName: "dropzFile", // 传到后台的参数名称
            maxFiles: 1,// 一次性上传的文件数量上限
            maxFilesize: 2, // 文件大小，单位：MB
            acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
            addRemoveLinks: true,
            parallelUploads: 1,// 一次上传的文件数量
            //previewsContainer:"#preview", // 上传图片的预览窗口
            dictDefaultMessage: '拖动文件至此或者点击上传',
            dictMaxFilesExceeded: "您最多只能上传"+maxFiles+"个文件！",
            dictResponseError: '文件上传失败!',
            dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
            dictFallbackMessage: "浏览器不受支持",
            dictFileTooBig: "文件过大上传文件最大支持.",
            dictRemoveLinks: "删除",
            dictCancelUpload: "取消",
            init: function () {
                this.on("success", function (file, data) {
                    $("#pic").val(data.fileName);
                });
            }
        });
    }*/
    /**
     * 暴露私有方法
     */
    return {
        /**
         * 初始化
         */
        init:function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },
        /**
         * 批量删除
         * @param url
         */
        deleteMulti:function (url) {
            handleDeleteMulti(url);
        },
        /**
         * 单笔删除
         * @param url
         */
        deleteSingle:function (url,id,msg) {
            handleDeleteSingle(url,id,msg);
        },
        /**
         * 初始化DataTables
         * @param url
         * @param columns
         * @returns {jQuery}
         */
        initDataTables:function (url, columns) {
            return handlerDataTables(url,columns);
        },
        /**
         * 显示详情
         * @param url
         */
        initShowDetail:function (url) {
            handlerSHowDetail(url);
        },
        /**
         * 格式化时间
         * @param time
         */
        formatDate:function (time) {
            return handlerFormatDate(time);
        },
        /**
         * 初始化Ztree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZtree:function (url,autoParam,callback) {
            handleInitZtree(url,autoParam,callback);
        },
        /**
         * 初始化Dropzone
         * @param opts
         */
        initDropzone:function (opts) {
            handlerInitDropzone(opts);
        }
    }
}();
$(document).ready(function () {
    Icheck.init();
});