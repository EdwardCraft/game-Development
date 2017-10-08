package Entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.LinkedList;

import FrameWork.GameObject;
import FrameWork.ObjectId;
import Utils.Constants;


public class Flag extends GameObject{

	public Flag(float x, float y, ObjectId id){
		super(x,y,id);

	}

	public void update(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g; 
		
		g.setColor(Color.yellow);
		g2d.draw(getBounds());
		
		
	}

	public Rectangle getBounds(){
		return new Rectangle(
				(int)x,
				(int)y - Constants.GAME_PORTAL_HEIGHT / 2 ,
				Constants.GAME_PORTAL_WIDTH,
				Constants.GAME_PORTAL_HEIGHT);
	}


	public void select() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return null;
	}


}