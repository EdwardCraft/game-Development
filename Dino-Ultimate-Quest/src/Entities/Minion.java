package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import Classes.Game;
import FrameWork.Animation;
import FrameWork.GameObject;
import FrameWork.ObjectId;
import Utils.Constants;
import Utils.Texture;
import Utils.Enums.Facing;
import Utils.Enums.JumpState;
import Utils.Enums.RectangleBounds;

public class Minion extends GameObject {
	
	private Texture texture;
	private Animation monster;
	private Manager manager;
	private Boolean hit;
	private int velocityHitX;
	
	public Minion(float x, float y, Manager manager, ObjectId id) {
		super(x, y, id);
		this.manager = manager;
		
		texture = Game.getTexture();
		monster = new Animation(Constants.ENEMY_ANIMATION_DURATION, 
				texture.enemy[0], texture.enemy[1],
				texture.enemy[2], texture.enemy[3],
				texture.enemy[4], texture.enemy[5],
				texture.enemy[6], texture.enemy[7],
				texture.enemy[8], texture.enemy[9]);
		
		facing = Facing.LEFT;
		hit = false;
		velocityHitX = 0;

	}


	public void update(LinkedList<GameObject> object) {
		
		y += velocity_Y;
		
		if(facing == Facing.RIGHT && hit == false){
			x -= Constants.ENEMY_MOVEMENT_SPEED;
			velocityHitX = 0;
		}
	    if(facing == Facing.LEFT && hit == false){
			x += Constants.ENEMY_MOVEMENT_SPEED;
			velocityHitX = 0;
		}
		
	    if(facing == Facing.RIGHT && hit == true){
	    	x += velocityHitX;
	    }
	    
	    if(facing == Facing.LEFT && hit == true){
	    	x -= velocityHitX;
	    }
	    
		if((falling || jumping)){
			velocity_Y += Constants.PLAYER_GRAVITY_ACCELERATION;
		}else{
			velocity_Y = 0;
		}
		
		
		monster.runAnimation();
		collisionGround();
		collisionPlayer();
		
	}
	


	public void render(Graphics g) {
		
		/*Graphics2D g2d = (Graphics2D) g; 
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());*/
		
		if(facing == Facing.RIGHT){
			monster.drawAnimation(g, (int)x, 
					(int)y , Constants.ENEMY_RECTANGLE_WIDTH, Constants.ENEMY_RECTANGLE_HEIGHT );
		}else{
			monster.drawAnimation(g, (int)x + Constants.ENEMY_RECTANGLE_WIDTH , 
					(int)y , - Constants.ENEMY_RECTANGLE_WIDTH , Constants.ENEMY_RECTANGLE_HEIGHT );
		}
				
	}
	
	private void collisionGround(){
		for(int i = 0; i <manager.gameObjects.size(); i++)
		{
			GameObject tempObject = manager.gameObjects.get(i);
			 if(tempObject.getObjectId() == ObjectId.Block)
			{

				if(getBounds().intersects(tempObject.getBounds())){
					y = tempObject.getY() - Constants.ENEMY_RECTANGLE_HEIGHT;
					velocity_Y = 0;
					falling = false;
					jumping = false;
					hit = false;
					
				}else falling = true;

				if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY()+32;
					velocity_Y =0;
				}

				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() + 35;
					facing = Facing.RIGHT;
	
				}

				if(getBoundsRight().intersects(tempObject.getBounds()))	{
					x =tempObject.getX() - Constants.ENEMY_RECTANGLE_WIDTH;
					facing = Facing.LEFT;

				}
			}			 
		}	
	}
	

	private void collisionPlayer(){
		for(int i = 0; i < manager.gameObjects.size(); i++ ){
			GameObject gameObject = manager.gameObjects.get(i);
			if(gameObject.getObjectId() == ObjectId.Player){
				if(gameObject.isCrucnh() == true){
					if(getBoundsLeft().intersects(gameObject.getBoundsRight())){
						jumping = true;
						hit = true;
						facing = Facing.RIGHT;
						velocity_Y = -Constants.ENEMY_KNOCKBACK[0];
						velocityHitX = Constants.ENEMY_KNOCKBACK[1];
					}
					if(getBoundsRight().intersects(gameObject.getBoundsLeft())){
						jumping = true;
						hit = true;
						facing = Facing.LEFT;
						velocity_Y = -Constants.ENEMY_KNOCKBACK[0];
						velocityHitX = +Constants.ENEMY_KNOCKBACK[1];
					}
				}
			}
		}
		
		
	}
	
	

	
	
	public Rectangle getBounds(){
			
		return  new Rectangle(
				(int)((int)x+(Constants.ENEMY_RECTANGLE_WIDTH / 4)),
				(int)((int)y+(Constants.ENEMY_RECTANGLE_HEIGHT/2)),
				(int)Constants.ENEMY_RECTANGLE_WIDTH/2,
				(int)Constants.ENEMY_RECTANGLE_HEIGHT/2);
	}
	

	public Rectangle getBoundsTop(){

		return new Rectangle(
				(int)((int)x+(Constants.ENEMY_RECTANGLE_WIDTH / 4)),
				(int)y,
				(int)Constants.ENEMY_RECTANGLE_WIDTH/2,
				(int)Constants.ENEMY_RECTANGLE_HEIGHT/2);
	}
	public Rectangle getBoundsRight(){

		return new Rectangle(
				(int)((int)x+Constants.ENEMY_RECTANGLE_WIDTH - 6),
				(int)y + 10,
				(int)5,
				(int)Constants.ENEMY_RECTANGLE_HEIGHT - 20);
	}
	public Rectangle getBoundsLeft(){

		return new Rectangle(
				(int)x,
				(int)y + 10,
				(int)5,
				(int)Constants.ENEMY_RECTANGLE_HEIGHT - 20);
	}
	
	
	public void select() {
		
		
	}

}
