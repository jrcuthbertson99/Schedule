package Project.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Project.model.Project;
//import Project.model.Project;
import Project.model.Task;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TaskController implements Initializable{
	@FXML
	private TableView<Task> taskTable;
	
	@FXML
	private TableColumn<Task, String> TaskNameColume;
	
    @FXML
    private TableColumn<Task, String> TaskStatusColume;
    
	@FXML
	private TableColumn<Task, LocalDate> TaskDueDateColume;


    
//    @FXML
//    private Label TaskNameLabel;
//    @FXML
//    private Label TaskDescriptionLabel;
//    @FXML
//    private Label TaskDueDateLabel;

//    private ProjectOverviewController projectController;
	private Project project;
//    
	// Reference to the main application.
	private ProjectOverviewController projectController;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public TaskController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

	}
	
	@FXML
	void onSend(ActionEvent event) {
		if(project == null) {
			showMessage("please select a project.");
			return;
		}
		
		int index = taskTable.getSelectionModel().getSelectedIndex();
		if(index == -1) {
			showMessage("please select one task");
		}else {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Send");
			dialog.setHeaderText("Send Task");
			dialog.showAndWait();
		}
	}
	
	@FXML
	void onComplete(ActionEvent event) {
		if(project == null) {
			showMessage("please select a project.");
			return;
		}
		
		int index = taskTable.getSelectionModel().getSelectedIndex();
		if(index == -1) {
			showMessage("please select one task");
		}else {
			Task oldTask = project.getTaskList().get(index);

			Task task = new Task(oldTask.getName());
			task.setDueDate(oldTask.getDueDate());
			task.setDescription("Finished");
			project.getTaskList().set(index, task);
			if(project.isAllFinished()) {
				project.setDescription("Finished");
			}
			
			if(project.isAllFinished()) {
				projectController.updateProject(project);
			}
			taskTable.setItems(FXCollections.observableList(project.getTaskList()));
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param Project
	 */
	public void setProject(Project project) {
		this.project = project;
		taskTable.setItems(FXCollections.observableList(this.project.getTaskList()));
	}

	public void setProjectController(ProjectOverviewController projectController) {
		this.projectController = projectController;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Initialize the project table with the two columns.
		TaskNameColume.setCellValueFactory(new PropertyValueFactory<>("name"));
//    	TaskDescriptionColume.setCellValueFactory(
//        		cellData -> cellData.getValue().TaskDescriptionProperty());
		TaskDueDateColume.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		TaskStatusColume.setCellValueFactory(new PropertyValueFactory<>("description"));
//        TaskTable.getSelectionModel().selectedItemProperty().addListener(
//       		 (observable, oldValue, newValue) -> showTasktDetails(newValue));
	}
	
	
	@FXML
	void onNewTask(ActionEvent event) {
		if(project == null) {
			showMessage("please select a project.");
			return;
		}
		
		Stage stage = new Stage();

		TextField tfName = new TextField();
		DatePicker tfDuedate = new DatePicker(LocalDate.now());
		TextField tfDescription = new TextField();
		Button btnOk;

		GridPane gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10));
		gridPane.add(new Label("Name:"), 0, 0);
		gridPane.add(new Label("Due Date:"), 0, 1);
		//gridPane.add(new Label("Description:"), 0, 2);

		gridPane.add(tfName, 1, 0);
		gridPane.add(tfDuedate, 1, 1);
		//gridPane.add(tfDescription, 1, 2);

		btnOk = new Button("Add");

		HBox buttons = new HBox(10, btnOk);
		buttons.setAlignment(Pos.CENTER);

		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(gridPane);
		mainPane.setBottom(buttons);
		mainPane.setPadding(new Insets(10));

		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String name = tfName.getText();
				LocalDate dueDate = tfDuedate.getValue();
				//String description = tfDescription.getText();

				if (name.isEmpty()) {
					showMessage("name cannot be empty.");
				} else {
					Task task = new Task(name);
					task.setDueDate(dueDate);

					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							project.getTaskList().add(task);
							taskTable.setItems(FXCollections.observableList(project.getTaskList()));
						}
					});
					stage.close();
				}
			}
		});

		stage.setScene(new Scene(mainPane));
		stage.showAndWait();

	}

	@FXML
	void onEditTask(ActionEvent event) {
		if(project == null) {
			showMessage("please select a project.");
			return;
		}
		
		int index = taskTable.getSelectionModel().getSelectedIndex();
		if(index == -1) {
			showMessage("please select one task");
			return;
		}
		
		Task task = project.getTaskList().get(index);
		
		Stage stage = new Stage();

		TextField tfName = new TextField(task.getName());
		DatePicker tfDuedate = new DatePicker(task.getDueDate());
		//TextField tfDescription = new TextField(project.getDescription());
		Button btnOk;

		GridPane gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10));
		gridPane.add(new Label("Name:"), 0, 0);
		gridPane.add(new Label("Due Date:"), 0, 1);
		//gridPane.add(new Label("Description:"), 0, 2);

		gridPane.add(tfName, 1, 0);
		gridPane.add(tfDuedate, 1, 1);
		//gridPane.add(tfDescription, 1, 2);

		btnOk = new Button("Confirm");

		HBox buttons = new HBox(10, btnOk);
		buttons.setAlignment(Pos.CENTER);

		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(gridPane);
		mainPane.setBottom(buttons);
		mainPane.setPadding(new Insets(10));

		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String name = tfName.getText();
				LocalDate dueDate = tfDuedate.getValue();
				//String description = tfDescription.getText();

				if (name.isEmpty() ) {
					showMessage("name cannot be empty.");
				} else {
					Task task = new Task(name);
					task.setDueDate(dueDate);

					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							project.getTaskList().set(index, task);
							taskTable.setItems(FXCollections.observableList(project.getTaskList()));
						}
					});
					stage.close();
				}
			}
		});

		stage.setScene(new Scene(mainPane));
		stage.showAndWait();

	}
	

	@FXML
	void onDeleteTask(ActionEvent event) {
		if(project == null) {
			showMessage("please select a project.");
			return;
		}
		
		int index = taskTable.getSelectionModel().getSelectedIndex();
		if(index == -1) {
			showMessage("please select one task");
		}else {
			project.getTaskList().remove(index);
			taskTable.setItems(FXCollections.observableList(project.getTaskList()));
		}
	}
	
	
	void showMessage(String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

}

//    public void setProjectController(ProjectOverviewController projectController) {
//        this.projectController = projectController;
//        ObservableList<Task> TaskList = this.projectController.getTaskData();
//        if (TaskList != null) {
//	        // Add observable list data to the table
//	        TaskTable.setItems(TaskList);
//        }
//        else { 
//        	TaskTable.setItems(null); 
//        	}
//        
//    }
//}
