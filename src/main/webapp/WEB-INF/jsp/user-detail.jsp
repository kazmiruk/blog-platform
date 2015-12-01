<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/taglib.jsp" %>

<h1>${user.name}</h1>

<c:forEach items="${user.posts}" var="post">
    <h2>${post.title}</h2>

    <table class="table table-bordered table-hover table-striped">
        <thead>
            <tr>
                <th>Content</th>
                <th>Published date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${post.commentaries}" var="commentary">
                <tr>
                    <td>${commentary.content}</td>
                    <td>${commentary.publishedDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:forEach>