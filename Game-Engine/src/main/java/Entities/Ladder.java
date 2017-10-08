package Entities;
import FrameWork.GameObject;
import util.Enums.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import util.Constants;
import Entities.Lines;
import util.Enums.RectangleBounds;
import java.util.LinkedList;
import java.util.Random;

public class Ladder extends GameObject{

	private LinkedList<Lines> lines;
	private GameObject tempLines;
	private Random rand = new Random();
	private int randSelection;
	private int temp;
	private int offset;
	private int[] offsetArray;

	public Ladder(float x, float y, ObjectId id){
		super(x, y, id);
		lines = new LinkedList<Lines>();
		offsetArray = new int[10];
        offset = 0;
        randSelection = 0;
        temp = 0;
        createLines();
	}




	public void update(double delta){
		

		if(lines != null){
			for(int i = 0; i < lines.size(); i++){
				lines.get(i).update(delta);
			}
		}

		

	}

	public void render(Graphics g,int gameWidth, int gameHeight){
		Graphics2D g2d = (Graphics2D) g; 
		g.setColor(Color.white);
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());

		if(lines != null){
			for(int i = 0; i < lines.size(); i++){
				lines.get(i).render(g, gameWidth, gameHeight);
			}
		}


	}


	public void createLines(){

		for(int i = 0; i < 6; i++ ){
			temp = reLocation(randomLocation(), 0);
			offsetArray[i] = temp;
			randSelection = rand.nextInt( 2 ) + 1;
			if(randSelection == 1){
				addObject(
				new Lines(
					0, 0,
					ObjectId.Lines, this,
					RectangleBounds.Right, temp)
				);
			}else{
				addObject(
				new Lines(
					0, 0,
					ObjectId.Lines, this,
					RectangleBounds.Left, temp)
				);
			}

		}

	}


	public void addObject(Lines object){ 
		lines.add(object); 
	}

	public void removeObject(Lines object){ 
		lines.remove(object);
	}

	public void clearLines(){
		lines.clear();
		for(int  i = 0; i < offsetArray.length; i++){
			offsetArray[i] = 0;
		}

	}

	public int reLocation(int temp, int i){

		if( i == offsetArray.length){
			return temp;
		}
		if(temp == offsetArray[i]){
			return reLocation(randomLocation(),0);
		}else{
			return reLocation(temp, i+1);
		 }
		
	}



	public int randomLocation(){
		offset = 0;
		offset = offset + rand.nextInt((int)getBoundsRight().getHeight()) + 1;
		if(offset <= 95){
			return offset = 95;
		}else if(offset <= 190){
			return offset = 190;
		}else if(offset <= 285){
			return offset = 285;
		}else if(offset <= 380){
			return offset = 380;
		}else if(offset <= 475){
			return offset = 475;
		}else{
			return offset = 570;
		}

	}


	public Rectangle getBounds(){return null;}


	public Rectangle getBoundsTop(){ 
		return new Rectangle(
			(int)x + Constants.GAME_WIDTH / 2,
			(int)y + 30,
			10,
			Constants.GAME_HEIGHT - 50
			); 
	}

	public Rectangle getBoundsRight(){
	 return new Rectangle(
	 	(int)x + Constants.GAME_WIDTH / 6,
	 	(int)y + 30,
	 	10,
	 	Constants.GAME_HEIGHT - 50
	 	);
	}

	public Rectangle getBoundsLeft(){ 
		return new Rectangle(
			(int)x + Constants.GAME_WIDTH - 200,
			(int)y + 30,
			10,
			Constants.GAME_HEIGHT - 50
			); 
	}


	public LinkedList<Lines> getLines(){ return lines;}


}
