package views.components;

import controllers.MainController;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CarViewpointView extends HBox {
	Rectangle[] levels;
	
	public CarViewpointView(int height) {
		this.levels = new Rectangle[4];
		for(int i = 0; i < 4; i++) {
			this.levels[i] = new Rectangle();
			this.levels[i].setHeight(height);
			this.levels[i].setWidth(40);
			if(MainController.DEBUGMODE) {
				this.levels[i].setOpacity(0.5);
			} else {
				this.levels[i].setOpacity(0);
			}
		}
		this.levels[0].setFill(Color.RED);
		this.levels[1].setFill(Color.ORANGE);
		this.levels[2].setFill(Color.GREY);
		this.levels[3].setFill(Color.GREEN);
		
//		setBackground(new Background(new BackgroundFill(Color.PURPLE, new CornerRadii(0), Insets.EMPTY)));
		
		getChildren().addAll(this.levels);
	}
	
	public Rectangle getLevel(int level) {
		return this.levels[level];
	}
}
