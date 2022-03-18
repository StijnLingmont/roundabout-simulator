package model;

import java.util.Random;

import controllers.SimulatorController;
import javafx.application.Platform;

public class CarAutomaticTask extends Thread {
	
	SimulatorController controller;
	private boolean isRunning;
	
	public CarAutomaticTask(SimulatorController controller) {
		this.controller = controller;
		this.isRunning = false;
	}
	
	@Override
	public void run() {
		this.isRunning = true;
		while(this.isRunning) {
			int timeout = (new Random()).nextInt(7000-1000) + 1000;
			
			Platform.runLater(
			  () -> {
				  controller.addCar(1);
			  }
			);

			
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void destroy() {
		this.isRunning = false;
	}
	
	public boolean isRunning() {
		return this.isRunning;
	}
}
