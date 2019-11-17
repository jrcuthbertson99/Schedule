package Project;


import java.io.IOException;

import Project.model.Project;
import Project.model.Task;
import Project.view.ProjectEditDialogController;
import Project.view.ProjectOverviewController;
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
			
//			AnchorPane.setTopAnchor(projectOverview , 0.0);
//			AnchorPane.setRightAnchor(projectOverview , 0.0);
//			AnchorPane.setLeftAnchor(projectOverview , 0.0);
//			AnchorPane.setBottomAnchor(projectOverview , 0.0);
//			Scene scene = new Scene(TaskOverview);
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
	 * Opens a dialog to edit details for the specified project. If the user
	 * clicks OK, the changes are saved into the provided project object and true
	 * is returned.
	 * 
	 * @param project the project object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showProjectEditDialog(Project project) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ProjectEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Project");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the project into the controller.
	        ProjectEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setProject(project);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
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