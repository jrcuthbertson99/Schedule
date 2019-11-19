package Project;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("HIVE Task Manager");
		// Load root layout from fxml file.
		FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/RootLayout.fxml"));
		Parent root = loader.load();
		// Show the scene containing the root layout.
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.sizeToScene();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
