<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Details</title>
<style>
    body {
        font-family: 'Arial', sans-serif;
        background: #444;
        margin: 0;
        padding: 0;
        color: #fff;
    }
    center {
        margin-top: 50px;
    }
    h3 {
        color: #4caf50;
        font-size: 32px;
        text-shadow: 2px 2px 4px #000;
        margin-bottom: 20px;
    }
    table {
        background-color: rgba(0, 0, 0, 0.7);
        border-collapse: collapse;
        width: 70%;
        margin: 0 auto;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
        border-radius: 10px;
        overflow: hidden;
    }
    th, td {
        padding: 15px;
        text-align: left;
        font-size: 16px;
        color: #4caf50;
    }
    tr:nth-child(even) {
        background-color: rgba(255, 255, 255, 0.1);
    }
    tr:nth-child(odd) {
        background-color: rgba(0, 0, 0, 0.1);
    }
    input[type="text"], input[type="file"] {
        width: calc(100% - 20px);
        padding: 10px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin: 5px;
        background-color: rgba(255, 255, 255, 0.8);
        color: #000;
    }
    input[type="submit"], a {
        background-color: #4caf50;
        color: white;
        padding: 10px 20px;
        text-decoration: none;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        margin: 5px;
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
    }
    input[type="submit"]:hover, a:hover {
        background-color: #388e3c;
    }
    a {
        text-align: center;
        display: inline-block;
    }
    a[href="index"] {
        background-color: #388e3c;
    }
    a[href="index"]:hover {
        background-color: #4caf50;
    }
    img {
        border-radius: 10px;
        border: 2px solid #4caf50;
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.5);
    }
    hr {
        border: 1px solid #4caf50;
        width: 70%;
        margin: 0 auto 20px;
    }
    form {
        background-color: rgba(0, 0, 0, 0.7);
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5);
        width: 70%;
        margin: 0 auto;
    }
</style>
</head>
<body>
<center>
    <h3>Student Details</h3>
    <hr>
    <form action="req1" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td colspan="2" align="center">
                    <img src="findimg/${student.sid}" height="200" width="160"><br>
                    <a href="finddocs/${student.sid}">View Docs</a>
                </td>
            </tr>
            <tr>
                <td align="right">ID:</td>
                <td><input type="text" name="sid" value="${student.sid}" readonly="readonly"></td>
            </tr>
            <tr>
                <td align="right">Name:</td>
                <td><input type="text" name="sname" value="${student.sname}"></td>
            </tr>
            <tr>
                <td align="right">City:</td>
                <td><input type="text" name="scity" value="${student.scity}"></td>
            </tr>
            <tr>
                <td align="right">Percentage:</td>
                <td><input type="text" name="spercentage" value="${student.spercentage}"></td>
            </tr>
            <tr>
                <td align="right">Change Img:</td>
                <td><input type="file" name="simg"></td>
            </tr>
            <tr>
                <td align="right">Change Docs:</td>
                <td><input type="file" name="sdoc"></td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <a href="index">Back</a>
                    <input type="submit" name="b1" value="Update">
                </td>
            </tr> 
        </table>
    </form>
</center>         
</body>
</html>
