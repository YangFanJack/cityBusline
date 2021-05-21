<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>城市公交后台管理</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/fontawesome-free/css/all.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/adminlte.min.css">

    <!-- toast弹出框 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/toast.min.css">
</head>

<body class="hold-transition sidebar-mini">
    <div class="wrapper">
        <!-- Navbar -->
        <nav class="main-header navbar navbar-expand navbar-white navbar-light">
            <!-- Left navbar links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
                </li>
                <li class="nav-item d-none d-sm-inline-block">
                    <a href="#" class="nav-link">闲置链接</a>
                </li>
            </ul>

            <!-- Right navbar links -->
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#" role="button">
                        当前管理员：<span>${sessionScope.managerAccount}</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" role="button">
                        <button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                            data-target="#modal_1">修改密码</button>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/managerLogout" role="button">
                        <button type="button" class="btn btn-primary btn-sm">退出登录</button>
                    </a>
                </li>

            </ul>
        </nav>
        <!-- /.navbar -->

        <!-- Main Sidebar Container -->
        <aside class="main-sidebar sidebar-dark-primary elevation-4">
            <!-- Brand Logo -->
            <a href="${pageContext.request.contextPath}/findAllBusLine" class="brand-link">
                <img src="${pageContext.request.contextPath}/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
                    style="opacity: .8">
                <span class="brand-text font-weight-light">公交后台管理</span>
            </a>

            <!-- Sidebar -->
            <div class="sidebar">

                <!-- SidebarSearch Form -->
                <div class="form-inline">
                    <div class="input-group" data-widget="sidebar-search">
                        <input class="form-control form-control-sidebar" type="search" placeholder="Search"
                            aria-label="Search">
                        <div class="input-group-append">
                            <button class="btn btn-sidebar">
                                <i class="fas fa-search fa-fw"></i>
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Sidebar Menu -->
                <nav class="mt-2">
                    <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                        data-accordion="false">
                        <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->

                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/findAllBusLine" class="nav-link">
                                <i class="nav-icon fas fa-edit"></i>
                                <p>
                                    线路管理
                                </p>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/findAllStation" class="nav-link">
                                <i class="nav-icon fas fa-edit"></i>
                                <p>
                                    站点管理
                                </p>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/findAllUser" class="nav-link">
                                <i class="nav-icon fas fa-edit"></i>
                                <p>
                                    用户管理
                                </p>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/findAllPost2" class="nav-link">
                                <i class="nav-icon fas fa-edit"></i>
                                <p>
                                    贴子管理
                                </p>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/findAllComment" class="nav-link">
                                <i class="nav-icon fas fa-edit"></i>
                                <p>
                                    评论管理
                                </p>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/findAllNotice2" class="nav-link">
                                <i class="nav-icon fas fa-edit"></i>
                                <p>
                                    通知管理
                                </p>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/findAllManager" class="nav-link">
                                <i class="nav-icon fas fa-edit"></i>
                                <p>
                                    管理员管理
                                </p>
                            </a>
                        </li>

                    </ul>
                </nav>
                <!-- /.sidebar-menu -->
            </div>
            <!-- /.sidebar -->
        </aside>

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <div class="container-fluid">
                    <div class="row mb-2">
                        <div class="col-sm-6">
                            <h1>用户管理</h1>
                        </div>
                    </div>
                </div><!-- /.container-fluid -->
            </section>

            <!-- Main content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">

                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">用户管理：删除用户</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>序号</th>
                                                <th>用户邮箱</th>
                                                <th>用户昵称</th>
                                                <th>用户发贴数量</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${userList}" var="keyword" varStatus="id">
                                            <tr>
                                                <td>${id.index+1}</td>
                                                <td>${keyword.email}</td>
                                                <td>${keyword.nickName}</td>
                                                <td>${keyword.postNum}</td>
                                                <td>
                                                    <a class="btn btn-danger btn-sm"
                                                       href="${pageContext.request.contextPath}/deleteUser?id=${keyword.id}">
                                                        <i class="fas fa-trash">
                                                        </i>
                                                        删除
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>序号</th>
                                                <th>用户邮箱</th>
                                                <th>用户昵称</th>
                                                <th>用户发贴数量</th>
                                                <th>操作</th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->
        <footer class="main-footer">
            <strong>Copyright &copy; 2020-2021 <a href="https://jack1024.link"> jack1024 </a>.</strong> All rights
            reserved.
        </footer>

        <!-- Control Sidebar -->
        <aside class="control-sidebar control-sidebar-dark">
            <!-- Control sidebar content goes here -->
        </aside>
        <!-- /.control-sidebar -->
    </div>
    <!-- ./wrapper -->


    <!-- Modals模态框 -->
  <section class="content">
    <div class="modal fade" id="modal_1">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">修改密码</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form action="#" method="post">
              <div class="input-group mb-3">
                <input id="password" type="password" class="form-control" placeholder="新密码">
                <div class="input-group-append">
                  <div class="input-group-text">
                    <span class="fas fa-lock"></span>
                  </div>
                </div>
              </div>
              <div class="input-group mb-3">
                <input id="rePassword" type="password" class="form-control" placeholder="重复密码">
                <div class="input-group-append">
                  <div class="input-group-text">
                    <span class="fas fa-lock"></span>
                  </div>
                </div>
              </div>
              <div class="row">
                <!-- /.col -->
                <div class="col-12">
                  <button id="modifyPassword" type="button" class="btn btn-primary btn-block">确认修改</button>
                </div>
                <!-- /.col -->
              </div>
            </form>
          </div>
        </div>
        <!-- /.modal-content -->
      </div>
      <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->

    </section>




    <script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
    <!-- Bootstrap 4 -->
    <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- DataTables  & Plugins -->
    <script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jszip/jszip.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/pdfmake/pdfmake.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/pdfmake/vfs_fonts.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.print.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/dist/js/adminlte.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="${pageContext.request.contextPath}/dist/js/demo.js"></script>
    <!-- Page specific script -->

    <!-- toast弹出框 -->
    <script src="${pageContext.request.contextPath}/dist/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/toast.min.js"></script>

    <script>
        $(function () {
            $("#example1").DataTable({
                "responsive": true,
                "lengthChange": false,
                "autoWidth": false,
                "buttons": ["copy", "csv", "excel", "pdf"]
            }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
            $('#example2').DataTable({
                "paging": true,
                "lengthChange": false,
                "searching": false,
                "ordering": true,
                "info": true,
                "autoWidth": false,
                "responsive": true,
            });
        });
        $(function () {
            if ('${msg}' !== "") {
                $.toast({
                    title: '提示',
                    subtitle: 'info',
                    content: '${msg}',
                    type: 'info',
                    pause_on_hover: false,
                    delay: 3000
                });
            }
        });
        //异步修改密码
        $("#modifyPassword").click(function (){
            $.ajax({
                data:{"password":$("#password").val(),"rePassword":$("#rePassword").val()},
                type: "POST",
                url : "${pageContext.request.contextPath}/managerModifyPasswd",
                success : function (msg){
                    if(msg==="error1"){
                        $.toast({
                            title: '提示',
                            subtitle: 'info',
                            content: '两次密码输入不一致',
                            type: 'info',
                            pause_on_hover: false,
                            delay: 3000
                        });
                    }
                    else{
                        $.toast({
                            title: '提示',
                            subtitle: 'info',
                            content: msg,
                            type: 'info',
                            pause_on_hover: false,
                            delay: 3000
                        });
                    }

                },
                error : function (msg){
                    $.toast({
                        title: '提示',
                        subtitle: 'info',
                        content: '系统错误，修改密码失败！',
                        type: 'info',
                        pause_on_hover: false,
                        delay: 3000
                    });
                }
            })
        })
    </script>
</body>

</html>