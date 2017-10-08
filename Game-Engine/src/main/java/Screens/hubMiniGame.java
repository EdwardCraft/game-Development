package Screens;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import util.Constants;
import java.util.LinkedList;
import FrameWork.GameObject;
import Logic.RecycleGame;
import util.Enums.ScreenState;
import Screens.FinishGame;

public class hubMiniGame{

	private Font font; 
	private int score;
	private int thrash;
	private float timer;
	private float countDown;
	private int c;
	private LinkedList<GameObject> canObjects;
	private RecycleGame game;

 	public hubMiniGame(LinkedList<GameObject> canObjects, RecycleGame game){
 		this.canObjects = canObjects;
 		this.game = game;
 		try{
			font = new Font("Bold", Font.PLAIN, 30);
		}catch(Exception e){
			e.printStackTrace();
		}

		score = 0;
		timer = 16000;
		countDown = 30f;
 	}

 	public void render(Graphics g, int gametWidth, int gametHeight){
 		timer = timer - 0.5f;
 		countDown = timer * 0.0019f;
 		
 		if((int)countDown <= 0){
 			countDown = 0;
 			game.setScreenState(ScreenState.Death);
 			game.setFinishScreen(new FinishGame(game));
 			game.setFinishScreenKey();
 		}

 		g.setFont(font);		
 		g.setColor(Color.BLUE);
 		g.drawString( "Time "+  String.valueOf((int)countDown), 
 			Constants.GAME_WIDTH - 130, 30);

 		g.setColor(Color.RED);
		g.drawString(String.valueOf(score), Constants.GAME_WIDTH - 80, 70);
		g.drawString(" / ", Constants.GAME_WIDTH - 50, 70);
		g.drawString(String.valueOf(canObjects.size()), Constants.GAME_WIDTH - 30, 70);

 	}


 	public int getScore(){ 
 		return score;
 	}

 	public void setScore(int score){
 		this.score = score;
 	}

 	public int getCountDown(){
 		return (int)countDown;
 	}


}