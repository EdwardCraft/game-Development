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
import FrameWork.PlayerCam;
import Utils.Constants;
import Utils.Texture;
import Utils.Enums.Facing;


public class Blast extends GameObject{
	
	private Texture texture;
	private Animation blast;
	private int blastBoost;
	private Manager manager;
	private GameObject enemy;
	private boolean visible;
	public Blast(float x, float y, ObjectId id, Facing facing, Manager manager, boolean visible) {
		super(x, y, id);
		this.facing = facing;
		this.manager = manager;
		this.visible = visible;
		texture = Game.getTexture();
		
		blast = new Animation(1, texture.fire[0],
				texture.fire[1], texture.fire[2]);
		blastBoost = 10;
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		if(facing == Facing.LEFT){
			x += Constants.FIRE_BALL_VELOCITY;
		}else if(facing == Facing.RIGHT){
			x -= Constants.FIRE_BALL_VELOCITY;
		}
		
		blast.runAnimation();
		collisionEnemy();
	}

	
	
	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g; 
		g.setColor(Color.red);
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		
			if(facing == Facing.RIGHT){
				blast.drawAnimation(g, (int)x + Constants.BLAST_RECTANGLE_WIDTH / 2, 
						(int)y + 10, - Constants.BLAST_RECTANGLE_WIDTH, Constants.BLAST_RECTANGLE_HEIGHT);
			}else{
				blast.drawAnimation(g, (int)x + Constants.BLAST_RECTANGLE_WIDTH / 2, 
						(int)y + 10, Constants.BLAST_RECTANGLE_WIDTH, Constants.BLAST_RECTANGLE_HEIGHT);
	
		}

		
	}
	
	private void collisionEnemy(){
		for(int i = 0; i < manager.gameObjects.size(); i++){
			GameObject gameObject = manager.gameObjects.get(i);
			if(gameObject.getObjectId() == ObjectId.Enemy){
				if(getBoundsRight().intersects(gameObject.getBoundsLeft())){
					manager.removeObject(gameObject);
					manager.removeObject(this);
				}
			}
		}
		

		
		
	}
	
	
	public Rectangle getBounds(){

		return null;
	}

	public Rectangle getBoundsTop(){

		return null;
	}

	public Rectangle getBoundsRight(){

		return new Rectangle(
				(int)x + Constants.BLAST_RECTANGLE_WIDTH / 2,
				(int)y + 34,
				Constants.BLAST_RECTANGLE_WIDTH,
				Constants.BLAST_RECTANGLE_HEIGHT - 40 
			);
	}

	public Rectangle getBoundsLeft(){

		return new Rectangle(
				(int)x - Constants.BLAST_RECTANGLE_WIDTH / 2 ,
				(int)y + 34,
				Constants.BLAST_RECTANGLE_WIDTH,
				Constants.BLAST_RECTANGLE_HEIGHT - 40 
			);
	}

	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void setblastBoost(int blastBoost ){
		this.blastBoost = blastBoost;
	}
}
