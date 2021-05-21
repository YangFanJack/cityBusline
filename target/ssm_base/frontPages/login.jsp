<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>登录</title>

    <link href="https://fonts.googleapis.com/css2?family=Cabin:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800&display=swap" rel="stylesheet">

    <!-- Template CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style-starter.css">

    <!-- toast弹出框 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/toast.min.css">
</head>

<body>

    <header class="w3l-header">

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
                </div>

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

            </div>
        </nav>

    </header>


    <nav id="breadcrumbs" class="breadcrumbs">
        <div class="container page-wrapper">

        </div>
    </nav>

    <section class="w3l-contact-2 py-5">
        <div class="container py-lg-5 py-md-4">
            <h3 class="section-title-left">用户登录 </h3>
            <div class="contact-grids d-grid">
                <div class="contact-left">
                    <h3 class="mb-3">用户登录注意事项</h3>
                    <p>Everything start with a Hello! We’re here to answer any questions you may have and create an
                        effective
                        solution for your instructional needs.</p>

                    <p>We have a dedicated support center for all of your support needs. We usually get back to you
                        within 12-24
                        hours.</p>

                </div>
                <div class="contact-right">
                    <form action="${pageContext.request.contextPath}/userLogin" method="post" class="signin-form">
                        <div class="input-grids">
                            <input type="email" name="email" id="email" placeholder="邮箱" class="contact-input"
                                required="" />
                            <input type="password" name="password" id="password" placeholder="密码"
                                class="contact-input" required="" />
                        </div>
                        <button type="submit" class="btn btn-style btn-outline">登录</button>
                        <a href="${pageContext.request.contextPath}/frontPages/changePasswd.jsp"
                            style="margin-left: 100px;">忘记密码</a>
                    </form>
                </div>
            </div>
        </div>
    </section>

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

</body>

</html>