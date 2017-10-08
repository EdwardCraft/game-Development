package FrameWork;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Logic.RecycleGame;
import Screens.Menu;
import util.Enums.ScreenState;
import Screens.miniGame;
import FrameWork.GameObject;
import util.Enums.ObjectId;
import util.Constants;
import util.Enums.BinState;
import util.Enums.Facing;
import Screens.LadderGame;
import Screens.FinishGame;

public class KeyInput extends KeyAdapter{
	
	private RecycleGame game;
	private Menu menu;
	private miniGame gameMini;
    private GameObject gameObject;
    private GameObject canObject;
    private LadderGame manager;
    private FinishGame finishGame;

	public KeyInput(RecycleGame game, Menu menu, miniGame gameMini, LadderGame manager){
		this.game = game;
		this.menu = menu;
		this.gameMini = gameMini;
		this.manager = manager;
	}


	@SuppressWarnings("deprecation")
	public void keyPressed(KeyEvent e){
		
	   int  key  = e.getKeyCode();

	   if(game.getScreenState() == ScreenState.Game){  
	   		if(gameMini.getHud().getCountDown() != 0){
	   			if(gameMini.getTrashObjects().peekFirst() != null){
	   				gameObject = gameMini.getTrashObjects().element();
	   				if(gameObject.getY() > 5 ){
	   					if(!gameObject.getHit()){
	   						if(key == KeyEvent.VK_Z){
	   							gameObject.setBinState(BinState.METAL);
	   							gameObject.setHit(true);
							}
							if(key == KeyEvent.VK_X){
	   							gameObject.setBinState(BinState.FOOD);
	   							gameObject.setHit(true);
							}
							if(key == KeyEvent.VK_C){
	   							gameObject.setBinState(BinState.PLASTIC);
	   							gameObject.setHit(true);		
					    	} 
	   			   		}
	   				}
	   		    } 	
	   	     }
	    }

	    if(game.getScreenState() == ScreenState.Ladder ){
			for(int i = 0; i < manager.getGameObjects().size(); i++){
				gameObject = manager.getGameObjects().get(i);
				if(gameObject.getObjectId() == ObjectId.Player){
					if(gameObject.getHit() == false){
							if(key == KeyEvent.VK_RIGHT){
								gameObject.setFacing(Facing.Left);
								gameObject.setVelocityX(Constants.PLAYER_MOVEMENT_SPEDD);
							}
							if(key == KeyEvent.VK_LEFT){
								gameObject.setFacing(Facing.Right);
								gameObject.setVelocityX(-Constants.PLAYER_MOVEMENT_SPEDD);
							}
			   		 }else{
			   		 	return;
			   		 }
				}
		    }
		}


	   if(game.getScreenState() == ScreenState.Menu){
			
			   if(key == KeyEvent.VK_ENTER){
				   menu.select();
			   }
			   if(key == KeyEvent.VK_UP) {
				   menu.setCurrentChoise(menu.getCurrentChoise()-1);
				   if(menu.getCurrentChoise() == -1){
					   menu.setCurrentChoise(menu.getOptions().length - 1);
				   }
			   }
			   if(key == KeyEvent.VK_DOWN) {
				   menu.setCurrentChoise(menu.getCurrentChoise()+1);
				   if(menu.getCurrentChoise() == menu.getOptions().length) {
					   menu.setCurrentChoise(0);
					}
			   }	   
		}

		if(game.getScreenState() == ScreenState.Death){
			    if(key == KeyEvent.VK_ENTER){
				   finishGame.select();
			   }
			   if(key == KeyEvent.VK_UP) {
				   finishGame.setCurrentChoise(finishGame.getCurrentChoise()-1);
				   if(finishGame.getCurrentChoise() == -1){
					   finishGame.setCurrentChoise(finishGame.getOptions().length - 1);
				   }
			   }
			   if(key == KeyEvent.VK_DOWN) {
				   finishGame.setCurrentChoise(finishGame.getCurrentChoise()+1);
				   if(finishGame.getCurrentChoise() == finishGame.getOptions().length) {
					   finishGame.setCurrentChoise(0);
					}
			   }	
		}



	   if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	   
	}

	public void keyReleased(KeyEvent e){
		int  key  = e.getKeyCode();
		for(int i = 0; i < manager.getGameObjects().size(); i++){
			gameObject = manager.getGameObjects().get(i);
			if(gameObject.getObjectId() == ObjectId.Player){
					if(key == KeyEvent.VK_RIGHT) gameObject.setVelocityX(0);
					if(key == KeyEvent.VK_LEFT) gameObject.setVelocityX(0);	
			}
		}

	}

	public void setFinishGame(FinishGame finishGame){
		this.finishGame = finishGame;
	}
	
	public void setMiniGame(miniGame gameMini){
		this.gameMini = gameMini;
	}	

	public void setLadderGame(LadderGame manager){
		this.manager = manager;
	}

}