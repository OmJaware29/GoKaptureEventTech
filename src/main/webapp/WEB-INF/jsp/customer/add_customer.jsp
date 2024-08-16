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
<title>Add Customer</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<style  >
	.required::after {
     content:"*" ;
     color:red   
     }
     .error{
     color:red;
     font-size:9px
     }
	</style>
	<style>
.custom-red-btn {
    background-color: red; /* Override Bootstrap's success color */
    border-color: red; /* Ensure the border matches the background */
}
</style>
</head>
<body style="background-color: gray;color: white;">
	<div class="container ">
		<spring:url value="/save" var="addURL" />

		<form:form modelAttribute="customerDTO" method="post"
			action="${addURL}" cssClass="form">
			<form:hidden path="id" />
			<form:hidden path="ops" />

			<c:if test="${customerDTO.ops ne 'u'}">
				<h2>Add Customer</h2>
			</c:if>
			<c:if test="${customerDTO.ops eq 'u'}">
				<h2>Update Customer</h2>
			</c:if>








			<div class="form-group bg-dark text-white">
				<div class="row">
					<div class="col-md-6">
						<label class="required">First Name</label>
						<form:input path="firstName" pattern="[A-Za-z]+"
							Class="form-control bg-dark text-white" />
						<form:errors class="error" path="firstName"></form:errors>
					</div>
					<div class="col-md-6">
						<label>Last Name</label>
						<form:input path="lastName" pattern="[A-Za-z]+"
							cssClass="form-control" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<label class="required">Phone</label>
						<form:input type="text" path="phone" pattern="[7-9]{1}[0-9]{9}" maxlength="10"
							cssClass="form-control" />
						<p>Phone number should be of 10 digits only.</p>
						<form:errors class="error" path="phone"></form:errors>
					</div>
					<div class="col-md-6">
						<label class="required">Email</label>
						<form:input type="email" path="email" cssClass="form-control" />
						<form:errors class="error" path="email"></form:errors>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<label>Street</label>
						<form:input type="text" path="street" maxlength="45"
							cssClass="form-control" />
					</div>
					<div class="col-md-4">
						<label class="required">City</label>
						<form:input type="text" path="city" pattern="[A-Za-z]+"
							maxlength="45" cssClass="form-control" />
						<form:errors class="error" path="city"></form:errors>
					</div>
					<div class="col-md-4">
						<label class="required">State</label>
						<form:input type="text" path="state" pattern="[A-Za-z]+"
							maxlength="45" cssClass="form-control" />
						<form:errors class="error" path="state"></form:errors>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<label class="required">Address</label>
						<form:textarea path="address" maxlength="100"
							cssClass="form-control" />
						<form:errors class="error" path="address"></form:errors>
					</div>
				</div>
			</div>
			<c:if test="${customerDTO.ops ne 'u' }">
				<button type="submit" class="btn btn-success">Submit</button>
			</c:if>
			<c:if test="${customerDTO.ops eq 'u' }">
				<button type="submit" class="btn btn-success">Update</button>
			</c:if>
			<spring:url value="/list" var="addURL" />
			<a class="btn btn-success custom-red-btn" href="${addURL}">Cancel</a>
		</form:form>

	</div>
</body>
</html>