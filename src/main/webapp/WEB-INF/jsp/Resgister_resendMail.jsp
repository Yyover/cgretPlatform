<%@ page import="com.javaee6.cgret.model.Client" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/28
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Client client = (Client)request.getSession().getAttribute("clientInfo");
%>
<html>
<head>
    <title>注册成功</title>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
    <link rel="stylesheet" href="<%=basePath%>resources/static/assets/css/style.css">

    <script type="text/javascript">
        var wait_time = parseInt(getCookie("wait"));
        var mail = <%=client.getEmail()%>;
        var name = <%=client.getClientName()%>;
        function aboutBtn(o) {
            if(wait_time == 0){
                o.removeAttribute("disabled");
                o.value="点此重发";
                wait_time = 60;
                document.cookie="wait=60;";
            }
            else {
                o.setAttribute("disabled",true);
                var times = wait_time.toString();
                o.value=times+"秒后重新发送";
                wait_time--;
                document.cookie="wait="+wait_time.toString()+";";
                setTimeout(function () {
                    aboutBtn(o);
                },1000)
            }
        }

        function reSend() {
            if(wait_time == 60){
                $.ajax({
                    type:"get",
                    url:"reSends",
                    data:{
                        regEmail:mail.toString(),
                        regName:name.toString()
                    },
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success:function (str) {
                        alert(str);
                    }
                })
            }
            else{
                alert("发送失败！倒计时内请不要刷新此页面！");
            }
        }

        function resetCookie() {
            var exp = new Date();
            exp.setTime(exp.getTime() + 60 * 1000);//60秒后过期
            if (getCookie("wait") == "") {
                document.cookie = "wait=60;expires="+exp.toUTCString();
            }
            else if(parseInt(getCookie("wait")) == 60 || parseInt(getCookie("wait")) == 0) {
                document.cookie = "wait=60;expires="+exp.toUTCString();
            }
            else
                document.cookie = "wait=59";
        }

        function getCookie(cname){
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for(var i=0; i<ca.length; i++) {
                var c = ca[i].trim();
                if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
            }
            return "";
        }
    </script>

</head>
<body onload="resetCookie();" onunload="resetCookie();">
<h1 style="text-align: center; color: black; font-size: 35px">我们已向您的注册邮箱<%=client.getEmail()%>发送了一封验证邮件，请按照邮件的步骤完成验证。</h1>
<h2 style="text-align: center; color: red; font-size: 20px">30秒内没有收到邮件?</h2>
<div style="position: absolute;right: 0px;width: 800px ;border: 3px; padding: 10px;">
    <input id="btn" type="button" value="点此发送" onclick="reSend();aboutBtn(this);"></input>
</div>
<br><br><br><br>


<%--如果当天没有完成验证，那么写一个功能，每天凌晨4点定时，将articleCode不为0000的账号从数据库删除。--%>
<h3 style="text-align: center; color: red; font-size: 30px">请当天内完成验证，否则我们将清空所有未激活账号的注册数据！</h3>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<%--<script src="https://cdn.staticfile.org/j query/3.4.0/jquery.min.js"></script>--%>
<%--<script src="https://cdn.staticfile.org/jquery-cookie/1.4.0/jquery.cookie.min.js"></script>--%>

</body>
</html>
