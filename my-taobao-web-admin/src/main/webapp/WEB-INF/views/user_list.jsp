<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 用户管理</title>
    <jsp:include page="../includes/header.jsp"/>
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
                用户管理
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
                <div class="col-xs-12">
                    <c:if test="${baseResult.message!=null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box box-info box-info-search" style="display:none">
                        <div class="box-header">
                            <div class="box-title">
                                高级搜索
                            </div>
                        </div>
                        <div class="box-body">
                            <div class="row form-horizontal">
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="username" class="col-sm-4 control-label">姓名</label>
                                        <div class="col-sm-8">
                                            <input id="username" placeholder="姓名" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-4 control-label">邮箱</label>
                                        <div class="col-sm-8">
                                            <input id="email" placeholder="邮箱" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-4 control-label">手机</label>
                                        <div class="col-sm-8">
                                            <input id="phone" placeholder="手机" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="box-footer">
                            <button type="button" class="btn btn-info pull-right" onclick="search();"><i class="fa fa-search">搜索</i></button>
                        </div>


                        <%--<form:form cssClass="form-horizontal" action="/user/search" method="post" modelAttribute="tbUser">
                             <div class="box-body">
                                 <div class="row">
                                     <div class="col-xs-12 col-sm-3">
                                         <div class="form-group">
                                             <label for="username" class="col-sm-4 control-label">姓名</label>
                                             <div class="col-sm-8">
                                                 <form:input path="username" cssClass="form-control" placeholder="姓名"/>
                                             </div>
                                         </div>
                                     </div>
                                     <div class="col-xs-12 col-sm-3">
                                         <div class="form-group">
                                             <label for="email" class="col-sm-4 control-label">邮箱</label>
                                             <div class="col-sm-8">
                                                 <form:input path="email" cssClass="form-control" placeholder="邮箱"/>
                                             </div>
                                         </div>
                                     </div>
                                     <div class="col-xs-12 col-sm-3">
                                         <div class="form-group">
                                             <label for="phone" class="col-sm-4 control-label">手机</label>
                                             <div class="col-sm-8">
                                                 <form:input path="phone" cssClass="form-control" placeholder="手机"/>
                                             </div>
                                         </div>
                                     </div>
                                 </div>
                             </div>
                             <div class="box-footer">
                                 <button type="submit" class="btn btn-info pull-right"><i class="fa fa-search">搜索</i></button>
                             </div>
                        </form:form>--%>
                    </div>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户管理</h3>
                        </div>
                        <div class="bo-body">
                               <a  href="/user/form"  type="button" class="btn btn-sm btn-default"><i class="fa fa-plus">新增</i></a>&nbsp;&nbsp;&nbsp;
                               <button  type="button" class="btn btn-sm btn-default" onclick="Icheck.deleteMulti('/user/deleteMulti')"><i class="fa fa-trash-o">删除</i></button>&nbsp;&nbsp;
                               <a  href="#"  type="button" class="btn btn-sm btn-default"><i class="fa fa-download">导入</i></a>&nbsp;&nbsp;&nbsp;
                               <a  href="#"  type="button" class="btn btn-sm btn-default"><i class="fa fa-upload">导出</i></a>&nbsp;&nbsp;&nbsp;
                               <button  href="#"  type="button" class="btn btn-sm btn-primary" onclick="$('.box-info-search').css('display')=='none'?$('.box-info-search').show('fast'):$('.box-info-search').hide('fast')"><i class="fa fa-search">搜索</i></button>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master"></th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                               <%-- <c:forEach items="${tbUsers}" var="tbUser">
                                    <tr>
                                        <td><input id="${tbUser.id}" type="checkbox" class="minimal"></td>
                                        <td>${tbUser.id}</td>
                                        <td>${tbUser.username}</td>
                                        <td>${tbUser.phone}</td>
                                        <td>${tbUser.email}</td>
                                        <td><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                            <a  href="#"  type="button" class="btn btn-sm  btn-default"><i class="fa fa-search">查看</i></a>
                                            <a  href="#"  type="button" class="btn btn-sm  btn-primary"><i class="fa fa-edit">编辑</i></a>
                                            <a  href="#"  type="button" class="btn btn-sm  btn-danger"><i class="fa fa-trash-o">删除</i></a>
                                        </td>
                                    </tr>
                                </c:forEach>--%>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"/>
</div>
<jsp:include page="../includes/footer.jsp"/>
<!--自定义模态框-->
<sys:modal />
<script>
    var _dataTable;

    $(function () {
        var columns=[
            {"data":function ( row, type, val, meta ) {
                    return '<input id="'+row.id+'" type="checkbox" class="minimal">';
                }},
            { "data": "id" },
            { "data": "username" },
            { "data": "phone" },
            { "data": "email" },
            { "data": function ( row, type, val, meta ) {
                    return Icheck.formatDate(row.updated);//update_time是实体类的属性
                }},
            {"data":function ( row, type, val, meta ) {
                    var detailUrl="/user/detail?id="+row.id;
                    var deleteUrl="/user/delete";
                    return '<button type="button" class="btn btn-sm  btn-default" onclick="Icheck.initShowDetail(\''+detailUrl+'\')"><i class="fa fa-search">查看</i></button>'+
                        '<a  href="/user/form?id='+row.id+'"  type="button" class="btn btn-sm  btn-primary"><i class="fa fa-edit">编辑</i></a>'+
                        '<button  href="#"  type="button" class="btn btn-sm  btn-danger" onclick="Icheck.deleteSingle(\''+deleteUrl+'\',\''+row.id+'\')"><i class="fa fa-trash-o">删除</i></button>';
                }},
        ];
        _dataTable=Icheck.initDataTables("/user/page",columns);
        /*$('#dataTable').DataTable({
            "padding":true,+
            "ajax": {
                "url": "/user/page",
            },
            "columns": [
                    {"data":function ( row, type, val, meta ) {
                        return '<input id="'+row.id+'" type="checkbox" class="minimal">';
                        }},
                    { "data": "id" },
                    { "data": "username" },
                    { "data": "phone" },
                    { "data": "email" },
                    { "data": "updated" },
                    {"data":function ( row, type, val, meta ) {
                        return '<a  href="#"  type="button" class="btn btn-sm  btn-default"><i class="fa fa-search">查看</i></a>'+
                               '<a  href="#"  type="button" class="btn btn-sm  btn-primary"><i class="fa fa-edit">编辑</i></a>'+
                               '<a  href="#"  type="button" class="btn btn-sm  btn-danger"><i class="fa fa-trash-o">删除</i></a>';
                    }},
                ],
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
               Icheck.init();
            }
        });*/
    });
    function search(){
        var username=$("#username").val();
        var phone=$("#phone").val();
        var email=$("#email").val();
        var param={
            "username":username,
            "phone":phone,
            "email":email,
        };
        console.log(_dataTable);
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }
</script>
</body>
</html>

