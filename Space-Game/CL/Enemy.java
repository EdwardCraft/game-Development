package CL;

import java.awt.Graphics;
import java.util.Random;
import EN.EntityB;
import EN.EntityA;
import java.awt.Rectangle;
import US.Animation;
import US.Physics;

public class Enemy extends GameObject implements EntityB{


	
	private Textures tex;
	Random r= new  Random();
	private int speed = r.nextInt(6)+1;
	private SpaceGame game;
	private Controller c;
	Animation anim;
	

	public Enemy(double x, double y, Textures tex,Controller c, SpaceGame game){
		super(x,y);
		this.tex=tex;
		this.c=c;
		this.game=game;
		anim= new Animation(8,tex.enemy[0],tex.enemy[1],tex.enemy[2]);
	}

	//method for moving objets

	public void tick(){
		Y();
		

		
		for(int i=0;i < game.ea.size();i++)
		{
			EntityA tempEnt =  game.ea.get(i);
			if(Physics.Collision(this,tempEnt))
			{

			 c.removeEntity(tempEnt);	
			 c.removeEntity(this);
			 game.setEnemy_killed(game.getEnemy_killed()+1);
	       	}
		}

		
		anim.runAnimation();
	}

	public void X(){
		x+=speed;
       	if(x > 320 * SpaceGame.SCALE)
       	{
			y= r.nextInt(640);
			x =-10;
		}

	}

	public void Y(){
		y+=speed;
       	if(y >SpaceGame.HEIGHT* SpaceGame.SCALE)
       	{
			x= r.nextInt(640);
			y =-30;
		}

	}

	public void render(Graphics g){

		anim.drawAnimation(g,x,y,0);

	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,32,32);
	}

	public double getY(){
		return y;
	}

	public void setY(double y){

		this.y=y;
	}

	public void setX(double x){
		this.x=x;

	}

	public double getX(){
		return x;
	}

}