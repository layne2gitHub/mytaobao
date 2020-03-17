/**
 *
 *函数对象
 * @constructor
 *
 * 不直接写function,为了安全
 * JS闭包
 * 整个Validate就是个函数对象
 * handlerInitValidate就是正常的函数
 */

var Validate = function () {
    /**
     * 初始化jQuery validation
     */
   var handlerInitValidate=function () {
        $("#inputForm").validate({
                errorElement: 'span',
                errorClass: 'help-block',
                errorPlacement: function (error, element) {
                    element.parent().parent().attr("class", "form-group has-error");
                    error.insertAfter(element);
                }
            });
   };
    /**
     * 增加自定义验证规则
     */
   var handlerInitCustomValidate = function () {
       $.validator.addMethod("mobile",function (value, element) {
           var length =value.length;
           var mobile= /^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/;
           return this.optional(element) || (length == 11 && mobile.test(value));
       },"手机号码格式错误");
   };
   return {
       /**
        * 初始化
        */
       init:function () {
           handlerInitValidate();
           handlerInitCustomValidate();
       }
   }
}();

$(document).ready(function () {
    Validate.init();
});