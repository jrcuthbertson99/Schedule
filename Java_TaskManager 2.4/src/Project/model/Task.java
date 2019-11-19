package Project.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Model class for a Task.
 *
 * @author Yongsheng Qian
 */
public class Task  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7856238928683923115L;
	private  String name;
	private  LocalDate dueDate;
	private  String description;
	
	/**
	 * Default constructor.
	 */
	public Task() {
		this(null);
	}
	
	public Task(String TaskName) {
		this.name = TaskName;
		this.description = ("To Do");
		this.dueDate = LocalDate.of(1999, 2, 21);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
