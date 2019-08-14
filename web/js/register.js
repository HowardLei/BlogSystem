/***
 * 获得数据，检查数据的合法性
 */
function getData() {
    var password = $("#password").val()
    var repassword = $("#rePassword").val()
    if (password != repassword) {
        alert("对不起，您的密码输入有误。请重新输入")
    } else {
        excuteData($("#userName").val(), password)
    }
}

/***
 * 处理注册的数据。
 * @param userName
 * @param password
 */
function excuteData(userName, password) {
    var data = {"userName": userName, "password": password}
    $.ajax({
        url: "/blogSystem/register",    //请求的url地址
        dataType: "json",   // 返回格式为json
        async: true,// 请求是否异步，默认为异步，这也是ajax重要特性
        data: data, // 参数值
        type: "post",  // 请求方式
        "beforeSend": function () {
            //请求前的处理
        },
        // 注意：当从后台要到执行成功的方法时，必须要返回一个 JSON 对象才行
        "success": function (req) {
            //请求成功时处理
            if (req["code"] == "200") {
                alert("恭喜你，注册成功")
            } else if (req["code"] == "403") {
                alert("对不起，该用户已经注册了")
            }
        },
        "complete": function () {
            // 请求完成的处理
        },
        "error": function () {
            // 请求出错处理
            alert("出错了");
        }
    })
}