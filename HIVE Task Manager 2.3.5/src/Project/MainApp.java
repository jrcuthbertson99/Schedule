package Project;


import java.io.*;
import java.util.*;
import java.text.*;
import java.time.*;

import Project.model.Project;
import Project.model.Task;
import Project.model.UserInfo;
import Project.model.UserInfoManager;
import Project.view.MenuController;
import Project.view.ProjectEditDialogController;
import Project.view.ProjectOverviewController;
import Project.view.UserLoginDialogController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
    private ArrayList<String[]> loginInfo;
    ObservableList<Project> myProjects;
	private UserInfo userinfo;
	private final UserInfoManager UserInfoManager = new UserInfoManager();;
	
	
    /**
     * The data as an observable list of Project.
     */
//    private ObservableList<Project> projectData = FXCollections.observableArrayList();
	private MenuController MenuController;
	private ProjectOverviewController ProjectOverviewController;
	private ProjectEditDialogController ProjectEditDialogController;
    
    /**
     * Constructor
     */
    public MainApp() {
//    	ObservableList<Task> taskData1 = FXCollections.observableArrayList();
//    	taskData1.add(new Task("Task1"));
//    	taskData1.add(new Task("Task2"));
//    	Project project1 = new Project("Project 1");
//    	project1.setTaskList(taskData1);
//    	projectData.add(project1);
    	
    }
    
    /**
     * Returns the data as an observable list of Projects. 
     * @return
     */
    public ObservableList<Project> getProjectData() {
        return this.UserInfoManager.getUserProjectList();
    }

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HIVE Task Manager");

		initRootLayout();

		this.showProjectOverview();
	}
	
	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			this.MenuController = loader.getController();
			this.MenuController.setMainApp(this);

			
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Shows the project overview inside the root layout.
	 */
	public void showProjectOverview() {
		try {
			// Load project overview.

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ProjectOverview.fxml"));
			
			AnchorPane projectOverview = (AnchorPane) loader.load();
			
			this.ProjectOverviewController = loader.getController();
			this.ProjectOverviewController.setMainApp(this);
	        
//			AnchorPane projectOverview = (AnchorPane) loader.load();

			// Set project overview into the center of root layout.
			rootLayout.setCenter(projectOverview);

	        // Give the controller access to the main app.
	        ProjectOverviewController controller = loader.getController();
	        controller.setMainApp(this);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	/**
//	 * Opens a dialog to edit details for the specified project. If the user
//	 * clicks OK, the changes are saved into the provided project object and true
//	 * is returned.
//	 * 
//	 * @param project the project object to be edited
//	 * @return true if the user clicked OK, false otherwise.
//	 */
//	public boolean showProjectEditDialog(Project project) {
//	    try {
//	        // Load the fxml file and create a new stage for the popup dialog.
//	        FXMLLoader loader = new FXMLLoader();
//	        loader.setLocation(MainApp.class.getResource("view/ProjectEditDialog.fxml"));
//	        AnchorPane page = (AnchorPane) loader.load();
//
//	        // Create the dialog Stage.
//	        Stage dialogStage = new Stage();
//	        dialogStage.setTitle("Edit Project");
//	        dialogStage.initModality(Modality.WINDOW_MODAL);
//	        dialogStage.initOwner(primaryStage);
//	        Scene scene = new Scene(page);
//	        dialogStage.setScene(scene);
//
//	        // Set the project into the controller.
//	        this.ProjectEditDialogController = loader.getController();
//	        this.ProjectEditDialogController.setMainApp(this);
//
//	        // Show the dialog and wait until the user closes it
//	        dialogStage.showAndWait();
//
//	        return this.ProjectEditDialogController.isOkClicked();
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        return false;
//	    }
//	}
	
	public ArrayList<String[]> getUserInfo() {
		return this.loginInfo;
	}
	
	public UserInfoManager getUserInfoManager() {
		return this.UserInfoManager;
	}
	
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public ProjectEditDialogController getProjectEditDialogController() {
		return this.ProjectEditDialogController;
	}
	
	public void setProjectEditDialogController(ProjectEditDialogController ProjectEditDialogController) {
		this.ProjectEditDialogController = ProjectEditDialogController;
	}
	
	public ProjectOverviewController getProjectOverviewController() {
		return this.ProjectOverviewController;
	}
	
	public void setProjectOverviewController() {
		this.ProjectOverviewController = ProjectOverviewController;
	}
	
	
}