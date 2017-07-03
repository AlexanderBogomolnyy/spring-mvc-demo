<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <c:url value="/index.html" var="home"/>
        <c:url value="/rest/users" var="users" />
        <c:url value="/rest/users/3" var="userById" />

        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">REST в Spring
                        <small>with Hibernate 5</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="${home}">Home</a>
                        </li>
                        <li class="active">REST page</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Content Row -->
            <div class="row">
                <!-- Sidebar Column -->
                <div class="col-md-3">
                    <div class="list-group">
                        <a href="${home}" class="list-group-item">Home</a>
                        <a href="${users}" class="list-group-item">Get All Users</a>
                        <a href="${userById}" class="list-group-item">Find User by ID</a>
                    </div>
                </div>
                <!-- Content Column -->
                <div class="col-md-9">
                </div>
            </div>
            <!-- /.row -->

            <hr>

        </div>
        <!-- /.container -->

    </jsp:body>
</page:template>