<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Пример загрузки файла
                        <small>pdf или excel</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">Пример загрузки файла</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <c:url value="/uploadFile" var="fileUploadControllerURL"/>
            <!-- Content Row -->
            <div class="row">

                <div class="col-lg-12">
                    <p>Пример загрузки файла с помощью Spring MVC </p>
                    <form action="${fileUploadControllerURL}" method="post"
                          enctype="multipart/form-data">
                        <div class="row justify-content-start">
                            <div class="col-lg-5 col-md-7 col-sm-9">
                                <h4>Input file for download here</h4>
                                <div class="input-group">
                                    <label class="input-group-btn">
                                        <span class="btn btn-primary">
                                            Browse&hellip; <input type="file" name="file" style="display: none;">
                                        </span>
                                    </label>
                                    <input type="text" class="form-control" readonly>
                                </div>
                                <span class="help-block">
                                    Try selecting file and submit it
                                </span>
                            </div>
                        </div>
                        <div class="row justify-content-start">
                            <div class="col-lg-2 col-md-3 col-sm-4">
                                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Submit file">
                            </div>
                        </div>
                    </form>

                    <br/>

                    <c:url value="/excel" var="excelController"/>
                    <c:url value="/pdf" var="pdfController"/>
                    <a href="${excelController}">Excel</a>
                    <br/>
                    <a href="${pdfController}">PDF</a>

                </div>

            </div>
            <!-- /.row -->
            <div class="row">
                <br/>
                    <%--Объект из примера scope.jstl--%>
                <c:if test="${not empty sessionScope.sessionObject}">
                    <p>Объект в области видимости session:</p>
                    <b>${sessionScope.sessionObject}</b>
                </c:if>
            </div>
            <hr>

        </div>
        <!-- /.container -->
    </jsp:body>
</page:template>
