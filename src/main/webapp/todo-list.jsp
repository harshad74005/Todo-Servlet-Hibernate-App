<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Harshad ToDo App</title>
   <style>
        body { font-family: Arial, sans-serif; background-color: #f4f7f6; margin: 40px; }
        .container { max-width: 600px; background: white; padding: 25px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); margin: 0 auto; }
        h2 { text-align: center; color: #333; }
        .form-group { margin-bottom: 15px; }
        input[type="text"], textarea { width: 95%; padding: 10px; margin-top: 5px; border: 1px solid #ccc; border-radius: 4px; }
        button { background-color: #28a745; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-size: 16px; }
        button:hover { background-color: #218838; }
        
        /* --- TABLE LAYOUT FIXES HERE --- */
        table { width: 100%; margin-top: 20px; border-collapse: collapse; table-layout: fixed; } /* Fixed layout mule table baher janar nahi */
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; word-wrap: break-word; } /* Word wrap mule text automatic khali yeil */
        
        /* Column wise width allocation */
        th:nth-child(1), td:nth-child(1) { width: 20%; } /* Status */
        th:nth-child(2), td:nth-child(2) { width: 25%; } /* Task */
        th:nth-child(3), td:nth-child(3) { width: 40%; } /* Description (Jaada space dili) */
        th:nth-child(4), td:nth-child(4) { width: 15%; } /* Action */
        
        .btn-delete { color: #dc3545; text-decoration: none; font-weight: bold; }
        .btn-toggle { color: #007bff; text-decoration: none; font-weight: bold; }
        .completed { text-decoration: line-through; color: #888; }
    </style>
</head>
<body>

<div class="container">
    <h2>📝 My ToDo List</h2>
    
    <form action="todo" method="post">
        <div class="form-group">
            <label>Task Title:</label>
            <input type="text" name="title" required placeholder="Enter task title...">
        </div>
        <div class="form-group">
            <label>Description:</label>
            <textarea name="description" rows="3" placeholder="Enter description..."></textarea>
        </div>
        <button type="submit">Add Task</button>
    </form>

    <table>
        <thead>
            <tr>
                <th>Status</th>
                <th>Task</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="task" items="${listTask}">
                <tr>
                    <td>
                        <a href="todo?action=toggle&id=${task.id}&status=${task.isCompleted}" class="btn-toggle">
                            <c:choose>
                                <c:when test="${task.isCompleted}">✅ Done</c:when>
                                <c:otherwise>⬜ Pending</c:otherwise>
                            </c:choose>
                        </a>
                    </td>
                    <td>
                        <span class="${task.isCompleted ? 'completed' : ''}"><c:out value="${task.title}" /></span>
                    </td>
                    <td>
                        <span class="${task.isCompleted ? 'completed' : ''}"><c:out value="${task.description}" /></span>
                    </td>
                    <td>
                        <a href="todo?action=delete&id=${task.id}" class="btn-delete" onclick="return confirm('Ha task delete karaycha ka?')">❌ Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty listTask}">
                <tr>
                    <td colspan="4" style="text-align: center; color: #888;">No tasks added yet! 😊</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

</body>
</html>