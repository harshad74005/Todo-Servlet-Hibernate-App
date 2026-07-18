package com.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.TaskDao;
import com.dto.Task;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskDao taskDao;

    @Override
    public void init() {
        taskDao = new TaskDao();
    }

    // GET Request: List dakhvane, status toggle karne kiwa delete karne
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (action.equals("delete")) {
                taskDao.deleteTask(id);
            } else if (action.equals("toggle")) {
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                taskDao.updateTaskStatus(id, status);
            }
            response.sendRedirect("todo"); // URL clean sathi parat redirect
            return;
        }

        // Default: Sarya tasks chi list dynamic JSP page kade forward karne
        List<Task> listTask = taskDao.fetchAllTasks();
        request.setAttribute("listTask", listTask);
        request.getRequestDispatcher("todo-list.jsp").forward(request, response);
    }

    // POST Request: Form submit kelyavar navin ToDo create karne
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Task newTask = new Task(title, description, false);
        taskDao.insertTask(newTask);
        
        response.sendRedirect("todo");
    }
}