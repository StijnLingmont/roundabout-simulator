package views.components;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Roundabout extends StackPane {
	
	private static final int roundaboutRadius = 200;
	private int height;
	private int width;
	
	public Roundabout(int height, int width) {
		super();
		
		this.height = height;
		this.width = width;
		
		setRoundabout();
		setRoads();
	}
	
	public void setRoads() {
		ArrayList<Rectangle> roads = new ArrayList<Rectangle>();
		
		for(int i = 0; i < 4; i++) {
			Rectangle road = new Rectangle();
			road.setFill(Color.LIGHTGREY);
			int radius = 160;
			
			if((i % 2 == 0)) {
				road.setHeight(120);
				road.setWidth((width / 2) - radius);
			} else {
				road.setHeight((height / 2) - radius);
				road.setWidth(120);	
			}
			
			roads.add(road);	
		}
		
		setAlignment(roads.get(0), Pos.CENTER_LEFT);
		setAlignment(roads.get(1), Pos.TOP_CENTER);
		setAlignment(roads.get(2), Pos.CENTER_RIGHT);
		setAlignment(roads.get(3), Pos.BOTTOM_CENTER);
		
		
		this.getChildren().addAll(roads);
	}
	
	public void setRoundabout() {
        Circle circle = new Circle();
        circle.setRadius(roundaboutRadius);
        circle.setFill(Color.LIGHTGREY);
        setAlignment(circle, Pos.CENTER);
		this.getChildren().add(circle);
		
		Circle innerCircle = new Circle();
		innerCircle.setRadius(roundaboutRadius * 0.7);
		innerCircle.setFill(Color.DARKGREY);
		setAlignment(innerCircle, Pos.CENTER);
		this.getChildren().add(innerCircle);
	}
}
