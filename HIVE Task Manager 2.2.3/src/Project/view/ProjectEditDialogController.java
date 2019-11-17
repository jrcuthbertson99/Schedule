package Project.view;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Project.model.Project;
import Project.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Yongsheng Qian
 */
public class ProjectEditDialogController {

    @FXML
    private TextField projectNameField;
    @FXML
    private TextField projectDueDateField;
    @FXML
    private TextArea projectDescriptionArea;


    private Stage dialogStage;
    private Project project;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
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
    private void ProjectHandleOk() {
        if (isInputValid()) {
            project.setProjectName(projectNameField.getText());
            project.setProjectDueDate(DateUtil.parse(projectDueDateField.getText()));
            project.setProjectDescription(projectDescriptionArea.getText());


            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void ProjectHandleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
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
}