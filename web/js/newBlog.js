function submitData() {
    let title = document.getElementById("title").value
    let content = document.getElementById("content").value
    let data = {"title": title, "content": content}
    $.ajax({
        url: "/BlogSystem/uploadBlog",    //请求的url地址
        dataType: "json",   // 返回格式为json
        async: true,// 请求是否异步，默认为异步，这也是ajax重要特性
        data: data, // 参数值
        type: "post",  // 请求方式
        "beforeSend": function () {
            //请求前的处理
        },
        "success": function (req) {
            //请求成功时处理
            if (req["msg"] == "200") {
                alert("博客发送成功")
                window.open("/BlogSystem/html/home.html")
            } else if (req["msg"] = "403") {
                alert("对不起，无法添加，请查看一下标题是否重复")
            } else {
                alert("")
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