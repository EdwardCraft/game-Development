package util;

public class Constants{

	public static final int GAME_WIDTH = 1200;
	public static final int GAME_HEIGHT = 720;
	public static final int GAME_MINIMUN_WIDTH = 1020;
	public static final int GAME_MINIMUN_HEIGHT = 720;
	public static final double AMOUNT_OF_TICKS = 60.0f;
	public static final double NANO_SECONDS = 1000000000 / AMOUNT_OF_TICKS;
	public static final int BUFFER_STRATEGY = 3;
	public static final int TRASH_IMGAME_HEIGHT = 152;
	public static final int TRASH_IMGAME_WIDTH = 140;
	public static final int SPIDER_RECTANGLE_WIDTH = 20;
	public static final int SPIDER_RECTANGLE_HEIGHT = 20;
	public static final int BLOCK_RECTANGLE_WIDTH = 10;
	public static final int BLOCK_RECTANGLE_HEIGTH = 10;
	public static final int BLOCK_AREA = 10;
	public static final int PLAYER_RECTANGLE_WIDTH = 83;
	public static final int PLAYER_RECTANGLE_HEIGHT = 192;
	public static final int SPIDER_RECTANGLE_HIT_SIZE = 150;
	public static final int PLAYER_RUNNING_SPRITE_WIDTH = 200;
	public static final int PLAYER_RUNNING_SPRITE_HEIGHT = 220;
	public static final int PLAYER_HIT_SPRITE_HEIGHT = 350 - 170;
	public static final int PLAYER_HIT_SPRITE_WIDTH = 222 - 100;
	public static final float PLAYER_GRAVITY_ACCELERATION = 0.5f;
	public static final float PLAYER_MOVEMENT_SPEDD = 15f;
	public static final float PLAYER_JUMP_HIGHT = 10f;
	public static final float SPIDER_VELOCITY_X = 5f;
	public static final float SPIDER_VELOCITY_Y = 5f;
	public static final int SPIDER_OFFSET_TEMP = 25;
	public static final float CHI_ANIMATION_IDLE_SPEED = 4.5f;
	public static final float CHI_ANIMATION_RUN_SPEED = 4f;
	public static final float CHI_ANIMATION_HIT_SPEED = 6;
	// PLAYER_VELOCITY[0] is for the X coordinate
	//PLAYER_VELOCITY[1] is for the Y coordinate
	public static final int[] PLAYER_NOCKBACK_VELOCITY = { 10, 7};
	// TRASH_VELOCITY[0] is for the X coordinate
	//TRASH_VELOCITY[1] is for the Y coordinate
	public static final float[] TRASH_NOCKBACK_VELOCITY = { 24f, 24f};

	// Ball 
	public static final int BALL_WAVE_LENGHT = 360 ;
	public static final int BALL_WAVE_AMPLITUDE = 200;
	public static final int BALL_RECTANGLE_WIDTH = 100;
	public static final int BALL_RECTANGLE_HEIGHT = 100;
	public static final int BALL_VELOCITY = 5;

	public static final String GAME_MENU_IMAGE = "/Menu/Menu.png";
	public static final String GAME_MENU_ICON = "/Menu/menuIcon.png";
	public static final String GAME_MINI_RECYCLE_IMAGE = "/miniRecycleGame/gameBackground.png";
	public static final String FOOD_TRASH_IMAGE = "/miniRecycleGame/foodBin.png";
	public static final String METAL_TRASH_IMAGE = "/miniRecycleGame/metalBin.png";
	public static final String PAPER_TRASH_IMAGE = "/miniRecycleGame/paperBin.png";
	public static final String CAN_TRASH_IMAGE = "/miniRecycleGame/can.png";
	public static final String PLAYER_IDEL_CHI_SPRITE_SHEET = "/Chi/chi.png";
	public static final String PLAYER_RUN_CHI_SPRITE_SHEET = "/Chi/beta.png";
	public static final String PLAYER_HIT_CHI_SPRITE_SHEET = "/Chi/ChiHit.png";


}	