package FrameWork;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Classes.Game;
import Entities.Blast;
import Entities.Manager;
import FrameWork.GameObject;
import FrameWork.ObjectId;
import Screens.DeathScreen;
import Screens.Menu;
import Utils.Constants;
import Utils.Enums.Facing;
import Utils.Enums.ScreenState;

public class KeyInput extends KeyAdapter{
	
	private Manager manager;
	private Menu menu;
	private GameObject gameObject;
	private Game game;
	private DeathScreen deathScreen;
	private GameObject player;
	
	public KeyInput(Manager manager, Menu menu,DeathScreen deathScreen, Game game){
		this.deathScreen = deathScreen;
		this.manager = manager;
		this.menu = menu;
		this.game = game;
		
	}


	@SuppressWarnings("deprecation")
	public void keyPressed(KeyEvent e){
		
		int  key  = e.getKeyCode();
		
		
		if(game.getScreenState() == ScreenState.Game || game.getScreenState() == ScreenState.Pause){
			for(int i = 0; i < manager.gameObjects.size(); i++){
				gameObject = manager.gameObjects.get(i);
				if(gameObject.getObjectId() == ObjectId.Player){
					if(game.getScreenState() != ScreenState.Pause){
						if(key == KeyEvent.VK_RIGHT){
							gameObject.setFacing(Facing.LEFT);
							gameObject.setVelocityX(Constants.PLAYER_MOVEMENT_SPEDD);
						}
						if(key == KeyEvent.VK_LEFT){
							gameObject.setFacing(Facing.RIGHT);
							gameObject.setVelocityX(-Constants.PLAYER_MOVEMENT_SPEDD);
						}
						if(key == KeyEvent.VK_SPACE) gameObject.setCruch(true);
						if(key == KeyEvent.VK_Z){		
							manager.addObject(new Blast(gameObject.getX(), 
									gameObject.getY(),
									ObjectId.Blast, 
									gameObject.getFacing(),
									manager,
									true));
							gameObject.setFire(true);
						}
						
						if(key == KeyEvent.VK_UP && !gameObject.isJumping()){					
							gameObject.setJumping(true);
							gameObject.setVelocityY(-Constants.PLAYER_JUMP_HIGHT);
						}
					}
					if(key == KeyEvent.VK_ENTER){
						if(game.getScreenState() != ScreenState.Pause){
							game.setScreenState(ScreenState.Pause);
						}else{
							game.setScreenState(ScreenState.Game);
						}		
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
				   for(int i = 0; i < manager.gameObjects.size(); i++){
					   GameObject gameObject = manager.gameObjects.get(i);
					   if(gameObject.getObjectId() == ObjectId.Player){
						   player = gameObject;
						   break;
					   }
				   }
				   
				   deathScreen.select(manager, player);
				   
			   }
			   if(key == KeyEvent.VK_UP) {
				   deathScreen.setCurrentChoise(deathScreen.getCurrentChoise()-1);
				   if(deathScreen.getCurrentChoise() == -1){
					   deathScreen.setCurrentChoise(deathScreen.getOptions().length - 1);
				   }
			   }
			   if(key == KeyEvent.VK_DOWN) {
				   deathScreen.setCurrentChoise(deathScreen.getCurrentChoise()+1);
				   if(deathScreen.getCurrentChoise() == deathScreen.getOptions().length) {
					   deathScreen.setCurrentChoise(0);
					}
			   }	   
		}
	 

	
	   
	   if(key == KeyEvent.VK_ESCAPE){
		   System.exit(1);
	   }
	   
	   
	}

	public void keyReleased(KeyEvent e){
		int  key  = e.getKeyCode();
		
		for(int i = 0; i < manager.gameObjects.size(); i++){
			gameObject = manager.gameObjects.get(i);
			if(gameObject.getObjectId() == ObjectId.Player){
				if(key == KeyEvent.VK_RIGHT) gameObject.setVelocityX(0);
				if(key == KeyEvent.VK_LEFT) gameObject.setVelocityX(0);
				if(key == KeyEvent.VK_SPACE) gameObject.setCruch(false);
				if(key == KeyEvent.VK_Z) gameObject.setFire(false);
				
			}
		}


	}

	
	
}
