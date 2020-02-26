<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String UiPath = basePath + "resources/easyui/";
    double total = 0;
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title >购物车</title>
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
    <link rel="stylesheet" href="<%=basePath%>resources/static/css/reset.css">
    <link rel="stylesheet" href="<%=basePath%>resources/static/css/carts.css">
</head>

<body class="hold-transition sidebar-mini layout-fixed">
<jsp:include page="Client_HeadNavbar.jsp"/>

<body>
<section class="cartMain">
    <div class="cartMain_hd">
        <ul class="order_lists cartTop">
            <li class="list_chk">
                <!--所有商品全选-->
                <input type="checkbox" id="all" class="whole_check">
                <label for="all"></label>
                全选
            </li>
            <li class="list_con">商品信息</li>
            <li class="list_info">商品参数</li>
            <li class="list_price">单价</li>
            <li class="list_amount">数量</li>
            <li class="list_sum">金额</li>
            <li class="list_op">操作</li>
        </ul>
    </div>

    <div class="cartBox">
        <div class="shop_info">
            <div class="all_check">
                <!--店铺全选-->
                <input type="checkbox" id="shop_a" class="shopChoice">
                <label for="shop_a" class="shop"></label>
            </div>
            <div class="shop_name">
                店铺：<a href="javascript:;">烟草公司</a>
            </div>
        </div>
        <div class="order_content">
           <%-- <ul class="order_lists">

                <li class="list_con">
                    <div class="list_img"><a href="javascript:;"><img src="./images/1.png" alt=""></a></div>
                    <div class="list_text"><a href="javascript:;">夏季男士迷彩无袖T恤圆领潮流韩版修身男装背心青年时尚打底衫男</a></div>
                </li>
                <li class="list_info">
                    <p>规格：默认</p>
                    <p>尺寸：16*16*3(cm)</p>
                </li>
                <li class="list_price">
                    <p class="price">￥980</p>
                </li>
                <li class="list_amount">
                    <div class="amount_box">
                        <a href="javascript:;" class="reduce reSty">-</a>
                        <input type="text" value="1" class="sum">
                        <a href="javascript:;" class="plus">+</a>
                    </div>
                </li>
                <li class="list_sum">
                    <p class="sum_price">￥980</p>
                </li>
                <li class="list_op">
                    <p class="del"><a href="javascript:;" class="delBtn">移除商品</a></p>
                </li>
            </ul>--%>

            <c:forEach items="${carts}" var="k" varStatus="status">
                <ul id="ul_${cgretList[status.index].productid}" class="order_lists">
                    <li class="list_chk">
                        <input type="checkbox" name="checkBox" id="${cgretList[status.index].productid}" class="son_check">
                        <label for="${cgretList[status.index].productid}"></label>
                    </li>
                    <li class="list_con">
                        <div class="list_img">
                            <script>

                                var thisUrl = '${cgretList[status.index].imageurl}';
                                var urlArray = new Array();
                                urlArray = thisUrl.split("|");
                                document.write("<a href='http://localhost:8080/cgret/items/info/${cgretList[status.index].productid}' target='_blank'><img height='80' width='100' src='/cgret/" +urlArray[0] +"'></a> ");
                            </script>
                        </div>
                        <div class="list_text"><a href="javascript:;">${cgretList[status.index].productname}</a></div>
                    </li>
                    <li class="list_info">
                        <p>规格：一条</p>
                        <p>余量：${cgretList[status.index].leftnum}</p>
                    </li>
                    <li class="list_price">
                        <p class="price">￥${k.commodityPrice}</p>
                    </li>
                    <li class="list_amount">
                        <div class="amount_box">

                            <a href="javascript:subNum('${cgretList[status.index].productid}')" class="reduce reSty">-</a>
                            <input type="text" value="${k.commodityNum}" class="sum" disabled="">
                            <a href="javascript:addNum('${cgretList[status.index].productid}')" class="plus">+</a>
                        </div>
                    </li>
                    <li class="list_sum">
                        <p class="sum_price">￥
                            <%--<script>
                                var totaool = ${k.commodityPrice}*${k.commodityNum};
                                document.write(totaool);
                            </script>--%>
                            ${k.commodityPrice}
                        </p>
                    </li>
                    <li class="list_op">
                        <p class="del"><a href="javascript:;" class="delBtn">移除商品</a></p>
                    </li>

                    <section class="my_model">
                        <p class="title">删除宝贝<span class="closeModel">X</span></p>
                        <p>您确认要删除该宝贝吗？</p>
                        <div class="opBtn"><a href="javascript:delItem('${cgretList[status.index].productid}');" class="dialog-sure">确定</a><a href="javascript:;" class="dialog-close">关闭</a></div>
                    </section>
                </ul>
            </c:forEach>
        </div>
    </div>


    <!--底部-->
    <div class="bar-wrapper">
        <div class="bar-right">
            <div class="piece">已选商品<strong class="piece_num">0</strong>件</div>
            <div class="totalMoney">共计: <strong class="total_text">0.00</strong></div>
            <div class="calBtn"><a href="javascript:calTotal();">结算</a></div>
        </div>
    </div>
</section>
<section class="model_bg"></section>

<script src="<%=basePath%>resources/static/js/jquery-1.10.2.js"></script>
<script src="<%=basePath%>resources/static/js/carts.js"></script>
</body>
<!-- ./wrapper -->


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
<!-- AdminLTE for demo purposes -->
<script src="<%=basePath%>resources/static/AdminLTE/dist/js/demo.js"></script>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="<%=basePath%>resources/static/js/carts.js"></script>

<script>

        // 获得需要结算的商品id数组
        function calItem(){
            var id = document.getElementsByName("checkBox");
            var value = new Array();

            for(var i = 0; i < id.length; i++){
                if(id[i].checked){
                    value.push(id[i].id);
                }
            }
            return value;
        }


        // 结算
        function  calTotal() {

            var idList =  calItem();
            $.ajax({
                type:"post",
                url:"/cgret/cart/calTotal",
                data: {
                    "idList" : idList
                },
                traditional:true,
                success: function (data) {
                    for(var i = 0; i < idList.length; i++){
                        var ulParentNode = document.getElementById("ul_"+idList[i]).parentNode;
                        var ulNode = document.getElementById("ul_"+idList[i]);
                        ulParentNode.removeChild(ulNode);
                    }

                    // 获取json字符串，保存到键为allJson中
                    sessionStorage.setItem("newOrderJson", JSON.stringify(data));
                    window.open("http://localhost:8080/cgret/cart/enterOrderDetail");
                },
                error:function () {
                    alert("订单确认失败");
                }
            })
        }

        // 增加数量
        function  addNum(id) {
            $.ajax({
                type: "post",
                url: "http://localhost:8080/cgret/cart/plus",
                data: id,
                success: function () {
                    console.log("添加成功");
                },
                error: function () {
                    alert("增加失败")
                }
            })
        }

        // 减少数量
        function  subNum(id) {
            $.ajax({
                type: "post",
                url: "http://localhost:8080/cgret/cart/substract",
                data: {
                    "productId" : id
                },
                success: function () {
                    console.log("减少成功");
                },
                error: function () {
                    alert("减少失败")
                }
            })
        }

        // 删除商品
        function  delItem(id) {
            $.ajax({
                type: "post",
                url: "http://localhost:8080/cgret/cart/delete",
                data: {
                    "productId" : id
                },
                success: function () {
                    console.log("删除成功");
                },
                error: function () {
                    alert("删除失败")
                }
            })
        }
</script>

</body>


</html>
