<%@ page import="com.javaee6.cgret.model.Client" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String UiPath = basePath + "resources/easyui/";

    Client client = (Client)request.getSession().getAttribute("clientInfo");
%>


<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="#" class="brand-link">
        <img src="<%=basePath%>resources/static/AdminLTE/dist/img/pikachu.png"
             alt="CgretPlatform   Logo"
             class="brand-image img-circle elevation-3"
             style="opacity: .8">
        <span class="brand-text font-weight-light">管理员</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <%--这个区块进入个人设置--%>
        <div  id="personalSettings" class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="<%=basePath%>resources/static/AdminLTE/dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
            </div>
            <div  class="info">
                <%--进入客户的个人设置，此处jsp仍然是商家的，但是进的确实client的设置，后期拓展的时候会改过来。--%>
                <a href="http://localhost:8080/cgret/client/personalSettings" class="d-block"> <%=client.getClientName()%> </a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            交易管理
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="../index.html" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>已卖出的宝贝</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="../index2.html" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>评价管理</p>
                            </a>
                        </li>
                    </ul>
                </li>


                <li class="nav-item has-treeview">
                    <a href="" class="nav-link ">

                        <i class="nav-icon fas fa-chart-pie"></i>
                        <p>
                            宝贝管理
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/shop/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>发布宝贝</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/shop/reviewGoodStatus" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>宝贝状态（上架、下架）</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/shop/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>出售中的宝贝</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/shop/" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>仓库中的宝贝</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/shop/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>我的产品库</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/shop/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>体检中心</p>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-copy"></i>
                        <p>
                            物流管理
                            <i class="fas fa-angle-left right"></i>
                            <span class="badge badge-info right">6</span>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/shop/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>发货</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/shop/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>我要寄快递</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/shop/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>物流工具</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/shop/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>物流服务</p>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-tree"></i>
                        <p>
                            店铺管理
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/client/lookShop" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>查看店铺</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>图片空间</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>店铺基本设置</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-edit"></i>
                        <p>
                            客户服务
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>退款管理</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="http://localhost:8080/cgret/releaseGood" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>投诉管理</p>
                            </a>
                        </li>

                    </ul>
                </li>
                <li class="nav-item">
                    <a href="http://localhost:8080/cgret/releaseGood" class="nav-link">
                        <i class="nav-icon fas fa-th"></i>
                        <p>
                            支付通商家中心
                            <span class="right badge badge-danger">New</span>
                        </p>
                    </a>
                </li>


                <li class="nav-header">MISCELLANEOUS</li>
                <li class="nav-item">
                    <a href="https://adminlte.io/docs/3.0" class="nav-link">
                        <i class="nav-icon fas fa-file"></i>
                        <p>Documentation</p>
                    </a>
                </li>
                <li class="nav-header">MULTI LEVEL EXAMPLE</li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="fas fa-circle nav-icon"></i>
                        <p>Level 1</p>
                    </a>
                </li>
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="nav-icon fas fa-circle"></i>
                        <p>
                            Level 1
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Level 2</p>
                            </a>
                        </li>
                        <li class="nav-item has-treeview">
                            <a href="#" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>
                                    Level 2
                                    <i class="right fas fa-angle-left"></i>
                                </p>
                            </a>
                            <ul class="nav nav-treeview">
                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>Level 3</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>Level 3</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                        <i class="far fa-dot-circle nav-icon"></i>
                                        <p>Level 3</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Level 2</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="fas fa-circle nav-icon"></i>
                        <p>Level 1</p>
                    </a>
                </li>
                <li class="nav-header">LABELS</li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="nav-icon far fa-circle text-danger"></i>
                        <p class="text">Important</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="nav-icon far fa-circle text-warning"></i>
                        <p>Warning</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="nav-icon far fa-circle text-info"></i>
                        <p>Informational</p>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>