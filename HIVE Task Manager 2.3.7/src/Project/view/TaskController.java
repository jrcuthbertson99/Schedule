package Project.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.time.LocalDate;

import Project.MainApp;
import Project.model.Project;
//import Project.model.Project;
import Project.model.Task;
import Project.view.ProjectOverviewController;


public class TaskController {
	@FXML
	public TableView<Task> TaskTable;
	@FXML
	public TableColumn<Task, String> TaskNameColume;

	@FXML
	public TableColumn<Task, LocalDate> TaskDueDateColume;

	public ProjectOverviewController ProjectOverviewController;
	public Project project;
//	
	// Reference to the main application.
	public MainApp MainApp;
	
	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	
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
	public void initialize() {
		// Initialize the project table with the two columns.
		TaskNameColume.setCellValueFactory(
				cellData -> cellData.getValue().TaskNameProperty());
//		TaskDescriptionColume.setCellValueFactory(
//				cellData -> cellData.getValue().TaskDescriptionProperty());
		TaskDueDateColume.setCellValueFactory(
				cellData -> cellData.getValue().TaskDueDateProperty());
		
//		TaskTable.getSelectionModel().selectedItemProperty().addListener(
//	   		 (observable, oldValue, newValue) -> showTasktDetails(newValue));
	}
	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param MainApp
	 */
	public void setProject(Project project) {
//		initialize();
		this.project = project;
		ObservableList<Task> TaskList = this.project.getProjectTaskList();
		// Add observable list data to the table
		TaskTable.setItems(TaskList);

	}
	
	
	
	
	public void refreshTasktList() {
		TaskTable.setItems(null);
		TaskTable.setItems(this.project.getProjectTaskList());
	}
	
	public void cleanTaskList() {
		TaskTable.setItems(null);
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
	 * Opens a dialog to edit details for the specified project. If the user
	 * clicks OK, the changes are saved into the provided project object and true
	 * is returned.
	 * 
	 * @param project the project object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showTaskEditDialog(Task Task) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/TaskEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Task");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.MainApp.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the project into the controller.
			TaskEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setTask(Task);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	public void handleDeleteTask() {
		int selectedIndex = TaskTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			TaskTable.getItems().remove(selectedIndex);
//			TaskTable.getSelectionModel().selectAboveCell();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(this.MainApp.getPrimaryStage());
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
	public void handleNewTask() {
		Task tempTask = new Task();
		boolean okClicked =  this.showTaskEditDialog(tempTask);
		if (okClicked ) {
			if (project==null || !this.MainApp.getProjectOverviewController().ProjectInProjectTable(project)) { 
				Project newProject = new Project("New");
				newProject.getProjectTaskList().add(tempTask);
				this.getMainApp().getProjectData().add(newProject);
				this.MainApp.getProjectOverviewController().tableSelectedLastOne();;
			} else getTaskData().add(tempTask);
			this.tableSelectedLastOne();;
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected project.
	 */
	@FXML
	public void handleEditTask() {
		Task selectedTask = TaskTable.getSelectionModel().getSelectedItem();
		if (selectedTask != null) {
			boolean okClicked = this.showTaskEditDialog(selectedTask);
//			if (okClicked) {
//				showTaskDetails(selectedTask);
//			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(this.MainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Project Selected");
			alert.setContentText("Please select a project in the table.");

			alert.showAndWait();
		}
	}
	
	public void tableSelectedLastOne() {
		this.TaskTable.getSelectionModel().selectLast();
	}
	
	public void tableSelectedNone() {
		this.TaskTable.getSelectionModel().selectLast();
	}
	
	public void tableSelectedFirst() {
		this.TaskTable.getSelectionModel().selectFirst();
	}
}
