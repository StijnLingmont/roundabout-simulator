package model;

import controllers.SimulatorController;
import javafx.scene.layout.BorderPane;
import views.components.CarView;

public class RaceCar extends Car {

	public RaceCar(CarView carView, BorderPane root, SimulatorController controller) {
		super(carView, root, 2, controller);
	}
	
}
