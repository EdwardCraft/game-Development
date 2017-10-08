package Entities;
import FrameWork.Animation;
import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.PlayerCam;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer.ConditionObject;

import Classes.Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.time.temporal.JulianFields;

import Utils.Constants;
import Utils.Texture;

import java.awt.Color;

import Utils.Enums.Facing;
import Utils.Enums.JumpState;
import Utils.Enums.ScreenState;

import java.awt.Graphics2D;
import FrameWork.GameObject;
import FrameWork.ObjectId;



public class Player extends GameObject{



	private Graphics2D g2d;
	private Manager manager;
	private GameObject gameObject;
	private Texture texture;

	
	private Animation playerIdle;
	private Animation palyerjump;
	private Animation palyerFall;
	private Animation playerRun;
	private Animation playerCrunch;
	private Animation palyerFire;
	private Boolean hit;
	private int velocityHitX;
	private GameObject portalA;
	private GameObject portalB;
	private Game game;
	private int found;
	
	public Player(float x, float y, Manager manager,ObjectId id,Game game){
		super(x, y, id);
		this.manager = manager;
		this.game = game;
		
		texture = Game.getTexture();
		
		palyerjump   = new Animation(3, texture.player[8]);
		palyerFall   = new Animation(10, texture.player[9]);
		palyerFire   = new Animation(1,texture.fireBreath[6]);
		
		playerCrunch = new Animation(1, texture.fireBreath[0],
				texture.fireBreath[1],texture.fireBreath[2],
				texture.fireBreath[3],texture.fireBreath[4],
				texture.fireBreath[5],texture.fireBreath[6]);
		
		playerIdle   = new Animation(3, texture.player[0], texture.player[1], 
				texture.player[2], texture.player[3], 
				texture.player[4], texture.player[5],
				texture.player[6], texture.player[7]
				);
				
		playerRun   = new Animation(Constants.PLAYER_ANIMATION_RUNNING_SPEED,
				texture.runnigS[0],texture.runnigS[1],
				texture.runnigS[2],texture.runnigS[3],texture.runnigS[4],
				texture.runnigS[5],texture.runnigS[6],texture.runnigS[7],
				texture.runnigS[8],texture.runnigS[9],texture.runnigS[10],
				texture.runnigS[11],texture.runnigS[12],texture.runnigS[13],
				texture.runnigS[14],texture.runnigS[15],texture.runnigS[16],
				texture.runnigS[17],texture.runnigS[18],texture.runnigS[19],
				texture.runnigS[20]
				);

		
		facing = Facing.LEFT;
		jumpState = JumpState.FALLING;
		crunch = false;
		fire = false;
		hit = false;
		velocityHitX = 0;
		health = Constants.PLAYER_HEALTH;
		found = 0;
	}



	public void update(LinkedList<GameObject> object){
		
		if(health <= 0){
			game.setScreenState(ScreenState.Death);
		}
		
		y +=velocity_Y;

		if(crunch == false && fire == false && hit == false){
			x +=velocity_X;
			velocityHitX = 0;
		}
		
		if(hit == true){
			x += velocityHitX;
		}
		
	
		if(x <= 0 ){
			x = 0;
		}
		
		if((falling || jumping)){
			velocity_Y += Constants.PLAYER_GRAVITY_ACCELERATION;
		}else{
			velocity_Y = 0;
		}
		

		playerIdle.runAnimation();
		palyerjump.runAnimation();
		palyerFall.runAnimation();
		playerRun.runAnimation();
		playerCrunch.runAnimation();
		palyerFire.runAnimation();
		
		collision();
		collisionEnemy();
		collisionPortal();
	}

	public void render(Graphics g){
		
		/*Graphics2D g2d = (Graphics2D) g; 
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());*/
		
			if(jumping){
				 if(fire == true){
					if(facing == Facing.RIGHT){
						palyerFire.drawAnimation(g, (int)x + Constants.PLAYER_RECTANGLE_WIDTH, 
								(int)y, - Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
					
					}else{
						palyerFire.drawAnimation(g, (int)x, (int)y,Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
						
					}
				}else{
					if(facing == Facing.RIGHT){
						palyerFall.drawAnimation(g,(int)x + Constants.PLAYER_RECTANGLE_WIDTH,
								(int)y, - Constants.PLAYER_RECTANGLE_WIDTH,Constants.PLAYER_RECTANGLE_HEIGHT);
					
					}
					else if(facing == facing.LEFT)
						palyerFall.drawAnimation(g,(int)x,(int)y,Constants.PLAYER_RECTANGLE_WIDTH,Constants.PLAYER_RECTANGLE_HEIGHT);
				
					}
			}else{
				if(crunch == false && fire == false){
					if(velocity_X !=0){
						if(facing == Facing.RIGHT){
							playerRun.drawAnimation(g,(int)x + Constants.PLAYER_RECTANGLE_WIDTH,
									(int)y, - Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
							
						}	
						else{
							playerRun.drawAnimation(g,(int)x,(int)y, Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
							
						}
							
					}else{
						if(facing == Facing.RIGHT){
							playerIdle.drawAnimation(g,(int)x + Constants.PLAYER_RECTANGLE_WIDTH,
									(int)y, - Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
							
						}else{
							playerIdle.drawAnimation(g,(int)x,(int)y, Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
							
						}
							
					}
				}else if (crunch == true){
					if(facing == Facing.RIGHT){
						playerCrunch.drawAnimation(g, (int)x + Constants.PLAYER_RECTANGLE_WIDTH, 
								(int)y, - Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
					
					}else{
						playerCrunch.drawAnimation(g, (int)x, (int)y,Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
						
					}
				}else if(fire == true){
					if(facing == Facing.RIGHT){
						palyerFire.drawAnimation(g, (int)x + Constants.PLAYER_RECTANGLE_WIDTH, 
								(int)y, - Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
						
					}else{
						palyerFire.drawAnimation(g, (int)x, (int)y,Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
						
					}
				}
			}	
			
			
	}

	private void collision(){

		for(int i = 0; i < manager.gameObjects.size(); i++){
			gameObject = manager.gameObjects.get(i);
			if(gameObject.getObjectId() == ObjectId.Block){
				
				if(getBoundsTop().intersects(gameObject.getBounds()))
				{
					y = gameObject.getY()+32;
					velocity_Y =0;
				}
				
				if(getBounds().intersects(gameObject.getBounds()))
				{
					y = gameObject.getY()-Constants.PLAYER_RECTANGLE_HEIGHT;
					velocity_Y = 0;
					hit = false;
					falling = false;
					jumping = false;
			
				}else{
					falling =true;
				}

				if(getBoundsRight().intersects(gameObject.getBounds()))
				{
					x = gameObject.getX() - Constants.PLAYER_RECTANGLE_WIDTH;
				}

				if(getBoundsLeft().intersects(gameObject.getBounds()))
				{
					x = gameObject.getX() + 35;
				}
			}
		}
	}
	
	private void collisionEnemy(){
		for(int i = 0; i <manager.gameObjects.size(); i++)
		{
			GameObject gameObject = manager.gameObjects.get(i);
			 if(gameObject.getObjectId() == ObjectId.Enemy)
			{
				if(crunch == false && hit == false){
					if(getBoundsRight().intersects(gameObject.getBoundsLeft()))
					{
						
							if(health > 0){
								System.out.println("right");
								 jumping = true;
								 hit = true;
								 health -= Constants.ENEMY_DAMAGE_POWER;
								 velocity_Y = -Constants.PLAYER_KNOCKBACK[0];
								 velocityHitX = -Constants.PLAYER_KNOCKBACK[1] * 2;
							}else{
								game.setScreenState(ScreenState.Death);
							}

					
					}

					if(getBoundsLeft().intersects(gameObject.getBoundsRight()))
					{	
						
							 if(health > 0){
								 System.out.println("left");
								 jumping = true;
								 hit = true;
								 health -= Constants.ENEMY_DAMAGE_POWER;
								 velocity_Y = -Constants.PLAYER_KNOCKBACK[0];
								 velocityHitX = Constants.PLAYER_KNOCKBACK[1] * 2;
							 }else{
								 game.setScreenState(ScreenState.Death);
							 }
					
					}
					
				}
			}			 
		}	
	}
	

	
	public void collisionPortal(){
		
		
		for(int i = 0; i < manager.gameObjects.size(); i++ ){
			GameObject gameObject = manager.gameObjects.get(i);		
			if(gameObject.getObjectId() == ObjectId.FlagA){
				 portalA = gameObject;
			}		
			if(gameObject.getObjectId() == ObjectId.FlagB){
				 portalB = gameObject;
			}
		}
	
			if(getBoundsRight().intersects(portalA.getBounds())){
				x = portalB.getX() +  Constants.GAME_PORTAL_WIDTH;
				y = portalB.getY() - Constants.PLAYER_RECTANGLE_HEIGHT;
			}
			if(getBoundsLeft().intersects(portalA.getBounds())){
				x = portalB.getX() + Constants.GAME_PORTAL_WIDTH;
				y = portalB.getY() - Constants.PLAYER_RECTANGLE_HEIGHT;
			}
			
			if(getBoundsLeft().intersects(portalB.getBounds())){
				x = portalA.getX() - (Constants.GAME_PORTAL_WIDTH * 2) - 30;
				y = portalA.getY() - Constants.PLAYER_RECTANGLE_HEIGHT;
			}
		    if(getBoundsRight().intersects(portalB.getBounds())){
				x = portalA.getX() - (Constants.PLAYER_RECTANGLE_WIDTH + Constants.GAME_PORTAL_WIDTH) - 30;
				y = portalA.getY() - Constants.PLAYER_RECTANGLE_HEIGHT;
			
			}	

	}
	

	public Rectangle getBounds(){

		return new Rectangle(
			(int)((int)x + (Constants.PLAYER_RECTANGLE_WIDTH / 4)),
			(int)((int)y + (Constants.PLAYER_RECTANGLE_HEIGHT / 2)),
			(int)Constants.PLAYER_RECTANGLE_WIDTH / 2,
			(int)Constants.PLAYER_RECTANGLE_HEIGHT / 2
			);
	}

	public Rectangle getBoundsTop(){

		return new Rectangle(
			(int)((int)x + (Constants.PLAYER_RECTANGLE_WIDTH / 4)),
			(int)y,
			(int)Constants.PLAYER_RECTANGLE_WIDTH / 2,
			(int)Constants.PLAYER_RECTANGLE_HEIGHT / 2
			);
	}

	public Rectangle getBoundsRight(){

		return new Rectangle(
			(int)((int)x + (Constants.PLAYER_RECTANGLE_WIDTH - 5)),
			(int)y + 5,
			(int)5.0,
			(int)Constants.PLAYER_RECTANGLE_HEIGHT - 10
			);
	}

	public Rectangle getBoundsLeft(){

		return new Rectangle(
			(int)x,
			(int)y + 5,
			(int)5.0,
			(int)Constants.PLAYER_RECTANGLE_HEIGHT - 10
			);
	}


	public void select() {	}
	

	
	public boolean getJump(){
		return jumping;
	}

} 