<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String UiPath = basePath + "resources/easyui/";
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>烟草公司订货平台</title>

    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="<%=basePath%>resources/static/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>resources/static/assets/css/form-elements.css">
    <link rel="stylesheet" href="<%=basePath%>resources/static/assets/css/style.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="<%=basePath%>resources/static/assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=basePath%>resources/static/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=basePath%>resources/static/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=basePath%>resources/static/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=basePath%>resources/static/assets/ico/apple-touch-icon-57-precomposed.png">

    <title>烟草公司订货平台</title>
<link href="<%=basePath%>resources/static/css/login.css" rel="stylesheet" type="text/css" />


</head>

<body>
<div class="login_box">
      <div class="login_l_img"><img src="<%=basePath%>resources/static/images/login-img.png" /></div>
      <div class="login">
          <div class="login_logo"><a href="#"><img src="<%=basePath%>resources/static/images/login_logo.png" /></a></div>
          <div class="login_name">
               <p>烟草公司订货平台</p>
          </div>
          <form method="post">
              <input id="loginName" name="username" type="text"  placeholder="用户名" />
              <input id="password" name="password" type="password"  placeholder="密码"  />
              <input id="rememberMe" type="checkbox" name="rememberMe"/>记住我<br>
          </form>
          <input value="登录" style="width:100%;" type="submit" onclick="check_login()"/>
          <div>  <a id="register_a"  class="launch-modal" href="#" data-modal-id="modal-register">免费注册</a> </div>


      </div>
      <div class="copyright">哈哈哈笑嘻嘻有限公司 版权所有©2016-2019 技术支持电话：000-123456abc</div>
</div>
<div style="text-align:center;">


    <!-- SignUp alert -->
    <div class="modal fade " id="modal-register" tabindex="-1" role="dialog" aria-labelledby="modal-register-label" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button id="close-btn" type="button" class="close" data-dismiss="modal" >
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                    <h3 class="modal-title" id="modal-register-label">注册</h3>
                    <p>Fill in the form below to get instant access:</p>
                    <p id="notice" hidden style="color: red" >  </p>
                    <p id="timer" hidden style="color: red">5秒</p>
                </div>

                <div class="modal-body">

                    <form role="form" action="" method="post" class="registration-form">
                        <div class="form-group">
                            <label class="sr-only" for="register-name">用户名</label>
                            <input type="text" value="" name="form-name" placeholder="用户名" class="form-first-name form-control" id="register-name">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="register-pwd1">密码</label>
                            <input type="password" value="" name="form-password" placeholder="密码" class="form-password form-control" id="register-pwd1">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="register-pwd2">确认密码</label>
                            <input type="password" value="" name="form-password" placeholder="确认密码" class="form-password form-control" id="register-pwd2">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="register-email">电子邮箱</label>
                            <input type="email"  value="" name="form-email" placeholder="电子邮箱" class="form-email form-control" id="register-email">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="register-tel">手机号</label>
                            <input type="tel"  value="" name="form-num" placeholder="手机号" class="form-email form-control" id="register-tel">
                        </div>

                        <button type="submit" class="btn launch-modal" onclick=" check_register() ">注册</button>
                    </form>

                </div>

            </div>
        </div>
    </div>
</div>


    <!-- Javascript -->
    <script src="<%=basePath%>resources/static/assets/js/jquery-1.11.1.min.js"></script>
    <script src="<%=basePath%>resources/static/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/static/assets/js/jquery.backstretch.min.js"></script>
    <script src="<%=basePath%>resources/static/assets/js/scripts.js"></script>
    <script src="<%=basePath%>resources/static/js/sweetalert.min.js"></script><!-- scripit init-->
    <script src="<%=basePath%>resources/static/js/vue.min.js"></script>

    <!--[if lt IE 10]>
    <script src="<%=basePath%>resources/static/assets/js/placeholder.js"></script>
    <![endif]-->
    </body>

<script type="text/javascript">
    // 登录检查函数
    function check_login () {
        var remember = document.getElementById("rememberMe");
        var rememberMe = remember.checked;
        console.log(rememberMe);

        var login = $("#loginName").val();
        var pwd = $("#password").val();
        var chk = document.getElementsByName("chk");
        if(login == "" || login == null)
            alert("用户名/邮箱地址不能为空！");
        else if(pwd == "" || pwd == null)
            alert("密码不能为空！");
        else {
            $.ajax({
                type:"post",
                data:{
                    loginName:login,
                    loginPwd:pwd,
                    rememberMe:rememberMe
                },
                url:"subLogin",
                success:function (str2) {
                    if(str2 == "admin"){
                        // 跳转到主界面
                         window.location.href="/cgret/Login/admin_main";
                    } else if(str2 == "user"){
                            window.location.href="http://localhost:8080/cgret/client/lookShop";
                    } else if(str2 == "unmatch"){
                        alert("用户名不存在或密码错误！");
                    } else if(str2 == "有admin权限"){
                        alert("你是一个admin")
                    } else if(str2 == "无admin权限"){
                        alert("你只是一个user");
                    }
                }
            })
        }
    }


    // 5s倒数计时
    var inter = null;
    var t ;
    var time = document.getElementById("timer");

    function  fun() {
        t--;
        time.innerHTML = t + "秒";
        if(parseInt(t) <= parseInt(0)){
            clearInterval(inter);
            //进入登录界面，在原本窗口返回登录界面
            window.location.href="http://localhost:8080/cgret/Login/login";
            $("#close-btn").click();

        }
    }

    //注册检查函数
    function check_register(){
        var username = $("#register-name").val();
        var reg_pwd = $("#register-pwd1").val();
        var conf_pwd = $("#register-pwd2").val();
        var email_add = $("#register-email").val();
        var telphone = $("#register-tel").val();

        t = 5;

        if(username == "" || username == null)
            alert("用户名不能为空！");
        else if(reg_pwd == "" || reg_pwd == null)
            alert("密码不能为空！");
        else if(conf_pwd == "" || conf_pwd == null)
            alert("请确认密码！")
        else if (email_add == "" || email_add == null)
            alert("邮箱地址不能为空！");
        else if(telphone == "" || telphone == null)
            alert("手机号不能为空");
        else if (reg_pwd != conf_pwd)
            alert("密码不一致，请重新输入！");
        else{
            $.ajax({
                type:"post",
                data:{
                    regName:username,
                    regPwd:reg_pwd,
                    regEmail:email_add,
                    regTel:telphone,
                },
                url:"Register",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (str) {
                    alert(str);
                    if(str == "注册成功"){
                        inter = setInterval("fun()",1000);
                        $("#notice").html("注册成功，5s后自动返回登录界面(...如果未自动跳转，请手动返回)");
                        $("#notice").show();
                        $("#timer").show();



                        //window.location.href="resendMail";
                        window.open("resendMail");
                        $.ajax({
                            type:"get",
                            url:"reSends",
                            data:{
                                regEmail:email_add,
                                regName:username,
                            },
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            success:function (str) {
                                Swal(str);
                            }
                        })
                    } else if(str == "用户名已存在" ){
                        $("#notice").html("注册失败，用户名已存在");
                        $("#notice").show();
                    }else if(str == "用户名格式不正确" ){
                        $("#notice").html("注册失败，用户名格式不正确");
                        $("#notice").show();
                    }else if(str == "手机号已绑定其他账号"){
                        $("#notice").html("注册失败，手机号已绑定其他账号");
                        $("#notice").show();
                    }else if(str == "手机号格式不正确"){
                        $("#notice").html("注册失败，手机号格式不正确");
                        $("#notice").show();
                    }else if (str == "邮箱已绑定其他账号"){
                        $("#notice").html("注册失败，邮箱已绑定其他账号");
                        $("#notice").show();
                    } else if (str == "邮箱格式不正确"){
                        $("#notice").html("注册失败，邮箱格式不正确");
                        $("#notice").show();
                    } else if (str == "密码长度需在8-20以内"){
                        $("#notice").html("注册失败，密码长度需在8-20以内");
                        $("#notice").show();
                    }
                }

            })
        }
    }


</script>

</html>
