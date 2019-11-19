package Project.view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import Project.model.Project;
import Project.model.User;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RootController implements Initializable {

	private Map<User, ArrayList<Project>> database = new HashMap<>();

	/**
	 * The data as an observable list of Project.
	 */
	private ArrayList<Project> projectData = null;

	@FXML
	private ProjectOverviewController projectOverviewController;

	public RootController() {
		/*
		 * ObservableList<Task> taskData1 = FXCollections.observableArrayList();
		 * taskData1.add(new Task("Task1")); taskData1.add(new Task("Task2")); Project
		 * project1 = new Project("Project 1"); project1.setTaskList(taskData1);
		 * projectData.add(project1); projectData.add(new Project("Project 2"));
		 * projectData.add(new Project("Project 3"));
		 */
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			FileInputStream fis = new FileInputStream("database.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			database = (Map<User, ArrayList<Project>>) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException | IOException e) {

		}

		doLogin();
	}

	void showMessage(String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	@FXML
	void onMenuClose(ActionEvent event) {
		try {
			FileOutputStream fos = new FileOutputStream("database.dat");

			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(database);
			oos.close();

			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuDelete(ActionEvent event) {

	}

	@FXML
	void onMenuHelp(ActionEvent event) {

	}

	private void doLogin() {

		Stage stage = new Stage();

		TextField tfUsername = new TextField();
		TextField tfPassword = new TextField();
		Button btnLogin;
		Button btnRegister;

		GridPane gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10));
		gridPane.add(new Label("Username:"), 0, 0);
		gridPane.add(new Label("Password:"), 0, 1);
		gridPane.add(tfUsername, 1, 0);
		gridPane.add(tfPassword, 1, 1);

		btnLogin = new Button("Login");
		btnRegister = new Button("Register");

		HBox buttons = new HBox(10, btnLogin, btnRegister);
		buttons.setAlignment(Pos.CENTER);

		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(gridPane);
		mainPane.setBottom(buttons);
		mainPane.setPadding(new Insets(10));

		btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String username = tfUsername.getText();
				String password = tfPassword.getText();

				for (User user : database.keySet()) {
					if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
						projectData = database.get(user);
						break;
					}
				}

				if (projectData == null) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText(null);
					alert.setContentText("login failed");
					alert.showAndWait();
				}else {
					stage.close();
				}
			}
		});

		btnRegister.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String username = tfUsername.getText();
				String password = tfPassword.getText();

				if (username.isEmpty() || password.isEmpty()) {
					showMessage("input is empty.");
					return;
				}

				User user = new User(username, password);
				if (database.keySet().contains(user)) {
					showMessage("the username already exists.");
				} else {
					projectData = new ArrayList<>();
					database.put(user, projectData);
					stage.close();
				}
			}
		});

		stage.setScene(new Scene(mainPane));
		stage.showAndWait();

		if (projectData == null) {
			System.exit(0);
		} else {
			projectOverviewController.setProjectList(projectData);
		}
	}
}
