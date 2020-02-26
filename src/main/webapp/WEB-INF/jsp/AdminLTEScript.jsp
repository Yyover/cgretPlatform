<%--
  Created by IntelliJ IDEA.
  User: Hyao
  Date: 2020/2/19 0019
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String UiPath = basePath + "resources/easyui/";
    double total = 0;
%>
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
