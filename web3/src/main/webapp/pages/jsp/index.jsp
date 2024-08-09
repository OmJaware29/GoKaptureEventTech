<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Registration</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	margin: 0;
	padding: 0;
}

.container {
	width: 80%;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
	margin-top: 40px;
}

h3 {
	color: #333;
	text-align: center;
	margin-top: 0;
}

hr {
	border: 1px solid #ccc;
}

form {
	width: 100%;
	margin: 20px 0;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px 0;
}

th, td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: left;
}

th {
	background-color: #f4f4f4;
}

tr:hover {
	background-color: #f1f1f1;
}

img {
	height: 100px;
	width: 80px;
}

.form-table {
	width: 100%;
	margin: 0 auto;
	max-width: 600px;
}

.form-table td {
	padding: 10px;
}

.form-table input[type="text"], .form-table input[type="file"] {
	width: 100%;
	padding: 8px;
	box-sizing: border-box;
}

.form-table input[type="submit"] {
	width: 100px;
	padding: 10px;
	margin: 5px;
	cursor: pointer;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
}

.form-table input[type="submit"]:hover {
	background-color: #45a049;
}

.status {
	text-align: center;
	color: red;
}
</style>
</head>
<body>
	<div class="container">
		<h3>Student Registration</h3>
		<hr>
		<form action="req1" method="post" enctype="multipart/form-data">
			<table class="form-table">
				<tr>
					<td align="right">ID:</td>
					<td><input type="text" name="sid" value="0"></td>
				</tr>
				<tr>
					<td align="right">Name:</td>
					<td><input type="text" name="sname"></td>
				</tr>
				<tr>
					<td align="right">City:</td>
					<td><input type="text" name="scity"></td>
				</tr>
				<tr>
					<td align="right">Percentage:</td>
					<td><input type="text" name="spercentage" value="0.0"></td>
				</tr>
				<tr>
					<td align="right">Upload Image:</td>
					<td><input type="file" name="simg"></td>
				</tr>
				<tr>
					<td align="right">Upload Doc:</td>
					<td><input type="file" name="sdoc"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="submit" name="b1" value="Add"> <input type="submit"
						name="b1" value="Delete"> <input type="submit" name="b1"
						value="Display"> <input type="submit" name="b1"
						value="DeleteAll">
				</tr>
			</table>
		</form>
		<div class="status">
			<h4>${status}</h4>
		</div>
		<h3>List Of Students</h3>
		<hr>
		<form action="searchbyany" method="post">
		<input type="text" name="t1" placeholder="Search By Any">
		<input type="submit" name="b1">
		</form>

		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Image</th>
					<th>Name</th>
					<th>City</th>
					<th>Percentage</th>
					<th>Documents</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="s" items="${students}">
					<tr>
						<td>${s.sid}</td>
						<td><a href="reqtodisplay/${s.sid}"><img src
									="findimg/${s.sid}" alt="Student Image"></a></td>
						<td>${s.sname}</td>
						<td>${s.scity}</td>
						<td>${s.spercentage}</td>
						<td><a href="/finddocs/${s.sid}">View Docs</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
