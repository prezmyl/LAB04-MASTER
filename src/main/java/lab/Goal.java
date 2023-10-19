package lab;


public class Goal {
	
	//Score score1, score2;
	private Ball ball;
	private Rectangle goalRect;
	
	public Goal(Ball ball, Rectangle goalRect) {
		this.ball = ball;
		this.goalRect = goalRect;
	}
	
	public Boolean detection() {
		return (ball.getBoundingBox().intersects(goalRect.getBoundingBox()));
	}
	
	
}
