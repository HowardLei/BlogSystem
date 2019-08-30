function showText() {
    document.getElementById("successMessage").innerText = ""
}
function addNewBlog() {
    window.open("/BlogSystem/html/newBlog.html","__self")
}
function logOut() {
    window.open("/BlogSystem")
}
function show() {
    $.ajax({
        url: "/BlogSystem/list",    //请求的url地址
        dataType: "json",   // 返回格式为json
        async: true,// 请求是否异步，默认为异步，这也是ajax重要特性
        data: null, // 参数值
        type: "post",  // 请求方式
        "beforeSend": function () {
            //请求前的处理
        },
        // 在后台处理该用户名是否存在以及能否添加
        "success": function (req) {
            //请求成功时处理
            showTable(req)
        },
        "complete": function () {
            // 请求完成的处理
        },
        "error": function () {
            // 请求出错处理
        }
    })
}
function showTable(req) {
    let table = "<table>";
    table += "<tr><td>标题</td><td>作者</td><td>修改时间</td></tr>"
    for (let i = 0; i < req.length; i++) {
        table += "<tr><td>" + req[i]["title"] +
            "</td><td>" + req[i]["author"] +
            "</td><td>" + req[i]["createdTime"] +
            "</td><td>" + "<input type='button' value='编辑' onclick='changeBlog()' id='" + i +"' />" +
            "</td></tr>"
    }
    table += "</table>"
    $("#table").empty().append(table)
}
function changeBlog() {
    $.ajax({
        url: "/BlogSystem/edit",    //请求的url地址
        dataType: "json",   // 返回格式为json
        async: true,// 请求是否异步，默认为异步，这也是ajax重要特性
        data: null, // 参数值
        type: "post",  // 请求方式
        "beforeSend": function () {
            //请求前的处理
        },
        // 在后台处理该用户名是否存在以及能否添加
        "success": function (req) {
            //请求成功时处理
        },
        "complete": function () {
            // 请求完成的处理
        },
        "error": function () {
            // 请求出错处理
        }
    })
}