package Project.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

import Project.model.Project;
import Project.util.DateUtil;
import Project.view.TaskController;

public class ProjectOverviewController {
	@FXML
	private TableView<Project> projectTable;
	@FXML
	private TableColumn<Project, String> projectNameColume;
	@FXML
	private TableColumn<Project, String> projectDescriptionColume;
	@FXML
	private TableColumn<Project, LocalDate> projectDueDateColume;

	@FXML
	private Label projectNameLabel;
	@FXML
	private Label projectDescriptionLabel;
	@FXML
	private Label projectDueDateLabel;

	@FXML
	private TaskController taskController;

	ArrayList<Project> projectList;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public ProjectOverviewController() {

	}

	@FXML
	void onSend(ActionEvent event) {
		int index = projectTable.getSelectionModel().getSelectedIndex();
		if(index == -1) {
			showMessage("please select one project");
		}else {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Send");
			dialog.setHeaderText("Send Project");
			dialog.showAndWait();
		}
	}
	
	@FXML
	void onNewProject(ActionEvent event) {

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
		gridPane.add(new Label("Description:"), 0, 2);

		gridPane.add(tfName, 1, 0);
		gridPane.add(tfDuedate, 1, 1);
		gridPane.add(tfDescription, 1, 2);

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
				String description = tfDescription.getText();

				if (name.isEmpty() || description.isEmpty()) {
					showMessage("name and description cannot be empty.");
				} else {
					Project project = new Project(name);
					project.setDescription(description);
					project.setDueDate(dueDate);

					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							projectList.add(project);
							projectTable.setItems(FXCollections.observableList(projectList));
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
	void onEditProject(ActionEvent event) {
		int index = projectTable.getSelectionModel().getSelectedIndex();
		if(index == -1) {
			showMessage("please select one project");
			return;
		}
		
		Project project = projectList.get(index);
		
		Stage stage = new Stage();

		TextField tfName = new TextField(project.getName());
		DatePicker tfDuedate = new DatePicker(project.getDueDate());
		TextField tfDescription = new TextField(project.getDescription());
		Button btnOk;

		GridPane gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10));
		gridPane.add(new Label("Name:"), 0, 0);
		gridPane.add(new Label("Due Date:"), 0, 1);
		gridPane.add(new Label("Description:"), 0, 2);

		gridPane.add(tfName, 1, 0);
		gridPane.add(tfDuedate, 1, 1);
		gridPane.add(tfDescription, 1, 2);

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
				String description = tfDescription.getText();

				if (name.isEmpty() || description.isEmpty()) {
					showMessage("name and description cannot be empty.");
				} else {
					Project project = new Project(name);
					project.setDescription(description);
					project.setDueDate(dueDate);

					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							projectList.set(index, project);
							projectTable.setItems(FXCollections.observableList(projectList));
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
	void onDeleteProject(ActionEvent event) {
		int index = projectTable.getSelectionModel().getSelectedIndex();
		if(index == -1) {
			showMessage("please select one project");
		}else {
			projectList.remove(index);
			projectTable.setItems(FXCollections.observableList(projectList));
		}
	}
	
	
	void showMessage(String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the project table with the two columns.
		projectNameColume.setCellValueFactory(new PropertyValueFactory<>("name"));
		projectDescriptionColume.setCellValueFactory(new PropertyValueFactory<>("description"));
		projectDueDateColume.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

		showProjectDetails(null);

		projectTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showProjectDetails(newValue));

		taskController.setProjectController(this);
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param MainApp
	 */
	public void setProjectList(ArrayList<Project> projectList) {
		this.projectList = projectList;
		// Add observable list data to the table
		projectTable.setItems(FXCollections.observableList(projectList));
		if(projectList.size() > 0) {
			projectTable.getSelectionModel().select(0);
		}
	}

	/**
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param person the person or null
	 */
	private void showProjectDetails(Project project) {
		if (project != null) {
			// Fill the labels with info from the project object.
			projectNameLabel.setText(project.getName());
//            projectDueDateLabel.setText(project.getProjectDueDate().toString());
			projectDescriptionLabel.setText(project.getDescription());
//            postalCodeLabel.setText(Integer.toString(project.getPostalCode()));
			projectDueDateLabel.setText(DateUtil.format(project.getDueDate()));

//            BorderPane rootLayout =  this.MainApp.getRootLayout();
//            rootLayout
//            
			taskController.setProject(project);

		} else {
			// Person is null, remove all the text.
			projectNameLabel.setText("");
			projectDescriptionLabel.setText("");
			projectDueDateLabel.setText("");

		}
	}

	public void updateProject(Project project) {
		int index = projectList.indexOf(project);
		
		Project newProject = new Project(project.getName());
		newProject.setDueDate(project.getDueDate());
		newProject.setDescription(project.getDescription());
		newProject.setTaskList(project.getTaskList());
		
		projectList.set(index, newProject);
		projectTable.setItems(FXCollections.observableList(projectList));
		showProjectDetails(newProject);
	}

}
