package Project.model;

import java.time.LocalDate;

import javafx.beans.property.ListProperty;
//import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * Model class for a Task.
 *
 * @author Yongsheng Qian
 */
public class Task {
	
	private final StringProperty TaskName;
	private final ObjectProperty<LocalDate> TaskDueDate;
	private final StringProperty TaskDescription;
	
	/**
	 * Default constructor.
	 */
	public Task() {
		this(null);
	}
	
	public Task(String TaskName) {
		this.TaskName = new SimpleStringProperty(TaskName);
		this.TaskDescription = new SimpleStringProperty("No Description");
		
		this.TaskDueDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}
	
	public String getTaskName() {
		return TaskName.get();
	}
	
	public void setTaskName(String TaskName) {
		this.TaskName.set(TaskName);
	}
	
	public StringProperty TaskNameProperty() {
		return TaskName;
	}	
	
	
	public String getTaskDescriptione() {
		return TaskDescription.get();
	}
	
	public void setTaskDescription(String TaskDescription) {
		this.TaskDescription.set(TaskDescription);
	}
	
	public StringProperty TaskDescriptionProperty() {
		return TaskDescription;
	}	
	
	
    public LocalDate getTaskDueDate() {
        return TaskDueDate.get();
    }

    public void setTaskDueDate(LocalDate TaskDueDate) {
        this.TaskDueDate.set(TaskDueDate);
    }
    
    public ObjectProperty<LocalDate> TaskDueDateProperty() {
        return TaskDueDate;
    }
    
//    public  ObservableList<Task>  getProjectTaskList() {
//        return ProjectTaskList.get();
//    }
//
//    public void setTaskList(ObservableList<Task> ProjectTaskList) {
//        this.ProjectTaskList.set(ProjectTaskList);
//    }
//    
//    public ListProperty<Task> projectTaskListProperty() {
//        return ProjectTaskList;
//    }
	
	
}
