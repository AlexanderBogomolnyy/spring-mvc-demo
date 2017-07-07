<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Login Page</title>
    <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>
    <spring:url value="/resources/css/signin.css" var="signin"/>
    <link href="${bootstrap}" rel="stylesheet"/>
    <link href="${signin}" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-offset-4 col-lg-4 col-lg-offset-4 col-md-offset-3 col-md-6 col-md-offset-3 col-sm-offset-2 col-sm-8 col-sm-offset-2 form-container-border">
            <form name="form" action="j_spring_security_check" method="post" class="form-signin">
                <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUser"/>
                <font size="2" color="red">
                    <c:if test="${not isUser}">
                        <c:if test="${empty param.error}">
                            <div style="text-align: center">Вы не вошли</div>
                        </c:if>
                    </c:if>
                </font>
                <font size="2" color="green">
                    <c:if test="${isUser}">
                        <div style="text-align: center">Вы вошли как:
                            <security:authentication property="principal.username"/> с ролью:
                            <b><security:authentication property="principal.authorities"/></b></div>
                    </c:if>
                </font>
                <c:if test="${not empty param.error}">
                    <font size="2" color="red">
                        <div style="text-align: center"><b>Неправильный логин или пароль</b></div>
                    </font>
                </c:if>
                <c:if test="${!isUser}">
                    <h2 class="form-signin-heading">Пожалуйста войдите</h2>

                    <label for="inputEmail" class="sr-only"><spring:message code="email" text="Email"/></label>
                    <input id="inputEmail" class="form-control" name="j_username" value="admin@gmail.com" required
                           autofocus/>

                    <label for="inputPassword" class="sr-only"><spring:message code="pass" text="Password"/></label>
                    <input type="password" id="inputPassword" class="form-control" name="j_password" value="12345"
                           required/>

                    <div class="checkbox">
                        <label>
                            <input type="checkbox" id="rememberme" name="_spring_security_remember_me"/>Запомнить меня
                        </label>
                    </div>
                    <input type="submit" value="Войти" class="btn btn-lg btn-primary btn-block">
                </c:if>
                <br/>
                <a href="javascript:history.back()">Назад</a>

                <br/><br/>
                <p>Доступные роли:</p>

                <b>ROLE_SUPER_USER</b><br/>
                Login:<span style="color: royalblue">superuser@outlook.com</span> Password: <span
                    style="color: royalblue">12345</span> <br/>
                <b>ROLE_ADMIN</b> <br/>
                Login:<span style="color: royalblue">admin@gmail.com</span> Password:
                <span style="color: royalblue">12345</span>
                <br/>
                <b>ROLE_USER</b> <br/>
                Login: <span style="color: royalblue">roleuser@outlook.com</span> Password: <span
                    style="color: royalblue">12345</span>
            </form>
        </div>
    </div>
</div>

<!-- jQuery -->
<spring:url value="/resources/js/jquery.js" var="jqueryjs"/>
<script src="${jqueryjs}"></script>

<!-- Bootstrap Core JavaScript -->
<spring:url value="/resources/js/bootstrap.min.js" var="js"/>
<script src="${js}"></script>

</body>

</html>
