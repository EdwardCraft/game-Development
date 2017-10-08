package Screens;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import util.Constants;
import util.Enums.ObjectId;
import FrameWork.GameObject;
import java.util.LinkedList;
import Entities.BlockFloor;
import Entities.Player;
import util.Assets;
import Logic.RecycleGame;
import java.lang.Math;
import Entities.Ball;

public class BolleyBall{

	private Assets assets;
	private LinkedList<GameObject> gameObjects;
	private RecycleGame recygleGame;
	private float timer;
	private float countDown;
	private int x , y;
	private int sinMovement;
	private int yBase;
	private int amplitude = 100;
	private int frequency = 3;

	public BolleyBall(Assets assets){
		this.assets = assets;
		gameObjects = new LinkedList<GameObject>();
		timer = 16000;
		countDown = 0f;
		x = 0;
		y = 0 ;
		yBase = 0;
		debugObjects();

	}


	public void update(double delta){
		if(gameObjects != null){
			for(int  i = 0; i < gameObjects.size(); i++){
				gameObjects.get(i).update(delta);
			}
		}

	}


	public void render(Graphics g){
		if(gameObjects != null){
			for(int  i = 0; i < gameObjects.size(); i++){
				gameObjects.get(i).render(g , 0 , 0);
			}
		}
		
	}	


	public void debugObjects(){
		addObject(new BlockFloor( 0,
		 Constants.GAME_HEIGHT - Constants.BLOCK_RECTANGLE_HEIGTH,
		 ObjectId.Block));
		addObject(new Ball( 500 , 
			Constants.GAME_HEIGHT - Constants.BALL_RECTANGLE_HEIGHT,
			ObjectId.Ball));
	}


	public void addObject(GameObject object){ 
		gameObjects.add(object); 
	}

	public void removeObject(GameObject object){ 
		gameObjects.remove(object);
	}


}