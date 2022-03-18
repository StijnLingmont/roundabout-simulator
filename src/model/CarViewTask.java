package model;

import com.sun.javafx.geom.Point2D;

import controllers.SimulatorController;

public class CarViewTask extends Thread {
	
	Car car;
	SimulatorController controller;
	double carX;
	double carY;
	boolean isRunning;

	public CarViewTask(Car car, SimulatorController controller) {
		this.car = car;
		this.controller = controller;
		this.isRunning = true;
	}

	@Override
	public void run() {
		while(this.isRunning) {
			int lowestLevel = 4;
			double speed = 1;
			
			for(Car otherCar : this.controller.getCars()) {
				if(car.equals(otherCar)) {
					continue;
				}
				
				lowestLevel = this.car.isSeeing(otherCar.getCarView());
				
				if(lowestLevel != 4) {
					break;
				}
			}

			speed = (0.25 * lowestLevel);
			this.car.getRoute().changeSpeed(speed);

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
