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
// FIXME: 利用JS当中的函数重写 showTable() 函数
function createTable(target) {
    let table = document.createElement("table")
    table.style.border = "1px"
    table.style.borderCollapse = "collapse"
    let colums = 4
    let row = target.length
    for (let i = 0; i < colums; i++) {
        let tr = table.insertRow(i)
    }
}
function showTable(req) {
    let table = "<table>";
    table += "<tr><td>标题</td><td>作者</td><td>修改时间</td></tr>"
    for (let i = 0; i < req.length; i++) {
        table += "<tr><td>" + req[i]["title"] +
            "</td><td>" + req[i]["author"] +
            "</td><td>" + req[i]["createdTime"] +
            "</td><td>" + "<input type='button' value='编辑' onclick='changeBlog(this)' id='" + (i + 1) + "' />" +
            "</td><td>" + "<input type='button' value='删除' onclick='deleteBlog(this)' id='" + (i + 1) + "' />" +
            "</td></tr>"
    }
    table += "</table>"
    $("#table").empty().append(table)
}
function changeBlog(element) {
    let id = element.id
    window.location.href = "/BlogSystem/edit?id=" + id
}
function deleteBlog(element) {
    let id = element.id
    let data = {"id": id}
    $.ajax({
        url: "/BlogSystem/delete",    //请求的url地址
        dataType: "json",   // 返回格式为json
        async: true,// 请求是否异步，默认为异步，这也是ajax重要特性
        data: data, // 参数值
        type: "post",  // 请求方式
        "beforeSend": function () {
            //请求前的处理
        },
        // 在后台处理该用户名是否存在以及能否添加
        "success": function (req) {
            //请求成功时处理
            if (req["message"] == "200") {
                alert("删除成功")
            } else if (req["message"] == "403") {
                alert("对不起，没有该数据，删除失败")
            }
        },
        "complete": function () {
            // 请求完成的处理
        },
        "error": function () {
            // 请求出错处理
        }
    })
}