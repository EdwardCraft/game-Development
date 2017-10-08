package FrameWork;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;

import Utils.Enums.Facing;
import Utils.Enums.JumpState;
import Utils.Enums.RectangleBounds;


public abstract class GameObject {
	
	protected float x,y;
	protected float velocity_X = 0, velocity_Y = 0;
	protected ObjectId id;
	protected JumpState jumpState;
	protected Facing facing;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected Boolean fire;
	protected Boolean crunch;
	protected int health;
	

	public GameObject(float x, float y, ObjectId id){
		this.x  = x;
		this.y  = y;
		this.id = id;
	
	
	}

	// for collision detection
	public abstract void update(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract Rectangle getBoundsTop();
	public abstract Rectangle getBoundsRight();
	public abstract Rectangle getBoundsLeft();
	public abstract void select();

	public float getX(){ return x; }
	public float getY(){ return y; }
	public void setX(float x){ this.x = x; }
	public void setY(float y){ this.y = y; }
	public float getVelocityX(){ return velocity_X; }
	public void setVelocityX(float velocity_X){ this.velocity_X = velocity_X; }
	public float getVelocityY(){ return velocity_Y; }
	public void setVelocityY(float velocity_Y){ this.velocity_Y = velocity_Y; }
	public JumpState getJumpState() { return jumpState; }
	public void setJumpState(JumpState jumpState){ this.jumpState = jumpState; }
	public Facing getFacing(){ return facing; }
	public void setFacing(Facing facing){ this.facing = facing; }
	public ObjectId getObjectId(){ return id; }
	public boolean isFalling() { return falling; }
	public void setFalling(boolean falling) { this.falling = falling; }
	public boolean isJumping() { return jumping; }
	public void setJumping(boolean jumping) { this.jumping = jumping;}
	public boolean isFIre(){ return fire; }
	public void setFire(Boolean fire){ this.fire = fire;}
	public boolean isCrucnh(){ return crunch; }
	public void setCruch(Boolean crunch){ this.crunch = crunch;}
	public RectangleBounds getBounds(RectangleBounds bounds){return bounds;}
	public int getHealth(){ return health;}
	public void setHelath(int health){ this.health = health;}
	
	
	
	

}
