<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String UiPath = basePath + "resources/easyui/";
    String jumpToUrl = "http://localhost:8080/cgret/client/lookShop";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv=refresh content=3;url=<%=jumpToUrl %>>
    <title>电脑网站支付return_url</title>
</head>
<%
/* *
 * 功能：支付宝服务器同步通知页面
 * 日期：2017-03-30
 * 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。


 *************************页面功能说明*************************
 * 该页面仅做页面展示，业务逻辑处理请勿在该页面执行
 */
%>
<body>

    <div align="center">
        <h2 style=color:red>请使用您的账号登陆系统</h2>
        <h2 style=color:red><span id=jump>3</span> 秒钟后页面将自动返回商户页面...</h2>
        <h2><a  href="http://localhost:8080/cgret/client/lookShop" target="_blank">点击此处立即跳转至商户页面</a></h2>
    </div>





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


    <script src="<%=basePath%>resources/static/AdminLTE/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="<%=basePath%>resources/static/AdminLTE/plugins/jquery-ui/jquery-ui.min.js"></script>
    <script src="<%=basePath%>resources/static/AdminLTE/dist/js/adminlte.min.js"></script>
    <script src="<%=basePath%>resources/static/AdminLTE/dist/js/demo.js" charset="UTF-8"></script>

    <script src="<%=basePath%>resources/static/assets/js/jquery-1.11.1.min.js"></script>
    <script src="<%=basePath%>resources/static/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>resources/static/assets/js/jquery.backstretch.min.js"></script>
    <script src="<%=basePath%>resources/static/assets/js/scripts.js"></script>

    <%--[if lt IE 10]>--%>
    <script src="<%=basePath%>resources/static/assets/js/placeholder.js"></script>
    <script src="<%=basePath%>resources/static/js/index.js"></script>


</body>

    <script>
        function countDown(secs){
            jump.innerText=secs;
            if(--secs>0)
                setTimeout("countDown("+secs+" )",1000);
        }
        countDown(3);
        function jumpToIndex(){
            window.location.href='<%=jumpToUrl %>';
        }
    </script>

</html>