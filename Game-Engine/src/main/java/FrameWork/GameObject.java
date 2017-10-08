package FrameWork;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;
import util.Enums.RectangleBounds;
import util.Enums.ObjectId;
import util.Enums.BinState;
import util.Enums.Facing;

public abstract class GameObject {
	
	protected float x,y;
	protected float velocity_X = 0, velocity_Y = 1;
	protected ObjectId id;
	protected float gravityAcceleration = 4;
	protected BinState binState;
	protected Boolean falling;
	protected Boolean jumping;
	protected Boolean hit;
	protected Facing facing;
	
	public GameObject(float x, float y, ObjectId id){
		this.x  = x;
		this.y  = y;
		this.id = id;
		falling = true;
		jumping = false;
	
	}

	// for collision detection
	public abstract void update(double delta);
	public abstract void render(Graphics g, int gameWidth, int gameHeight);
	public abstract Rectangle getBounds();
	public abstract Rectangle getBoundsTop();
	public abstract Rectangle getBoundsRight();
	public abstract Rectangle getBoundsLeft();

	public float getX(){ return x; }
	public float getY(){ return y; }
	public void setX(float x){ this.x = x; }
	public void setY(float y){ this.y = y; }
	public float getVelocityX(){ return velocity_X; }
	public void setVelocityX(float velocity_X){ this.velocity_X = velocity_X; }
	public float getVelocityY(){ return velocity_Y; }
	public void setVelocityY(float velocity_Y){ this.velocity_Y = velocity_Y; }
	public ObjectId getObjectId(){ return id; }
	public BinState getBinState(){ return binState; }
	public void setBinState(BinState binState ){ this.binState = binState; }
	public Boolean getHit(){ return hit;}
	public void setHit(Boolean hit) { this.hit = hit;}
	public boolean isFalling() { return falling; }
	public void setFalling(boolean falling) { this.falling = falling; }
	public boolean isJumping() { return jumping; }
	public void setJumping(boolean jumping) { this.jumping = jumping;}
	public Facing getFacing(){ return facing; }
	public void setFacing(Facing facing){ this.facing = facing; }
}