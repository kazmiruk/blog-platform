<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../../layout/taglib.jsp" %>

<c:forEach items="${postPage.content}" var="post">
    <h2>
        <a href="<spring:url value="/post/${post.id}" />">
            <c:out value="${post.title}"/>
        </a>
    </h2>
    <p>
        <c:choose>
            <c:when test="${fn:length(post.title) > 100}">
                <c:out value="${fn:substring(post.title, 0, 100)}..."/>
            </c:when>
            <c:otherwise>
                <c:out value="${post.title}"/>
            </c:otherwise>
        </c:choose>
    </p>
    <table class="table table-bordered table-hover table-striped">
        <tbody>
            <tr>
                <td>
                    <fmt:formatDate value="${post.publishedDate}" pattern="dd.MM.yyy HH:mm"/>
                </td>
                <td>${fn:length(post.commentaries)} ${fn:length(post.commentaries) == 1? "commentary": "commentaries"}</td>
            </tr>
        </tbody>
    </table>
</c:forEach>

<c:set var="_page" value="${postPage}" scope="request"/>
<%@ include file="../../helpers/pager.jsp" %>
