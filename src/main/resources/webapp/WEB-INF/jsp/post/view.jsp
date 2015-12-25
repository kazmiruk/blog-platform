<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../../layout/taglib.jsp" %>

<h1>${post.title}</h1>
<small><span class="glyphicon glyphicon-time"></span>&nbsp;<fmt:formatDate value="${post.publishedDate}" pattern="dd.MM.yyy HH:mm"/></small>

<div id="content">
    ${post.content}
</div>

<br/>
<br/>
<h4 id="commentaries">Commentaries <span class="badge">${fn:length(post.commentaries)}</span></h4>

<c:forEach items="${post.commentaries}" var="commentary">
    <div class="panel panel-info" id="commentary_${commentary.id}">
        <div class="panel-heading">
            <strong><c:out value="${commentary.user.email}"/></strong>
            <small class="pull-right">
                <span class="glyphicon glyphicon-time"></span>
                &nbsp;<fmt:formatDate value="${commentary.publishedDate}" pattern="dd.MM.yyy HH:mm"/>
            </small>
        </div>
        <div class="panel-body">
            <c:out value="${commentary.content}"/>
        </div>
    </div>
</c:forEach>

<form:form action="/post/${post.id}/commentary/" commandName="commentary" cssClass="form-horizontal commentaryForm">
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

<script>
    jQuery(document).ready(function() {
        jQuery(".commentaryForm").validate({
            rules: {
                content: {
                    required: true
                }
            },
            highlight: function(element) {
                jQuery(element).closest(".form-group").removeClass("has-success").addClass("has-error");
            },
            unhighlight: function(element) {
                jQuery(element).closest(".form-group").removeClass("has-error").addClass("has-success");
            }
        });
    });
</script>