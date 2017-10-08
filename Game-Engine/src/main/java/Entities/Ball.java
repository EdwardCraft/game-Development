package Entities;
import java.awt.Graphics;
import java.awt.Rectangle;
import util.Enums.ObjectId;
import FrameWork.GameObject;
import util.Constants;

public class Ball extends GameObject{

	private int yBase;
	private Boolean right;
	private Boolean flag;
	private Boolean tempFlag;
	public Ball(float x, float y , ObjectId id){
		super(x, y, id);
		yBase = 0;
		right = true;
		flag = false;
		tempFlag = true;
	}


	public  void update(double delta){


		if(flag == true){
			right = false;
		}else if(flag == false){
			right = true;
		}

		if(right){
			x += Constants.BALL_VELOCITY;

		}else{
			x -= Constants.BALL_VELOCITY;
		}

		yBase += 2;
		yBase = yBase % Constants.BALL_WAVE_LENGHT;
		if(yBase == (Constants.BALL_WAVE_LENGHT / 2)){
			//yBase = 0;
			flag = true;

		}
		if(yBase == 0){
			flag = false;
		}

		System.out.println(yBase);
		final double normalzed = (double)yBase / (double)Constants.BALL_WAVE_LENGHT;
		final double radians = normalzed * Math.PI * 2;
		final double sine = Math.sin(radians);
		y = (int)(sine * Constants.BALL_WAVE_AMPLITUDE) + (Constants.GAME_HEIGHT / 2);

	}

	public  void render(Graphics g, int gameWidth, int gameHeight){
		g.drawOval( (int)x, (int)y, Constants.BALL_RECTANGLE_WIDTH, Constants.BALL_RECTANGLE_HEIGHT);

	}

	public  Rectangle getBounds(){
		return  null;
	}

	public  Rectangle getBoundsTop(){
		return  null;
	}

	public  Rectangle getBoundsRight(){
		return null;
	}

	public  Rectangle getBoundsLeft(){
		return  null;
	}



}
