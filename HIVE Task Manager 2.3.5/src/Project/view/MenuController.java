package Project.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Project.MainApp;
import Project.model.Project;
import Project.model.Task;
import Project.model.UserInfo;
import Project.model.UserInfoManager;
import Project.util.DateUtil;


public class MenuController {
    @FXML
    private MenuBar MenuBar;
    @FXML
    private Menu FileMenu;
    @FXML
    private Menu EditMenu;
    @FXML
    private Menu HelpMenu;
    @FXML
    private Menu AccountMenu;    
    @FXML
    private MenuItem LogoutSelection;
    @FXML
    private MenuItem LoginSelection;
    @FXML
    private MenuItem AccountDetialSelection;
    @FXML
    private MenuItem AboutSelection;
    @FXML
    private MenuItem CloseSelction;
    @FXML
    private MenuItem DeletSelection;

    
    
    @FXML
    private AnchorPane TaskPane; //can be register pane
    
    ObservableList<Project> myProjects;
    private ArrayList<String[]> loginInfo;
    
//    private Project tempProject = null;
    // Reference to the main application.
    private MainApp MainApp;
    private UserLoginDialogController  UserLoginDialogController;
    
    private UserRegisterDialogController UserRegisterDialogController;
    
//    private final TaskController taskController;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
//    public MenuController(MainApp MainApp) {
//    	this.MainApp = MainApp;
////    	this.taskController = new TaskController();
//    }
    
    public MenuController() {
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
    
    
	public UserInfoManager getUserInfoManager() {
		return this.MainApp.getUserInfoManager();
	}
	
    @FXML
    public void LogOutSelectionEventHandler(ActionEvent event) {
//    	UserInfo userInfo = new UserInfo();
//    	boolean LoginClicked = showUserLoginDialog(userInfo);
//    	if (LoginClicked) {
    	this.getUserInfoManager().closeAccount();
    	this.MainApp.getProjectOverviewController().refreshProjectList();
    }
    
    
    
    @FXML
    public void LoginSelectionEventHandler(ActionEvent event) {
    	if (this.getUserInfoManager().getLogState()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Warning!");
            alert.setHeaderText("Cannot signin Now");
            alert.setContentText("There is an account which has already signed in!\nPlease logout this account:\n\t"+this.MainApp.getUserInfoManager().getUserNow()+"\n");
            alert.showAndWait();

    	}
    	UserInfo userInfo = new UserInfo();
    	boolean LoginClicked = showUserLoginDialog(userInfo);
    	if (LoginClicked) {
        	this.MainApp.getProjectOverviewController().refreshProjectList();
    	}
    	
    }
//    LoginSelection.setOnAction(new EventHandler<ActionEvent>());
        
        
	public boolean showUserLoginDialog(UserInfo userinfo) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/LoginDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("HIVE TAS5K MANAGER REGITER");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
//	        dialogStage.initOwner(this.MainApp.getPrimaryStage());
	        dialogStage.initOwner(null);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the project into the controller.
	        this.UserLoginDialogController = loader.getController();
	        this.UserLoginDialogController.setMainApp(this.getMainApp());
	        this.UserLoginDialogController.setDialogStage(dialogStage);


	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return this.UserLoginDialogController.isLoginClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	
    @FXML
    public void RegisterSelectionEventHandler(ActionEvent event) {
    	UserInfo userInfo = new UserInfo();
    	boolean LoginClicked = showUserRegisterDialog(userInfo);
    	if (LoginClicked) {
        	this.MainApp.getProjectOverviewController().refreshProjectList();
    	}
    	
    }
//    LoginSelection.setOnAction(new EventHandler<ActionEvent>());
        
        
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
//	        dialogStage.initOwner(this.MainApp.getPrimaryStage());
	        dialogStage.initOwner(null);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the project into the controller.
	        UserRegisterDialogController controller = loader.getController();
	        controller.setMainApp(this.getMainApp());
	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isSignUpClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
    public void recreatedLoginInfo() {
//    	userinfo = new UserInfo();
    	this.myProjects = FXCollections.observableArrayList();
    	this.loginInfo = new ArrayList<String[]>();
//        this.username = username;
//        this.password = password;
    	try{
    		File file = new File(System.getProperty("user.dir")+File.separator+"loginInfo.txt");
    		if (!file.exists()) {
    			 file.createNewFile();
    		}
    		Scanner li = new Scanner(file);
    		int i=0;
    		while(li.hasNext()){
    			String[] currentLogin = new String[2];
    			currentLogin[0]=li.next();
    			currentLogin[1]=li.next();
    			loginInfo.add(currentLogin);
    			i++;
    		}
    		li.close();
    	}catch(IOException e){
    		System.out.print("Error:"+ e);
    	}
    }
    

}
    
    
    
    
    
    
    
    