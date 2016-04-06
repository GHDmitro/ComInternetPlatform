<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <%--<link rel="icon" href="../../favicon.ico">--%>

    <title>Off Canvas Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/pages/css/bootstrap.min.css"/> " rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="<c:url value="/pages/assets/css/ie10-viewport-bug-workaround.css"/> " rel="stylesheet">

    <!-- Custom pages.styles for this template -->
    <link href="<c:url value="/pages/styles/offcanvas.css"/> " rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><![endif]-->
    <script src="<c:url value="/pages/assets/js/ie8-responsive-file-warning.js "/> "></script>
    <script src="<c:url value="/pages/assets/js/ie-emulation-modes-warning.js"/> "></script>

    <!-- HTML5 shim and Respond.pages.jsmymy for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <!--<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>-->
    <!--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>-->
    <%--<![endif]-->--%>
</head>

<body>
<sec:authorize access="!isAuthenticated()">

    <p class="text-center"><a class="btn btn-lg btn-success" href="<c:url value="/login"/>" role="button">Войти</a></p>

</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <nav class="navbar navbar-fixed-top navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- <a class="active" href="#">Прайс лист</a> -->
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Прайс лист</a></li>
                    <li><a href="#">Добавить позицию</a></li>
                    <li><a href="#about">Заказы</a></li>
                    <li><a href="#ownChanges">Личные данные</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">

                    <%--<li><p class="text"><sec:authentication property="principal.username"/></p></li>--%>
                    <li><a href="<c:url value="/logout"/>">Вийти</a></li>
                    <!-- <li><a href="#">Войти</a></li> -->
                </ul>
            </div>
            <!-- /.nav-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    <!-- /.navbar -->

    <div class="container">

        <div class="row row-offcanvas row-offcanvas-right">

            <div class="col-xs-12 col-sm-9">
                <p class="pull-right visible-xs">
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
                </p>
                <!-- <div class="page-header"> -->
                <img class="page-header" style="margin-top : 0" src="<c:url value="/pages/images/header.jpg"/> "
                     height="250" width="1000">
                <!--  <h1>Hello, world!</h1>
                 <p>This is an example to show the potential of an offcanvas layout pattern in Bootstrap. Try some responsive-range viewport sizes to see it in action.</p> -->
                <!-- </div> -->
                <!--           <div class="row">
                            <div class="col-xs-6 col-lg-4">
                              <h2>Heading</h2>
                              <div class="col-md-5">
                              <img class="featurette-image img-responsive center-block" src="img/header.jpg.jpg" height="960" width="1280" alt="Изображение загружается">
                              </div>
                              <! <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                            </div> -->
            </div>
        </div>
    </div>


    <div class="container marketing">


        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 col-md-push-5">
                <ul>
                    <li><h3 class="featurette-heading">Название товара</h3></li>
                    <li><h4 class="text-muted">qwerty1234</h4></li>
                    <li><p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta
                        felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce
                        dapibus, tellus ac cursus commodo.</p></li>
                    <li><p><a class="btn btn-default" href="#" role="button">Изменить &raquo;</a></p></li>
                    <li><p><a class="btn btn-default" href="#" role="button">Удалить &raquo;</a></p></li>
                </ul>

            </div>
            <div class="col-md-5 col-md-pull-7">
                <img class="featurette-image img-responsive center-block" src="<c:url value="/pages/images/staff.jpg"/> "
                     alt="Изображение загружается">

            </div>
            <div class="panel panel-center">
                <div class="panel-body">
                    <form action="#" method="post">
                        <label for="comment">Коментарий</label>
                        <textarea class="form-control" id="comment" placeholder="Описание"></textarea>
                    </form>
                    <%--сюда подтягивается foreach--%>
                    <p>Все коменти</p>
                </div>
            </div>

        </div>

        <!--   <hr class="featurette-divider"> -->

        <!-- <div class="row featurette">
          <div class="col-md-7">
            <h2 class="featurette-heading">And lastly, this one. <span class="text-muted">Checkmate.</span></h2>
            <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
          </div>
          <div class="col-md-5">
            <img class="featurette-image img-responsive center-block" data-src="holder.pages.jsmymy/500x500/auto" alt="Generic placeholder image">
          </div>
        </div> -->

        <hr class="featurette-divider">

        <!-- /END THE FEATURETTES -->


        <!-- FOOTER -->
        <footer>
            <p class="pull-right"><a href="#">Back to top</a></p>
                <%--<p>&copy; 2015 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>--%>
        </footer>

    </div>
</sec:authorize>
<!-- </div> --><!-- /.container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.pages.jsmy "></script>
<script>window.jQuery || document.write('<script src="/pages/assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="<c:url value="/pages/js/bootstrap.min.js"/> "></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/pages/assets/js/ie10-viewport-bug-workaround.js"/> "></script>
<script src="<c:url value="/pages/jsmy/offcanvas.js"/> "></script>

</body>
</html>