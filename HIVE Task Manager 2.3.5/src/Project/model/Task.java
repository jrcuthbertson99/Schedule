package Project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.beans.property.ListProperty;
//import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import Project.model.Project;

/**
 * Model class for a Task.
 *
 * @author Yongsheng Qian
 */
public class Task {
	
	private final StringProperty TaskName;
	private final ObjectProperty<LocalDate> TaskDueDate;
	private final StringProperty TaskDescription;
	
	private final ObjectProperty<LocalDateTime> TaskCreatedDateTime;
	private final ObjectProperty<LocalDateTime> TaskLastUpdateDateTime;
	private final ObjectProperty<Project> TaskProject;
	private boolean TaskComplete;
	
	/**
	 * Default constructor.
	 */
	public Task() {
		this(null);
		this.setTaskCreatedDateTime(LocalDateTime.now());
		this.markAsCompleted();
	}
	
	public Task(String TaskName) {
		this.TaskName = new SimpleStringProperty(TaskName);
		this.TaskDescription = new SimpleStringProperty("No Description");
		
		this.TaskDueDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		
		this.TaskCreatedDateTime = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(LocalDate.now(),LocalTime.now()));
		this.TaskLastUpdateDateTime = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.of(LocalDate.now(),LocalTime.now()));
		this.TaskComplete = true;
		this.TaskProject = new SimpleObjectProperty<Project>();
//		this.TaskProject = new SimpleObjectProperty<Project>();
	}
	
	public String getTaskName() {
		return TaskName.get();
	}
	
	public void setTaskName(String TaskName) {
		this.TaskName.set(TaskName);
		this.setTaskLastUpdateDateTime(LocalDateTime.now());
		Project P;
		if ((P = this.TaskProject.get()) != null) {
			P.setProjectLastUpdateDateTime(LocalDateTime.now());
		}
	}
	
	public StringProperty TaskNameProperty() {
		return TaskName;
	}	
	
	
	public String getTaskDescriptione() {
		return TaskDescription.get();
	}
	
	public void setTaskDescription(String TaskDescription) {
		this.TaskDescription.set(TaskDescription);
		this.setTaskLastUpdateDateTime(LocalDateTime.now());
		Project P;
		if ((P = this.TaskProject.get()) != null) {
			P.setProjectLastUpdateDateTime(LocalDateTime.now());
		}
	}
	
	public StringProperty TaskDescriptionProperty() {
		return TaskDescription;
	}	
	
	
    public LocalDate getTaskDueDate() {
        return TaskDueDate.get();
    }

    public void setTaskDueDate(LocalDate TaskDueDate) {
        this.TaskDueDate.set(TaskDueDate);
		this.setTaskLastUpdateDateTime(LocalDateTime.now());
		Project P;
		if ((P = this.TaskProject.get()) != null) {
			P.setProjectLastUpdateDateTime(LocalDateTime.now());
		}
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
    
    
    
    
    
    public LocalDateTime getTaskCreatedDateTime() {
        return TaskCreatedDateTime.get();
    }

    public void setTaskCreatedDateTime(LocalDateTime TaskCreatedDateTime) {
        this.TaskCreatedDateTime.set(TaskCreatedDateTime);
		this.setTaskLastUpdateDateTime(LocalDateTime.now());
		Project P;
		if ((P = this.TaskProject.get()) != null) {
			P.setProjectLastUpdateDateTime(LocalDateTime.now());
		}
    }
    
    public ObjectProperty<LocalDateTime> taskCreatedDateTimeProperty() {
        return TaskCreatedDateTime;
    }
	
    // LastUpdate
	
    public LocalDateTime getTaskLastUpdateDateTime() {
        return TaskLastUpdateDateTime.get();
    }

    public void setTaskLastUpdateDateTime(LocalDateTime TaskLastUpdateDateTime) {
        this.TaskLastUpdateDateTime.set(TaskLastUpdateDateTime);
//		this.setTaskLastUpdateDateTime(LocalDateTime.now());
		Project P;
		if ((P = this.TaskProject.get()) != null) {
			P.setProjectLastUpdateDateTime(LocalDateTime.now());
		}
    }
    
    public ObjectProperty<LocalDateTime> taskLastUpdateDateTimeProperty() {
        return TaskLastUpdateDateTime;
    }
    
    
    public Project  getTaskProjectProject() {
    	return this.TaskProject.get();
    }
    
    public void setTaskProject(Project TaskProject) {
        this.TaskProject.set(TaskProject);
		this.setTaskLastUpdateDateTime(LocalDateTime.now());
		Project P;
		if ((P = this.TaskProject.get()) != null) {
			P.setProjectLastUpdateDateTime(LocalDateTime.now());
		}
    }
    
    public ObjectProperty<Project> TaskProjectProperty() {
        return TaskProject;
    }

	public void markAsCompleted(){
		this.TaskComplete = true;
		this.setTaskLastUpdateDateTime(LocalDateTime.now());
		Project P;
		if ((P = this.TaskProject.get()) != null) {
			P.setProjectLastUpdateDateTime(LocalDateTime.now());
		}
	}
	
	public void setTaskCompletion(boolean completion){
		this.TaskComplete = completion;
	}
	
	public boolean getTaskCompletion(){
		return this.TaskComplete;
	}
	
	
	public String toString(){
		String writtenForm=this.TaskName.get()+System.lineSeparator()
							+this.TaskDueDate.get()+System.lineSeparator()
							+this.TaskDescription.get();
		return writtenForm;
	}
	public String toStringMeta(){
		String writtenForm="Task"+System.lineSeparator()
							+this.TaskName.get()+System.lineSeparator()
							+this.TaskDueDate.get().toString()+System.lineSeparator()
							+this.TaskDescription.get()+System.lineSeparator()
							+this.TaskCreatedDateTime.get().toString()+System.lineSeparator()
							+this.TaskLastUpdateDateTime.get().toString()+System.lineSeparator();
		return writtenForm;
	}
}
