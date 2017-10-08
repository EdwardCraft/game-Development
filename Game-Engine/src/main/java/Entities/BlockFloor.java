package Entities;
import FrameWork.GameObject;
import util.Enums.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import util.Constants;


public class BlockFloor extends GameObject{

	private int gameWidth;
	private int gameHeight;
	

	public BlockFloor(float x, float y, ObjectId id){
		super(x, y, id);

	}



	public void update(double delta){
	



	}



	public void render(Graphics g,int gameWidth, int gameHeight){
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		Graphics2D g2d = (Graphics2D) g; 
		g.setColor(Color.yellow);
		g2d.draw(getBounds());
	}



	public Rectangle getBounds(){ 
		return new Rectangle(
			(int)x,
			(int)y ,
			Constants.GAME_WIDTH,
			10
			);
	}

	public Rectangle getBoundsTop(){ return null;}
	public Rectangle getBoundsRight(){return null;}
	public Rectangle getBoundsLeft(){return null;}





}