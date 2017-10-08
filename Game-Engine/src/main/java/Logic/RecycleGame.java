package Logic;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import util.Constants;
import java.awt.Color;
import FrameWork.KeyInput;
import Screens.Menu;
import util.Enums.ScreenState;
import Screens.miniGame;
import Screens.LadderGame; 
import util.Assets;
import Screens.FinishGame;
import Screens.BolleyBall;

public class RecycleGame extends Canvas implements Runnable{

	private boolean running;
	private static Thread thread;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	private Graphics2D graphics2D;
	private ScreenState state;
	private Menu menu;
	private miniGame gameMini;
	private LadderGame ladder;
	private Assets assets;
	private FinishGame finishGame;
	private KeyInput keyInput;
	private BolleyBall bolleyBall;

	public RecycleGame(){
		running = false;
		assets = new Assets();
		bolleyBall = new BolleyBall(assets);
		gameMini = new miniGame(this);
		ladder = new LadderGame(assets, this);
		state = ScreenState.Menu;
		menu = new Menu(this);
		keyInput = new KeyInput( this, menu, gameMini, ladder);
		this.addKeyListener(keyInput);
	}

	public synchronized void start(){

		if(running) return;	

		running = true;
		thread = new Thread(this);
		thread.start(); 

	}

	public void run(){

		this.requestFocus();
		long lastTime = System.nanoTime();
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while(running){
		long now = System.nanoTime();
		delta += (now - lastTime) / Constants.NANO_SECONDS;
		lastTime = now;
		while(delta >= 1){
			update(delta);
			updates++;
			delta--;
		}

		render();
		frames++;
			
		if(System.currentTimeMillis() - timer > 1000){
			timer += 1000;
			frames = 0;
			updates = 0;
			}
		}
	}


	private void update(double delta){

		if(state == ScreenState.Game){
			if(gameMini != null) gameMini.update(delta);
		}

		if(state == ScreenState.Ladder){
			if(ladder != null) ladder.update(delta);
		}

		if(state == ScreenState.Bolley){
			if(bolleyBall != null) bolleyBall.update(delta);
		}
	}


	private void render(){
		bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null){
			this.createBufferStrategy(Constants.BUFFER_STRATEGY);
			return;
		}


		graphics = bufferStrategy.getDrawGraphics();
	    graphics2D = (Graphics2D) graphics;
	    graphics.setColor(new Color( 0, 0, 0));
	    graphics.fillRect(0, 0, getWidth(), getHeight());

		if(state == ScreenState.Menu){
			if(menu != null)menu.render( graphics, getWidth(), getHeight());
		}
		
		if(state == ScreenState.Game){
			if(gameMini != null ) gameMini.render(graphics , getWidth(), getHeight());	
		}
		
		if(state == ScreenState.Ladder){
			if(ladder != null) ladder.render(graphics, getWidth(), getHeight());
		}

		if(state == ScreenState.Death){
			if(finishGame != null) finishGame.render(graphics);
		}

		if(state == ScreenState.Bolley){
			if(bolleyBall != null) bolleyBall.render(graphics);
		}

		bufferStrategy.show();
		dispose();

	}


	private void dispose(){
		graphics.dispose();
	}

	public ScreenState getScreenState(){
		return state;
	}
	
	public void setScreenState(ScreenState state){
		this.state = state;
	}

	public void setFinishScreen(FinishGame  finishGame){
		this.finishGame = finishGame;
	}

	public void setFinishScreenKey(){
		keyInput.setFinishGame(finishGame);
	}

	public void setNewMiniGame(miniGame gameMini){
		this.gameMini = gameMini;
	}

	public void setNewMiniGameKey(){
		keyInput.setMiniGame(gameMini);
	}

	public void setNewLadderGame(LadderGame ladder){
		this.ladder = ladder;
		keyInput.setLadderGame(ladder);
	}


}