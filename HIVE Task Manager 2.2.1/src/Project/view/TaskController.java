package Project.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

import Project.MainApp;
import Project.model.Project;
//import Project.model.Project;
import Project.model.Task;
import Project.view.ProjectOverviewController;


public class TaskController {
    @FXML
    private TableView<Task> TaskTable;
    @FXML
    private TableColumn<Task, String> TaskNameColume;
//    @FXML
//    private TableColumn<Task, String> TaskDescriptionColume;
    @FXML
    private TableColumn<Task, LocalDate> TaskDueDateColume;

//    @FXML
//    private Label TaskNameLabel;
//    @FXML
//    private Label TaskDescriptionLabel;
//    @FXML
//    private Label TaskDueDateLabel;

    
    private Project project;
//    
    // Reference to the main application.
    private MainApp MainApp;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TaskController() {
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the project table with the two columns.
    	TaskNameColume.setCellValueFactory(
    			cellData -> cellData.getValue().TaskNameProperty());
//    	TaskDescriptionColume.setCellValueFactory(
//        		cellData -> cellData.getValue().TaskDescriptionProperty());
    	TaskDueDateColume.setCellValueFactory(
        		cellData -> cellData.getValue().TaskDueDateProperty());
    	
//        TaskTable.getSelectionModel().selectedItemProperty().addListener(
//       		 (observable, oldValue, newValue) -> showTasktDetails(newValue));
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param MainApp
     */
    public void setProject(Project project) {
//    	initialize();
        this.project = project;
        ObservableList<Task> TaskList = this.project.getProjectTaskList();
        // Add observable list data to the table
	    TaskTable.setItems(TaskList);

    }
}
