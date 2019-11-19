package Project.view;

import java.io.*;
import java.util.*;
import java.text.*;
import java.time.*;

import Project.MainApp;
import Project.model.Project;
import Project.model.Task;
import Project.model.UserInfo;
import Project.model.UserInfoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import Project.model.Task;
//import Project.model.Task;
import Project.util.DateUtil;
import Project.model.Project;

/**
 * Dialog to edit details of a person.
 * 
 * @author Yongsheng Qian
 */
public class UserLoginDialogController {

//    private static final boolean LoginClicked = false;
	@FXML
    private TextField UserNameField;
    @FXML
    private PasswordField PassWordField;


    private Stage dialogStage;
//    private UserInfo userinfo;
    private boolean LoginClicked = false;
    
    private MainApp MainApp;
    private UserRegisterDialogController UserRegisterDialogController;
    private MenuController MenuController;
    
    private UserInfoManager UserInfoManager = new UserInfoManager();
//    ObservableList<Project> myProjects;
//    private ArrayList<String[]> loginInfo;
//    private String username;
//    private String password;
    
//    private boolean hasLogined = false;
    
//    public void setMenuController(MenuController MenuController) {
//        this.MenuController = MenuController;
//        this.UserInfoManager = this.MenuController.getUserInfoManager();
//    }
    
    
//    public UserLoginDialogController(MainApp MainApp) {
//    	this.MainApp = MainApp;
//
//    }
    
	public void setMainApp(MainApp MainApp) { 
		this.MainApp = MainApp;
	}
	
	public MainApp getMainApp() {
		return this.MainApp;
	}
    
//	public UserInfoManager getUserInfoManager() {
//		
//    	if (this.MenuController!=null) {
//    		return 
//    	}
//    	alart(AlertType.WARNING, "Load Menu userinfo fail!", "Please check!", null);
//    	return new UserInfoManager();
//
//	}
    

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
    
    
    public void setUserInfo(UserInfo UserInfo) {
        this.MainApp.getUserInfoManager().setUserInfo(UserInfo);

        UserNameField.setText(UserInfo.getUserName());

    }


  

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isLoginClicked() {
        return LoginClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void LoginHandleLogin() {
//    	UserInfo newUserInfo = new UserInfo();
//    	boolean loginChecked = MainApp.showUserLoginDialog(newUserInfo);
//    	
//    }
    	if (isInputValid()) {
    		String UserName = UserNameField.getText();
        	String PassWord = PassWordField.getText();
        	if (this.MainApp.getUserInfoManager().setAccount(UserName, PassWord)){
        		this.LoginClicked = true;
        		dialogStage.close();
        		return;
        	}
        	
        }
    }
    
    
    @FXML
    public void LoginHandleRegister(ActionEvent event) {
    	UserInfo userInfo = new UserInfo();
    	showUserRegisterDialog(userInfo);
//    	boolean LoginClicked = showUserRegisterDialog(userInfo);
//    	if (LoginClicked) {
//    		this.MainApp.getProjectOverviewController().refreshProjectList();
//    		
//    	}

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
//	        dialogStage.initOwner(this.MenuController.getMainApp().getPrimaryStage());
	        dialogStage.initOwner(null);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        
	        // Set the project into the controller.
	        this.UserRegisterDialogController = loader.getController();
	        this.UserRegisterDialogController.setMainApp(this.getMainApp());
	        this.UserRegisterDialogController.setDialogStage(dialogStage);

	        // Set the project into the controller.
	        
//	        UserRegisterDialogController controller =  loader.getController();
//	        controller.setMainApp(this.getMainApp());

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
    private void LoginHandleCancel() {
        dialogStage.close();
    }
    
//    private void LoginHandleRegister() {
//    	
//    	
//    	UserRegisterDialogController.setLoginInfo(loginInfo);
//    	UserRegisterDialogController.setUserLoginDialogController(this);
//    	
//    	
//    }
//    
//    @FXML
//    private void LoginHandleLogOut() {
//    	UserInfoManager.closeAccount();
//    }
//    	String UserName;
//    	if (!UserInfoManager.getLogState()) {
//            // Show the error message.
//    		alart(AlertType.ERROR, "Logout Failing!","No Account Login!", null);
//            return;
//    	}
//    	if ((UserName = UserInfoManager.getUserNow()) != null) {
//            // Show the error message.
//    		alart(AlertType.ERROR, "Logout Failing!","UserName is Empty!", null);
//            return;
//    	}
//        try{
//            File file =new File(System.getProperty("user.dir")+File.separator+UserName+".txt");
//    		if (!file.exists()) {
//   				file.createNewFile();
//    		}
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            for(int i=0; i<UserInfoManager.getUserInfo().getUserProjectList().size();i++){
//            	writer.write(UserInfoManager.getUserInfo().getUserProjectList().get(i).toStringMeta());
//            }
//            writer.close();
//            UserInfoManager.closeAccount();
//
//            
//            alart(AlertType.INFORMATION, "Logout", "Account " + UserName + " Logout Seccuessful!", null);
//            
//        }catch(IOException e){
//        	alart(AlertType.WARNING, "Logout", "Account " + UserName + " Logout Fail!", "Exception:" + e);
//        	}
//    	
//
//        return;
//
//	}

    public Stage getDialogStage() {
    	return this.dialogStage;
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
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
    
    
    private void alart(AlertType Type, String Title, String HeaderText, String ContentText) {
    	Alert alert = new Alert(Type);
    	alert.initOwner(dialogStage);
    	if (Title!=null)  alert.setTitle(Title);
    	if (HeaderText!=null) alert.setHeaderText(HeaderText);
    	if (ContentText!=null) alert.setContentText(ContentText);
        alert.showAndWait();
    }


    
//    public String getUserName
    
}