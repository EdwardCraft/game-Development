package Entities;
import FrameWork.GameObject;
import util.Enums.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import util.Constants;
import java.lang.Math;
import java.util.Random;
import util.Enums.RectangleBounds;

public class Lines extends GameObject{

	private Ladder ladder;
	private int offset;
	private Random rand = new Random();
	private RectangleBounds bound;
	private GameObject tempLine;
	private int z = 0;

	public Lines(float x, float y, ObjectId id, Ladder ladder, RectangleBounds bound, int offset){
		super(x, y, id);
		this.ladder = ladder;
		this.bound = bound;
		this.offset = offset;
	
	
	}

	


	public void update(double delta){


	 
	  

	}


	public void render(Graphics g,int gameWidth, int gameHeight){

		Graphics2D g2d = (Graphics2D) g; 
		g.setColor(Color.red);
		g2d.draw(getBounds());



	}

	public void collision(){
		int i = 0;
			for(i = 0; i < ladder.getLines().size(); i++){
				for(int j = 0; j < ladder.getLines().size(); j++){
					if(i != j){
						if(ladder.getLines().get(i).getBounds().intersects(ladder.getLines().get(j).getBounds())){
							ladder.getLines().get(i).setY(ladder.getLines().get(i).getY() - 50);
					    }
					}
				}

			}
		

	}


	private int distanceRightMiddle(){

		double xRight = ladder.getBoundsRight().getX();
		double yRight = ladder.getBoundsRight().getY();
		double xMiddle = ladder.getBoundsTop().getX();
		double yMiddle = ladder.getBoundsTop().getY();

		int distance = (int)(Math.sqrt(
			Math.pow(xMiddle - xRight,2) + Math.pow(yMiddle - yRight, 2)));
		

		return distance;
	}

	private int distanceMiddleLeft(){

		double xMiddle = ladder.getBoundsTop().getX();
		double yMiddle = ladder.getBoundsTop().getY();
		double xLeft = ladder.getBoundsLeft().getX();
		double yLeft = ladder.getBoundsLeft().getY();

		int distance = (int)(Math.sqrt(
			Math.pow(xLeft - xMiddle, 2) + Math.pow(yLeft - yMiddle, 2)));
		

		return distance;
	}

	private int selectLocation(RectangleBounds bound){
		if(bound == RectangleBounds.Right){
			return distanceRightMiddle();
		}

		if (bound == RectangleBounds.Left){
			return distanceMiddleLeft();
		}

		return 0;
	}


	private int selectPositionX(RectangleBounds bound){
		if(bound == RectangleBounds.Right){
			return (int)ladder.getBoundsRight().getX();
		}

		if (bound == RectangleBounds.Left){
			return (int)ladder.getBoundsTop().getX();
		}

		return 0;
	}


	private int selectPositionY(RectangleBounds bound){
		if(bound == RectangleBounds.Right){
			return (int)ladder.getBoundsRight().getY();
		}

		if (bound == RectangleBounds.Left){
			return (int)ladder.getBoundsTop().getY();
		}

		return 0;
	}


	public Rectangle getBounds(){
		return new Rectangle(
			(int)x  + selectPositionX(bound) + 15,
			(int)y  + selectPositionY(bound) + offset,
			selectLocation(bound) - 20,
			10
			);
	}
	
	public Rectangle getBoundsTop(){ 
		return null;
	}

	public Rectangle getBoundsRight(){
	 return null;
	}
	public Rectangle getBoundsLeft(){ 
		return null;
	}

	public int getOffset(){ return offset;}

}
