<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 30.03.16
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>customer</title>
</head>
<body>
<div class="container">

  <div class="row" align="center">

    <%--<sec:authorize access="!isAuthenticated()">--%>
      <%--<p><a class="btn btn-lg btn-success" href="<c:url value="/signin" />" role="button">Войти</a></p>--%>
    <%--</sec:authorize>--%>

    <form role="form" class="form-horizontal" action="/addPricePosition" enctype="multipart/form-data" method="post">
      <input class="login" type="hidden" name="login"  value="${login}">
      <div class="price"><input class="price_position" type="file" name="photo" placeholder="Photo"></div>
      <div class="price"><input class="price_position" type="text" name="name" placeholder="Name"></div>
      <div class="price"><input class="price_position" type="text" name="codeOfModel" placeholder="Code of model"></div>
      <div class="price"><input class="price_position" type="text" name="description" placeholder="Description"></div>
      <div class="price"><input class="price_position" type="submit" name="Add"></div>
    </form>


    <%--<c:forEach items="${accounts}" var="account">--%>
    <%--<option value="${account.login}">Name: ${account.login} , User age: ${account.pass}</option>--%>
    <%--</c:forEach>--%>
  </div>


</div>

</body>
</html>
