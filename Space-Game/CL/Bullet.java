package CL;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import EN.EntityA;
import java.awt.Rectangle;
import US.Physics;
import US.Animation;

public class Bullet extends GameObject implements EntityA{

	
	private Textures tex;
	private SpaceGame game;

	Animation anim;

	public Bullet(double x, double y, Textures tex, SpaceGame game){
		super(x,y);
		this.tex = tex;
		this.game=game; 
		anim= new Animation(3, tex.missile[0],tex.missile[1],tex.missile[2]);
	}

	public void tick(){
		y-=10;
		

		anim.runAnimation();

	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,32,32);
	}

	public void render(Graphics g){
		anim.drawAnimation(g,x,y,0);
	}

	public double getY(){
		return y;
	}

	public double getX(){
		return x;
	}


}