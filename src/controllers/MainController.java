package controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class MainController extends Application {
	public static final boolean DEBUGMODE = true;

	public void startup(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		SimulatorController mySceneController = new SimulatorController();
		stage.setTitle("Car Simulator");
		stage.setScene(mySceneController.loadScene());
		stage.setResizable(false);
		stage.show();
		stage.setOnCloseRequest(e -> System.exit(0));
	}

}
