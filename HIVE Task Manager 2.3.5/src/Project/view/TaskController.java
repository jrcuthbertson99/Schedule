package Project.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    private ProjectOverviewController ProjectOverviewController;
    private Project project;
//    
    // Reference to the main application.
    private MainApp MainApp;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
//    public TaskController(MainApp MainApp) {
//    	this.MainApp = MainApp;
//    }
    
    public TaskController() {
    }
    
	public void setMainApp(MainApp MainApp) { 
		this.MainApp = MainApp;
	}
	
	public MainApp getMainApp() {
		return this.MainApp;
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
    
    public void setProjectController(ProjectOverviewController ProjectOverviewController) {
    	this.ProjectOverviewController = ProjectOverviewController;
    }
    
    
    /**
     * Returns the data as an observable list of Projects. 
     * @return
     */
    public ObservableList<Task> getTaskData() {
        return project.getProjectTaskList();
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteTask() {
        int selectedIndex = TaskTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	TaskTable.getItems().remove(selectedIndex);
//        	TaskTable.getSelectionModel().selectAboveCell();
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(ProjectOverviewController.getMainApp().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Task Selected");
            alert.setContentText("Please select a task in the table.");

            alert.showAndWait();
        }

    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new project.
     */
    @FXML
    private void handleNewTask() {
        Task tempTask = new Task();
        boolean okClicked =  ProjectOverviewController.showTaskEditDialog(tempTask);
        if (okClicked ) {
        	if (project==null || !this.ProjectOverviewController.ProjectInProjectTable(project)) { 
        		Project newProject = new Project("New");
        		newProject.getProjectTaskList().add(tempTask);
        		ProjectOverviewController.getMainApp().getProjectData().add(newProject);
        		
        		
        	} else getTaskData().add(tempTask);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected project.
     */
    @FXML
    private void handleEditTask() {
        Task selectedTask = TaskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            boolean okClicked = ProjectOverviewController.showTaskEditDialog(selectedTask);
//            if (okClicked) {
//                showTaskDetails(selectedTask);
//            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(ProjectOverviewController.getMainApp().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Project Selected");
            alert.setContentText("Please select a project in the table.");

            alert.showAndWait();
        }
    }
}
