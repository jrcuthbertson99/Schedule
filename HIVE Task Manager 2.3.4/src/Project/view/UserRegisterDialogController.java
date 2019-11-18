package Project.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Project.MainApp;
import Project.model.Project;
import Project.model.UserInfo;
import Project.util.DateUtil;

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
    
    public void setMenuController(MenuController MenuController) {
        this.MenuController = MenuController;
    }
    
    public UserRegisterDialogController() {
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
    		for(int i=0; i<loginInfo.size();i++){
    			if(UserNameField.getText().equals(loginInfo.get(i)[0])){
    				
    	            Alert alert = new Alert(AlertType.WARNING);
    	            alert.initOwner(dialogStage);
    	            alert.setTitle("Registed Fail!");
    	            alert.setHeaderText("Sorry, that username has been taken\\n");
//    	            alert.setContentText("");
    	            alert.showAndWait();
    				return;
    			}
    		}
    		String[] newLoginInfo = {UserNameField.getText(),PassWordField.getText()};
    		loginInfo.add(newLoginInfo);
    		try{
    			File file = new File(System.getProperty("user.dir")+"loginInfo");
    			if (!file.exists()) {
       			 file.createNewFile();
    			}
    			BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+File.separator+"loginInfo.txt", true));
    			writer.write(newLoginInfo[0]+" "+newLoginInfo[1]+System.lineSeparator());
    			writer.close();
    			
	            Alert alert = new Alert(AlertType.CONFIRMATION);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Registed Successful!");
	            alert.setHeaderText("Thank you for your signup!\n");
//	            alert.setContentText("");
	            alert.showAndWait();
                SignUpClicked = true;
                dialogStage.close();
                return;
    		}catch(IOException e){
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Registed Fail!");
                alert.setHeaderText("Error:"+ e);
//                alert.setContentText("");
                alert.showAndWait();
    		}
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void RegisterHandleCancel() {
        dialogStage.close();
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
            errorMessage += "No valid Password!\n";   
        } else if (!PassWordField.getText().equals(RePassWordField.getText()) ) {
        	errorMessage += "Password not Matching!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Fail to create account\n");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    public void setLoginInfo(ArrayList<String[]> loginInfo) {
    	this.loginInfo = loginInfo;
    }
    
    public void setUserLoginDialogController(UserLoginDialogController  UserLoginDialogController) {
    	this.UserLoginDialogController =  UserLoginDialogController;
    }
}