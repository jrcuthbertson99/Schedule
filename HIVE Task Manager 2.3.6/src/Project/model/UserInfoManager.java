package Project.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import Project.MainApp;
import Project.view.MenuController;
import Project.view.UserRegisterDialogController;
import Project.view.UserLoginDialogController;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UserInfoManager {
	
	private Stage dialogStage;
	private UserInfo UserInfo;
	
	private MainApp MainApp;
	private UserRegisterDialogController UserRegisterDialogController;
	private UserLoginDialogController UserLoginDialogController;
	private MenuController MenuController;
	
	private ArrayList<UserInfo>  UserList;		// store the loaded user infomation
	private ArrayList<String[]> loginInfo;
	
	private boolean LogState = false;
	private String UserNow = null;
	private ObservableList<Project> UserProjectList = null;
//	private String password;
	
	public UserInfoManager() {
		this.UserNow = null;
		this.loginInfo = new ArrayList<String[]>();
		this.UserInfo = new UserInfo();
		this.UserProjectList = FXCollections.observableArrayList();
		this.LogState = false;
		try{
			File file = new File(System.getProperty("user.dir")+File.separator+"loginInfo.txt");
			if (!file.exists()) {
				 file.createNewFile();
			}
			Scanner li = new Scanner(file);
			int i=0;
			while(li.hasNextLine()){
				String[] currentLogin = new String[2];
				currentLogin = li.nextLine().split(" ");
				loginInfo.add(currentLogin);
				i++;
			}
			li.close();
		}catch(IOException e){
			System.out.print("Error:"+ e);
		}
	}
	
	public boolean AccountPassCheck(String UserName, String PassWord) {
		for(int i=0; i<loginInfo.size();i++){
			if(UserName.equals(loginInfo.get(i)[0])) {
				if (PassWord.equals(loginInfo.get(i)[1])){
					this.UserInfo.setUserName(UserName);;
					loadProjects(UserName);
					this.LogState = true;
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	public boolean checkUserExist(String UserName) {
		for(int i=0; i<loginInfo.size();i++){
			if(UserName.equals(loginInfo.get(i)[0])){
				return true;
			}
		}
		return false;
	}
	
	// loading user's projects
	public UserInfo loadProjects(String user){
		UserInfo userinfo;
		try{
			File file =new File(System.getProperty("user.dir")+File.separator+user+".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			Scanner lp = new Scanner(file);
			int p =-1;
			int t = -1;
			userinfo = new UserInfo();
			ObservableList<Project> myProjects = FXCollections.observableArrayList();
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
					this.UserInfo.setProjectList(myProjects);
				}
			}
			userinfo.setProjectList(myProjects);
			lp.close();
			return userinfo;
		}catch(IOException e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("ERROR!");
			alert.setHeaderText("There are some error happend!\n");
			alert.setContentText("Error:"+ e);
			alert.showAndWait();
		}
	return null;
	}
	
	public boolean setAccount(String UserName, String PassWord) {
		if (this.getLogState()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(dialogStage);
			alert.setTitle("Warning!");
			alert.setHeaderText("Cannot signin Now");
			alert.setContentText("There is an account which has already signed in!\nPlease logout this account:\n\t"+this.getUserNow()+"\n");
			alert.showAndWait();
			return false;
		}
		if (AccountPassCheck(UserName, PassWord)) {
			this.setUserNow(UserName);
			this.UserInfo = loadProjects(UserName);
			this.UserProjectList = this.UserInfo.getUserProjectList();
			this.LogState = true;
			alart(AlertType.INFORMATION, "Login Successful!", "Login Successful!", "Login account: "+UserName+" Successful!");
			return true;
		}
		alart(AlertType.WARNING, "Login Fail!", "Please check your Username and Password", null);
		return false;
		
	}
	
	public boolean closeAccount() {
		if (!this.getLogState()) {
			alart(AlertType.WARNING,"Warning!","Cannot Logout!","There is no account which has already signed in!\nPlease login an account!\n");
			return false;
		}
		
		try{
			File file =new File(System.getProperty("user.dir")+File.separator+this.UserNow+".txt");
			if (!file.exists()) {
   				file.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for(int i=0; i<this.UserInfo.getUserProjectList().size();i++){
				writer.write(this.UserInfo.getUserProjectList().get(i).toStringMeta());
			}
			writer.close();

			
			alart(AlertType.INFORMATION, "Logout", "Account " + this.UserNow + " Logout Seccuessful!", null);
			
		}catch(IOException e){
			alart(AlertType.WARNING, "Logout", "Account " + this.UserNow + " Logout Fail!", "Exception:" + e);
			}

		
		this.UserNow = null;
		this.UserInfo = new UserInfo();
		this.UserProjectList = null;
		this.LogState = false;
		return true;
		
	}
	private void setUserNow(String UserName) { this.UserNow = UserName; }
	
	public UserInfo getUserInfo() { return this.UserInfo; }
	
	
	public void setUserInfo(UserInfo UserInfo) { this.UserInfo = UserInfo; }
		
	
	public String getUserNow() { return this.UserNow; }
	
	public boolean getLogState() { return this.LogState; }
	
	public void setLogState(boolean state) { this.LogState = state; }
	
	
	public boolean addNewAccount(String[] newLoginInfo) {
		if (checkUserExist(newLoginInfo[0])) {
			return false;
		}
		this.loginInfo.add(newLoginInfo);
		
		try{
			File file = new File(System.getProperty("user.dir")+"loginInfo");
			if (!file.exists()) {
   			 file.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+File.separator+"loginInfo.txt", true));
			writer.write(newLoginInfo[0]+" "+newLoginInfo[1]+System.lineSeparator());
			writer.close();
			

			return true;
		}catch(IOException e){
			alart(AlertType.WARNING, "Registed Fail!", "Error:"+ e, null);
		}
		return false;
	}
	
	public ObservableList<Project> getUserProjectList()  {
		return this.UserProjectList; 
	}
	
	// create login account information
	private void recreatedLoginInfo() {
		this.loginInfo = new ArrayList<String[]>();
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
	
	
	
	private void alart(AlertType Type, String Title, String HeaderText, String ContentText) {
		Alert alert = new Alert(Type);
		alert.initOwner(dialogStage);
		if (Title!=null)  alert.setTitle(Title);
		if (HeaderText!=null) alert.setHeaderText(HeaderText);
		if (ContentText!=null) alert.setContentText(ContentText);
		alert.showAndWait();
	}


	
	
	
	

}
