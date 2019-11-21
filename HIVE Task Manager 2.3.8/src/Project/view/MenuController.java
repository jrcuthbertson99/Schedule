package Project.view;


//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
//import javafx.scene.control.SplitPane;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

//import java.io.File;
import java.io.IOException;
//import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Scanner;

import Project.MainApp;
import Project.model.Project;
//import Project.model.Task;
import Project.model.UserInfo;
import Project.model.UserInfoManager;
//import Project.util.DateUtil;


public class MenuController {
	@FXML
	public MenuBar MenuBar;
	@FXML
	public Menu FileMenu;
	@FXML
	public Menu EditMenu;
	@FXML
	public Menu HelpMenu;
	@FXML
	public Menu AccountMenu;	
	@FXML
	public MenuItem LogoutSelection;
	@FXML
	public MenuItem LoginSelection;
	@FXML
	public MenuItem AccountDetialSelection;
	@FXML
	public MenuItem AboutSelection;
	@FXML
	public MenuItem CloseSelction;
	@FXML
	public MenuItem DeletSelection;

	@FXML
	public AnchorPane TaskPane; //can be register pane
	
	ObservableList<Project> myProjects;
	public ArrayList<String[]> loginInfo;

	// Reference to the main application.
	public MainApp MainApp;
	public UserLoginDialogController  UserLoginDialogController;
	
	public UserRegisterDialogController UserRegisterDialogController;
	
	public Stage LoginDialogStage;
	public Stage registerDialogStage;
	
//	public final TaskController taskController;
	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */

	public MenuController() { }
	
	public void setMainApp(MainApp MainApp) { this.MainApp = MainApp; }
	
	public MainApp getMainApp() { return this.MainApp; }
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	public void initialize() { }
	
	
	public UserInfoManager getUserInfoManager() { return this.MainApp.getUserInfoManager(); }
	
	@FXML
	public void LogOutSelectionEventHandler(ActionEvent event) {
		if (this.getUserInfoManager().closeAccount()) {
			this.MainApp.getProjectOverviewController().refreshProjectList();
			this.MainApp.getProjectOverviewController().tableSelectedNone();
			try {
				this.MainApp.getProjectOverviewController().getTaskController().refreshTasktList();
				this.MainApp.getProjectOverviewController().getTaskController().tableSelectedNone();			}
			catch (NullPointerException e) {}
		}
		this.MainApp.restart(this.MainApp.getPrimaryStage());
	}
	
	
	
	@FXML
	public void LoginSelectionEventHandler(ActionEvent event) {
		if (this.getUserInfoManager().getLogState()) {			// check if there is an account has been login, show the warning and return.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(null);
			alert.setTitle("Warning!");
			alert.setHeaderText("Cannot signin Now");
			alert.setContentText("There is an account which has already signed in!\nPlease logout this account:\n\t"+this.MainApp.getUserInfoManager().getUserNow()+"\n");
			alert.showAndWait();
			return;
		}
		UserInfo userInfo = new UserInfo();						// create a new user Information class
		boolean LoginClicked = showUserLoginDialog(userInfo);
		if (LoginClicked) {
			this.MainApp.getProjectOverviewController().refreshProjectList();
		}
		
	}
//	LoginSelection.setOnAction(new EventHandler<ActionEvent>());
		
	public boolean showUserLoginDialog(UserInfo userinfo) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LoginDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			this.LoginDialogStage = new Stage();
			this.LoginDialogStage.setTitle("HIVE TAS5K MANAGER REGITER");
			this.LoginDialogStage.initModality(Modality.WINDOW_MODAL);
			this.LoginDialogStage.initOwner(this.MainApp.getPrimaryStage());
//			dialogStage.initOwner(null);
			Scene scene = new Scene(page);
			this.LoginDialogStage.setScene(scene);

			// Set the project into the controller.
			this.UserLoginDialogController = loader.getController();							// probably UserLoginDialogController setting to the temp var
			this.UserLoginDialogController.setMainApp(this.getMainApp());
			this.UserLoginDialogController.setDialogStage(this.LoginDialogStage);


			// Show the dialog and wait until the user closes it
			this.LoginDialogStage.showAndWait();
			return this.UserLoginDialogController.isLoginClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
//	
//	@FXML
//	public void RegisterSelectionEventHandler(ActionEvent event) {
//		try { this.UserLoginDialogController.getDialogStage().close(); }
//		catch (NullPointerException e){ }
////		this.UserLoginDialogController.getDialogStage().close();
//		UserInfo userInfo = new UserInfo();
//		boolean RegisterClicked = showUserRegisterDialog(userInfo);
//
//		if (RegisterClicked) {
//			this.MainApp.getProjectOverviewController().refreshProjectList();
//		}
//	}
////	LoginSelection.setOnAction(new EventHandler<ActionEvent>());
//		
//		
//	public boolean showUserRegisterDialog(UserInfo userinfo) {
//		try {
//			// Load the fxml file and create a new stage for the popup dialog.
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(MainApp.class.getResource("view/RegisterDialog.fxml"));
//			AnchorPane page = (AnchorPane) loader.load();
//
//			// Create the dialog Stage.
//			this.registerDialogStage = new Stage();
//			this.registerDialogStage.setTitle("HIVE TAS5K MANAGER REGITER");
//			this.registerDialogStage.initModality(Modality.WINDOW_MODAL);
//			this.registerDialogStage.initOwner(this.MainApp.getPrimaryStage());
////			dialogStage.initOwner(null);
//			Scene scene = new Scene(page);
//			this.registerDialogStage.setScene(scene);
//
//			// Set the project into the controller.
//			UserRegisterDialogController controller = loader.getController();
//			controller.setMainApp(this.getMainApp());
//			// Show the dialog and wait until the user closes it
//			this.registerDialogStage.showAndWait();
//
//			return controller.isSignUpClicked();
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
	
	public Stage getRegisterDialogStage() {
		return this.registerDialogStage;
	}
	
	public Stage getLoginDialogStage() {
		return this.LoginDialogStage;
	}
	
	
	public void clickLogoutSelection() {
		this.LogoutSelection.fire();
	}
	@FXML
	public void clickLoginSelection() {
		this.LoginSelection.fire();
	}
	
	
	
	
}
	
	
	
	
	
	
	
	