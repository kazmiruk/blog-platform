<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ include file="taglib.jsp" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <meta charset="UTF-8">
    <title><tiles:getAsString name="title"/></title>
</head>
<body>
    <tilesx:useAttribute name="current"/>

    <div class="container">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<spring:url value="/"/>">Blog Platform</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="${current == 'index' ? 'active': ''}">
                            <a href="<spring:url value="/"/>">Home</a>
                        </li>
                        <security:authorize access="!isAuthenticated()">
                            <li class="${current == 'registration' ? 'active': ''}">
                                <a href="<spring:url value="/registration"/>">Sign up</a>
                            </li>
                            <li class="${current == 'login' ? 'active': ''}">
                                <a href="<spring:url value="/login"/>">Login</a>
                            </li>
                        </security:authorize>

                        <security:authorize access="hasRole('ROLE_ADMIN')">
                            <li class="${current == 'admin' ? 'active': ''}">
                                <a href="<spring:url value="/admin"/>">Admin</a>
                            </li>
                        </security:authorize>

                        <security:authorize access="isAuthenticated()">
                            <li class="${current == 'user-account' ? 'active': ''}">
                                <a href="<spring:url value="/user"/>">My account</a>
                            </li>
                            <li>
                                <form:form method="post" action="/logout" name="logoutForm"/>
                                <a onclick="document.logoutForm.submit(); return false;" href="#">Logout</a>
                            </li>
                        </security:authorize>
                    </ul>
                </div>
            </div>
        </nav>

        <tiles:insertAttribute name="body"/>

        <br/>
        <br/>

        <tiles:insertAttribute name="footer"/>
    </div>
</body>
</html>