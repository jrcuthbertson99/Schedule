package Project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

//import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

/**
 * Model class for a Project.
 *
 * @author Yongsheng Qian
 */


public class Project {
	
	public final StringProperty ProjectName;
	public final ObjectProperty<LocalDate> ProjectDueDate;
	public final StringProperty ProjectDescription;

	public final ListProperty<Task> ProjectTaskList;
	
	public final ObjectProperty<LocalDateTime> ProjectCreatedDateTime;
	public final ObjectProperty<LocalDateTime> ProjectLastUpdateDateTime;

//	public final AnchorPane TaskPane;
	/**
	 * Default constructor.
	 */
	public Project() {
		this(null);
		this.setProjectCreatedDateTime(LocalDateTime.now());
		this.setTaskList(new SimpleListProperty<Task>(FXCollections.observableArrayList()));
	}
	
    /**
     * Constructor with some initial data.
     * 
     * @param ProjectName
     */
	public Project(String ProjectName) {
		this.ProjectName = new SimpleStringProperty(ProjectName);
		this.ProjectDescription = new SimpleStringProperty("No Description");
		
		this.ProjectDueDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		
		ObservableList<Task> observableList = FXCollections.observableArrayList();
		this.ProjectTaskList = new SimpleListProperty<Task>(observableList);
		
		this.ProjectCreatedDateTime = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());
		this.ProjectLastUpdateDateTime = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());
		
		
	}
	
	
	
	public String getProjectName() { return ProjectName.get(); }
	
	public void setProjectName(String ProjectName) {
		this.ProjectName.set(ProjectName);
		this.ProjectLastUpdateDateTime.set(LocalDateTime.now());
	}
	
	public StringProperty projectNameProperty() { return ProjectName; }	

	public String getProjectDescription() { return ProjectDescription.get(); }
	
	public void setProjectDescription(String ProjectDescription) {
		this.ProjectDescription.set(ProjectDescription);
		this.ProjectLastUpdateDateTime.set(LocalDateTime.now());
	}
	
	public StringProperty projectDescriptionProperty() { return ProjectDescription; }	

    public  ObservableList<Task>  getProjectTaskList() { return ProjectTaskList.get(); }

    public void setTaskList(ObservableList<Task> ProjectTaskList) {
        this.ProjectTaskList.set(ProjectTaskList);
        this.ProjectLastUpdateDateTime.set(LocalDateTime.now());
    }
    
    public void addTaskToProject(Task Task) {
    	 this.ProjectTaskList.add(Task);
    	 this.ProjectLastUpdateDateTime.set(LocalDateTime.now());
    }
    
    public void dropTaskToProject(Task Task) {
   	 this.ProjectTaskList.remove(Task);
   	 this.ProjectLastUpdateDateTime.set(LocalDateTime.now());
   }
    
    public ListProperty<Task> projectTaskListProperty() { return ProjectTaskList; }

    public LocalDate getProjectDueDate() { return ProjectDueDate.get(); }

    public void setProjectDueDate(LocalDate ProjectDueDate) {
        this.ProjectDueDate.set(ProjectDueDate);
        this.ProjectLastUpdateDateTime.set(LocalDateTime.now());
    }
    
    public ObjectProperty<LocalDate> projectDueDateProperty() { return ProjectDueDate; }
    
    
    public LocalDateTime getProjectCreatedDateTime() { return ProjectCreatedDateTime.get(); }

    public void setProjectCreatedDateTime(LocalDateTime ProjectCreatedDateTime) {
        this.ProjectCreatedDateTime.set(ProjectCreatedDateTime);
        this.ProjectLastUpdateDateTime.set(LocalDateTime.now());
    }
    
    public ObjectProperty<LocalDateTime> projectCreatedDateProperty() { return ProjectCreatedDateTime;
    }
	
    // LastUpdate
	
    public LocalDateTime getProjectLastUpdateDateTime() { return ProjectLastUpdateDateTime.get(); }

    public void setProjectLastUpdateDateTime(LocalDateTime ProjectLastUpdateDateTime) { this.ProjectLastUpdateDateTime.set(ProjectLastUpdateDateTime); }
    
    public ObjectProperty<LocalDateTime> ProjectLastUpdateDateTimeProperty() { return ProjectLastUpdateDateTime; }
	
	
    //needs a getTask(taskname) to print?
	public void dropCompleted(ArrayList<Task> ProjectTaskList){
		for(int i=0;i< ProjectTaskList.size();i++){
			if (ProjectTaskList.get(i).getTaskCompletion()==true){
				ProjectTaskList.remove(i);
			}
		}
	}
	
	
	public String toString(){
		String writtenForm=ProjectName.get()+System.lineSeparator()
							+ProjectDueDate.get()+System.lineSeparator()
							+ProjectDescription.get();
		String writtenTasks="";
		for(int i=0; i<ProjectTaskList.size();i++){
			writtenTasks+=ProjectTaskList.get().get(i).toStringMeta()+"\n";
		}
		writtenForm+="\n"+writtenTasks;
		return writtenForm;
	}	
	
	
	public String toStringMeta(){
		String writtenForm="Project"+System.lineSeparator()
								+ProjectName.get()+System.lineSeparator()
								+ProjectDueDate.get().toString()+System.lineSeparator()
								+ProjectDescription.get()+System.lineSeparator()
								+ProjectCreatedDateTime.get()+System.lineSeparator()
								+ProjectLastUpdateDateTime.get()+System.lineSeparator();
		String writtenTasks="";
		for(int i=0; i<ProjectTaskList.size();i++){
			writtenTasks+=ProjectTaskList.get().get(i).toStringMeta();
		}
		writtenForm+="\n"+writtenTasks;
		return writtenForm;
	}
}
