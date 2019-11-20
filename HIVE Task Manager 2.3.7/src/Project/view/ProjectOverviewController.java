package Project.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
//import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import Project.MainApp;
import Project.model.Project;
import Project.model.Task;
import Project.model.UserInfoManager;
import Project.util.DateUtil;


public class ProjectOverviewController {
	@FXML
	public TableView<Project> projectTable;
	@FXML
	public TableColumn<Project, String> projectNameColume;
	@FXML
	public TableColumn<Project, String> projectDescriptionColume;
	@FXML
	public TableColumn<Project, LocalDate> projectDueDateColume;

	@FXML
	public Label projectNameLabel;
	@FXML
	public Label projectDescriptionLabel;
	@FXML
	public Label projectDueDateLabel;
	
	@FXML
	public AnchorPane TaskPane;

	// Reference to the main application.
	public MainApp MainApp;
	
	public TaskController TaskController;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */	
	public ProjectOverviewController() {
	}
	
	public void setMainApp(MainApp MainApp) { 
		this.MainApp = MainApp;
		this.TaskController.setMainApp(this.MainApp);
		projectTable.setItems(this.MainApp.getProjectData());
	}
	
	
	public void refreshProjectList() {
		projectTable.setItems(null);
		projectTable.setItems(this.MainApp.getProjectData());
	}
	
	public void cleanProjectList() {
		projectTable.setItems(null);
	}
	
	
	public MainApp getMainApp() {
		return this.MainApp;
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	public void initialize() {
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
		
		this.initialTask();
	}
	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param MainApp
	 */

	
	public void initialTask() {
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
			
//		  this.tempProject = project;
			this.TaskController = Taskloader.getController();
			this.TaskController.setMainApp(this.getMainApp());

//			TaskTable.setItems(project.getProjectTaskList());
//			showTaskDetails();
//			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Fills all text fields to show details about the project.
	 * If the specified project is null, all text fields are cleared.
	 * 
	 * @param project the project or null
	 */
	public void showProjectDetails(Project project) {
		if (project != null) {
			// Fill the labels with info from the project object.
			projectNameLabel.setText(project.getProjectName());
//			projectDueDateLabel.setText(project.getProjectDueDate().toString());
			projectDescriptionLabel.setText(project.getProjectDescription());
//			postalCodeLabel.setText(Integer.toString(project.getPostalCode()));
			projectDueDateLabel.setText(DateUtil.format(project.getProjectDueDate()));
			
			this.TaskController.setProject(project);
			
		} else {
			// Project is null, remove all the text.
			projectNameLabel.setText("");
			projectDescriptionLabel.setText("");
			projectDueDateLabel.setText("");
			
			
		}
	}
   
	
	
	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	public void handleDeleteProject() {
		int selectedIndex = projectTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			
			ObservableList<Task> ProjectTaskList = projectTable.getItems().get(selectedIndex).getProjectTaskList();
			for (int i=0; i < ProjectTaskList.size(); i++) {
				ProjectTaskList.remove(i);
			}
			
			projectTable.getItems().remove(selectedIndex);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(MainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Project Selected");
			alert.setContentText("Please select a project in the table.");

			alert.showAndWait();
		}

	}
	
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new project.
	 */
	@FXML
	public void handleNewProject() {
		Project tempProject = new Project();
		boolean okClicked = this.showProjectEditDialog(tempProject);
		if (okClicked) {
			this.MainApp.getProjectData().add(tempProject);
			projectTable.getSelectionModel().selectLast();;
		}
	}
	

	
	
	/**
	 * Opens a dialog to edit details for the specified project. If the user
	 * clicks OK, the changes are saved into the provided project object and true
	 * is returned.
	 * 
	 * @param project the project object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showProjectEditDialog(Project project) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ProjectEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Project");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.MainApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the project into the controller.
			this.MainApp.setProjectEditDialogController(loader.getController());
			this.MainApp.getProjectEditDialogController().setMainApp(this.getMainApp());
			this.MainApp.getProjectEditDialogController().setProject(project);
			this.MainApp.getProjectEditDialogController().setDialogStage(dialogStage);;

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return this.MainApp.getProjectEditDialogController().isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected project.
	 */
	@FXML
	public void handleEditProject() {
		Project selectedProject = projectTable.getSelectionModel().getSelectedItem();
		if (selectedProject != null) {
			boolean okClicked = this.showProjectEditDialog(selectedProject);
			if (okClicked) {
				showProjectDetails(selectedProject);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(MainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Project Selected");
			alert.setContentText("Please select a project in the table.");

			alert.showAndWait();
		}
	}
	
	public boolean TaskInProject(Task task) {
		int selectedIndex = projectTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			return this.projectTable.getItems().get(selectedIndex).getProjectTaskList().contains(task);
				
		}
		return false;
	}
	
	public boolean ProjectInProjectTable(Project project) {
		int selectedIndex = projectTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			return this.projectTable.getItems().contains(project);
				
		}
		return false;
	}
	
	public UserInfoManager getUserInfoManager() {
		return this.MainApp.getUserInfoManager();
	}
	
	public void tableSelectedLastOne() {
		this.projectTable.getSelectionModel().selectLast();
	}
	
	
	public void tableSelectedNone() {
		this.projectTable.getSelectionModel().selectLast();
	}
	
	public void tableSelectedFirst() {
		this.projectTable.getSelectionModel().selectFirst();
	}

	public TaskController getTaskController() {
		return this.TaskController;
	}
	

	
}
