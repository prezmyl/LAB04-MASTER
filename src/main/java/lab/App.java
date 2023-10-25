package lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *  Class <b>App</b> - extends class Application and it is an entry point of the program
 * @author     Java I
 */
public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	
	private GameController controller;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Construct a main window with a canvas.  
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameView2.fxml"));
			BorderPane root = loader.load();
			
			Scene scene = new Scene(root);
			//Canvas gameCanvas = new Canvas();
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
	
			primaryStage.resizableProperty().set(false);
			primaryStage.setTitle("Java 1 - 5th DU-Pong");
			primaryStage.show();
			
			//this.controller = new GameController(canvas);
			controller = loader.getController();
			//controller.startGame();
			//Exit program when main window is closed
			primaryStage.setOnCloseRequest(this::exitProgram);
	
		
			//Draw scene on a separate thread to avoid blocking UI.
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void exitProgram(WindowEvent evt) {
		System.exit(0);
	}
}