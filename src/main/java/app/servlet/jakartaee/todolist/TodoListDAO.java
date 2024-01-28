package app.servlet.jakartaee.todolist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class TodoListDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public TodoListDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws Exception {
		if(jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch(ClassNotFoundException e){
				e.printStackTrace();
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws Exception {
		try {
		if(jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	public Boolean insertTask(TodoList todoList) throws Exception {
		String sql = "Insert into todolist(title, target_date, status) values (?,?,?)";
		Boolean rowsInserted = false;
		try {
			connect();
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, todoList.title);
			statement.setString(2, todoList.targetDate);
			statement.setBoolean(3, todoList.status);
			
			rowsInserted = statement.executeUpdate() > 0;
			statement.close();
			disconnect();
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return rowsInserted;
	}
	
	public List<TodoList> ListAllTasks() throws Exception{
		List<TodoList> TodoLists = new ArrayList<>();
		String sql = "Select * from todolist";
		try {
			connect();
			ResultSet resultSet = jdbcConnection.createStatement().executeQuery(sql);
			while(resultSet.next()) {
				TodoList todoList = new TodoList(resultSet.getInt("list_id"), resultSet.getString("title"), resultSet.getString("target_date"), resultSet.getBoolean("Status"));
				TodoLists.add(todoList);
			}
			resultSet.close();
			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
		return TodoLists;
	}
	
	public boolean deleteTodo(TodoList todoList) throws Exception {
        String sql = "DELETE FROM todolist where list_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, todoList.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateTodo(TodoList todoList) throws Exception {
        String sql = "UPDATE todolist SET title = ?, target_date = ?, status = ? WHERE list_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, todoList.getTitle());
        statement.setString(2, todoList.getTargetDate());
        statement.setBoolean(3, todoList.isStatus() ? true : false);
        statement.setInt(4, todoList.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public TodoList getTodoLists(int id) throws Exception {
    	TodoList todoList = null;
        String sql = "SELECT * FROM todolist WHERE list_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String targetDate = resultSet.getString("target_date");
            Boolean status = resultSet.getBoolean("status");
             
            todoList = new TodoList(id, title, targetDate, status);
        }
         
        resultSet.close();
        statement.close();
         
        return todoList;
    }
	
}
