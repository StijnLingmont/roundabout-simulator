package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import controllers.SimulatorController;
import enums.CarStatus;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import views.SimulatorScene;
import views.components.CarView;

public class Car {
	private CarView carView;
	private CarViewTask carViewTask;
	private CarRoute route;
	private int startRoad;
	private int endRoad;
	private SimulatorController controller;
	private CarStatus carStatus;
	private BackgroundFill backgroundColor;
	
	public Car(CarView carView, BorderPane root, int speed, SimulatorController controller) {
		this.carView = carView;
		this.carViewTask = new CarViewTask(this, controller);
		this.controller = controller;
		this.carStatus = CarStatus.GOINGTO;
		this.backgroundColor = new BackgroundFill(Color.BLACK, new CornerRadii(0), Insets.EMPTY);
		setRoads();
		
		this.route = new CarRoute(this, root, SimulatorScene.WIDTH, SimulatorScene.HEIGHT, startRoad, endRoad, speed);
		this.route.run();
		this.carViewTask.start();
	}
	
	private Background getBackground() {
		return new Background(this.backgroundColor);
	}
	
	private void setRoads() {
		List<Integer> usedRoads = new ArrayList<Integer>();
		for(Car filterCar : controller.getCars()) {
			if(filterCar.getCarStatus() == CarStatus.GOINGTO) {
				if(!usedRoads.contains(filterCar.getStartRoad())) {
					usedRoads.add(filterCar.getStartRoad());	
				}
			}
			
			if(usedRoads.size() >= 4) {
				usedRoads.remove(0);
			}
		}
		
		boolean correctRoad = false;
		int startRoad = 0;
		while(correctRoad == false) {
			startRoad = ThreadLocalRandom.current().nextInt(1, 4 + 1);
			if(!usedRoads.contains(startRoad)) {
				correctRoad = true;
			}
		}
		
		this.startRoad = startRoad;
		this.endRoad = ThreadLocalRandom.current().nextInt(1, 4 + 1);
	}
	
	/*
	 * Check if a car sees another car and if so return on which level he sees it.
	 */
	public int isSeeing(CarView otherCar) {
		Bounds otherCarBound = otherCar.localToScene(otherCar.getCarObject().getBoundsInParent());
		for(int i = 0; i < 4; i++) {
			Bounds carBound = this.carView.localToScene(this.carView.getViewRange(i).getBoundsInParent());
			if(carBound.intersects(otherCarBound)) {
				return i - 1;
			}
		}
		
		return 4;
	}
	
	public CarView getCarView() {
		return this.carView;
	}
	
	public CarRoute getRoute() {
		return this.route;
	}
	
	public List<Car> getCars() {
		return this.controller.getCars();
	}
	
	public void setCarstatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}
	
	public CarStatus getCarStatus() {
		return this.carStatus;
	}
	
	public int getStartRoad() {
		return this.startRoad;
	}
	
	public void destroy() {
		this.carViewTask.stop();
    	getCars().remove(this);
	}
}
