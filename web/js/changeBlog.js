function changeData() {
    let id = window.location.href.substring(window.location.href.length - 1)
    alert(window.location.href)
    let title = document.getElementById("title").value
    let content = document.getElementById("content").value
    let data = {"title": title, "content": content, "id": id}
    $.ajax({
        url: "/BlogSystem/edit",    //请求的url地址
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
        },
        "complete": function () {
            // 请求完成的处理
        },
        "error": function () {
            // 请求出错处理
        }
    })
}