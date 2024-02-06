<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="List.css">
    <title>Todo List Application</title>
</head>
<body>
    <header>
        <nav>
        	<a class="logo">TODO LIST</a>
            <a class = "NewTask" href="new">Add New Task</a>
            <a class = "ListTask" href="list">List All Tasks</a>
        </nav>
    </header>
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
                    <td class = "actions"> 
                    	<div>
                    		<form class="inline" id = "Edit" action="edit?id=<c:out value='${TodoList.id}' />" method="post">
                            	<input type="submit" value="Edit"/>
                        	</form>
                        	<form class="inline" id ="Delete" action="delete?id=<c:out value='${TodoList.id}' />" method="post">
                            	<input type="submit" value="Delete" />
                        	</form>
                    	</div>                                                        
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>
