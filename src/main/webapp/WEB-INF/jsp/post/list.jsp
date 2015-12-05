<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../../layout/taglib.jsp" %>


<c:forEach items="${posts}" var="post">
    <a href="<spring:url value="/post/${post.id}" />">${post.title}</a>
</c:forEach>