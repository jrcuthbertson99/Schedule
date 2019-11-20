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

	public Stage primaryStage;
	public BorderPane rootLayout;
	
	public ArrayList<String[]> loginInfo;
	ObservableList<Project> myProjects;
	public UserInfo userinfo;
	public final UserInfoManager UserInfoManager = new UserInfoManager();;
	
	
	/**
	 * The data as an observable list of Project.
	 */
//	public ObservableList<Project> projectData = FXCollections.observableArrayList();
	public MenuController MenuController;
	public ProjectOverviewController ProjectOverviewController;
	public ProjectEditDialogController ProjectEditDialogController;
	
	/**
	 * Constructor
	 */
	public MainApp() {
	}
	
	void cleanup() {
	    this.primaryStage.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	void startRun(Stage stage) {
		this.primaryStage = stage;
		this.primaryStage.setTitle("HIVE Task Manager");

		initRootLayout();

		this.showProjectOverview();
	}
	
	public void restart(Stage stage) {
	    cleanup();
	    startRun(stage);
	}

	@Override
	public void start(Stage primaryStage) {
		startRun(primaryStage);
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
			this.ProjectOverviewController = loader.getController();		// V 
			this.ProjectOverviewController.setMainApp(this);				// V
			// Set project overview into the center of root layout.			// switch?
			rootLayout.setCenter(projectOverview);							// A

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() { return primaryStage; }
	
	/**
	 * Returns the data as an observable list of Projects. 
	 * @return
	 */
	public ObservableList<Project> getProjectData() { return this.UserInfoManager.getUserProjectList(); }

	public ArrayList<String[]> getUserInfo() { return this.loginInfo; }
	
	public UserInfoManager getUserInfoManager() { return this.UserInfoManager; }

	public ProjectEditDialogController getProjectEditDialogController() { return this.ProjectEditDialogController; }
	
	public void setProjectEditDialogController(ProjectEditDialogController Controller) { this.ProjectEditDialogController = Controller; }
	
	public ProjectOverviewController getProjectOverviewController() {
		return this.ProjectOverviewController;
	}
	
	public void setProjectOverviewController(ProjectOverviewController Controller) { this.ProjectOverviewController = Controller; }
	
	
	
}