package views;

import java.util.ArrayList;

import controllers.SimulatorController;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import views.components.CarView;
import views.components.Roundabout;
import views.components.SimulatorMenu;

public class SimulatorScene extends Scene {
	private SimulatorController controller;
	private BorderPane root;
	private Roundabout roundabout;
	private ArrayList<CarView> cars;
	
	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;
	
	public SimulatorScene(SimulatorController controller) {
		super(new Pane(), WIDTH, HEIGHT);
		
		this.controller = controller;
		this.cars = new ArrayList<>();
		roundabout = new Roundabout(HEIGHT , WIDTH);
		this.root = new BorderPane();
		
		//Init the root border
		this.root.setTop(new SimulatorMenu(this.controller));
		this.root.setCenter(roundabout);
		this.root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, null)));
		setRoot(this.root);
		
		addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
		    public void handle(KeyEvent ke) {
		        if (ke.getCode() == KeyCode.N) {
		        	controller.addCar(1);
		        } else if (ke.getCode() == KeyCode.R) {
		        	controller.addCar(2);
		        }
		    }
		});
	}
}
