
import util.Enums.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import util.Constants;
import util.Enums.Facing;

public class Player extends GameObject{

	private GameObject gameObject;
	private Assets assets;
	private Animation chiIdle;
	private Animation chiRun;
	private Animation chiHit;
	private int velocityHitX;
	private int i;
	public Player(float x, float y, ObjectId id, Assets assets, int i){
		super(x, y, id);
		this.i = i;
		falling = true;
		jumping = false;
		facing = Facing.Left;
		hit = false;
		velocityHitX = 0;
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
	

		//y +=velocity_Y;
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



		chiIdle.runAnimation();
		chiRun.runAnimation();
		chiHit.runAnimation();
		collision();
		collisionSpider();

	}



	public void render(Graphics g){


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

	
	}


	private void collisionSpider(){


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





}