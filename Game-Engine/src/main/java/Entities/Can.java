package Entities;

import FrameWork.GameObject;
import util.Enums.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import util.Enums.RectangleBounds;
import util.Constants;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import util.Enums.BinState;
import Screens.miniGame;
import java.lang.Math;
import java.util.Random;
import Screens.hubMiniGame;


public class Can extends GameObject{

	private BufferedImage canImg;
	private float tempVelocity;
	private miniGame game;
	private GameObject gameObject;
	private Boolean knockback;
	private Boolean falling;
	private int maxVelocity;
	private Random rand = new Random();
	private int direction;
	private hubMiniGame hud;
	private float speed;
	private int gameWidth;
	private int gameHeight;

	public Can(float x, float y, ObjectId id, miniGame game, hubMiniGame hud){
		super(x, y , id);
		this.game = game;
		this.hud = hud;
		knockback = false;
		falling = true;
		maxVelocity = 10;
		direction = 1;
		hit = false;
		speed = 1;
		binState = BinState.BIN;
		try{
			canImg = ImageIO.read(
				getClass().getResourceAsStream(
					Constants.CAN_TRASH_IMAGE
				)
			);

		}catch(Exception e){
			e.printStackTrace();
		}

		
	}




	public void update(double delta){
		//System.out.println(y);
		if(hud.getCountDown() == 20){
			speed = 1.5f;
		}

		if(hud.getCountDown() == 10){
			speed = 3f;
		}

		if(velocity_Y < maxVelocity){
			y = y + velocity_Y + speed;
		}else{
			y = y + maxVelocity + speed;
		}

		if(binState == BinState.METAL && knockback == false){
			if(x > -( (gameWidth / 4) + 70 )){
				x = x - (float)delta * (15 * speed);
				velocity_Y = 20;
			}

		}else if(binState == BinState.METAL && knockback == true){
			x -= velocity_X * speed;
		}

		if(binState == BinState.PLASTIC && knockback == false){
			if(x < (gameWidth / 2 ) - 180){
				x = x + (float)delta * (15 * speed);
				velocity_Y = 20;
			}
		}else if(binState == BinState.PLASTIC && knockback == true){
			if(x > (gameWidth / 2)){
				removeAnAdObject();
			}else{
				x += velocity_X * speed;	
			}
				
		}


		if(binState == BinState.FOOD){
			velocity_Y += 1f;
			if(direction == 1){
				if(x > -(gameWidth / 2) + 70){
					x -= velocity_X;
				}else{
					removeAnAdObject();
				}	
			}else{
				if(x < (gameWidth / 2) + 70){
					x += velocity_X;
				}else{
					removeAnAdObject();
				}	
			}
		}


		if(falling && knockback){
			velocity_Y += 0.5f;
		}

		if(y > gameHeight){
			System.out.println("remove");
			removeAnAdObject();
		}

	

		collisionMetalBin();
		collisionFoodBin();
		collisionPlasticBin();

	}

	public void render(Graphics g,int gameWidth, int gameHeight){
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		Graphics2D g2d = (Graphics2D) g; 
		g.setColor(Color.red);
		g2d.draw(getBounds());


		g.drawImage(canImg, (int)x + (gameWidth / 2) - 50, (int)y, null);


	}

	private void removeAnAdObject(){

		game.removeObject(this, BinState.METAL);
		hit = false;
	}


	private void collisionMetalBin(){
		for(int i = 0; i < game.getGameObjects().size(); i++){
			gameObject = game.getGameObjects().get(i);
			if(gameObject.getObjectId() == ObjectId.metalBin){
				if(getBounds().intersects(gameObject.getBounds())){
					hud.setScore(hud.getScore() + 1);
					removeAnAdObject();
				}
			}
		}
	}

	private void collisionFoodBin(){
		for(int i = 0; i < game.getGameObjects().size(); i++){
			gameObject = game.getGameObjects().get(i);
			if(gameObject.getObjectId() == ObjectId.foodBin){
				if(getBounds().intersects(gameObject.getBounds())){
					direction = rand.nextInt(2) + 1;
					knockback = true;
					velocity_X = Constants.TRASH_NOCKBACK_VELOCITY[0];
					velocity_Y = -Constants.TRASH_NOCKBACK_VELOCITY[1];
				}
			}
		}
	}

	private void collisionPlasticBin(){
		for(int i = 0; i < game.getGameObjects().size(); i++){
			gameObject = game.getGameObjects().get(i);
			if(gameObject.getObjectId() == ObjectId.plasticBin){
				if(getBounds().intersects(gameObject.getBounds())){
					knockback = true;
					velocity_X = Constants.TRASH_NOCKBACK_VELOCITY[0];
					velocity_Y = -Constants.TRASH_NOCKBACK_VELOCITY[1];
				}
			}
		}
	}

	public Rectangle getBounds(){
		return new Rectangle(
				(int)x + (gameWidth / 2) - 50,
				(int)y ,
				50,
				50
			);
	}

	public Rectangle getBoundsTop(){ return null; }
	public Rectangle getBoundsRight(){ return null;}
	public Rectangle getBoundsLeft(){ return null;}
	public float getSpeed(){ return speed;}
	public void setSpeed(float speed) { this.speed = speed;}


}