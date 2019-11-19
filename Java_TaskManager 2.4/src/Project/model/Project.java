package Project.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for a Project.
 *
 * @author Yongsheng Qian
 */

public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8209581750320356753L;
	private String name;
	private LocalDate dueDate;
	private String description;
	private List<Task> taskList;

	/**
	 * Default constructor.
	 */
	public Project() {
		this(null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param ProjectName
	 */
	public Project(String ProjectName) {
		this.name = ProjectName;
		this.description = "No Description";

		this.dueDate =LocalDate.of(1999, 2, 21);
		this.taskList = new ArrayList<Task>();
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

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public boolean isAllFinished() {
		for (Task task : taskList) {
			if(!task.getDescription().equals("Finished")) {
				return false;
			}
		}
		return true;
	}

}
