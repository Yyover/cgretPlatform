<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String UiPath = basePath + "resources/easyui/";
    double total = 0;
%>

<!DOCTYPE html><html lang="en">
<head>
    <meta charset="UTF-8">
    <title>网络聊天室</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="description" content=""/>
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name=""/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=basePath%>resources/static/AdminLTE/plugins/fontawesome-free/css/all.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Tempusdominus Bbootstrap 4 -->
    <link rel="stylesheet" href="<%=basePath%>resources/static/AdminLTE/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="<%=basePath%>resources/static/AdminLTE/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- JQVMap -->
    <link rel="stylesheet" href="<%=basePath%>resources/static/AdminLTE/plugins/jqvmap/jqvmap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<%=basePath%>resources/static/AdminLTE/dist/css/adminlte.min.css">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="<%=basePath%>resources/static/AdminLTE/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="<%=basePath%>resources/static/AdminLTE/plugins/daterangepicker/daterangepicker.css">
    <!-- summernote -->
    <link rel="stylesheet" href="<%=basePath%>resources/static/AdminLTE/plugins/summernote/summernote-bs4.css">
    <!-- Google Font: Source Sans Pro -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>resources/static/css/tasp.css" />
    <link href="<%=basePath%>resources/static/css/orderconfirm.css" rel="stylesheet" />

</head>
<style type="text/css">
    .msg_board {
        width: 322px;
        height: 100px;
        border: solid 1px darkcyan;
        padding: 5px;
        overflow-y: scroll;
    // 文字长度大于div宽度时换行显示
    word-break: break-all;
    }
    /*set srcoll start*/
    ::-webkit-scrollbar
    {
        width: 10px;
        height: 10px;
        background-color: #D6F2FD;
    }
    ::-webkit-scrollbar-track
    {
        -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
        /*border-radius: 5px;*/
        background-color: #D6F2FD;
    }
    ::-webkit-scrollbar-thumb
    {
        height: 20px;
        /*border-radius: 10px;*/
        -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
        background-color: #89D7F7;
    }
    /*set srcoll end*/
</style>
<body>
<label>房间名</label>
<input id="input_roomName" size="10" maxlength="10"/>
<button onclick="initWebSocket()">进入聊天室</button>
<button onclick="closeWs()">退出聊天室</button><br>
<div class="msg_board"></div>
<input id="input_msg" size="43" maxlength="40"/>
<button onclick="send_msg()" >发送</button>
<br>
<H6>下面才是真的聊天业务实现</H6>
<a href="javascript:closeWs(),initWebSocket(2)">点击和hy聊天</a>
<a href="javascript:closeWs(),initWebSocket(4)">点击和hy3聊天</a>


<!-- jQuery -->
<script src="<%=basePath%>resources/static/AdminLTE/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="<%=basePath%>resources/static/AdminLTE/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="<%=basePath%>resources/static/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="<%=basePath%>resources/static/AdminLTE/plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="<%=basePath%>resources/static/AdminLTE/plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="<%=basePath%>resources/static/AdminLTE/plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="<%=basePath%>resources/static/AdminLTE/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="<%=basePath%>resources/static/AdminLTE/plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="<%=basePath%>resources/static/AdminLTE/plugins/moment/moment.min.js"></script>
<script src="<%=basePath%>resources/static/AdminLTE/plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="<%=basePath%>resources/static/AdminLTE/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="<%=basePath%>resources/static/AdminLTE/plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="<%=basePath%>resources/static/AdminLTE/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=basePath%>resources/static/AdminLTE/dist/js/adminlte.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<%=basePath%>resources/static/AdminLTE/dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=basePath%>resources/static/AdminLTE/dist/js/demo.js"></script>

<script src="<%=basePath%>resources/static/address/city.js" charset="UTF-8"></script>
<script src="<%=basePath%>resources/static/address/city02.js"></script>
<script src="<%=basePath%>resources/static/address/method01.js"></script>
<script src="<%=basePath%>resources/static/address/method02.js"></script>
<script src="<%=basePath%>resources/static/address/method03.js"></script>
<script src="<%=basePath%>resources/static/js/sweetalert.min.js"></script><!-- scripit init-->

</body>
<script type="text/javascript">
    var webSocket;
/*
    // 如果没有连接ws
    if(webSocket != null){
        closeWs();
    }*/

    function send_msg() {
        if (webSocket != null) {
            var input_msg = document.getElementById("input_msg").value.trim();
            if (input_msg == "") {
                return;
            }
            webSocket.send(input_msg);
            // 清除input框里的信息
            document.getElementById("input_msg").value = "";
        } else {
            alert("您已掉线，请重新进入聊天室...");
        }
    };

    function closeWs() {
        if(webSocket != null){
            webSocket.close();

        }
    };

    // websocket需要先关闭，再打开
    function initWebSocket(clientid) {
        var roomName = "shop" + clientid;
        // 房间名不能为空
        if ("WebSocket" in window) {
            //alert("您的浏览器支持 WebSocket!");
            if (webSocket == null) {
                // 这里的路径需要注意 ws://域名:port/应用部署名/EndPoint的路径
                var url = "ws://localhost:8080/cgret/webSocket/chat/" + roomName;
                // 打开一个 web socket
                webSocket = new WebSocket(url);
                alert("新建ws");

            } else {
                alert("您已进入聊天室...");
            }

            webSocket.onopen = function () {
                alert("已进入聊天室" + "shop" + clientid + "，畅聊吧...");
            };

            webSocket.onmessage = function (evt) {
                var msg_board = document.getElementsByClassName("msg_board")[0];
                var received_msg = evt.data;
                var old_msg = msg_board.innerHTML;
                msg_board.innerHTML = old_msg + received_msg + "<br>";
                // 让滚动块往下移动
                msg_board.scrollTop = msg_board.scrollTop + 40;
            };

            webSocket.onclose = function () {
                // 关闭 websocket，清空信息板
                alert("连接已关闭...");
                webSocket = null;
                document.getElementsByClassName("msg_board")[0].innerHTML = "";
            };
        }
        else {
            // 浏览器不支持 WebSocket
            alert("您的浏览器不支持 WebSocket!");
        }
    }
</script>
</html>