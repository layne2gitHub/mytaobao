<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="title" type="java.lang.String" required="false" description="modal is title"%>
<%@ attribute name="msg" type="java.lang.String" required="false" description="modal is message"%>
<%--<%@ attribute name="opts" type="java.lang.String" required="false" description="操作信息：info/信息提示 confirm/确认对话框"%>
<%@ attribute name="url" type="java.lang.String" required="false" description="跳转链接，主要用于确认对话框删除时使用"%>--%>


<!-- modal  start-->
<div class="modal fade" id="modal-default">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">${title==null?"温馨提示":title}</h4>
            </div>
            <div class="modal-body">
                <p id="modal-message">${msg}</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button id="btnModalClick" type="button" class="btn btn-primary" >确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<%--
<script>
   $(function btnModalClick() {
       $(".modal-footer .btn-primary").bind("click",function () {
               <c:if test="${opts!= 'confirm'}">
               $("#modal-default").modal("hide");
               </c:if>
               <c:if test="${opts == 'confirm'}">
                 console.log("${url}")
               </c:if>
           }
       )
   })
</script>--%>
