<%--
  Created by IntelliJ IDEA.
  User: luod1
  Date: 12/9/2020
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/test?methodName=addCourse">新建课程</a>
    <a href="${pageContext.request.contextPath}/test?methodName=findByName">根据课程名查询</a>
    <a href="${pageContext.request.contextPath}/test?methodName=findByStatus">根据课程状态查询</a>
</body>
</html>
