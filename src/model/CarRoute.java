package model;

import controllers.MainController;
import enums.CarStatus;
import javafx.animation.Animation.Status;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.PauseTransition;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import views.components.CarView;

public class CarRoute {
	
	CarView carView;
	Car car;
	BorderPane root;
	int width;
	int height;
	int startRoad;
	int endRoad;
	int speed;
	PathTransition ptMove;
	Path line;
	
	public CarRoute(Car car, BorderPane root, int width, int height, int startRoad, int endRoad, int speed) {
		this.carView = car.getCarView();
		this.root = root;
		this.width = width;
		this.height = height;
		this.startRoad = startRoad;
		this.endRoad = endRoad;
		this.car = car;
		this.speed = speed;
		ptMove = new PathTransition();
		line = new Path();
	}
	
	public void run() {
		getCarPosition();
		
		goToRoundabout();
		drawCirclePoints(startRoad, endRoad);
		
		ptMove.setNode(this.carView);
        ptMove.setCycleCount(1);
        ptMove.setDuration(Duration.millis(30000 / this.speed));
        ptMove.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT); 
        ptMove.play();
        ptMove.setOnFinished(e -> {
        	this.car.destroy();
        	this.root.getChildren().remove(this.carView);
    		if(MainController.DEBUGMODE) {
            	this.root.getChildren().remove(this.line);
    		} 
        });
	}
	

	private void drawCirclePoints (int startRoad, int exit) {
		double xAs = 0;
		double yAs = 0;
		int points = 22;
		int radius = 50;
		double slice = 2 * Math.PI / points;
		
		//Get start position roundabout
		if((startRoad % 2) == 0) {
			xAs = getBeginRoundabout();
			yAs = getRoundAboutLength(startRoad);
		} else {
			xAs = getRoundAboutLength(startRoad);
			yAs = getBeginRoundabout();
		}
		
		//Calculate how big the circle must be
		int point = exit - startRoad;
		if(point <= 0) {
			point = point + 4;
		}
		double percentage = (point * 0.25);
		double skipRounds = points * ((startRoad - 1) * 0.25);
		double amountRound = points * percentage;
		
        //Roundabout
		for(double i = skipRounds; i < amountRound + skipRounds; i++) {
			double angle = slice * i;
			double newX = (radius * Math.cos(angle)) + xAs;
			double newy = (radius * Math.sin(angle)) + yAs;

	        line.setStroke(Color.DARKBLUE);
	        
	        CubicCurveTo move = new CubicCurveTo(newX, newy, newX, newy, newX, newy);
	        
	        xAs = newX;
	        yAs = newy;
	        
	        if(i == skipRounds || i >= (amountRound + skipRounds) - 1) {
				continue;
			}
	        line.getElements().add(move);
	    }
		
		setEndRoad(xAs, yAs);
		
		//Init animation
		if(MainController.DEBUGMODE) {
	        root.getChildren().add(line);
		} 

        ptMove.setPath(line);
	}
	
	private void goToRoundabout() {
        line.getElements().add(new MoveTo(carView.getTranslateX(), carView.getTranslateY()));
        
        if(startRoad == 2 || startRoad == 4) {
        	line.getElements().add(new CubicCurveTo(carView.getTranslateX(), carView.getTranslateY(), getBeginRoundabout(), carView.getTranslateY(), getBeginRoundabout(), carView.getTranslateY()));	
        } else {
        	line.getElements().add(new CubicCurveTo(carView.getTranslateX(), carView.getTranslateY(), carView.getTranslateX(), getBeginRoundabout(), carView.getTranslateX(), getBeginRoundabout()));
        }
	}
	
	private int getBeginRoundabout() {
		int center = 0;
		
		if((startRoad % 2) == 0) {
			center = (width / 2);	
		} else {
			center = (height / 2);	
		}
		
		switch(startRoad) {
			case 1:
				center = center - 160;
				break;
			case 2:
				center = center + 175;
				break;
			case 3:
				center = center + 190;
				break;
			case 4:
				center = center - 175;
				break;
		}
		
		return center;
	}
	
	private int getRoundAboutLength(int road) {
		int number = 0;
		int adding = 25;
		
		switch(road) {
			case 1:
				number = ((width / 2) - 25);
				break;
			case 2:
				number = ((height / 2) - 10);
				break;
			case 3:
				number = ((width / 2) + 25);
				break;
			case 4:
				number = ((height / 2) + 40);
				break;
			default:
				number = 0;
		}
		
		return number;
	}

	private void getCarPosition() {
		int widthCenter = width / 2;
		int heightCenter = height / 2;
		
		switch(startRoad) {
			case 1:
				carView.setTranslateY(0);
				carView.setTranslateX(heightCenter + 30);
				break;
			case 2:
				carView.setTranslateY(heightCenter + 50);
				carView.setTranslateX(width);
				break;
			case 3:
				carView.setTranslateY(height);
				carView.setTranslateX(widthCenter - 30);
				break;
			case 4:
				carView.setTranslateY(heightCenter - 25);
				carView.setTranslateX(0);
				break;
			default:
				System.out.println("The number is not between 1 and 4!");
		}
	}
	
	private int getLeaveRoad() {
		int number = 0;
		
		switch(endRoad) {
			case 1:
				number = ((width / 2) - 25);
				break;
			case 2:
				number = ((height / 2) - 25);
				break;
			case 3:
				number = ((width / 2) + 30);
				break;
			case 4:
				number = ((height / 2) + 50);
				break;
			default:
				number = 0;
		}
		
		return number;
	}
	
	private void setEndRoad(double xAs, double yAs) {
		int endPosition = getLeaveRoad();
		int newPos = 0;
		
		if(endRoad == 2 || endRoad == 3) {
			newPos = (endRoad % 2) == 0 ? width : height;
		}
			
		if((endRoad % 2) == 0) {
			line.getElements().add(new CubicCurveTo(xAs, endPosition,xAs, endPosition,xAs, endPosition));
			line.getElements().add(new CubicCurveTo(xAs, endPosition,xAs, endPosition,newPos, endPosition));
		} else {
			line.getElements().add(new CubicCurveTo(endPosition, yAs,endPosition, yAs,endPosition, yAs));
			line.getElements().add(new CubicCurveTo(endPosition, yAs,endPosition, yAs,endPosition, newPos));	
		}
	}
	
	public void changeSpeed(double rate) {
		if(rate == 0) {
			this.ptMove.pause();
		} else {
			this.ptMove.play();
			this.ptMove.setRate(rate);
		}
	}
}
