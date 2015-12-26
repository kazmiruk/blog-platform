<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../../../layout/taglib.jsp" %>

<form:form commandName="post" cssClass="form-horizontal postForm">
    <div class="form-group">
        <label for="title" class="col-sm-2 control-label">Title:</label>
        <div class="col-sm-10">
            <form:input path="title" cssClass="form-control"/>
            <form:errors path="title"/>
        </div>
    </div>
    <div class="form-group">
        <label for="previewImg" class="col-sm-2 control-label">Preview image:</label>
        <div class="col-sm-10">
            <form:input path="previewImg" cssClass="form-control"/>
            <form:errors path="previewImg"/>
        </div>
    </div>
    <div class="form-group">
        <label for="content" class="col-sm-2 control-label">Content:</label>
        <div class="col-sm-10">
            <form:textarea path="content"  cssClass="form-control"/>
            <form:errors path="content"/>
        </div>
    </div>

    <form:checkboxes path="tags" items="${tags}" itemValue="id" itemLabel="name"/>

    <div class="form-group">
        <div class="col-sm-2">
            <input type="submit" value="Save" class="btn btn-lg btn-primary"/>
        </div>
    </div>
</form:form>

<script>
    jQuery(document).ready(function() {
        jQuery(".postForm").validate({
            rules: {
                title: {
                    required: true
                },
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