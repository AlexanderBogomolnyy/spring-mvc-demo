<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="title" fragment="true" %>

<head>

    <title><jsp:invoke fragment="title"/></title>

    <!-- Bootstrap Core CSS -->
    <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet"/>

    <!-- Custom CSS -->
    <spring:url value="/resources/css/modern-business.css" var="startertemplate"/>
    <link href="${startertemplate}" rel="stylesheet"/>

    <!-- Custom Fonts -->
    <spring:url value="/resources/font-awesome/css/font-awesome.min.css" var="fontawesome"/>
    <link href="${fontawesome}" rel="stylesheet"/>

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <c:url value="/index.html" var="home"/>
            <a class="navbar-brand" href="${home}">Start Bootstrap</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access= "hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var= "isUser"/>

                <c:if test= "${not isUser}">
                    <li style= "padding-top: 15px; padding-bottom: 15px; color: red">
                        <c:if test= "${empty param.error}">
                            Вы не вошли в приложение
                        </c:if>
                    </li>
                    <li> <a style= "color: Green;" href= "<c:url value= "/login.html"/>">Login</a> </li>
                </c:if>

                <c:if test= "${isUser}">
                    <li style= "padding-top: 15px; padding-bottom: 15px; color: green">
                        Вы вошли как:
                        <security:authentication property= "principal.username"/> с ролью:
                        <b><security:authentication property= "principal.authorities"/></b>

                    </li>
                    <li> <a style= "color: red;" href= "<c:url value= "/j_spring_security_logout"/>">Logout</a> </li>
                </c:if>

                <c:url value="/about.html" var="about"/>
                <li><a href="${about}">About</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tutorial<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:url value="/file.html" var="file"/>
                        <li>
                            <a href="${file}"><b>Lesson 1.</b> File download example</a>
                        </li>
                        <c:url value="/jdbc.html" var="jdbc"/>
                        <li>
                            <a href="${jdbc}"><b>Lesson 2.</b> Spring JDBC example</a>
                        </li>
                        <c:url value="/email.html" var="email"/>
                        <li>
                            <a href="${email}"><b>Lesson 3.</b> Send Email example</a>
                        </li>
                        <c:url value="/orm.html" var="orm"/>
                        <li>
                            <a href="${orm}"><b>Lesson 4.</b> ORM example</a>
                        </li>
                        <c:url value="/rest.html" var="rest"/>
                        <li>
                            <a href="${rest}"><b>Lesson 4.</b> REST example</a>
                        </li>
                        <c:url value="/runtimeException" var="exception"/>
                        <li>
                            <a href="${exception}"><b>Lesson 5.</b> Runtime Exception example</a>
                        </li>
                        <c:url value="/jstlReturnUsers" var="jstl"/>
                        <li>
                            <a href="${jstl}"><b>Lesson 6.</b> JSTL in Spring</a>
                        </li>
                        <c:url value="/redirectExample" var="redirect"/>
                        <li>
                            <a href="${redirect}"><b>Lesson 7.</b> Redirect. External resource</a>
                        </li>
                        <c:url value="/scope.html" var="scope"/>
                        <li>
                            <a href="${scope}"><b>Lesson 8.</b> Bean scopes</a>
                        </li>
                        <c:url value="/cookie.html" var="cookies"/>
                        <li>
                            <a href="${cookies}"><b>Lesson 9.</b> Work with Cookie example</a>
                        </li>
                        <c:url value="/security.html" var="security" />
                        <li>
                            <a href="${security}"><b>Lesson 10.</b> Spring Security</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<jsp:doBody/>

<div class="container">
    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright © Your Website 2014</p>
            </div>
        </div>
    </footer>
</div>

<!-- jQuery -->
<spring:url value="/resources/js/jquery.js" var="jqueryjs"/>
<script src="${jqueryjs}"></script>

<!-- Bootstrap Core JavaScript -->
<spring:url value="/resources/js/bootstrap.min.js" var="js"/>
<script src="${js}"></script>

<!-- Custom JavaScript-->
<spring:url value="/resources/js/main.js" var="main"/>
<script src="${main}"></script>

</body>

</html>