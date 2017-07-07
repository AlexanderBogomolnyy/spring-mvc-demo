<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:attribute name="title">Something wrong</jsp:attribute>
    <jsp:body>
        <!-- Page Content -->
        <div class="container">
            <c:url value="/index.html" var="home"/>
            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">404
                        <small>Page Not Found</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="${home}">Home</a>
                        </li>
                        <li class="active">404</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <c:url value="/about.html" var="about"/>
            <c:url value="/file.html" var="file"/>
            <c:url value="/jdbc.html" var="jdbc"/>
            <c:url value="/email.html" var="email"/>
            <c:url value="/rest.html" var="rest"/>
            <c:url value="/orm.html" var="orm"/>
            <c:url value="/runtimeException" var="exception"/>
            <c:url value="/jstl.html" var="jstl"/>
            <c:url value="/redirectExample" var="redirect"/>
            <c:url value="/scope.html" var="scope"/>
            <c:url value="/cookie.html" var="cookie"/>
            <c:url value="/security.html" var="security"/>

            <div class="row">
                <div class="col-lg-12">
                    <div class="jumbotron">
                        <h1><span class="error-404">404</span>
                        </h1>
                        <p>Ошибочка 404. Страница настроена в web.xml error-page. Можете перейти к:</p>
                        <ul>
                            <li>
                                <a href="${home}">Home</a>
                            </li>
                            <li>
                                <a href="${about}">About</a>
                            </li>
                            <li>
                                Tutorial
                                <ul>
                                    <li>
                                        <a href="${file}"><b>Lesson 1.</b> File download example</a>
                                    </li>
                                    <li>
                                        <a href="${jdbc}"><b>Lesson 2.</b> Spring JDBC example</a>
                                    </li>
                                    <li>
                                        <a href="${email}"><b>Lesson 3.</b> Send Email example</a>
                                    </li>
                                    <li>
                                        <a href="${orm}"><b>Lesson 4.</b> ORM example</a>
                                    </li>
                                    <li>
                                        <a href="${rest}"><b>Lesson 4.</b> REST example</a>
                                    </li>
                                    <li>
                                        <a href="${exception}"><b>Lesson 5.</b> Runtime Exception example</a>
                                    </li>
                                    <li>
                                        <a href="${jstl}"><b>Lesson 6.</b> JSTL in Spring</a>
                                    </li>
                                    <li>
                                        <a href="${redirect}"><b>Lesson 7.</b> Redirect. External resource</a>
                                    </li>
                                    <li>
                                        <a href="${scope}"><b>Lesson 8.</b> Bean scopes</a>
                                    </li>
                                    <li>
                                        <a href="${cookie}"><b>Lesson 9.</b> Work with Cookie example</a>
                                    </li>
                                    <li>
                                        <a href="${security}"><b>Lesson 10.</b> Spring Security</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>

            <hr>
        </div>
    </jsp:body>
</page:template>
