package Entities;
import FrameWork.GameObject;
import util.Enums.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import util.Constants;
import Screens.LadderGame;
import util.Assets;
import FrameWork.Animation;
import util.Enums.Facing;
import Logic.RecycleGame;
import util.Enums.ScreenState;

public class Player extends GameObject{

	private LadderGame game;
	private GameObject gameObject;
	private Assets assets;
	private Animation chiIdle;
	private Animation chiRun;
	private Animation chiHit;
	private int velocityHitX;
	private int gameWidth;
	private int gameHeight;
	private int health;
	private RecycleGame recycleGame;

	public Player(float x, float y, ObjectId id, LadderGame game, Assets assets, RecycleGame recycleGame){
		super(x, y, id);
		this.recycleGame = recycleGame;
		falling = true;
		jumping = false;
		facing = Facing.Left;
		hit = false;
		velocityHitX = 0;
		health = 0;
		this.game = game;
		this.assets = assets;

		chiIdle = new Animation(Constants.CHI_ANIMATION_IDLE_SPEED,
			assets.getChiSpriteSheet()[0], assets.getChiSpriteSheet()[1],
			assets.getChiSpriteSheet()[2], assets.getChiSpriteSheet()[3],
			assets.getChiSpriteSheet()[4], assets.getChiSpriteSheet()[5],
			assets.getChiSpriteSheet()[6], assets.getChiSpriteSheet()[7],
			assets.getChiSpriteSheet()[8], assets.getChiSpriteSheet()[9]);

		chiRun = new Animation(Constants.CHI_ANIMATION_RUN_SPEED,
			assets.getChiSpriteRun()[0], assets.getChiSpriteRun()[1],
			assets.getChiSpriteRun()[2], assets.getChiSpriteRun()[3],
			assets.getChiSpriteRun()[4], assets.getChiSpriteRun()[5],
			assets.getChiSpriteRun()[6], assets.getChiSpriteRun()[7]
			);

		chiHit = new Animation(Constants.CHI_ANIMATION_HIT_SPEED,
			assets.getChiSpriteHit()[0], assets.getChiSpriteHit()[1],
			assets.getChiSpriteHit()[2], assets.getChiSpriteHit()[3],
			assets.getChiSpriteHit()[4], assets.getChiSpriteHit()[4],
			assets.getChiSpriteHit()[4], assets.getChiSpriteHit()[4],
			assets.getChiSpriteHit()[4], assets.getChiSpriteHit()[4],
			assets.getChiSpriteHit()[4], assets.getChiSpriteHit()[4]);


	}



	public void update(double delta){

		y +=velocity_Y;
		x +=velocity_X;
		
		if(hit){
			x += velocityHitX;
		}

		if(x <= 0 ){
			x = 0;
		}
		
		if(x >= Constants.GAME_WIDTH - 80){
			x = Constants.GAME_WIDTH - 80;
		}

		if((falling || jumping)){
			velocity_Y += Constants.PLAYER_GRAVITY_ACCELERATION;
		}else{
			velocity_Y = 0;
		}

		chiIdle.runAnimation();
		chiRun.runAnimation();
		chiHit.runAnimation();
		if(health >= 3){
			recycleGame.setScreenState(ScreenState.Menu);
			recycleGame.setFinishScreenKey();
			health = 0;
		}
		collision();
		collisionSpider();

	}



	public void render(Graphics g,int gameWidth, int gameHeight){
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		Graphics2D g2d = (Graphics2D) g; 
		g.setColor(Color.blue);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());

		if(!hit){
			if(velocity_X != 0){
				if(facing == Facing.Left){
					chiRun.drawAnimation(g, (int)x + Constants.PLAYER_RUNNING_SPRITE_WIDTH  / 2, 
					(int)y - 30, - Constants.PLAYER_RUNNING_SPRITE_WIDTH, Constants.PLAYER_RUNNING_SPRITE_HEIGHT);
				}else{
					chiRun.drawAnimation(g, (int)x - 10, (int)y - 30,
					Constants.PLAYER_RUNNING_SPRITE_WIDTH, Constants.PLAYER_RUNNING_SPRITE_HEIGHT);
				}
			}else if(velocity_X == 0){
				if(facing == Facing.Left){
					chiIdle.drawAnimation(g, (int)x + Constants.PLAYER_RECTANGLE_WIDTH, 
					(int)y, - Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
				}else{
					chiIdle.drawAnimation(g, (int)x, (int)y,
					Constants.PLAYER_RECTANGLE_WIDTH, Constants.PLAYER_RECTANGLE_HEIGHT);
				}
			}

		}else{
			if(facing == Facing.Left){
					chiHit.drawAnimation(g, (int)x + Constants.PLAYER_HIT_SPRITE_WIDTH  / 2, 
					(int)y - 30, - Constants.PLAYER_HIT_SPRITE_WIDTH, Constants.PLAYER_HIT_SPRITE_HEIGHT);
				}else{
					chiHit.drawAnimation(g, (int)x - 10, (int)y - 30,
					Constants.PLAYER_HIT_SPRITE_WIDTH, Constants.PLAYER_HIT_SPRITE_HEIGHT);
				}
		}

	





	}


	private void collision(){

		for(int i = 0; i < game.getGameObjects().size(); i++){
			gameObject = game.getGameObjects().get(i);
			if(gameObject.getObjectId() == ObjectId.Block){
				
				if(getBoundsTop().intersects(gameObject.getBounds()))
				{
					y = gameObject.getY()+32;
					velocity_Y =0;
				}
				
				if(getBounds().intersects(gameObject.getBounds()))
				{
					y = gameObject.getY() - Constants.PLAYER_RECTANGLE_HEIGHT;
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


	private void collisionSpider(){

		for(int  i = 0; i < game.getGameObjects().size(); i++){
			gameObject = game.getGameObjects().get(i);
			if(gameObject.getObjectId() == ObjectId.Spider){
				if(hit == false){
					if(getBoundsLeft().intersects(gameObject.getBoundsTop())){
						hit = true;
						health += 1;
						jumping = true;
						velocity_Y = -Constants.PLAYER_NOCKBACK_VELOCITY[1] ;
						velocityHitX = Constants.PLAYER_NOCKBACK_VELOCITY[0] ;
					}
					if(getBoundsRight().intersects(gameObject.getBoundsTop())){
						hit = true;
						health += 1;
						jumping = true;
						velocity_Y = -Constants.PLAYER_NOCKBACK_VELOCITY[1] ;
						velocityHitX = -Constants.PLAYER_NOCKBACK_VELOCITY[0] ;
					}
				}
			}
		}



	}



	public Rectangle getBounds(){

		return new Rectangle(
			(int)((int)x + (Constants.PLAYER_RECTANGLE_WIDTH / 4)),
			(int)((int)y +  (Constants.PLAYER_RECTANGLE_HEIGHT / 2)),
			(int)Constants.PLAYER_RECTANGLE_WIDTH / 2,
			(int)Constants.PLAYER_RECTANGLE_HEIGHT / 2
			);
	}

	public Rectangle getBoundsTop(){

		return new Rectangle(
			(int)((int)x + (Constants.PLAYER_RECTANGLE_WIDTH / 4)),
			(int)y ,
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





}