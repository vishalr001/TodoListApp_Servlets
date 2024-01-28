package app.servlet.jakartaee.todolist;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoListDAO todoListDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jbbcPassword = getServletContext().getInitParameter("jdbcPassword");
		
		todoListDAO = new TodoListDAO(jdbcURL, jdbcUsername, jbbcPassword);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if(action == null) action = "/";
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertTodo(request, response);
                break;
            case "/delete":
                deleteTodo(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateTodo(request, response);
                break;
            default:
                listTodo(request, response);
                break;
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
            throw new ServletException(ex);
        }
    }
	
	private void listTodo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TodoList> listTodoTasks = todoListDAO.ListAllTasks();
		request.setAttribute("TodoLists", listTodoTasks);
		request.getRequestDispatcher("TodoList.jsp")
			   .forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getRequestDispatcher("TodoListForm.jsp")
			   .forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		TodoList existingTodoTask = todoListDAO.getTodoLists(id);
		request.setAttribute("TodoList", existingTodoTask);
		request.getRequestDispatcher("TodoListForm.jsp")
			   .forward(request, response);
	}
	
	private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		todoListDAO.insertTask(
				new TodoList(
						request.getParameter("title"), request.getParameter("targetDate"), Boolean.parseBoolean(request.getParameter("status"))));
		response.sendRedirect("list");
	}
	
	private void updateTodo(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
 
        todoListDAO.updateTodo(
        		new TodoList(
        		    Integer.parseInt(request.getParameter("id")), request.getParameter("title"), request.getParameter("targetDate"), Boolean.parseBoolean(request.getParameter("status"))));
        response.sendRedirect("list");
    }
 
    private void deleteTodo(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
 
        todoListDAO.deleteTodo(
        		new TodoList(Integer.parseInt(request.getParameter("id"))));
        response.sendRedirect("list");
 
    }
}
