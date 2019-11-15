package Project.model;

import java.time.LocalDate;

//import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model class for a Project.
 *
 * @author Yongsheng Qian
 */


public class Project {
	
	private final StringProperty ProjectName;
	private final ObjectProperty<LocalDate> ProjectDueDate;
	private final StringProperty ProjectDescription;
//	private final IntegerProperty NumTask;
	private final ListProperty<Task> ProjectTaskList;
	
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
		this.ProjectName = new SimpleStringProperty(ProjectName);
		this.ProjectDescription = new SimpleStringProperty("No Description");
		
		this.ProjectDueDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
//		this.NumTask = new SimpleIntegerProperty(0);
		this.ProjectTaskList = new SimpleListProperty<Task>();
		
	}
	
	public String getProjectName() {
		return ProjectName.get();
	}
	
	public void setProjectName(String ProjectName) {
		this.ProjectName.set(ProjectName);
	}
	
	public StringProperty projectNameProperty() {
		return ProjectName;
	}	
	
	
	public String getProjectDescription() {
		return ProjectDescription.get();
	}
	
	public void setProjectDescription(String ProjectDescription) {
		this.ProjectDescription.set(ProjectDescription);
	}
	
	public StringProperty projectDescriptionProperty() {
		return ProjectDescription;
	}	
	
	
    public LocalDate getProjectDueDate() {
        return ProjectDueDate.get();
    }

    public void setProjectDueDate(LocalDate ProjectDueDate) {
        this.ProjectDueDate.set(ProjectDueDate);
    }
    
    public ObjectProperty<LocalDate> projectDueDateProperty() {
        return ProjectDueDate;
    }
    
    
//    public int getNumTask() {
//        return NumTask.get();
//    }
//
//    public void setNumTask(int NumTask) {
//        this.NumTask.set(NumTask);
//    }
//    
//    public IntegerProperty NumTaskProperty() {
//        return NumTask;
//    }
//    
    
    
    
    public  ObservableList<Task>  getProjectTaskList() {
        return ProjectTaskList.get();
    }

    public void setTaskList(ObservableList<Task> ProjectTaskList) {
        this.ProjectTaskList.set(ProjectTaskList);
    }
    
    public ListProperty<Task> projectTaskListProperty() {
        return ProjectTaskList;
    }
    

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
