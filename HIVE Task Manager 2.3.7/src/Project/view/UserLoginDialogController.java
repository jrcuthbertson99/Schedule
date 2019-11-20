package Project.view;

import java.io.*;
//import java.util.*;
//import java.text.*;
//import java.time.*;

import Project.MainApp;
//import Project.model.Project;
//import Project.model.Task;
import Project.model.UserInfo;
import Project.model.UserInfoManager;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import Project.model.Task;
//import Project.model.Task;
//import Project.util.DateUtil;
//import Project.model.Project;

/**
 * Dialog to edit details of a person.
 * 
 * @author Yongsheng Qian
 */
public class UserLoginDialogController {

//	public static final boolean LoginClicked = false;
	@FXML
	public TextField UserNameField;
	@FXML
	public PasswordField PassWordField;


	public Stage dialogStage;
//	public UserInfo userinfo;
	public boolean LoginClicked = false;
	
	public MainApp MainApp;
	public UserRegisterDialogController UserRegisterDialogController;
	public MenuController MenuController;
	
	public UserInfoManager UserInfoManager = new UserInfoManager();

	public UserLoginDialogController() {
	}
	
	public void setMainApp(MainApp MainApp) {  this.MainApp = MainApp; }
	
	public MainApp getMainApp() { return this.MainApp; }

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	public void initialize() { }
	
	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) { this.dialogStage = dialogStage; }
	
	
	public void setUserInfo(UserInfo UserInfo) {
		this.MainApp.getUserInfoManager().setUserInfo(UserInfo);

		UserNameField.setText(UserInfo.getUserName());
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isLoginClicked() { return LoginClicked; }

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	public void LoginHandleLogin() {
		if (isInputValid()) {
			String UserName = UserNameField.getText();
			String PassWord = PassWordField.getText();
			if (this.MainApp.getUserInfoManager().setAccount(UserName, PassWord)){
				this.LoginClicked = true;
				dialogStage.close();
//				return;
			}
			this.MainApp.getProjectOverviewController().refreshProjectList();
			this.MainApp.getProjectOverviewController().tableSelectedNone();
			try {
				this.MainApp.getProjectOverviewController().getTaskController().refreshTasktList();
				this.MainApp.getProjectOverviewController().getTaskController().tableSelectedNone();
			}
			catch (NullPointerException e) {}
		}
	}
	
	@FXML
	public void LoginHandleRegister(ActionEvent event) {
		UserInfo userInfo = new UserInfo();
		showUserRegisterDialog(userInfo);
	}
	
	public boolean showUserRegisterDialog(UserInfo userinfo) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RegisterDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("HIVE TAS5K MANAGER REGITER");
			dialogStage.initModality(Modality.WINDOW_MODAL);
//			dialogStage.initOwner(this.MenuController.getMainApp().getPrimaryStage());
			dialogStage.initOwner(null);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			
			// Set the project into the controller.
			this.UserRegisterDialogController = loader.getController();
			this.UserRegisterDialogController.setMainApp(this.getMainApp());
			this.UserRegisterDialogController.setDialogStage(dialogStage);

			// Set the project into the controller.
			
//			UserRegisterDialogController controller =  loader.getController();
//			controller.setMainApp(this.getMainApp());

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return this.UserRegisterDialogController.isSignUpClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	public void LoginHandleCancel() { dialogStage.close(); }
	

	public Stage getDialogStage() {
		return this.dialogStage;
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	public boolean isInputValid() {
		String errorMessage = "";

		if (UserNameField.getText() != null && UserNameField.getText().length() != 0) { } 
		else { errorMessage += "No valid User Name!\n"; }
		
		if (PassWordField.getText() == null || PassWordField.getText().length() == 0) {
			errorMessage += "No valid Password!\n"; }


		if (errorMessage.length() == 0) { return true; } 
		else { // Show the error message.
			alart(AlertType.WARNING, "Login Fail!", "Please check your Username or/and Password", errorMessage);
			return false;
		}
	}
	
	public void loginInfoAddUserInfo(String[] NameAndPass) {
		this.MainApp.getUserInfoManager().addNewAccount(NameAndPass);
	}

	public void alart(AlertType Type, String Title, String HeaderText, String ContentText) {
		Alert alert = new Alert(Type);
		alert.initOwner(dialogStage);
		if (Title!=null)  alert.setTitle(Title);
		if (HeaderText!=null) alert.setHeaderText(HeaderText);
		if (ContentText!=null) alert.setContentText(ContentText);
		alert.showAndWait();
	}
}