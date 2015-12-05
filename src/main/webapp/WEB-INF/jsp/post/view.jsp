<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../../layout/taglib.jsp" %>

<h1>${post.title}</h1>

<div id="content">${post.content}</div>

<c:forEach items="${post.commentaries}" var="commentary">
    <div id="commentary_${commentary.id}"><c:out value="${commentary.content}"/></div>
</c:forEach>

<security:authorize access="isAuthenticated()">
    <form:form action="/post/${post.id}/commentary/" commandName="commentary" cssClass="form-horizontal">
        <div class="form-group">
            <label for="content" class="col-sm-2 control-label">Content:</label>
            <div class="col-sm-10">
                <form:textarea path="content"  cssClass="form-control"/>
                <form:errors path="content"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2">
                <input type="submit" value="Send" class="btn btn-lg btn-primary"/>
            </div>
        </div>
    </form:form>
</security:authorize>