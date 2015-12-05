<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../../layout/taglib.jsp" %>


<c:forEach items="${posts}" var="post">
    <h1>${post.title}</h1>
</c:forEach>