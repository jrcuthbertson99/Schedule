package Project;


import java.io.IOException;

import Project.model.Project;
import Project.model.Task;
import Project.view.ProjectOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
    /**
     * The data as an observable list of Project.
     */
    private ObservableList<Project> projectData = FXCollections.observableArrayList();
    
    /**
     * Constructor
     */
    public MainApp() {
    	ObservableList<Task> taskData1 = FXCollections.observableArrayList();
    	taskData1.add(new Task("Task1"));
    	taskData1.add(new Task("Task2"));
    	Project project1 = new Project("Project 1");
    	project1.setTaskList(taskData1);
    	projectData.add(project1);
    	projectData.add(new Project("Project 2"));
    	projectData.add(new Project("Project 3"));
    	projectData.add(new Project("Project 4"));
    	projectData.add(new Project("Project 5"));
    	projectData.add(new Project("Project 6"));
    	projectData.add(new Project("Project 7"));
    	projectData.add(new Project("Project 8"));
    	projectData.add(new Project("Project 9"));
    	projectData.add(new Project("Project 10"));
    	projectData.add(new Project("Project 11"));
    	projectData.add(new Project("Project 12"));
    	
    }
    


    
    /**
     * Returns the data as an observable list of Projects. 
     * @return
     */
    public ObservableList<Project> getProjectData() {
        return projectData;
    }

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HIVE Task Manager");

		initRootLayout();

		showProjectOverview();
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
			
			// Set project overview into the center of root layout.
			rootLayout.setCenter(projectOverview);
			
	        // Give the controller access to the main app.
	        ProjectOverviewController controller = loader.getController();
	        controller.setMainApp(this);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
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
}