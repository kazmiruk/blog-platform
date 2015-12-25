<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../../layout/taglib.jsp" %>

<h1>${post.title}</h1>
<small><fmt:formatDate value="${post.publishedDate}" pattern="dd.MM.yyy HH:mm"/></small>

<div id="content">
    ${post.content}
</div>

<h3>Commentaries (${fn:length(post.commentaries)})</h3>

<table class="table table-bordered table-hover table-striped">
    <tbody>
        <c:forEach items="${post.commentaries}" var="commentary">
            <tr id="commentary_${commentary.id}">
                <td>
                    <strong><c:out value="${commentary.user.email}"/></strong>
                    <br/>
                    <i>
                        <fmt:formatDate value="${commentary.publishedDate}" pattern="dd.MM.yyy HH:mm"/>
                    </i>
                </td>
                <td><c:out value="${commentary.content}"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

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