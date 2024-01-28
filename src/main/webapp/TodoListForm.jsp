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
        <h1>TODO List</h1>
        <form action="new" method="post">
        	<input type="submit" value="Add New Task" />
        </form>
        <form action="list" method="post">
        	<input type="submit" value="List All Tasks" />
        </form>    
    </center>
    <div align="center">
        <c:if test="${TodoList != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${TodoList == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${TodoList != null}">
                        Edit Task
                    </c:if>
                    <c:if test="${TodoList == null}">
                        Add New Task
                    </c:if>
                </h2>
            </caption>
                <c:if test="${TodoList != null}">
                    <input type="hidden" name="id" value="<c:out value='${TodoList.id}' />" />
                </c:if>           
            <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="title" size="45"
                            value="<c:out value='${TodoList.title}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Traget Date: </th>
                <td>
                    <input type="text" name="targetDate" size="45"
                            value="<c:out value='${TodoList.targetDate}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Status</th>
                <td>
                    <input type="text" name="status" size="5"
                            value="<c:out value='${TodoList.status}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
	               <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>
