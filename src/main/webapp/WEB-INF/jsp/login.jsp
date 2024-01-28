<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<style>
	.required::after {
     content:"*" ;
     color:red   
     }
     .error{
     color:red;
     font-size:9px
     }
	</style>
</head>
<body>
	<div class="container">
		<spring:url value="/login" var="loginURL" />

		<form:form modelAttribute="loginDTO" method="post"
			action="${loginURL}" cssClass="form">

				<h2>Login</h2>

			<div class="form-group">
				<div class="row">
					<div class="col-md-12">
						<label class="required">Username</label>
						<form:input path="username" pattern="[A-Za-z]+"
							cssClass="form-control" />
							
					</div>
					<div class="col-md-12">
						<label>Password</label>
						<form:input path="password"
							cssClass="form-control" />
					</div>
				</div>
				<c:if test="${error ne null}">
							<p class="error">${error}</p>
							</c:if>
			</div>
			<button type="submit" class="btn btn-success">Submit</button>
			
		</form:form>

	</div>
</body>
</html>