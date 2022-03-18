package views.components;

import java.util.ArrayList;
import java.util.List;

import controllers.SimulatorController;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.CarAutomaticTask;

public class SimulatorMenu extends MenuBar {
	List<Menu> menus;
	SimulatorController controller;
	boolean automaticOn;
	CarAutomaticTask automaticTask;

	public SimulatorMenu(SimulatorController controller) {
		super();
		
		this.controller = controller;
		this.automaticOn = false;
		menus = new ArrayList<>();
		
		buildCarOptions();
		buildMenu();
	}
	
	@SuppressWarnings("deprecation")
	private void buildCarOptions() {
		Menu carOptions = new Menu("New");
		
		addMenuItem(carOptions, "Car").setOnAction(e -> {
			controller.addCar(1);
		});
		
		addMenuItem(carOptions, "Race Car").setOnAction(e -> {
			controller.addCar(2);
		});
		
		addCheckMenuItem(carOptions, "Automatic").setOnAction(e -> {
			if(this.automaticTask != null && this.automaticTask.isRunning()) {
				this.automaticTask.destroy();
			} else {
				this.automaticTask = new CarAutomaticTask(this.controller);
				this.automaticTask.start();
			}
		});
		
		menus.add(carOptions);
	}
	
	private MenuItem addMenuItem(Menu menu, String text) {
		MenuItem menuItem = new MenuItem(text);
		menu.getItems().add(menuItem);
		return menuItem;
	}
	
	private CheckMenuItem addCheckMenuItem(Menu menu, String text) {
		CheckMenuItem menuItem = new CheckMenuItem(text);
		menu.getItems().add(menuItem);
		return menuItem;
	}
	
	private void buildMenu() {
		for(Menu menu : menus) {
			getMenus().add(menu);
		}
	}
}
