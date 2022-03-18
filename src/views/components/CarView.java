package views.components;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CarView extends BorderPane {
	private CarViewpointView viewRange;
	private Rectangle carObject;
	private static final int CARWIDTH = 60;
	private static final int CARHEIGHT = 30;
	private static final int VIEWRANGE = 3;
	
	public CarView() {
		this.carObject = new Rectangle();
		this.carObject.setHeight(CARHEIGHT);
		this.carObject.setWidth(CARWIDTH);
		
		this.viewRange = new CarViewpointView(CARHEIGHT);
		
		setCenter(this.carObject);
		setRight(this.viewRange);

		setBackground(getBackground());
		
		setHeight(CARHEIGHT);
		setWidth(CARWIDTH);
	}
	
	public Rectangle getCarObject() {
		return this.carObject;
	}
	
	public CarViewpointView getViewRange() {
		return this.viewRange;
	}
	
	public Rectangle getViewRange(int index) {
		return (Rectangle)this.viewRange.getLevel(index);
	}
}
