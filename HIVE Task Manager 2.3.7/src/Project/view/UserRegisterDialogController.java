package Project.view;


//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
import java.util.ArrayList;
//import java.util.Scanner;

import Project.MainApp;
import Project.model.Project;
import Project.model.UserInfo;
import Project.model.UserInfoManager;
//import Project.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Yongsheng Qian
 */
public class UserRegisterDialogController {

	@FXML
	private TextField UserNameField;
	@FXML
	private PasswordField PassWordField;
	@FXML
	private PasswordField RePassWordField;
	
//  private Project project;
	private Stage dialogStage;
	private UserInfo userinfo;
	private boolean SignUpClicked = false;
	
	private MainApp MainApp;
	private UserLoginDialogController UserLoginDialogController;
	private MenuController MenuController;
	
	ObservableList<Project> myProjects;
	private ArrayList<String[]> loginInfo;
	private String username;
	private String password;
	
	
	private boolean hasRegisted = false;
	private UserInfoManager UserInfoManager;
	private MainApp MianApp;
	
	public void setMenuController(MenuController MenuController) {
		this.MenuController = MenuController;
	}
	
	public UserRegisterDialogController() {
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
	 * Sets the userInfo to be edited in the dialog.
	 * 
	 * @param project
	 */
	public void setUserInfo(UserInfo UserInfo) {
		this.userinfo = UserInfo;

		UserNameField.setText(UserInfo.getUserName());

	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isSignUpClicked() {
		return SignUpClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void RegisterHandleSignUp() {
		if (isInputValid()) {
			if (this.MainApp.getUserInfoManager().checkUserExist(UserNameField.getText())) {
				alart(AlertType.WARNING, "Registed Fail!", "Sorry, that username has been taken", null);
				return;
			}
			String[] newLoginInfo = {UserNameField.getText(),PassWordField.getText()};
			if (this.MainApp.getUserInfoManager().addNewAccount(newLoginInfo)) {
				alart(AlertType.CONFIRMATION, "Registed Successful!", "Thank you for your signup!\n", null);
				SignUpClicked = true;
				dialogStage.close();
			}
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void RegisterHandleCancel() {
		this.dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (UserNameField.getText() == null || UserNameField.getText().length() == 0) {
			errorMessage += "No valid User Name!\n"; 
		}
		if (PassWordField.getText() == null || PassWordField.getText().length() == 0) {
			errorMessage += "No valid Password!\n"; 
		}
		if (RePassWordField.getText() == null || RePassWordField.getText().length() == 0) {
			errorMessage += "No valid Retyped Password!\n";   
		} else if (!PassWordField.getText().equals(RePassWordField.getText()) ) {
			errorMessage += "Password not Matching!\n"; 
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			alart(AlertType.ERROR, "Fail to create account", "Please correct invalid fields", errorMessage);
			return false;
		}
	}
	
	public void setLoginInfo(ArrayList<String[]> loginInfo) {
		this.loginInfo = loginInfo;
	}
	
	public void setUserLoginDialogController(UserLoginDialogController  UserLoginDialogController) {
		this.UserLoginDialogController =  UserLoginDialogController;
	}

	private void alart(AlertType Type, String Title, String HeaderText, String ContentText) {
		Alert alert = new Alert(Type);
		alert.initOwner(dialogStage);
		if (Title!=null)  alert.setTitle(Title);
		if (HeaderText!=null) alert.setHeaderText(HeaderText);
		if (ContentText!=null) alert.setContentText(ContentText);
		alert.showAndWait();
	}
}