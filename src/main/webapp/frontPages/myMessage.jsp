<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>智能公交线路站点查询系统</title>

    <link href="https://fonts.googleapis.com/css2?family=Cabin:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800&display=swap" rel="stylesheet">

    <!-- Template CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style-starter.css">

    <!-- toast弹出框 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/toast.min.css">
</head>

<body>
    <!-- header -->
    <header class="w3l-header">
        <!--/nav-->
        <nav class="navbar navbar-expand-lg navbar-light fill px-lg-0 py-0 px-3">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/frontPages/main.jsp">
                    <span class="fa fa-pencil-square-o"></span>智能公交查询系统</a>
                <!-- if logo is image enable this   
						<a class="navbar-brand" href="#main.html">
							<img src="image-path" alt="Your logo" title="Your logo" style="height:35px;" />
						</a> -->
                <button class="navbar-toggler collapsed" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <!-- <span class="navbar-toggler-icon"></span> -->
                    <span class="fa icon-expand fa-bars"></span>
                    <span class="fa icon-close fa-times"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/frontPages/main.jsp">首页</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/findAllPost">论坛</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/findAllNotice">通知</a>
                        </li>
                    </ul>

                    <!-- author -->
                    <div id="afterLogin" style="display: none;">
                        <div class="header-author d-flex ml-lg-4 pl-2 mt-lg-0 mt-3">
                            <a class="img-circle img-circle-sm" href="#">
                                <img src="${pageContext.request.contextPath}/assets/images/head.jpeg" class="img-fluid"
                                    alt="...">
                            </a>
                            <div class="align-self ml-3">
                                <h5>用户昵称</h5>
                                <a href="${pageContext.request.contextPath}/findPostByUserId">我的贴子</a>
                                <a href="#" data-toggle="modal" data-target="#exampleModal">发布贴子</a>
                                <a href="${pageContext.request.contextPath}/findMessageByUserId">我的消息</a>
                                <a href="${pageContext.request.contextPath}/userLogout">退出登录</a>
                            </div>
                        </div>
                    </div>
                    <div id="beforeLogin">
                        <div class="header-author d-flex ml-lg-4 pl-2 mt-lg-0 mt-3">
                            <div class=" align-self ml-3">
                                <a href="${pageContext.request.contextPath}/frontPages/login.jsp">
                                    <h5>前往登录</h5>
                                </a>
                                <a href="${pageContext.request.contextPath}/frontPages/register.jsp">
                                    <h5>前往注册</h5>
                                </a>
                            </div>
                        </div>
                    </div>
                    <!-- // author-->

                </div>

                <!-- toggle switch for light and dark theme -->
                <div class="mobile-position">
                    <nav class="navigation">
                        <div class="theme-switch-wrapper">
                            <label class="theme-switch" for="checkbox">
                                <input type="checkbox" id="checkbox">
                                <div class="mode-container">
                                    <i class="gg-sun"></i>
                                    <i class="gg-moon"></i>
                                </div>
                            </label>
                        </div>
                    </nav>
                </div>
                <!-- //toggle switch for light and dark theme -->
            </div>
        </nav>
        <!--//nav-->
    </header>
    <!-- //header -->

    <nav id="breadcrumbs" class="breadcrumbs">
        <div class="container page-wrapper">
            <form action="${pageContext.request.contextPath}/findMessageByUserId" method="GET">
                <div class="input-group mb-3 col-lg-5">
                    <input type="text" name="searchString" class="form-control" placeholder="搜索通知" aria-label="Recipient's username"
                           aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon2">搜索</button>
                    </div>
                </div>
            </form>
        </div>
    </nav>
    <div class="w3l-searchblock w3l-homeblock1 py-5">
        <div class="container py-lg-4 py-md-3">
            <!-- block -->
            <div class="row">
                <div class="col-lg-8 most-recent">
                    <h3 class="section-title-left">我的消息</h3>
                    <div class="row">
                        <c:if test="${postPageInfo.list.size() == 0}">
                            您还没有消息~~~
                        </c:if>
                        <div class="col-md-12 item">
                            <c:forEach items="${messagePageInfo.list}" var="keyword" varStatus="id">
                                <div class="card">
                                    <div class="card-body p-0 blog-details">
                                        <span class="blog-desc">${keyword.content}</span>
                                        <ul class="blog-meta">
                                            <li class="meta-item blog-lesson">
                                                <span class="meta-value"> ${keyword.time} </span>
                                            </li>
                                            <li class="meta-item blog-lesson">
                                                <a href="${pageContext.request.contextPath}/deleteMessage?id=${keyword.id}"><button
                                                    style="margin-left: 350px;" type="button"
                                                    class="btn btn-outline-danger">删除</button></a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                    <!-- pagination -->
                    <div class="pagination-wrapper mt-5">
                        <ul class="page-pagination">
                            <c:forEach var="i" begin="1" end="${messagePageInfo.pages}">
                                <c:if test="${messagePageInfo.pageNum == i }">
                                    <li><a class="page-numbers"
                                           href="${pageContext.request.contextPath}/findMessageByUserId?page=${i}&searchString=${searchString}"><span
                                            aria-current="page" class="page-numbers current">${i}</span></a></li>
                                </c:if>
                                <c:if test="${messagePageInfo.pageNum != i }">
                                    <li><a class="page-numbers"
                                           href="${pageContext.request.contextPath}/findMessageByUserId?page=${i}&searchString = ${searchString}">${i}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                            <li><a class="next"
                                   href="${pageContext.request.contextPath}/findMessageByUserId?page=${messagePageInfo.pageNum+1}&pages=${messagePageInfo.pages}&searchString=${searchString}"><span
                                    class="fa fa-angle-right"></span></a></li>
                        </ul>
                    </div>
                    <!-- //pagination -->
                </div>

                <div class="col-lg-4 trending mt-lg-0 mt-5 mb-lg-5">
                    <div class="pos-sticky">
                        <h3 class="section-title-left">我最近的贴子 </h3>
                        <c:forEach items="${postList}" var="keyword" varStatus="id">
                            <div class="grids5-info">
                                <h4>${id.index+1}.</h4>
                                <div class="blog-info">
                                    <a href="${pageContext.request.contextPath}/findPostById?id=${keyword.id}"
                                       class="blog-desc1"> ${keyword.title} </a>
                                    <ul class="blog-meta">
                                        <li class="meta-item blog-lesson">
                                            <span class="meta-value"> ${keyword.time} </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <!-- //block-->


        </div>
    </div>

    <!-- footer -->
    <footer class="w3l-footer-16">
        <div class="footer-content py-lg-5 py-4 text-center">
            <div class="container">
                <div class="copy-right">
                    <h6>Copyright &copy; 2020.Jack1024 All rights reserved.<a target="_blank"
                            href="http://jack1024.link">我的博客</a></h6>
                </div>
                <ul class="author-icons mt-4">
                    <li><a class="facebook" href="#"><span class="fa fa-facebook" aria-hidden="true"></span></a>
                    </li>
                    <li><a class="twitter" href="#"><span class="fa fa-twitter" aria-hidden="true"></span></a></li>
                    <li><a class="google" href="#"><span class="fa fa-google-plus" aria-hidden="true"></span></a>
                    </li>
                    <li><a class="linkedin" href="#"><span class="fa fa-linkedin" aria-hidden="true"></span></a></li>
                    <li><a class="github" href="#"><span class="fa fa-github" aria-hidden="true"></span></a></li>
                    <li><a class="dribbble" href="#"><span class="fa fa-dribbble" aria-hidden="true"></span></a></li>
                </ul>
                <button onclick="topFunction()" id="movetop" title="Go to top">
                    <span class="fa fa-angle-up"></span>
                </button>
            </div>
        </div>

        <!-- move top -->
        <script>
            // When the user scrolls down 20px from the top of the document, show the button
            window.onscroll = function () {
                scrollFunction()
            };

            function scrollFunction() {
                if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                    document.getElementById("movetop").style.display = "block";
                } else {
                    document.getElementById("movetop").style.display = "none";
                }
            }

            // When the user clicks on the button, scroll to the top of the document
            function topFunction() {
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
            }
        </script>
        <!-- //move top -->
    </footer>
    <!-- //footer -->


    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">发布贴子</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="${pageContext.request.contextPath}/addPost" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="title">贴子标题</label>
                            <input type="text" class="form-control" id="title" name="title"
                                aria-describedby="emailHelp" required>
                            <small id="emailHelp" class="form-text text-muted">新颖的标题能让你的贴子获得更多的关注！</small>
                        </div>
                        <label for="doPost">贴子内容</label>
                        <textarea id="doPost" name="content" placeholder="从这里开始你的一篇贴子吧！"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="reset" class="btn btn-secondary">重置</button>
                        <button type="submit" class="btn btn-primary">发布</button>
                    </div>
                </form>
            </div>
        </div>
    </div>



    <!-- Template JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/js/theme-change.js"></script>

    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.3.1.min.js"></script>

    <!-- toast弹出框 -->
    <script src="${pageContext.request.contextPath}/dist/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/toast.min.js"></script>

    <!-- disable body scroll which navbar is in active -->
    <script>
        $(function () {
            $('.navbar-toggler').click(function () {
                $('body').toggleClass('noscroll');
            });

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

            //登录用户信息显示
            var userId = '<%=session.getAttribute("userId")%>';
            if (userId==="null") {
                $("#beforeLogin").css("display", "");
                $("#afterLogin").css("display", "none");
            } else {
                $("#beforeLogin").css("display", "none");
                $("#afterLogin").css("display", "");
            }
        });
    </script>
    <!-- disable body scroll which navbar is in active -->

    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src='${pageContext.request.contextPath}/assets/js/tinymce/tinymce.min.js'></script>
    <script>
        tinymce.init({
            selector: '#doPost',
            language: "zh_CN",
            height: 475,
            resize: false
        });
    </script>

</body>

</html>