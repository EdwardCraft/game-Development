package Screens;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import util.Constants;
import Entities.Ladder;
import util.Enums.ObjectId;
import FrameWork.GameObject;
import java.util.LinkedList;
import Entities.Spider;
import Entities.BlockFloor;
import Entities.Player;
import util.Assets;
import Logic.RecycleGame;

public class LadderGame{

	private Ladder ladder;
	private LinkedList<GameObject> gameObjects;
	private Assets assets;
	private int gametWidth;
	private int gameHeight;
	private RecycleGame recygleGame;

	public LadderGame( Assets assets, RecycleGame recygleGame){
		this.assets = assets;
		this.recygleGame = recygleGame;
		ladder = new Ladder(0, 0, ObjectId.LadderMaster);
		gameObjects = new LinkedList<GameObject>();
		debugObjects();

	}


	public void update(double delta){

		ladder.update(delta);

		if(gameObjects != null){
	    	for(int  i = 0; i < gameObjects.size(); i++){
                gameObjects.get(i).update(delta);
	        }	
	    }

	}



	public void render(Graphics g, int gametWidth, int gameHeight){
		this.gametWidth = gametWidth;
		this.gameHeight = gameHeight;
		g.setColor(new Color(0, 0, 0));
	    g.fillRect(0, 0, gametWidth + 10, gameHeight + 10);
	    ladder.render(g, gametWidth, gameHeight);
	    if(gameObjects != null){
	    	for(int  i = 0; i < gameObjects.size(); i++){
                gameObjects.get(i).render(g, gametWidth, gameHeight);
	        }	
	    }


	}

	public void debugObjects(){
		addObject(new Spider(0, 0, 
			ObjectId.Spider, ladder));
		addObject(new BlockFloor( 0 , Constants.GAME_HEIGHT - Constants.BLOCK_RECTANGLE_HEIGTH, ObjectId.Block));
		addObject(new Player(
			100, Constants.GAME_HEIGHT - Constants.PLAYER_RECTANGLE_HEIGHT - 50, 
			ObjectId.Player,
			this,
			assets,
			recygleGame));
	}


	public void addObject(GameObject object){ 
		gameObjects.add(object); 
	}

	public void removeObject(GameObject object){ 
		gameObjects.remove(object);
	}

	public LinkedList<GameObject> getGameObjects(){ return gameObjects;}

	public RecycleGame getRecycleGame(){return recygleGame;}

}