<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <style>
        .upper-section {
            margin-bottom: 30px; /* Add space below the username field */
        }
        .lower-section {
            margin-top: 20px; /* Add space above the password field */
        }
       .register-button {
            position: fixed;
            bottom: 30px;
            left: 30px;
        }
    </style>
</head>
<body style="background-color: black; color:white; position: relative;">
    <center>
        <div class="container bg-dark text-white" style="padding: 30px;">
            <spring:url value="/login" var="loginURL" />

            <form:form modelAttribute="loginDTO" method="post"
                       action="${loginURL}" cssClass="form">

                <h3 style="color: white;">Login</h3>

                <div class="form-group upper-section">
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4">
                            <label class="required">USER NAME</label>
                            <form:input path="username" pattern="[A-Za-z]+" type="text"
                                        cssClass="form-control" />
                        </div>
                    </div>
                </div>

                <div class="form-group lower-section">
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4">
                            <label>PASSWORD</label>
                            <form:input path="password" type="password"
                                        cssClass="form-control" />
                        </div>
                    </div>
                </div>

                <c:if test="${error ne null}">
                    <p class="error text-danger">${error}</p>
                </c:if>

                <button type="submit" class="btn btn-success">Submit</button>
            </form:form>
        </div>
    </center>
	<div style="align-content: center;">
    <a href="/loadLogin" class="btn btn-primary register-button" >Register User</a>
	</div>
</body>
</html>
