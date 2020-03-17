<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容详情</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
 <div class="box-body">
     <table  id="dataTable" class="table">
         <tbody>
         <tr>
             <td>标题：</td>
             <td>${tbContent.title}</td>
         </tr>
         <tr>
             <td>子标题：</td>
             <td>${tbContent.subTitle}</td>
         </tr>
         <tr>
             <td>标题描述：</td>
             <td>${tbContent.titleDesc}</td>
         </tr>
         <tr>
             <td>URL：</td>
             <td>${tbContent.url}</td>
         </tr>
         <tr>
             <td>路径：</td>
             <td>${tbContent.pic}</td>
         </tr>
         <tr>
             <td>创建时间：</td>
             <td><fmt:formatDate value="${tbContent.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
         </tr>
         <tr>
             <td>更新时间：</td>
             <td><fmt:formatDate value="${tbContent.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
         </tr>
         </tbody>
     </table>
 </div>
</body>
</html>
