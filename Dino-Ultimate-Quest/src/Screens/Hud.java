package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Entities.Manager;
import Entities.Player;
import FrameWork.GameObject;
import FrameWork.ObjectId;
import FrameWork.PlayerCam;
import Utils.Constants;

public class Hud {
	
	private BufferedImage hud;
	private Manager manager;
	private GameObject gameObject;
	private GameObject player;
	public Hud(Manager manager){
		
		this.manager = manager;
		
		try {
			hud = ImageIO.read(
				getClass().getResourceAsStream(
					Constants.GAME_HUD
				)
			);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	public void update(){
		
	}
	
	public void render(Graphics g, PlayerCam playerCamera) {
		
		g.setColor(new Color(1f,0f,0f,.7f));
		
		for(int i = 0; i < manager.gameObjects.size(); i++ ){
			gameObject = manager.gameObjects.get(i);
			if(gameObject.getObjectId() == ObjectId.Player){
				player = gameObject; 
				break;
			}
		}   
		
		if(playerCamera.getPositionX() < 0){
			g.fillRect(-(int)playerCamera.getPositionX() + 91, (int)Constants.GAME_CAM_OFFSET + 56,player.getHealth(),40); 
			g.drawImage(hud, -(int)playerCamera.getPositionX(), (int)Constants.GAME_CAM_OFFSET+28,null);
		}else{
			g.fillRect(91,(int)Constants.GAME_CAM_OFFSET + 56,player.getHealth(),40); 
			g.drawImage(hud, 0, (int)Constants.GAME_CAM_OFFSET + 28,null);
		}

		
	}
		
	
}
