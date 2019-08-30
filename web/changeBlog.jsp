<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2019/8/30
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/newBlog.css" />
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <script src="../js/changeBlog.js" defer></script>
    <title>编辑博客</title>
</head>
<body>
    <input type="text" name="title" id="title" placeholder="文章标题" value="${title}" />
    <textarea id="content" placeholder="正文" content="${content}"></textarea>
    <input type="button" value="提交" id="button" onclick="changeData()" />
</body>
</html>
