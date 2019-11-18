package Project.view;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Project.model.Task;
import Project.model.Task;
import Project.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Yongsheng Qian
 */
public class TaskEditDialogController {

    @FXML
    private TextField TaskNameField;
    @FXML
    private TextField TaskDueDateField;
    @FXML
    private TextArea TaskDescriptionArea;


    private Stage dialogStage;
    private Task Task;
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
     * Sets the task to be edited in the dialog.
     * 
     * @param Task
     */
    public void setTask(Task Task) {
        this.Task = Task;

        TaskNameField.setText(Task.getTaskName());
        TaskDueDateField.setText(DateUtil.format(Task.getTaskDueDate()));
        TaskDueDateField.setPromptText("mm/dd/yyyy");
//        TaskDescriptionArea.setWrapText(true);
//        projectDescriptionArea.setText(task.getProjectDescription());
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
    private void TaskHandleOk() {
        if (isInputValid()) {
            Task.setTaskName(TaskNameField.getText());
            Task.setTaskDueDate(DateUtil.parse(TaskDueDateField.getText()));
//            Task.setTaskDescription(TaskDescriptionArea.getText());


            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void TaskHandleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (TaskNameField.getText() == null || TaskNameField.getText().length() == 0) {
            errorMessage += "No valid task Name!\n"; 
        }
//        if (TaskDescriptionArea.getText() == null || TaskDescriptionArea.getText().length() == 0) {
//            errorMessage += "No valid task Description!\n"; 
//        }


        if (TaskDueDateField.getText() == null || TaskDueDateField.getText().length() == 0) {
            errorMessage += "No valid task DueDate!\n";
        } else {
            if (!DateUtil.validDate(TaskDueDateField.getText())) {
                errorMessage += "No valid task Due Date. Use the format mm/dd/yyy!\n";
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