package Project.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    
    
    // Reference to the main application.
    private MainApp MainApp;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ProjectOverviewController() {
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

        // Add observable list data to the table
        projectTable.setItems(this.MainApp.getProjectData());
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

        } else {
            // Person is null, remove all the text.
        	projectNameLabel.setText("");
        	projectDescriptionLabel.setText("");
            projectDueDateLabel.setText("");
        }
    }
    
//    private TaskController Task;
//
//    public void setTaskView(TaskController Task) {
//       this.Task = Task;
//    }
}
