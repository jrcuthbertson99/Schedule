package Project.view;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Project.MainApp;
import Project.model.Project;
import Project.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Yongsheng Qian
 */
public class ProjectEditDialogController {

	@FXML
	public TextField projectNameField;
	@FXML
	public TextField projectDueDateField;
	@FXML
	public TextArea projectDescriptionArea;


	public Stage dialogStage;
	public Project project;
	public boolean okClicked = false;
	public MainApp MainApp;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */	
	public ProjectEditDialogController() {
	}
	public void setMainApp(MainApp MainApp) { 
		this.MainApp = MainApp;
	}
	
	public MainApp getMainApp() {
		return this.MainApp;
	}
	
	@FXML
	public void initialize() {
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the project to be edited in the dialog.
	 * 
	 * @param project
	 */
	public void setProject(Project project) {
		this.project = project;

		projectNameField.setText(project.getProjectName());
		projectDueDateField.setText(DateUtil.format(project.getProjectDueDate()));
		projectDueDateField.setPromptText("mm/dd/yyyy");
		projectDescriptionArea.setWrapText(true);
		projectDescriptionArea.setText(project.getProjectDescription());
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	public void ProjectHandleOk() {
		if (isInputValid()) {
			this.project.setProjectName(projectNameField.getText());
			this.project.setProjectDueDate(DateUtil.parse(projectDueDateField.getText()));
			this.project.setProjectDescription(projectDescriptionArea.getText());


			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	public void ProjectHandleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	public boolean isInputValid() {
		String errorMessage = "";

		if (projectNameField.getText() == null || projectNameField.getText().length() == 0) {
			errorMessage += "No valid project Name!\n"; 
		}
		if (projectDescriptionArea.getText() == null || projectDescriptionArea.getText().length() == 0) {
			errorMessage += "No valid project Description!\n"; 
		}


		if (projectDueDateField.getText() == null || projectDueDateField.getText().length() == 0) {
			errorMessage += "No valid project DueDate!\n";
		} else {
			if (!DateUtil.validDate(projectDueDateField.getText())) {
				errorMessage += "No valid project Due Date. Use the format mm/dd/yyy!\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			
			alert.showAndWait();
			
			return false;
		}
	}

	public void setMain(MainApp mainApp) {
		this.MainApp = mainApp;
		
	}
}