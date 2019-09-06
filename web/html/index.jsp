<%--
  Created by IntelliJ IDEA.
  User: howardlei
  Date: 2019/8/31
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/newBlog.css" />
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js" defer></script>
    <script src="js/changeBlog.js" defer></script>
    <title>编辑博客</title>
</head>
<body>
    <input type="text" name="title" id="title" placeholder="文章标题" value="${title}" />
    <textarea id="content" placeholder="正文">${content}</textarea>
    <input type="button" value="提交" id="button" onclick="changeData()" />
    <input type="button" value="删除" id="deleteButton" onclick="deleteBlog()">
</body>
</html>