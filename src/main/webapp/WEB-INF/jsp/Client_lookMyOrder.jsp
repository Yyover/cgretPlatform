<%--
  Created by IntelliJ IDEA.
  User: Hyao
  Date: 2020/2/23 0023
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String UiPath = basePath + "resources/easyui/";
%>

<html>
<head>
    <title>我的订单</title>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <title>欢迎来到烟草公司订烟平台</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/static/css/index.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/static/css/main.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/static/css/demo.css">
    <link href="<%=basePath%>resources/static/css/demo2.css" rel="stylesheet" media="all">
    <!--[if IE]>
    <style type="text/css">
        li.remove_frame a {
            padding-top: 5px;
            background-position: 0px -3px;
        }
    </style>
    <![endif]-->

    <script src="<%=basePath%>resources/static/js/vue.js" charset="UTF-8"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
    <jsp:include page="Client_HeadNavbar.jsp"/>

    <button>待付款</button>
    <button>待发货</button>
    <button>待收货</button>
    <button>评价</button>
    <button>退货/售后</button>

    <table id="table">
        <tr>

        </tr>
    </table>
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
</html>
