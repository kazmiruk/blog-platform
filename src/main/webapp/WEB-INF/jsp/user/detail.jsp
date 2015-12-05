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
        <c:forEach items="${user.commentaries}" var="commentary">
            <tr>
                <td>${commentary.post.title}</td>
                <td>${commentary.content}</td>
                <td>${commentary.publishedDate}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>