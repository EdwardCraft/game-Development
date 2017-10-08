package Entities;

import FrameWork.GameObject;
import util.Enums.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import util.Constants;
import Entities.Ladder;
import Entities.Lines;
import util.Enums.RectangleBounds;
import java.util.Random;

public class Spider extends GameObject{

	private Ladder ladder;
	private Random rand = new Random();
	private int temp;
	private Boolean hit;
	private int offset;
	private RectangleBounds rectangle;
	private RectangleBounds direction;
	private int temp2;

	public Spider(float x, float y, ObjectId id, Ladder ladder){
		super(x, y, id);
		this.ladder = ladder;
		offset = 0;
		temp = 0;
		hit = false;
		velocity_Y = Constants.SPIDER_VELOCITY_Y;
		velocity_X = Constants.SPIDER_VELOCITY_X;
		temp2 = Constants.SPIDER_OFFSET_TEMP;
		randPosition();
		rectangle = RectangleBounds.Botton;
		direction = RectangleBounds.N;
	}



	public void update(double delta){

		if(direction == RectangleBounds.N){
			collisionLadder();
			collisionRL();
			if(direction == RectangleBounds.N){
				if(rectangle == RectangleBounds.Right){
					y += velocity_Y;
				}else if(rectangle == RectangleBounds.Top){
					y += velocity_Y;
				}else if(rectangle == RectangleBounds.Left){
					y += velocity_Y;
				}
			}
		}

		if(direction == RectangleBounds.L){
			collisionLeft();
			if(direction == RectangleBounds.L){
				x -= velocity_X;
			}
		
		}

		if(direction == RectangleBounds.R){
			collisionRight();
			if(direction == RectangleBounds.R){
				x += velocity_X;
			}
  	
		}


		if(y >= ladder.getBoundsRight().getHeight()){
			randPosition();
			y = y - (int)ladder.getBoundsRight().getHeight();
			ladder.clearLines();
			ladder.createLines();
		}

			
		

	}



	public void render(Graphics g,int gameWidth, int gameHeight){

		Graphics2D g2d = (Graphics2D) g; 
		g.setColor(new Color(142, 255, 219));
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());


	}


	public void collisionLadder(){

		if(getBounds().intersects(ladder.getBoundsRight())){
			rectangle = RectangleBounds.Right;
		}

		if(getBounds().intersects(ladder.getBoundsTop())){
			rectangle = RectangleBounds.Top;
		}	

		if(getBounds().intersects(ladder.getBoundsLeft())){
			rectangle = RectangleBounds.Left;
		}

		
	}

	public void collisionRL(){
		for(int  i = 0; i < ladder.getLines().size(); i++){
			Lines line = ladder.getLines().get(i);
				if(getBoundsLeft().intersects(line.getBounds())){
					direction = RectangleBounds.L;
					hit = true;
					return;
				}else if(getBoundsRight().intersects(line.getBounds())){
					hit = true;
					direction = RectangleBounds.R;
					return;
				}
		}

	}


	public void collisionLeft(){
		if(direction == RectangleBounds.R){
			return;
		}

		for(int  i = 0; i < ladder.getLines().size(); i++){
			Lines line = ladder.getLines().get(i);
				if(getBoundsLeft().intersects(line.getBounds())){
					direction = RectangleBounds.L;
					hit = true;
					return;
				}
		}
		



		if(hit){
			x -= temp2;
			y += 19;
			hit = false;
		}
		direction = RectangleBounds.N;

	}

	public void collisionRight(){
		if(direction == RectangleBounds.L){
			return;
		}

		for(int  i = 0; i < ladder.getLines().size(); i++){
			Lines line = ladder.getLines().get(i);
				if(getBoundsRight().intersects(line.getBounds())){
					hit = true;
					direction = RectangleBounds.R;
					return;
				}
		}


		if(hit){
            x += temp2;
			y += 19;
			hit = false;
		}
		direction = RectangleBounds.N;
	}


	public void randPosition(){
		temp = rand.nextInt(3) + 1;
		//if(temp == 1){
			offset = (int)ladder.getBoundsRight().getX() + Constants.SPIDER_RECTANGLE_WIDTH - 5;
			//System.out.println(offset);
		/*}else if(temp == 2){
			offset = (int)ladder.getBoundsTop().getX() + Constants.SPIDER_RECTANGLE_WIDTH - 5;
			//System.out.println(offset);
		}else{
			offset = (int)ladder.getBoundsLeft().getX() + Constants.SPIDER_RECTANGLE_WIDTH - 5;
			//System.out.println(offset);
		}*/

	}

    public Rectangle getBounds(){

		return new Rectangle(
			(int)((int)x + (Constants.SPIDER_RECTANGLE_WIDTH / 4)) + offset - 30,
			(int)((int)y + (Constants.SPIDER_RECTANGLE_HEIGHT / 4)),
			(int)Constants.SPIDER_RECTANGLE_WIDTH + 10,
			(int)Constants.SPIDER_RECTANGLE_HEIGHT + 10
			);
	}

	public Rectangle getBoundsTop(){
		return new Rectangle(
			(int)x + (Constants.SPIDER_RECTANGLE_HIT_SIZE - 25) ,
			(int)y - (Constants.SPIDER_RECTANGLE_HIT_SIZE / 4) - 20,
			Constants.SPIDER_RECTANGLE_HIT_SIZE,
			Constants.SPIDER_RECTANGLE_HIT_SIZE
			);
	}

	public Rectangle getBoundsRight(){

		return new Rectangle(
			(int)((int)x + (Constants.SPIDER_RECTANGLE_WIDTH - 5)) + offset - 10,
			(int)y + 15,
			(int)Constants.SPIDER_RECTANGLE_WIDTH - 15,
			(int)Constants.SPIDER_RECTANGLE_HEIGHT - 10
			);
	}

	public Rectangle getBoundsLeft(){

		return new Rectangle(
			(int)x + offset - 30,
			(int)y + 15,
			(int)Constants.SPIDER_RECTANGLE_WIDTH - 15,
			(int)Constants.SPIDER_RECTANGLE_HEIGHT - 10
			);
	}






}