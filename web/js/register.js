// 这个地方是不是应该引入 jQuery？
/***
 * 获得数据，检查数据的合法性
 */
function getData() {
    var password = document.getElementsByName('pwd')[0].value
    var rePassword = document.getElementsByName('rePassword')[0].value
    if (password == null || rePassword == null) {
        alert("对不起，请查看一下有没有信息没有输入进去。")
        return
    }
    if (password != rePassword) {
        alert("对不起，您两次密码输入的不一致，请再次查看一下。")
    } else {
        excuteData(document.getElementsByName('account')[0].value, password)
    }
}

/***
 * 处理注册的数据。
 * @param account 用户名
 * @param password 密码
 */
function excuteData(account, password) {
    var data = {"account": account, "pwd": password}
    $.ajax({
        url: "/BlogSystem/register",    //请求的url地址
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
            alert("处理成功")
            if (req["code"] == "200") {
                alert("恭喜你，注册成功！快去看看这个新奇的世界做点笔记吧。")
                window.open("/BlogSystem")
            } else if (req["code"] == "403") {
                alert("对不起，该用户已经注册了。换个名字吧")
            } else {
                alert("我也不知道咋了，咋就错了")
            }
        },
        "complete": function () {
            // 请求完成的处理
        },
        "error": function () {
            // 请求出错处理
            alert("出错了")
        }
    })
}