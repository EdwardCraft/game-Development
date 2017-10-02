package CL;


import java.awt.image.BufferedImage;
import java.awt.Graphics;
import EN.EntityA;
import EN.EntityB;
import java.awt.Rectangle;
import US.Animation;
import US.Physics;

public class player extends GameObject implements EntityA{


	
	private double velX=0;
	private double velY=0;
	
	private Textures tex;
	Animation anim;
	SpaceGame game;
	Controller controller;

	public player(double x, double y,Textures tex,SpaceGame game, Controller controller ){
		super(x,y);
		this.tex=tex;  
		this.game=game;
		this.controller=controller;
		anim = new Animation(7,tex.player[0],tex.player[1],tex.player[2]);

	}
    // move 
	public void tick(){

		x+=velX;
		y+=velY;
    //coliciones 
		if(x <= 0)
	    x=0;
		if(x >= 640-19)
		x=640-19;
		if(y <= 0)
		y=0;
		if(y >= 480-32)
		y=480-32;	
		// this part of the code is for lowering the health on  impac with the enemy
		for(int i=0;i<game.eb.size(); i++)
		{
			EntityB tempEnt= game.eb.get(i);
			
			if(Physics.Collision(this,tempEnt))
			{
				controller.removeEntity(tempEnt);
				SpaceGame.HEALTH -= 40;
				game.setEnemy_killed(game.getEnemy_killed()+1);
				if(SpaceGame.HEALTH == 100)
		        {
		        EntityA tempEnta= game.ea.get(i);
		        controller.removeEntity(tempEnta);		
				}
			}
		}
        //////////////////////////////////////////////////////////////////////////////
		
		anim.runAnimation();

	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,32,32);
	}


	public void render(Graphics g){
		anim.drawAnimation(g,x,y,0);
		
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public void setX(double x){
		this.x = x;

	}
	public void setY(double y){
	 	this.y = y;
	 }

	public void setVelX(double velX){
		this.velX=velX;
	}
	public void setVelY(double velY){
		this.velY=velY;
	} 



}