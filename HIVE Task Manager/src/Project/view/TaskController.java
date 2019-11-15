package Project.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

import Project.MainApp;
//import Project.model.Project;
import Project.model.Task;


public class TaskController {
    @FXML
    private TableView<Task> TaskTable;
    @FXML
    private TableColumn<Task, String> TaskNameColume;
    @FXML
    private TableColumn<Task, String> TaskDescriptionColume;
    @FXML
    private TableColumn<Task, LocalDate> TaskDueDateColume;

//    @FXML
//    private Label TaskNameLabel;
//    @FXML
//    private Label TaskDescriptionLabel;
//    @FXML
//    private Label TaskDueDateLabel;
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
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param MainApp
     */
//    public void setMainApp(MainApp MainApp) {
//        this.MainApp = MainApp;
//
//        // Add observable list data to the table
//        TaskTable.setItems(this.MainApp.getTaskData());
//    }
}
