<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String UiPath = basePath + "resources/easyui/";
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>【商品详情页】${productname}</title>
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
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        .slide{
            position: relative;
            width: 500px;
            height: 400px;
            margin: 10px;
            overflow: hidden;
        }
        .img ul{
            position: absolute;
            left: -500px;
            width: 2500px;
        }
        .img li{
            list-style-type: none;
            float: left;
        }
        img{
            width: 500px;
            height: 400px;
        }
        #num li{
            list-style-type: none;
            background-color: white;
            border: 1px solid red;
            border-radius: 100px;
            float: left;
            width: 10px;
            height: 10px;
            margin: 10px;
            cursor: pointer;
        }
        #num {
            position: absolute;
            top: 370px;
            left: 200px;
        }
        .btn{
            font-size: 50px;
            color: grey;
        }
        #left{
            position: absolute;
            top: 110px;
            left: 20px;
            cursor: pointer;
        }
        #right{
            position: absolute;
            top: 110px;
            right: 40px;
            cursor: pointer;
        }
    </style>

</head>

<body>

    <jsp:include page="Client_HeadNavbar.jsp"/>

            <div class="slide">
                <div class="img">
                    <ul id="slide_img">

                        <script>

                            var thisUrl = '${imageurl}';
                            var urlArray = new Array();
                            urlArray = thisUrl.split("|");

                            document.write(" <li><img height='200' width='200' src='/cgret/" +urlArray[0] +"'style=\"display: block\"></li>");
                            document.write(" <li><img height='200' width='200' src='/cgret/" +urlArray[1] +"'></li>");
                            document.write(" <li><img height='200' width='200' src='/cgret/" +urlArray[2] +"'></li>");
                            document.write(" <li><img height='200' width='200' src='/cgret/" +urlArray[0] +"'></li>");

                        </script>

                    </ul>
                </div>
                <ul id="num">
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
                <div class="btn">
                    <span id="left"><</span>
                    <span id="right">></span>
                </div>


            </div>

    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>
                    $(document).ready(function () {
                    initRadius();
                });

                var number = 1;  //设置为全局变量

                //轮播图图片主体
                function startSlide() {
                    dealRadius(number);
                    if(number == 3) {
                        number = 0;
                        $('#slide_img').css({left: '0px'});  //处理无缝衔接图
                        dealRadius(0); // 处理无缝衔接小圆点的跳转
                    }
                    number++;
                    var imageLeft = -500 * number;
                    $('#slide_img').animate({left: imageLeft});
                }
                var timer = setInterval(startSlide,3000);



                //小圆点的轮播实现
                function dealRadius(num) {
                    var lis = $('#num li');
                    lis.eq(num).css('background-color', 'red');
                    for(var i = 0; i < num; i++) {
                        lis.eq(i).css('background-color','white');
                    }
                    for(var i = num + 1; i < 3; i++) {
                        lis.eq(i).css('background-color','white');
                    }
                }

                //初始化小圆点
                function initRadius() {
                    var lis = $('#num li');
                    lis.eq(0).css('background-color', 'red');
                }


                //左右按钮的实现
                $('#left').mousedown (function() {
                    clearInterval(timer);
                    if(number == 0) {
                        $('#slide_img').css({left: '-4000px'});
                        number = 4;
                    }
                    var imageLeft = -500 * (number-1);
                    $('#slide_img').animate({left: imageLeft});
                    number--;
                    if(number == 0) {
                        dealRadius(3);
                    } else {
                        dealRadius(number-1);
                    }
                });
                $('#left').mouseup(function() {
                    timer = setInterval(startSlide,3000);
                });

                $('#right').mousedown (function() {
                    clearInterval(timer);
                    if(number == 3) {
                        number = 0;
                        $('#slide_img').css({left: '0px'});  //处理无缝衔接图
                    }
                    var imageLeft = -500 * (number+1);
                    $('#slide_img').animate({left: imageLeft});
                    dealRadius(number);
                    number++;

                });
                $('#right').mouseup(function() {
                    timer = setInterval(startSlide,3000);
                });


                //小圆点的点击实现
                $('#num').on('click','li',function(){
                    clearInterval(timer);
                    var _number = $(this).index() + 1;
                    number = _number
                    dealRadius(number-1);
                    var imageLeft = -500 * number;
                    $('#slide_img').animate({left: imageLeft});
                    timer = setInterval(startSlide,3000);
                });
    </script>


        <input id="input" value="input"/>
        <div style="">
        商品名称：${productname};
        <br>
            余量:${leftnum}<br>
            运费:未知<br>
            月销量:未知<br>
            累计评价：未知<br>
            <div class="form-group">
                <label>数量（单位：条)</label>
                <input type="number" id="count">

            </div>

            <button onclick="addItemToCart(${productid},${leftnum})">加入购物车</button>

        </div>



        <!-- Control Sidebar -->
        <aside class="control-sidebar control-sidebar-dark">
            <!-- Control sidebar content goes here -->
        </aside>
        <!-- /.control-sidebar -->



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

</body>


    <script>

        function addItemToCart(id,leftnum) {
            alert(id + "," +leftnum);
            if(leftnum < $("#count").val()){
                alert("商品库存不足");
            }else {
                $.ajax({
                    type:"post",
                    url:"http://localhost:8080/cgret/client/addItemTocart",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    data:{
                        "productId":id,
                        "count": $("#count").val()
                    },
                    success:function () {
                        alert("添加购物车成功" + $("#count").val()  + "" + id)
                    },
                    error:function () {
                        alert("添加购物车失败" + $("#count").val()  + "" + id);
                        window.open("http://localhost:8080/cgret/login");

                    }
                })
            }

        }

    </script>
</html>
