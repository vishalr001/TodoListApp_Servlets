package app.servlet.jakartaee.todolist;

public class TodoList {
	
	protected int id;
	protected String title;
	protected String targetDate;
	protected boolean status;
	
	public TodoList() {}
	
	public TodoList(int id) {
		this.id = id;
	}
	
	public TodoList(int id, String title, String targetDate, boolean status) {
		this(title, targetDate, status);
		this.id = id;
	}
	
	public TodoList(String title, String targetDate, boolean status) {
		this.title = title;
		this.targetDate = targetDate;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
		
}
