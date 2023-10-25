package lab;

import java.security.spec.DSAGenParameterSpec;
import java.util.Iterator;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.geometry.Rectangle2D;



public class Game {

	private final double width;
	private final double height;
	private final BackgroundObjects backgrObjects;
	private final double lineWidth = 20;
	private final double sideLineWidth = 1;
	private final double gap = 40;

	private final double sizeBatX = 20;
	private final double sizeBatY = 100;
	private double sizeBall = 20;
	private double actualTime;
	private DrawAble[] entities;

	private final Goal goal1, goal2;
	private final Score score1, score2;

	
	
	
	public Game(double width, double height) {
		
		this.width = width;
		this.height = height;
		this.backgrObjects = new BackgroundObjects(this);
		this.entities = new DrawAble[] {
				new Ball(this, new Point2D(width/2 - sizeBall/2 ,height/2), new Point2D(6.41,0), sizeBall),
				new Bat(this, new Point2D(gap, lineWidth), new Point2D(0,0/*4.7*/), sizeBatX, sizeBatY, "left"),
				new Bat(this, new Point2D(width - (gap + sizeBatX), height - (sizeBatY + lineWidth)), new Point2D(0,0 /*-4.5*/), sizeBatX, sizeBatY, "right"),
				new Rectangle(this, new Point2D(0,0), sideLineWidth, this.getHeight(), "leftLine", Color.BLACK), //leftLine 
				new Rectangle(this, new Point2D(this.getWidth() - sideLineWidth,0), sideLineWidth, this.getHeight(), "rightLine", Color.BLACK),		//rightLine 
				new Rectangle(this, new Point2D(0, this.getHeight() - lineWidth), this.getWidth(), lineWidth, "upperLine"),//upperLine
				new Rectangle(this, new Point2D(0, 0), this.getWidth(), lineWidth, "bottomLine")  //bottomtLine
		};		
		
		this.score1 = new Score(this, new Point2D(width/2 - 4 * gap, height - 2.5 * gap), 0);
		this.score2 = new Score(this, new Point2D(width/2 +  2 * gap, height - 2.5 * gap), 0);
		Ball ball = (Ball) entities[0];
		this.goal1 = new Goal(ball, (Rectangle)entities[4] );
		this.goal2 = new Goal(ball, (Rectangle)entities[3] );
		
	}

	public void draw(GraphicsContext gc) {
		gc.clearRect(0, 0, width, height);
		
		this.backgrObjects.draw(gc);
		this.score1.renderScore(gc);
		this.score2.renderScore(gc);
		for (DrawAble entityDrAb : entities) {
			entityDrAb.draw(gc);
		}
	}
	
	public void simulate(double timeDelta) { //probehne cela kazdy snimek
		
		for (DrawAble drawAble : entities) {
			if (drawAble instanceof DrawableSimulable drawAbSimAble) {
				drawAbSimAble.simulate(timeDelta);
			}
				
			if (drawAble instanceof Ball ball) {  
				for (DrawAble drawAble2 : entities) {
					if (drawAble2 instanceof Collisionable ce) {
						ball.collision(ce);
					}
				}
				
			}
		}
		
		if (goal1.detection()) {
			score1.addScore();
		}
		
		if (goal2.detection()) {
			score2.addScore();
		}
		//this.control("up", "up");
			
	}	
		
	
	public Point2D transform2Canvas(Point2D worldPoint, double entityHieght) {
		return new Point2D(worldPoint.getX(), height - worldPoint.getY() - entityHieght);
	}
	
	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
	
	public void moveUp(String side){
		for(DrawAble drAble : entities) {
			if (drAble instanceof Bat bat && bat.getSide() == side) {
				System.out.println("moveUpGame");
				/*if (bat.getPositionY() < lineWidth) {
					bat.updatePosition(new Point2D(bat.getPositionX(),bat.getPositionY() + 10));
				}
				else {*/
				bat.control("up");
				//}
			}

		}
	}
	
	public void moveDown(String side){
		for(DrawAble drAble : entities) {
			if (drAble instanceof Bat bat && bat.getSide() == side) {
				System.out.println("moveDownGame");
				bat.control("down");
			}

		}
	}
		
}
		
			



