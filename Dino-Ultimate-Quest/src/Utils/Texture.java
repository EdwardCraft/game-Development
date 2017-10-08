package Utils;

import java.awt.image.BufferedImage;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer.ConditionObject;

public class Texture{

	private SpriteSheet blockSprite,playerSpriteIdle,palyerMovingSprite,levelSprite, MenuSprite;
	private SpriteSheet dinoFirePreparation;
	private SpriteSheet fireBallBreath;
	private SpriteSheet EnemyOneSheet;
	private BufferedImageLoader imageloader;
	
	private BufferedImage blockSheet   = null;
	private BufferedImage playerIdleSheet  = null;
	private BufferedImage palyerRunnigSheet  = null;
	private BufferedImage levelSheet = null;
	private BufferedImage MenuSheet = null; 
	public  BufferedImage Level1Game = null;
	private BufferedImage dinofirePrepationSheet = null;
	private BufferedImage fireBall = null;
	private BufferedImage enemyOneImage = null;
	
	public BufferedImage[] block   =  new BufferedImage[3];
	public BufferedImage[] player  =  new BufferedImage[17];
	public BufferedImage[] runnigS =  new BufferedImage[22];
	public BufferedImage[] levels = new BufferedImage[3];
	public BufferedImage[] levelBarckground = new BufferedImage[3];
	public BufferedImage[] Backgrounds = new BufferedImage[3];
	public BufferedImage[] Menu = new BufferedImage[3];
	public BufferedImage[] menuBackground = new BufferedImage[1];
	public BufferedImage[] fireBreath = new BufferedImage[7];
	public BufferedImage[] fire = new BufferedImage[4];
	public BufferedImage[] enemy = new BufferedImage[10];
	
	public Texture(){

		imageloader = new BufferedImageLoader();
		
		try{
			enemyOneImage = imageloader.loadImage(Constants.ENEMY_SPRITE_SHEET);
			fireBall = imageloader.loadImage(Constants.FIRE_BALL);
			blockSheet  = imageloader.loadImage(Constants.BLOCK_1);
			playerIdleSheet = imageloader.loadImage(Constants.PLAYER_IDLE_SPRITES);
			palyerRunnigSheet = imageloader.loadImage(Constants.PLAYER_RUNNING_SPRITES);
			dinofirePrepationSheet = imageloader.loadImage(Constants.PLAYER_FIRE_PREPARATION);
			Backgrounds[0] = imageloader.loadImage(Constants.GAME_LEVEL_1_SKY);
			Backgrounds[1] = imageloader.loadImage(Constants.GAME_LEVEL_1_BACKGROUND);
			MenuSheet = imageloader.loadImage(Constants.GAME_MENU_SCREEN);
			menuBackground[0] = imageloader.loadImage(Constants.GAME_MENU_BACKGROUND);
			Level1Game = imageloader.loadImage(Constants.GAME_LEVEL_1_IMAGE);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		EnemyOneSheet = new SpriteSheet(enemyOneImage);
		fireBallBreath = new SpriteSheet(fireBall);
		dinoFirePreparation = new SpriteSheet(dinofirePrepationSheet);
		levelSprite = new SpriteSheet(levelSheet);
		blockSprite = new SpriteSheet(blockSheet);
		playerSpriteIdle = new SpriteSheet(playerIdleSheet);
		palyerMovingSprite = new SpriteSheet(palyerRunnigSheet);
		MenuSprite = new SpriteSheet(MenuSheet);
		getTextures();

	}
	
	
	private void getTextures(){	
		
		for(int i = 0; i < 7; i++){
			fireBreath[i] = dinoFirePreparation.grabImage(i+1, 1, 106, 100);
		}
		
		for(int i = 0; i < enemy.length; i++){
			enemy[i] = EnemyOneSheet.grabImage(i+1, 1, 100, 100);
		}
		
		Menu[0] =  MenuSprite.grabImage(1, 1, 1, 1);
			
		block[0]   = blockSprite.grabImage(2,2,32,32);//dirt block
		block[1]   = blockSprite.grabImage(1,2,32,32);// grass

		for(int i = 0; i < 8 ; i++){
			player[i]  = playerSpriteIdle.grabImage(i + 1, 1, 100, 100);// idle frame for player
		}

		for(int i = 0; i < 3 ; i++){
			fire[i] = fireBallBreath.grabImage(i + 1, 1, 106, 100);
		}
		
		
		player[8]  = playerSpriteIdle.grabImage(1,2,100,100);// jump
		player[9]  = playerSpriteIdle.grabImage(2,2,100,100);// fall

		runnigS[0] = palyerMovingSprite.grabImage(1,1,105,100);//runnihg
		runnigS[1] = palyerMovingSprite.grabImage(2,1,106,100);//runnihg
		runnigS[2] = palyerMovingSprite.grabImage(3,1,106,100);//runnihg
		runnigS[3] = palyerMovingSprite.grabImage(4,1,106,100);//runnihg
		runnigS[4] = palyerMovingSprite.grabImage(5,1,106,100);//runnihg
		runnigS[5] = palyerMovingSprite.grabImage(6,1,106,100);//runnihg
		runnigS[6] = palyerMovingSprite.grabImage(7,1,106,100);//runnihg

		runnigS[7] = palyerMovingSprite.grabImage(1,2,105,100);//runnihg
		runnigS[8] = palyerMovingSprite.grabImage(2,2,106,100);//runnihg
		runnigS[9] = palyerMovingSprite.grabImage(3,2,106,100);//runnihg
		runnigS[10] = palyerMovingSprite.grabImage(4,2,106,100);//runnihg
		runnigS[11] = palyerMovingSprite.grabImage(5,2,106,100);//runnihg
		runnigS[12] = palyerMovingSprite.grabImage(6,2,106,100);//runnihg
		runnigS[13] = palyerMovingSprite.grabImage(7,2,106,100);//runnihg

		runnigS[14] = palyerMovingSprite.grabImage(1,3,105,100);//runnihg
		runnigS[15] = palyerMovingSprite.grabImage(2,3,106,100);//runnihg
		runnigS[16] = palyerMovingSprite.grabImage(3,3,106,100);//runnihg
		runnigS[17] = palyerMovingSprite.grabImage(4,3,106,100);//runnihg
		runnigS[18] = palyerMovingSprite.grabImage(5,3,106,100);//runnihg
		runnigS[19] = palyerMovingSprite.grabImage(6,3,106,100);//runnihg
		runnigS[20] = palyerMovingSprite.grabImage(7,3,106,100);//runnihg
		



	}
	
	public BufferedImage[] getLevelBarckground() {
		return levelBarckground;
	}



	public void setLevelBarckground(BufferedImage[] levelBarckground) {
		this.levelBarckground = levelBarckground;
	}
	

}