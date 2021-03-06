<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../../layout/taglib.jsp" %>

<div class="row">
    <c:forEach items="${postPage.content}" var="post">
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <c:if test="${post.previewImg != null}">
                    <a href="<spring:url value="/post/${post.id}" />">
                        <img src="${post.previewImg}"/>
                    </a>
                </c:if>
                <div class="caption">
                    <h3>
                        <a href="<spring:url value="/post/${post.id}" />">
                            <c:out value="${post.title}"/>
                        </a>
                        <a href="<spring:url value="/post/${post.id}" />#commentaries">
                            <span class="badge">${fn:length(post.commentaries)}</span>
                        </a>
                    </h3>
                    <small><span class="glyphicon glyphicon-time"></span>&nbsp;<fmt:formatDate value="${post.publishedDate}" pattern="dd.MM.yyy HH:mm"/></small>
                    <p>
                        <c:choose>
                            <c:when test="${fn:length(post.content) > 100}">
                                <c:out value="${fn:substring(post.content, 0, 100)}..."/>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${post.content}"/>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <c:if test="${post.tags.size() > 0}">
                        <p>
                            <c:forEach items="${post.tags}" var="tag">
                                <span class="label ${tag.className}">${tag.name}</span>
                            </c:forEach>
                        </p>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<c:set var="_page" value="${postPage}" scope="request"/>
<%@ include file="../../helpers/pager.jsp" %>
