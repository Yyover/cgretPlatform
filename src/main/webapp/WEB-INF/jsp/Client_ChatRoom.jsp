<%--
  Created by IntelliJ IDEA.
  User: Hyao
  Date: 2020/2/23 0023
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String UiPath = basePath + "resources/easyui/";
    String wsPath = "ws://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    double total = 0;
%>

<html>
<head>
    <title>正在与商家洽谈......</title>
</head>
<script>
    var text = document.title;  //定义文章的title
    var timerID;    //定义时间变量
    function newtext() {    //定义函数
        clearTimeout(timerID);  //清除定时器
        document.title = text.substring(1, text.length) + text.substring(0, 1); //substring()方法用于提取字符创中介于两个指定下标之间的字符
        text = document.title.substring(0, text.length);
        timerID = setTimeout("newtext()", 100)
    }
</script>
<body onload="newtext()">
    <div id="message">

    </div>
    <button onclick="chat()">找客服</button>

    <script type="text/javascript" src="<%=basePath%>/resources/static/js/sockjs.min.js"></script>
    <script>
        function chat(){
            //建立socket连接
            var sock = new WebSocket("ws://localhost:8080/cgret/websocket");

            sock.onopen = function (e) {
                console.log(e);
            };
            sock.onmessage = function (e) {
                console.log(e);
                $("#message").append("<p><font color='red'>"+e.data+"</font>")
            };
            sock.onerror = function (e) {
                console.log(e);
            };
            sock.onclose = function (e) {
                console.log(e);
            }
        }

    </script>
</body>
</html>
