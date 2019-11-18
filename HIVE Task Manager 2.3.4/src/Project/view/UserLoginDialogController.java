package Project.view;

import java.io.*;
import java.util.*;
import java.text.*;
import java.time.*;

import Project.MainApp;
import Project.model.Project;
import Project.model.Task;
import Project.model.UserInfo;
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

    @FXML
    private TextField UserNameField;
    @FXML
    private PasswordField PassWordField;


    private Stage dialogStage;
    private UserInfo userinfo;
    private boolean LoginClicked = false;
    
    private MainApp MainApp;
    private UserRegisterDialogController UserRegisterDialogController;
    private MenuController MenuController;
    

    ObservableList<Project> myProjects;
    private ArrayList<String[]> loginInfo;
    private String username;
    private String password;
    
    private boolean hasLogined = false;
    
    public void setMenuController(MenuController MenuController) {
        this.MenuController = MenuController;
    }
    
    
    public UserLoginDialogController() {
//    	userinfo = new UserInfo();
    	this.myProjects = FXCollections.observableArrayList();
    	this.loginInfo = new ArrayList<String[]>();
    	
    	this.userinfo = new UserInfo();
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
    
    
    public void setUserInfo(UserInfo UserInfo) {
        this.userinfo = UserInfo;

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
        	String userName = UserNameField.getText();
        	String PassWord = PassWordField.getText();
        	this.userinfo.setUserName(username);
        	
            for(int i=0; i<loginInfo.size();i++){
                if(userName.equals(loginInfo.get(i)[0]) && PassWord.equals(loginInfo.get(i)[1])){
                  username=userName;
                  loadProjects(username);
                  this.hasLogined = true;
                  LoginClicked = true;
                  dialogStage.close();
                  return;
                }
//                else{
//                	break;
//                }
             }
        	
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Login Fail!");
            alert.setHeaderText("Please check your Username or/and Password");
//            alert.setContentText("");
            alert.showAndWait();
 

        }
    }
    
    void loadProjects(String user){
 	   try{
 	     File file =new File(System.getProperty("user.dir")+File.separator+username+".txt");
 	     Scanner lp = new Scanner(file);
 	     int p =-1;
 	     int t = -1;
 	     while(lp.hasNextLine()){
 	       String title = lp.nextLine();
 	       if(title.equals("Project")){
 	         p++;
 	         t=-1;
 	         String name = lp.nextLine();
 	         String deadlineDate = lp.nextLine();
 	         String description = lp.nextLine();
 	         String createdDate = lp.nextLine();
 	         String lastUpdatedDate = lp.nextLine();
 	         LocalDate pulledDueDate = LocalDate.parse(deadlineDate);
 	         LocalDateTime pulledCreatedDate = LocalDateTime.parse(createdDate);
 	         LocalDateTime pulledUpdateDate = LocalDateTime.parse(lastUpdatedDate);
 	         Project newProject = new Project(name);
 	         newProject.setProjectDueDate(pulledDueDate);
 	         newProject.setProjectDescription(description);
 	         newProject.setProjectCreatedDateTime(pulledCreatedDate);
 	         newProject.setProjectLastUpdateDateTime(pulledUpdateDate);
 	         myProjects.add(newProject);

 	       }
 	       else if (title.equals("Task")){
 	         t++;
 	         String name = lp.nextLine();
 	         String deadlineDate = lp.nextLine();
 	         String description = lp.nextLine();
 	         String createdDate = lp.nextLine();
 	         String lastUpdatedDate = lp.nextLine();
 	         LocalDate pulledDueDate = LocalDate.parse(deadlineDate);
 	         LocalDateTime pulledCreatedDate = LocalDateTime.parse(createdDate);
 	         LocalDateTime pulledUpdateDate =  LocalDateTime.parse(lastUpdatedDate);
 	         Task newTask  = new Task(name);
 	         newTask.setTaskDueDate(pulledDueDate);
 	         newTask.setTaskDescription(description);
 	         newTask.setTaskCreatedDateTime(pulledCreatedDate);
 	         newTask.setTaskLastUpdateDateTime(pulledUpdateDate);
 	         myProjects.get(p).getProjectTaskList().add(newTask);
 	         this.userinfo.setProjectList(myProjects);

 	       }
 	     }
 	     lp.close();
 	   }catch(IOException e){
 	       System.out.print("Error:"+ e);
 	     }
 	   return;
	}
    
    @FXML
    public void LoginHandleRegister(ActionEvent event) {
    	UserInfo userInfo = new UserInfo();
    	boolean LoginClicked = showUserRegisterDialog(userInfo);
    	if (LoginClicked) {
    		
    	}
    	
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
	        dialogStage.initOwner(this.MenuController.getMainApp().getPrimaryStage());
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the project into the controller.
	        UserRegisterDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setUserInfo(userinfo);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isSignUpClicked();
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
    @FXML
    private void LoginHandleLogOut() {
        try{
            File file =new File(System.getProperty("user.dir")+File.separator+username+".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(int i=0; i<myProjects.size();i++){
            writer.write(myProjects.get(i).toStringMeta());
            }
            writer.close();
        }catch(IOException e){
        	System.out.print("Error:"+ e);
        	}
        username=null;
        return;

	}

    

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (UserNameField.getText() != null && UserNameField.getText().length() != 0) {
        	
        } else {
        	errorMessage += "No valid User Name Name!\n"; 
        }
        
        if (PassWordField.getText() == null || PassWordField.getText().length() == 0) {
            errorMessage += "No valid task Password!\n"; 
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Login Fail!");
            alert.setHeaderText("Please check your Username or/and Password");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    public void loginInfoAddUserInfo(String[] NameAndPass) {
    	this.loginInfo.add(NameAndPass);
    }
    
    private void recreatedLoginInfo() {
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
    
//    public String getUserName
    
}