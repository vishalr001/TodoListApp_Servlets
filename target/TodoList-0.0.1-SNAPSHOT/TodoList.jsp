<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>TODO List Application</title>
</head>
<body>
    <center>
        <h1>TODO List Application</h1>
        <h2>
            <a href="ControllerServlet?action=new">Add New Task</a>
            <a href="ControllerServlet?action=list">List All Tasks</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Todo Tasks</h2></caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Target Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="TodoList" items="${TodoLists}">
                <tr>
                    <td><c:out value="${TodoList.id}" /></td>
                    <td><c:out value="${TodoList.title}" /></td>
                    <td><c:out value="${TodoList.targetDate}" /></td>
                    <td><c:out value="${TodoList.status}" /></td>
                    <td>
                        <a href="ControllerServlet?id=<c:out value='${TodoList.id}' />" action = "edit">Edit</a>
                        <a href="ControllerServlet?action?id=<c:out value='${TodoList.id}' />" action = "delete">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>