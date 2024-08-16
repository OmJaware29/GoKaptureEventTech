<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<title >Customer-Home</title>
<style>
table, th, td {
	border: 1px solid;
}

.success {
	background-color: #248C25;
	color: white;
	padding: 1em 1.5em;
	text-decoration: none;
	border-radius: 10px;
	padding: 12px;
	width: 100px;
	height: 100px;
}

.info {
	background-color: #BEC348;
	color: white;
	padding: 1em 1.5em;
	text-decoration: none;
	border-radius: 10px;
	padding: 6px;
	width: 50px;
	height: 50px;
}

.danger {
	background-color: #DD3825;
	color: white;
	padding: 0.5em 0.5em;
	text-decoration: none;
	border-radius: 8px;
	padding: 8px;
	width: 30px;
	height: 30px;
}

#ul-links {
	width: 150px;
}

#ul-links li {
	width: 50px;
	display: inline-block;
}
</style>
</head>

<body style="background-color: gray">

	<div></div>
	<hr>
	<div style="width: 1200px; margin: 0 auto;">
		<spring:url value="/save" var="addURL" />
		<a href="${addURL}" style="color: orange;font-size: 20px">Add <i class="material-icons"
			style="font-size: 30px; border-width: 3px; border-style: solid; border-color: black;color: white;">add</i></i></a>
		<spring:url value="/sync" var="syncURL" />
		<a class="info" href="${syncURL}"> Sync Customers</a> <select
			id="searchBy" name="search">
			<c:forEach items="${searchBy}" var="s">
				<option value="${s.value}">${s.key}</option>
			</c:forEach>
		</select> <input type="text" name="searchVal" id="searchVal">
		<button type="button" id="search">
			<i class="material-icons" style="font-size: 15px; color: black;">search</i>
		</button>
		<button type="button" id="reset">
			<i class="material-icons" style="font-size: 15px; color: black;">autorenew</i>
		</button>
		<hr>

		<c:choose>
			<c:when test="${fn:length(customerDTOs) > 0}">
				<table class="">
					<thead style="background-color:black;;color: white">
						<th
							style="width: 200px; margin: 0 auto; text-align: center;background-color:black; color: white">First
							Name</th>
						<th style="width: 200px; margin: 0 auto; text-align: center">Last
							Name</th>
						<th style="width: 200px; margin: 0 auto; text-align: center;">Address</th>
						<th style="width: 200px; margin: 0 auto; text-align: center">City</th>
						<th style="width: 200px; margin: 0 auto; text-align: center">State</th>
						<th style="width: 200px; margin: 0 auto; text-align: center">Email</th>
						<th style="width: 200px; margin: 0 auto; text-align: center">Phone</th>
						<th style="width: 200px; margin: 0 auto; text-align: center">Action</th>
					</thead>


					<tbody style="background-color: black; color: white">
						<c:forEach items="${customerDTOs}" var="customerDTO">
							<tr>
								<td style="width: 200px; margin: 0 auto; text-align: center">${customerDTO.firstName}</td>
								<td style="width: 200px; margin: 0 auto; text-align: center">${customerDTO.lastName}</td>
								<td style="width: 200px; margin: 0 auto; text-align: center">${customerDTO.address}</td>
								<td style="width: 200px; margin: 0 auto; text-align: center">${customerDTO.city}</td>
								<td style="width: 200px; margin: 0 auto; text-align: center">${customerDTO.state}</td>
								<td style="width: 200px; margin: 0 auto; text-align: center">${customerDTO.email}</td>
								<td style="width: 200px; margin: 0 auto; text-align: center">${customerDTO.phone}</td>
								<td style="width: 200px; margin: 0 auto; text-align: center">
									<ul id="ul-links">
										<li><spring:url value="/update/${customerDTO.id}"
												var="editURL" /> <a href="${editURL}"><i
												class="material-icons"
												style="font-size: 24px; color: #BEC348">border_color</i></a></li>
										<li><spring:url value="/delete/${customerDTO.id}"
												var="deleteURL" /> <a href="${deleteURL}"><i
												class="material-icons"
												style="font-size: 24px; color: #DD3825">delete_forever</i></a></li>
									</ul>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>

			<c:otherwise>
				<h1 style="color: white; text-align: center;">No Data Found!!</h1>
			</c:otherwise>
		</c:choose>
	</div>
</body>
<script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('#search').click(
						function() {

							var searchBy = $('#searchBy').val();
							console.log(searchBy);

							var searchVal = $('#searchVal').val();
							console.log(searchVal);

							var searchURL = "/list?searchBy=" + searchBy
									+ "&searchValue=" + searchVal;
							window.location = window.location.protocol + '//'
									+ window.location.host + searchURL

						});

				$('#reset').click(
						function() {

							var resetURL = "/list";
							window.location = window.location.protocol + '//'
									+ window.location.host + resetURL

						});
			});
</script>
</html>