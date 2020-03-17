<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css" />
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/dropzone.min.css" />
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../includes/menu.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${baseResult.message!=null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbContent.id==null?"新增":"编辑"}内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <form:form id="inputForm" cssClass="form-horizontal"  action="/content/save" method="post" modelAttribute="tbContent">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">父级类目</label>
                                    <div class="col-sm-10">
                                        <form:hidden id="categoryId" path="tbContentCategory.id" />
                                        <input Class="form-control required" id="categoryName" placeholder="请选择" data-toggle="modal" data-target="#modal-default" readonly="true" value="${tbContent.tbContentCategory.name}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label">标题</label>
                                    <div class="col-sm-10">
                                       <form:input cssClass="form-control required" path="title" placeholder="标题"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-2 control-label">子标题</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="subTitle" placeholder="子标题"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="titleDesc" placeholder="标题描述"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">链接</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="url" placeholder="链接"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label">图片1</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="pic" placeholder="图片1"/>
                                        <div id="dropz" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic2" class="col-sm-2 control-label">图片2</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="pic2" placeholder="图片2"/>
                                        <div id="dropz1" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <%--<label for="content" class="col-sm-2 control-label">详情</label>--%>
                                    <label class="col-sm-2 control-label">详情</label>
                                    <div class="col-sm-10">
                                        <from:hidden path="content"/>
                                       <%-- <form:textarea cssClass="form-control required" path="content" placeholder="详情"/>--%>
                                           <div id="editor">
                                               <c:if test="${tbContent.content!=null}">
                                                   ${tbContent.content}
                                               </c:if>
                                                <c:if test="${tbContent.content==null}">
                                                    <p>此处编辑内容······</p>
                                                </c:if>
                                           </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button id="btnSubmit" type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"/>
</div>
<jsp:include page="../includes/footer.jsp"/>
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<script type="text/javascript" src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
<!--自定义模态框-->
<sys:modal title="请选择" msg="<ul id='myTree' class='ztree'></ul>"/>
<script type="text/javascript">
  $(function () {
      /*获取节点树*/
      Icheck.initZtree("/content/category/tree/data",["id", "name=n", "level=lv"],function (nodes) {
          var node=nodes[0];
          $("#categoryId").val(node.id);
          $("#categoryName").val(node.name);
          $("#modal-default").modal("hide");
      });
      initWangEditor();
  });

  /*初始化wangEditor*/
  function initWangEditor(){
      var E = window.wangEditor
      var editor = new E('#editor')
      // 配置服务器端地址
      editor.customConfig.uploadImgServer = '/upload',
      // 自定义 fileName
      editor.customConfig.uploadFileName = 'editorFiles',
      editor.create()
      $("#btnSubmit").bind("click",function () {
          var contenthtml =editor.txt.html()
          $("#content").val(contenthtml);
      })
  }
  /*上传图片*/
  Icheck.initDropzone({
      id:"#dropz",
      url:"/upload",
      init: function () {
          this.on("success", function (file, data) {
              $("#pic").val(data.fileName);
          });
      }
  })
  Icheck.initDropzone({
      id:"#dropz1",
      url:"/upload",
      init: function () {
          this.on("success", function (file, data) {
              $("#pic2").val(data.fileName);
          });
      }
  })

</script>
</body>
</html>
