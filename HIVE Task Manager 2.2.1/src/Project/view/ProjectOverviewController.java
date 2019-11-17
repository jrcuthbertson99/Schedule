package Project.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;

import Project.MainApp;
import Project.model.Project;
import Project.model.Task;
import Project.util.DateUtil;


public class ProjectOverviewController {
    @FXML
    private TableView<Project> projectTable;
    @FXML
    private TableColumn<Project, String> projectNameColume;
    @FXML
    private TableColumn<Project, String> projectDescriptionColume;
    @FXML
    private TableColumn<Project, LocalDate> projectDueDateColume;

    @FXML
    private Label projectNameLabel;
    @FXML
    private Label projectDescriptionLabel;
    @FXML
    private Label projectDueDateLabel;
    
    @FXML
    private AnchorPane TaskPane;
    
    @FXML
//    private TableView<Task> TaskTable;
//    @FXML
//    private TableColumn<Task, String> TaskNameColume;
//    @FXML
//    private TableColumn<Task, String> TaskDescriptionColume;
//    @FXML
//    private TableColumn<Task, LocalDate> TaskDueDateColume;
    
//    private Project tempProject = null;
    // Reference to the main application.
    private MainApp MainApp;
    
//    private final TaskController taskController;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ProjectOverviewController() {
//    	this.taskController = new TaskController();
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the project table with the two columns.
    	projectNameColume.setCellValueFactory(
    			cellData -> cellData.getValue().projectNameProperty());
        projectDescriptionColume.setCellValueFactory(
        		cellData -> cellData.getValue().projectDescriptionProperty());
        projectDueDateColume.setCellValueFactory(
        		cellData -> cellData.getValue().projectDueDateProperty());
        
        showProjectDetails(null);
        
        projectTable.getSelectionModel().selectedItemProperty().addListener(
        		 (observable, oldValue, newValue) -> showProjectDetails(newValue));
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param MainApp
     */
    public void setMainApp(MainApp MainApp) {
        this.MainApp = MainApp;
        ObservableList<Project> ProjectList = this.MainApp.getProjectData();
        // Add observable list data to the table
        projectTable.setItems(ProjectList);
    }
    
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showProjectDetails(Project project) {
        if (project != null) {
            // Fill the labels with info from the project object.
        	projectNameLabel.setText(project.getProjectName());
//            projectDueDateLabel.setText(project.getProjectDueDate().toString());
            projectDescriptionLabel.setText(project.getProjectDescription());
//            postalCodeLabel.setText(Integer.toString(project.getPostalCode()));
            projectDueDateLabel.setText(DateUtil.format(project.getProjectDueDate()));
            

            try {

    			FXMLLoader Taskloader = new FXMLLoader();
    			Taskloader.setLocation(MainApp.class.getResource("view/TaskOverview.fxml"));
    			AnchorPane TaskOverview = (AnchorPane) Taskloader.load();
    			AnchorPane.setTopAnchor(TaskOverview, 0.0);
    			AnchorPane.setRightAnchor(TaskOverview, 0.0);
    			AnchorPane.setLeftAnchor(TaskOverview, 0.0);
    			AnchorPane.setBottomAnchor(TaskOverview, 0.0);
    			Scene scene = new Scene(TaskOverview);
    			
                this.TaskPane.getChildren().add(TaskOverview);
    			
    			
//              this.tempProject = project;
                TaskController Taskcontroller = Taskloader.getController();
                Taskcontroller.setProject(project);
                
                
//    	        this.taskController.setProject(project);
                
//                TaskTable.setItems(project.getProjectTaskList());
//                showTaskDetails();
//                
            } catch (IOException e) {
    			e.printStackTrace();
    		}


        } else {
            // Person is null, remove all the text.
        	projectNameLabel.setText("");
        	projectDescriptionLabel.setText("");
            projectDueDateLabel.setText("");
            
            
        }
    }
    
//    public ObservableList<Task> getTaskData() {
//    	if (this.tempProject == null) {
//    		return null;
//    	}
//        return this.tempProject.getProjectTaskList();
//    }
    
//	private void showTaskDetails() {
//        // Initialize the task table with the two columns.
//		TaskNameColume.setCellValueFactory(
//     			cellData -> cellData.getValue().TaskNameProperty());
////		TaskDescriptionColume.setCellValueFactory(
////         		cellData -> cellData.getValue().TaskDescriptionProperty());
//		TaskDueDateColume.setCellValueFactory(
//         		cellData -> cellData.getValue().TaskDueDateProperty());
//	}    
//    
    
    
//    private TaskController Task;
//
//    public void setTaskView(TaskController Task) {
//       this.Task = Task;
//    }
}
