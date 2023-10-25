package lab;



import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import javafx.animation.AnimationTimer;

public class GameController {
	
	private Game game;
	
	private AnimationTimer animationTimer;
	
	@FXML
	private Canvas gameCanvas;
	
	@FXML
	private Button start;
	
	@FXML
	private Button up;
	
	@FXML
	private Button down;
	
	@FXML
	private Button upRight;
	
	@FXML
	private Button downRight;
	
	@FXML
    private TextField leftText;

    @FXML
    private TextField rightText;
	
	public GameController() {
		//this.canvas = canvas;
	}
	
	public void startGame() {
		this.game = new Game(gameCanvas.getWidth(), gameCanvas.getHeight());
		
		animationTimer = new DrawingThread(gameCanvas, game);
		animationTimer.start();
	}
	
	public void stopGame() {
		animationTimer.stop();
	}

	@FXML
	private void startPressed() {
		this.startGame();
	}
	
	
	@FXML
	void movementHandler(ActionEvent event) {
		if(event.getSource() == up) {
			System.out.println("movementHandlGameContrl");
			game.moveUp("left");
			
			//world.control("","up");
		}
		else if(event.getSource() == down) {
			//world.control("","down");
			System.out.println("movementHandlGameContrl");
			game.moveDown("left");
		}
		
	}
	
	@FXML
	void movementHandlerRight(ActionEvent event) {
		if(event.getSource() == upRight) {
			game.moveUp("right");
		}
		else if(event.getSource() == downRight) {
			game.moveDown("right");
		}
		
	}
}
