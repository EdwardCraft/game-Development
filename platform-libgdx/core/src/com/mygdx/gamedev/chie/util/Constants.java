package com.mygdx.gamedev.chie.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;



/**
 * Created by PEDRO on 11/04/2016.
 */
public class Constants {
    // Game settings
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final float WORLD_SIZE = 128;
    public static final float WORLD_SIZE_HEIGHT = 1920;
    public static final float WORLD_SIZE_WIDTH = 1080;
    public static final String TEXTURE_ATLAS_1 = "images/chie.pack.atlas";
    public static final float GRAVITY = 1000;
    public static final float KILL_PLANE = -100;

    //Chie
    public static final float CHIE_SIZE_WIDTH = 27;
    public static final float CHIE_SIZE_HEIGHT = 30;
    public static final Vector2 CHIE_EYE_POSITION= new Vector2( 16, 30);
    public static final float CHIE_MOVE_SPEED = 150;
    public static final float CHIE_JUMP_SPEED = 250;
    public static final float MAX_JUMP_DURATION = .9f;
    public static final float WALK_LOOP_DURATION = 0.05f;
    public static final float FIRE_ANIMATION_DURATION = 0.060f;
    public static final Vector2 NKOCKBACK_VELOCITY = new Vector2(200, 200);
    public static final Vector2 CHIE_CANNON_OFFSET = new Vector2(12, 10);
    public static final Vector2 DEFAULT_SPAWN_LOCATION = new Vector2(100, 60);
    public static final int INITIAL_LIVES = 3;
    public static final int INITIAL_AMMO = 3;

    // Onscreen Controls

    public static final float ONSCREEN_CONTROLS_VIEWPORT_SIZE = 200;
    public static final String MOVE_LEFT_BUTTON = "button-move-left";
    public static final String MOVE_RIGHT_BUTTON = "button-move-right";
    public static final String SHOOT_BUTTON = "button-shoot";
    public static final String JUMP_BUTTON = "button-jump";
    public static final Vector2 BUTTON_CENTER = new Vector2(15, 15);
    public static final float BUTTON_RADIUS = 32;

    //Music
    public static final String LEVEL_1_MUSIC = "music/level-1-music.mp3";


    // platform
    public static final int PLATFORM_EDGE = 8;

    //camera
    public static final float CHASE_CAMERA_MOVE_SPEED = 128;

    //hud
    public static final float HUD_VIEWPORT_SIZE = 480;
    public static final float HUD_MARGIN = 20;
    public static final String HUD_AMMO_LABEL = "Ammo: ";
    public static final String HUD_SCORE_LABEL = "SCORE: ";
    public static final int ENEMY_KILL_SCORE = 100;
    public static final int ENEMY_HIT_SCORE = 25;
    public static final int POWERUP_SCORE = 50;


    // Chie Sprites
    public static final String STANDING_RIGHT = "walk-1-right";
    public static final String WALKING_RIGHT = "walk-2-right";
    public static final String WALKING_RIGHT_3 = "walk-3-right";
    public static final String WALKING_RIGHT_4 = "walk-4-right";
    public static final String WALKING_RIGHT_5 = "walk-5-right";
    public static final String WALKING_RIGHT_6 = "walk-6-right";
    public static final String WALKING_RIGHT_7 = "walk-7-right";
    public static final String WALKING_RIGHT_8 = "walk-8-right";

    public static final String JUMPING_RIGHT = "walk-3-right";

    public static final String STANDING_LEFT = "walk-1-left";
    public static final String WALKING_LEFT = "walk-2-left";
    public static final String WALKING_LEFT_3 = "walk-3-left";
    public static final String WALKING_LEFT_4 = "walk-4-left";
    public static final String WALKING_LEFT_5 = "walk-5-left";
    public static final String WALKING_LEFT_6 = "walk-6-left";
    public static final String WALKING_LEFT_7 = "walk-7-left";
    public static final String WALKING_LEFT_8 = "walk-8-left";
    public static final String JUMPING_LEFT = "walk-3-left";

    public static final String FIRE_1_LEFT = "fire-1-left";
    public static final String FIRE_2_LEFT = "fire-2-left";
    public static final String FIRE_3_LEFT = "fire-3-left";
    public static final String FIRE_4_LEFT = "fire-4-left";
    public static final String FIRE_5_LEFT = "fire-5-left";
    public static final String FIRE_6_LEFT = "fire-6-left";
    public static final String FIRE_7_LEFT = "fire-7-left";
    public static final String FIRE_8_LEFT = "fire-8-left";
    public static final String FIRE_9_LEFT = "fire-9-left";
    public static final String FIRE_10_LEFT = "fire-10-left";
    public static final String FIRE_11_LEFT = "fire-11-left";
    public static final String FIRE_12_LEFT = "fire-12-left";
    public static final String FIRE_13_LEFT = "fire-13-left";

    public static final String FIRE_1_RIGHT = "fire-1-right";
    public static final String FIRE_2_RIGHT = "fire-2-right";
    public static final String FIRE_3_RIGHT = "fire-3-right";
    public static final String FIRE_4_RIGHT = "fire-4-right";
    public static final String FIRE_5_RIGHT = "fire-5-right";
    public static final String FIRE_6_RIGHT = "fire-6-right";
    public static final String FIRE_7_RIGHT = "fire-7-right";
    public static final String FIRE_8_RIGHT = "fire-8-right";
    public static final String FIRE_9_RIGHT = "fire-9-right";
    public static final String FIRE_10_RIGHT = "fire-10-right";
    public static final String FIRE_11_RIGHT = "fire-11-right";
    public static final String FIRE_12_RIGHT = "fire-12-right";
    public static final String FIRE_13_RIGHT = "fire-13-right";

    // platform sprites
    public static final String PLATFORM_SPRITE = "platform-Neon-7";

    //enemy
    public static final String ENEMy_SPRITE = "enemy";
    public static final int ENEMY_HEALTH = 5;
    public static final float ENEMY_SHOT_RADIUS = 17;
    public static final Vector2 ENEMY_CENTER =  new Vector2( 14, 22);
    public static final float ENEMY_MOVEMENT_SPEED = 10;
    public static final float ENEMY_BOB_AMPLITUDE = 2;
    public static final float ENEMY_BOB_PERIOD = 1.0f;
    public static final float ENEMY_COLLISION_RADIUS = 15;

    //chie blast and power ups
    public static final String BULLET_SPRITE = "blast-1-sprite";
    public static final String BULLET_SPRITE_LEFT = "blast-1-left";
    public static final Vector2 BULLET_CETER = new Vector2( 3, 2);

    //explosion
    public static final String EXPLOSION_LARGE = "explosion-large";
    public static final String EXPLOSION_MEDIUM = "explosion-medium";
    public static final String EXPLOSION_SMALL = "explosion-small";
    public static final Vector2 EXPLOSION_CENTER = new Vector2(8, 8);
    public static final float EXPLOSION_DURATION = 0.5f;

    //power up
    public static final String POWERUP_SPRITE = "powerup";
    public static final Vector2 POWERUP_CENTER = new Vector2(7, 5);
    public static final float BULLET_MOVE_SPEED = 150;
    public static final int POWER_UP_AMMO = 2;


    //Level Loading
    public static final String LEVEL_DIR = "levels";
    public static final String LEVEL_FILE_EXTENSION = "dt";
    public static final String LEVEL_COMPOSITE = "composite";
    public static final String LEVEL_9PATCHES = "sImage9patchs";
    public static final String LEVEL_IMAGES = "sImages";
    public static final String LEVEL_ERROR_MESSAGE = "The was a problem loading the level :";
    public static final String LEVEL_IMAGENAME_KEY = "imageName";
    public static final String LEVEL_X_KEY = "x";
    public static final String LEVEL_Y_KEY = "y";
    public static final String LEVEL_WIDTH_KEY = "width";
    public static final String LEVEL_HEIGHT_KEY = "height";
    public static final String LEVEL_IDENTIFIER_KEY = "itemIdentifier";
    public static final String LEVEL_ENEMY_TAG = "Enemy";
    public static final String LEVELS = "levels/LevelOne.dt";

    // Exit Portal
    public static final float EXIT_PORTAL_FRAME_DURATION = 0.1f;
    public static final float EXIT_PORTAL_RADIUS = 28;
    public static final Vector2 EXIT_PORTAL_DEFAULT_LOCATION =  new Vector2(200, 200);
    public static final String EXIT_PORTAL_SPRITE_1 = "exit-portal-1";
    public static final String EXIT_PORTAL_SPRITE_2 = "exit-portal-2";
    public static final String EXIT_PORTAL_SPRITE_3 = "exit-portal-3";
    public static final String EXIT_PORTAL_SPRITE_4 = "exit-portal-4";
    public static final String EXIT_PORTAL_SPRITE_5 = "exit-portal-5";
    public static final String EXIT_PORTAL_SPRITE_6 = "exit-portal-6";
    public static final Vector2 EXIT_PORTAL_CENTER = new Vector2(31, 31);

    //vicotry/Game over
    public static final float LEVEL_END_DURATION = 5;
    public static final String VICTORY_MESSAGE = "You Winrar!";
    public static final String GAME_OVER_MESSAGE = "GAME OVER";
    public static final int EXPLOSION_COUNT = 500;
    public static final int ENEMY_COUNT = 200;
    public static final String FONT_FILE = "font/header.fnt";





}
