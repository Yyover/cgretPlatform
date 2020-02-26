<%@ page import="com.javaee6.cgret.model.Client" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String UiPath = basePath + "resources/easyui/";
    Client client = (Client)request.getSession().getAttribute("clientInfo");
%>

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>欢迎来到烟草公司订烟平台</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
</head>


<body class="hold-transition sidebar-mini">

<div class="wrapper">

    <%--左边菜单和上面的导航栏--%>
    <jsp:include page="Shop_HeadNavbar.jsp"/>
    <jsp:include page="Shop_leftDropMenu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">

        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>个人设置</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="<%=basePath%>admin_main">首页</a></li>
                            <li class="breadcrumb-item active">个人设置</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="sticky-top mb-3">

                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">填写信息</h3>
                                </div>
                                <div class="card-body">
                                    <%--clientemail--%>
                                    <div class="form-group">
                                        <label>用户名</label>
                                        <input type="text" class="form-control" placeholder="<%=client.getClientName()%>" disabled="">
                                    </div>
                                        <div class="form-group">
                                            <label>ID</label>
                                            <input type="text" class="form-control" placeholder="#<%=client.getClientId()%>" disabled="">
                                        </div>
                                    <%--clientemail--%>
                                    <div class="form-group">
                                        <label>密码</label>
                                        <input type="text" class="form-control" placeholder="<%=client.getPassword()%>" disabled="">
                                    </div>
                                    <%--clientemail--%>
                                    <div class="form-group">
                                        <label>电话</label>
                                        <input type="text" class="form-control" placeholder="<%=client.getTelephone()%>" disabled="">
                                    </div>

                                        <div class="form-group">
                                            <label>Email</label>
                                            <input type="text" class="form-control" placeholder="<%=client.getEmail()%>" disabled="">
                                        </div>

                                        <div class="input-group">
                                            <label>上传头像</label>
                                            <br>
                                            <div class="custom-file">
                                                <input type="file" class="custom-file-input" id="exampleInputFile">
                                                <label class="custom-file-label" for="exampleInputFile">选择图片</label>
                                            </div>
                                            <div class="input-group-append">
                                                <span class="input-group-text" id="">上传</span>
                                            </div>
                                        </div>



                                        <div>
                                                <form action="#">
                                                    <label for="addr-show">默认收货地址：
                                                    <!--省份选择-->
                                                    <select id="prov" onchange="showCity(this)">
                                                        <option>=请选择省份=</option>

                                                    </select>

                                                    <!--城市选择-->
                                                    <select id="city" onchange="showCountry(this)">
                                                        <option>=请选择城市=</option>
                                                    </select>

                                                    <!--县区选择-->
                                                    <select id="country" onchange="selecCountry(this)">
                                                        <option>=请选择县区=</option>
                                                    </select>
                                                    <button type="button" class="met1" onClick="showAddr()">确定</button>
                                                    </label>
                                                    <br>

                                                    <div id="defaultAddress" class="input-group ">
                                                        <input type="text" id="addr-show" value="" disabled="">
                                                        <input type="text" id="detailAddress" class="form-control" value=""  placeholder="街道、单元、门牌号" >
                                                        <span class="input-group-append">
                                                            <button type="button" onclick="setDefaultAddress()" class="btn btn-info btn-flat">保存</button>
                                                        </span>
                                                    </div>
                                                    <br/>
                                                </form>
                                        </div>

                                    <!-- /input-group -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.col -->

                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div><!-- /.container-fluid -->
        </section>
        <!-- /.content -->

    </div>
    <!-- /.content-wrapper -->

    <footer class="main-footer">
        <div class="float-right d-none d-sm-block">
            <b>Version</b> 3.0.1
        </div>
        <strong>Copyright &copy; 2014-2019 <a href="http://adminlte.io">AdminLTE.io</a>.</strong> All rights
        reserved.
    </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

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
<script src="<%=basePath%>resources/static/js/sweetalert.min.js"></script><!-- scripit init-->
<script src="<%=basePath%>resources/static/js/vue.min.js"></script>

<%--[if lt IE 10]>--%>
<script src="<%=basePath%>resources/static/assets/js/placeholder.js"></script>
</body>

<script>

      function setDefaultAddress(){

        var detailAddress = document.getElementById("detailAddress").value;
        alert(detailAddress);
        console.log(detailAddress);

        $.ajax({
            method:'post',
            url:'http://localhost:8080/cgret/client/setDefaultAddress',
            data:{
                detailAddress: detailAddress,
                province: provice[current.prov].name,
                city: provice[current.prov]["city"][current.city].name,
                district: provice[current.prov]["city"][current.city].districtAndCounty[current.country]
            },
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            dataType: "json",
            success: function (data) {
                alert("成功");
            }
        })

      }

</script>
</html>
