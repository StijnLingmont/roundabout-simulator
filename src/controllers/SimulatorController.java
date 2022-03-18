package controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import model.Car;
import views.SimulatorScene;
import views.components.CarView;

public class SimulatorController {
	
	SimulatorScene scene;
	List<Car> cars;
	int[] chosenPaths;
	
	public SimulatorController() {
		this.scene = new SimulatorScene(this);
		this.cars = new ArrayList<>();
		this.chosenPaths = new int[4];
	}
	
	public Scene loadScene() {
		return this.scene;
	}
	
	public void addCar(int speed) {
		BorderPane root = (BorderPane) this.scene.getRoot();
		CarView carView = new CarView();
		carView.setTranslateX(0);
		carView.setTranslateY(100);
		root.getChildren().add(carView);
		this.cars.add(new Car(carView, root, speed, this));
	}
	
	public List<Car> getCars() {
		return this.cars;
	}
}
