<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../../layout/taglib.jsp" %>

<h1>${user.email}</h1>

<table class="table table-bordered table-hover table-striped">
    <thead>
        <tr>
            <th>Post title</th>
            <th>Content</th>
            <th>Published date</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${commentaryPage.content}" var="commentary">
            <tr>
                <td>
                    <a href="<spring:url value="/post/${commentary.post.id}"/>">
                        <c:out value="${commentary.post.title}"/>
                    </a>
                </td>
                <td><c:out value="${commentary.content}"/></td>
                <td>
                    <fmt:formatDate value="${commentary.publishedDate}" pattern="dd.MM.yyy HH:mm"/>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<c:set var="_page" value="${commentaryPage}" scope="request"/>
<%@ include file="../../helpers/pager.jsp" %>
