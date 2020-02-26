<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String UiPath = basePath + "resources/easyui/";
    double total = 0;
%>

<!DOCTYPE html>
<html>
<head>
    <title>订单详情页面</title>
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

    <style>
        #page{width:auto;}
        #comm-header-inner,#content{width:950px;margin:auto;}
        #logo{padding-top:26px;padding-bottom:12px;}
        #header .wrap-box{margin-top:-67px;}
        #logo .logo{position:relative;overflow:hidden;display:inline-block;width:140px;height:35px;font-size:35px;line-height:35px;color:#f40;}
        #logo .logo .i{position:absolute;width:140px;height:35px;top:0;left:0;background:url(http://a.tbcdn.cn/tbsp/img/header/logo.png);}
    </style>

</head>
<body data-spm="1">

<jsp:include page="Client_HeadNavbar.jsp"/>

<div id="page">

    <div id="content" class="grid-c">

        <div id="address" class="address" style="margin-top: 20px;" data-spm="2">
            <form name="addrForm" id="addrForm" action="#">
                <h3>确认收货地址
                    <span class="manage-address">
 <a href="http://member1.taobao.com/member/fresh/deliver_address.htm" target="_blank" title="管理我的收货地址"
    class="J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.7">管理收货地址</a>
 </span>
                </h3>
                <!-- 这里可以写一个地址list循环 -->
                <ul id="address-list" class="address-list">
                    <li class="J_Addr J_MakePoint clearfix  J_DefaultAddr "  data-point-url="http://log.mmstat.com/buy.1.20">
                        <s class="J_Marker marker"></s>
                        <span class="marker-tip">寄送至</span>
                        <div class="address-info">
                            <a href="#" class="J_Modify modify J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.21">修改本地址</a>
                            <input name="address"
                                   class="J_MakePoint "
                                   type="radio"
                                   value="674944241"
                                   id="addrId_674944241"
                                   data-point-url="http://log.mmstat.com/buy.1.20"
                                   checked="checked" >
                            <label for="addrId_674944241" class="user-address">
                                湖北省 恩施土家族苗族自治州 恩施市 湖北民族学院（信息工程学院）  男生宿舍楼235栋2323102 (某某 收) <em>18427717260</em>
                            </label>
                            <em class="tip" style="display: none">默认地址</em>
                            <a class="J_DefaultHandle set-default J_MakePoint" href="/auction/update_address_selected_status.htm?addrid=674944241" style="display: none" data-point-url="http://log.mmstat.com/buy.1.18">设置为默认收货地址</a>
                        </div>
                    </li>
                </ul>


                <div class="address-bar">
                    <a href="#" class="new J_MakePoint" id="J_NewAddressBtn">使用新地址</a>
                </div>

            </form>
        </div>

            <div>
                <h3 class="dib">确认订单信息</h3>
                <table cellspacing="0" cellpadding="0" class="order-table" id="J_OrderTable" summary="统一下单订单信息区域">
                    <caption style="display: none">统一下单订单信息区域</caption>
                    <thead>
                    <tr>
                        <th class="s-title">店铺宝贝<hr/></th>
                        <th class="s-price">单价(元)<hr/></th>
                        <th class="s-amount">数量<hr/></th>
                        <th class="s-agio">优惠方式(元)<hr/></th>
                        <th class="s-total">小计(元)<hr/></th>
                    </tr>
                    </thead>



                    <tbody data-spm="3" class="J_Shop" data-tbcbid="0" data-outorderid="47285539868"  data-isb2c="false" data-postMode="2" data-sellerid="1704508670">
                    <tr class="first"><td colspan="5"></td></tr>
                    <tr class="shop blue-line">
                        <td colspan="3">
                            订单编号：
                            <script>
                                var obj = eval(JSON.parse(sessionStorage.getItem("newOrderJson")));
                                console.log(obj.newOrder.orderId);
                                document.write(obj.newOrder.orderId);
                            </script>
                            店铺：<a class="J_ShopName J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.6" href="http://localhost:8080/cgret/client/lookShop"
                                  target="_blank" title="烟草公司">烟草公司</a>
                            <span class="seller">卖家：<a href="http://localhost:8080/cgret/client/lookShop" target="_blank" class="J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.15">烟草公司</a></span>
                            <span class="J_WangWang"  data-nick="烟草公司"   data-display="inline" ></span>

                        </td>
                        <td colspan="2" class="promo">
                            <div>
                                <ul class="scrolling-promo-hint J_ScrollingPromoHint">
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr class="item" data-lineid="19614514619:31175333266:35612993875" data-pointRate="0">
                        <td class="s-title">
                            <a href="#" target="_blank" title="Huawei/华为 G520新款双卡双待安卓系统智能手机4.5寸四核手手机" class="J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.5">
                                <img src="http://img03.taobaocdn.com/bao/uploaded/i3/18670026332876589/T1A4icFbNeXXXXXXXX_!!0-item_pic.jpg_60x60.jpg" class="itempic"><span class="title J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.5">Huawei/华为 G520新款双卡双待安卓系统智能手机4.5寸四核手手机</span></a>

                            <div class="props">
                                <span></span>
                            </div>
                            <a title="消费者保障服务，卖家承诺商品如实描述" href="#" target="_blank">
                                <img src="http://img03.taobaocdn.com/tps/i3/T1bnR4XEBhXXcQVo..-14-16.png"/>
                            </a>
                            <div>
                                <span style="color:gray;">卖家承诺72小时内发货</span>
                            </div>
                        </td>
                        <td class="s-price">

                          <span class='price '>
                                 <em class="style-normal-small-black J_ItemPrice"  >630.00</em>
                          </span>

                            <input type="hidden" name="costprice" value="630.00" class="J_CostPrice" />
                        </td>
                        <td class="s-amount" data-point-url="">
                            <input type="hidden" class="J_Quantity" value="1" name="19614514619_31175333266_35612993875_quantity"/>1

                        </td>
                        <td class="s-agio">
                            <div class="J_Promotion promotion" data-point-url="">无优惠</div>
                        </td>
                        <td class="s-total">

                           <span class='price '>
                         <em class="style-normal-bold-red J_ItemTotal "  >630.00</em>
                          </span>
                            <input id="furniture_service_list_b_47285539868" type="hidden" name="furniture_service_list_b_47285539868"/>
                        </td>
                    </tr>



                    <tr class="item-service">
                        <td colspan="5" class="servicearea" style="display: none"></td>
                    </tr>

                    <tr class="blue-line" style="height: 2px;"><td colspan="5"></td></tr>
                    <tr class="other other-line">
                        <td colspan="5">
                            <ul class="dib-wrap">

                                <li class="dib extra-info">

                                    <div class="shoparea">
                                        <ul class="dib-wrap">
                                            <li class="dib title">店铺优惠：</li>
                                            <li class="dib sel"><div class="J_ShopPromo J_Promotion promotion clearfix" data-point-url="http://log.mmstat.com/buy.1.16"></div></li>
                                            <li class="dib fee">  <span class='price '>
 -<em class="style-normal-bold-black J_ShopPromo_Result"  >0.00</em>
  </span>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="shoppointarea"></div>

                                    <div class="farearea">
                                        <ul class="dib-wrap J_farearea">
                                            <li class="dib title">运送方式：</li>
                                            <li class="dib sel" data-point-url="http://log.mmstat.com/jsclick?cache=*&tyxd=wlysfs">
                                                <input type="hidden" name="1704508670:2|actualPaidFee" value="0" class="J_ActualPaidFee" />
                                                <input type="hidden" name="1704508670:2|codAllowMultiple" value="true"/>
                                                <input type="hidden" name="1704508670:2|codSellerFareFee" value="" class="J_CodSellerFareFee"/>
                                                <input type="hidden" name="1704508670:2|codServiceFeeRate" value="" class="J_CodServiceFeeRate"/>
                                                <input type="hidden" name="1704508670:2|codPostFee" value="0" class="J_CodPostFee"/>
                                                <select name="1704508670:2|post" class="J_Fare">
                                                    <option data-fare="1500" value=" 2 " data-codServiceType="2" data-level=""  selected="selected"  >
                                                        快递 15.00元
                                                    </option>
                                                    <option data-fare="2500" value=" 7 " data-codServiceType="7" data-level=""  >
                                                        EMS 25.00元
                                                    </option>
                                                    <option data-fare="1500" value=" 1 " data-codServiceType="1" data-level=""  >
                                                        平邮 15.00元
                                                    </option>
                                                </select>
                                                <em tabindex="0" class="J_FareFree" style="display: none">免邮费</em>
                                            </li>
                                            <li class="dib fee">  <span class='price '>
 <em class="style-normal-bold-red J_FareSum"  >30.00</em>
  </span>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="extra-area">
                                        <ul class="dib-wrap">
                                            <li class="dib title">发货时间：</li>
                                            <li class="dib content">卖家承诺订单在买家付款后，72小时内<a href="#">发货</a></li>
                                        </ul>
                                    </div>

                                    <div class="servicearea" style="display: none"></div>
                                </li>
                            </ul>
                        </td>
                    </tr>

                    <tr class="shop-total blue-line">
                        <td colspan="5">店铺合计(<span class="J_Exclude" style="display: none">不</span>含运费<span class="J_ServiceText" style="display: none">，服务费</span>)：
                            <span class='price g_price '>
                                         <span>&yen;</span><em class="style-middle-bold-red J_ShopTotal"  >630.00</em> </span>
                            <input type="hidden" name="1704508670:2|creditcard" value="false" />
                            <input type="hidden" id="J_IsLadderGroup" name="isLadderGroup" value="false"/>

                        </td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="5">

                            <div class="order-go" data-spm="4">
                                <div class="J_AddressConfirm address-confirm">
                                    <div class="kd-popup pop-back" style="margin-bottom: 40px;">
                                        <div class="box">
                                            <div class="bd">
                                                <div class="point-in">

                                                    <em class="t">实付款：</em>  <span class='price g_price '>
                                                    <span>&yen;</span><em class="style-large-bold-red"  id="J_ActualFee"  >630.00</em>  </span>
                                                </div>

                                                <ul >
                                                    <li><em>寄送至:</em><span id="J_AddrConfirm" style="word-break: break-all;"></span></li>
                                                    <li><em>收货人:</em><span id="J_AddrNameConfirm">某某某 18124317260 </span></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <a id="gotoPay" href="javascript:goForPay()" class=" btn-go" tabindex="0" title="点击此按钮，提交订单。">去支付<b class="dpl-button"></b></a>
                                        <a id="cancelOrder"  href="javascript:cancelOrder();" >取消订单</a>
                                    </div>
                                </div>

                                <div class="J_confirmError confirm-error">
                                    <div class="msg J_shopPointError" style="display: none;"><p class="error">积分点数必须为大于0的整数</p></div>
                                </div>


                                <div class="msg" style="clear: both;">
                                    <p class="tips naked" style="float:right;padding-right: 0">若价格变动，请在提交订单后联系卖家改价，并查看已买到的宝贝</p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>

            <input type="hidden" id="J_useSelfCarry" name="useSelfCarry" value="false" />
            <input type="hidden" id="J_selfCarryStationId" name="selfCarryStationId" value="0" />
            <input type="hidden" id="J_useMDZT" name="useMDZT" value="false" />
            <input type="hidden" name="useNewSplit" value="true" />
            <input type="hidden" id="J_sellerIds" name="allSellIds" value="1704508670" />
        </form>
    </div>

    <div id="footer"></div>
</div>
<div style="text-align:center;">
    <p>来源:<a href="http://www.mycodes.net/" target="_blank">源码之家</a></p>
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

<script>
    function getOrderId() {
        var obj = eval(JSON.parse(sessionStorage.getItem("newOrderJson")));
        console.log(obj.newOrder.orderId);
        console.log(obj.newOrderDetail[0].productid)
    }

    function  cancelOrder() {
        // 获取字符串orderId
        var obj = eval(JSON.parse(sessionStorage.getItem("newOrderJson")));
        var orderId = obj.newOrder.orderId;
        $.ajax({
            url: "http://localhost:8080/cgret/Order/cancelOrder",
            type: "post",
            data: {
              "orderId" : orderId
            },
            success: function (data) {
                if(data == "success"){
                    alert("订单取消成功");
                    window.location.href("http://localhost:8080/cgret/Order/enterMyOrder");
                }
            },
            error:function () {
                alert("订单取消失败");
            }
        })
    }

    function goForPay(){
        var page = document.getElementById("page");

        var obj = eval(JSON.parse(sessionStorage.getItem("newOrderJson")));
        var orderId = obj.newOrder.orderId;
        $.ajax({
            url: "http://localhost:8080/cgret/Order/goForPay",
            type: "post",
            data: {
                "orderId" : orderId
            },
            success:function () {
                alert("支付成功");
                page.empty();
                window.location.href("http://localhost:8080/cgret/Order/enterMyOrder");
            },
            error:function () {
                alert("error");
            }
        })
    }
</script>
</body>

</html>
