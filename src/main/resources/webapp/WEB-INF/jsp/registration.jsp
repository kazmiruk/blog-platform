<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/taglib.jsp" %>

<form:form commandName="user" cssClass="form-horizontal registrationForm">
    <c:if test="${success eq true}">
        <div class="alert alert-success">Registration successful!</div>
    </c:if>

    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">Email:</label>
        <div class="col-sm-10">
            <form:input path="email" cssClass="form-control"/>
            <form:errors path="email"/>
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password:</label>
        <div class="col-sm-10">
            <form:password path="password" cssClass="form-control"/>
            <form:errors path="password"/>
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password again:</label>
        <div class="col-sm-10">
            <input type="password" name="password_again" id="password_again" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-2">
            <input type="submit" value="Save" class="btn btn-lg btn-primary"/>
        </div>
    </div>
</form:form>

<script>
    jQuery(document).ready(function() {
        jQuery(".registrationForm").validate({
            rules: {
                email: {
                    required: true,
                    email: true,
                    remote: {
                        url: "<spring:url value="/registration/available" />",
                        type: "get",
                        data: {
                            email: function() {
                                jQuery('#email').val();
                            }
                        }
                    }
                },
                password: {
                    required: true,
                    minlength: 5
                },
                password_again: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password"
                }
            },
            highlight: function(element) {
                jQuery(element).closest(".form-group").removeClass("has-success").addClass("has-error");
            },
            unhighlight: function(element) {
                jQuery(element).closest(".form-group").removeClass("has-error").addClass("has-success");
            },
            messages: {
                email: {
                    remote: "Such email already exists!"
                }
            }
        });
    });
</script>