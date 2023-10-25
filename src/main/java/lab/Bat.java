package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Bat implements DrawableSimulable, Collisionable{
		
	private final Game game;
	private final double sizeY;
	private final double sizeX;
	private final double lineWidth = 20;
	private final String side;
	
	private Point2D position;
	private Point2D velocity;
	//private Point2D acceleration;

	
	public Bat(Game game, Point2D position, Point2D velocity, double sizeX, double sizeY, String side) {
		this.position = position;
		this.velocity = velocity;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.game = game;
		this.side = side;
		
	
	}
	
	public void draw(GraphicsContext gc) {
		gc.save();
		gc.setFill(Color.GRAY);
		Point2D canvasPosition = game.transform2Canvas(position, sizeY);
		gc.fillRect(canvasPosition.getX(), canvasPosition.getY(), sizeX, sizeY);
		gc.restore();
	}
	
	public double GetBatCenterY() {
		return getBoundingBox().getMinY() + getBoundingBox().getHeight()/2;
	}
	
	public double GetBatCenterX() {
		return getBoundingBox().getMinX() + getBoundingBox().getWidth()/2;
	}
	
	
	public void simulate(double timeStep) {
		position = position.add(velocity.multiply(timeStep));	
	    
	    if (position.getY() - lineWidth < 0 || position.getY() + sizeY > (game.getHeight() - lineWidth)) {
			this.velocity = velocity.multiply(-1);
		}
	    
	   // Point2D newPosition = new Point2D(this.getPositionX(), this.getPositionY() + this.getSpeedY());
		//newPosition = newPosition.add(0, this.getSpeedY());
		//position = newPosition;
	}
	
	public void updatePosition(Point2D newPosition) {
		this.position = newPosition;
	}
	
	public Rectangle2D getBoundingBox() {
		return new Rectangle2D(position.getX(), position.getY(), sizeX, sizeY);
	}
	
	public void refreshDown() {
		this.updatePosition(new Point2D(getPositionX(), lineWidth));
	}
	
	public void refreshUp() {
		this.updatePosition(new Point2D(getPositionX(), game.getHeight() - sizeY - lineWidth));
	}

	public void control(String input) {
		
		
		if(input.equals("up")) {
			System.out.println("controlBatUP");
			updatePosition(new Point2D(this.getPositionX(), this.getPositionY() + 20));
		}
		
		if(position.getY() + sizeY > (game.getHeight() - lineWidth)){
			refreshUp();
			return;
		}
		
		System.out.println("position" + position.getY());
		if (position.getY() < 2*lineWidth) {
			refreshDown();
			return;
		}
		
		if (input.equals("down")) {
			System.out.println("controlBatDOWN");
			updatePosition(new Point2D(this.getPositionX(), this.getPositionY() - 20)); 
		}
	}
	

	public double getSpeedY() {
		return this.velocity.getY();
	}
	
	public double getPositionY() {
		return this.position.getY();
	}

	public double getPositionX() {
		return this.position.getX();
	}
	
	public String getSide() {
		return this.side;
	}
	
}
