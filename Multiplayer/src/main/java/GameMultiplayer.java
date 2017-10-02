

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import io.socket.client.Socket;
import io.socket.client.IO;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.Constants;
import util.Enums.ObjectId;
import java.util.HashMap;
import util.Enums.Facing;

public class GameMultiplayer extends Canvas implements Runnable{

	private boolean running;
	private static Thread thread;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	private Graphics2D graphics2D;
	private Socket socket;
	private Assets assets;
	private Player player;
	private Dog dog;	
	private float timerServer;
	//private Player coopPlayer;
	private HashMap<String, Player> friendlyPlayers;

	public GameMultiplayer(){
		running = false;
		assets = new Assets();
		friendlyPlayers = new HashMap<String, Player>();
		connectSocket();
		configSocketEvents();
	}

	public void startKeyListener(Player player){
		if(player == null){
			System.out.println(" : ( ");
		}else{
			System.out.println(" Fuck yea!!");
			this.addKeyListener(new KeyInput(player));
		}
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
		delta += (now - lastTime) / (1000000000 / 60.0f);
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

		updateSeerver(delta);

		if(player != null){
			player.update(delta);
		}

		if(friendlyPlayers != null){
			for(HashMap.Entry<String, Player> entry : friendlyPlayers.entrySet()){
				entry.getValue().update(delta);
			}
		}


	}


	private void render(){

		bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null){
			this.createBufferStrategy(3);
			return;
		}

		graphics = bufferStrategy.getDrawGraphics();
	    graphics2D = (Graphics2D) graphics;
	    graphics.setColor(new Color( 0, 0, 0));
	    graphics.fillRect(0, 0, getWidth(), getHeight());

	    if(player != null){
	    	 player.render(graphics);
	    }
	  	

	   	for(HashMap.Entry<String, Player> entry : friendlyPlayers.entrySet()){
			entry.getValue().render(graphics);
		}

	

	    dispose();
		bufferStrategy.show();

	}


	private void dispose(){
		graphics.dispose();
	}



	public void updateSeerver(double delta){
		timerServer += (float)delta;
		if((timerServer >= Constants.UPDATE_TIMER) && (player != null )){
			JSONObject data = new JSONObject();
			try{
				data.put("x", player.getX());
				data.put("y", player.getY());
				 if(player.getFacing() == Facing.Left){
				 	data.put("facing", 1);
				 }else{
				 	data.put("facing", 0);
				 }
				 data.put("velocityX", player.getVelocityX());
				 socket.emit("playerMove", data);
				 
			} catch(JSONException e){
				System.out.println(e);
			}
		}

	}


	public void connectSocket(){
		try{
			socket = IO.socket("http://localhost:8080");
			socket.connect();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public void configSocketEvents(){
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener(){
			@Override
			public void call(Object... args){
				System.out.println("Connected");
				player = new Player(Constants.PLAYER_RECTANGLE_WIDTH, 
							Constants.GAME_HEIGHT - Constants.PLAYER_RECTANGLE_HEIGHT,
							ObjectId.Player, assets,1);
				startKeyListener(player);

			}
		}).on("socketID" , new Emitter.Listener(){
			@Override
			public void call(Object... args){
				JSONObject data = (JSONObject) args[0];
				try{
					String id = data.getString("id");
					System.out.println(" My ID: " + id);

				}catch(JSONException e){
					System.out.println("erro getting id");
				}
			}
		}).on("newPlayer", new Emitter.Listener(){
			@Override
			public void call(Object... args){
				JSONObject data = (JSONObject) args[0];
				try{
					String playerId = data.getString("id");
					System.out.println(" new Player ID: " + playerId);

					friendlyPlayers.put( playerId, new Player( Constants.PLAYER_RECTANGLE_WIDTH, 
							Constants.GAME_HEIGHT - Constants.PLAYER_RECTANGLE_HEIGHT,
							ObjectId.Player, assets,1));

				}catch(JSONException e){
					System.out.println("erro getting new player id");
				}
			}
		}).on("playerDisconnected", new Emitter.Listener(){
			@Override
			public void call(Object... args){
				JSONObject data = (JSONObject) args[0];
				try{
					String id = data.getString("id");
					System.out.println(" Player disconnected ID: " + id);
					friendlyPlayers.remove(id);

				}catch(JSONException e){
					System.out.println("error removing new player id");
				}
			}
		}).on("playerMove", new Emitter.Listener(){
			@Override
			public void call(Object... args){
				JSONObject data = (JSONObject) args[0];
				try{
					String playerId = data.getString("id");
					Double x = data.getDouble("x");
					Double y = data.getDouble("y");
					Double facing = data.getDouble("facing");
					float velocityX = ((Double)data.getDouble("velocityX")).floatValue();

					if(friendlyPlayers.get(playerId) != null){
						friendlyPlayers.get(playerId).setX(x.floatValue());
						friendlyPlayers.get(playerId).setY(y.floatValue());
						if(facing == 1){
							friendlyPlayers.get(playerId).setFacing(Facing.Left);
						}else{
							friendlyPlayers.get(playerId).setFacing(Facing.Right);
						}
						friendlyPlayers.get(playerId).setVelocityX(velocityX);
					
					}
				}catch(JSONException e){
					System.out.println(e);
				}
			}
		}).on("getPlayers", new Emitter.Listener(){
			@Override
			public void call(Object... args){
				JSONArray objects = (JSONArray) args[0];
				try{
					for(int i = 0; i < objects.length(); i++){
						float x = ((Double)objects.getJSONObject(i).getDouble("x")).floatValue();
						float y = ((Double)objects.getJSONObject(i).getDouble("y")).floatValue();
						Player coopPlayer = new Player( x, y, ObjectId.Player, assets,1);
						friendlyPlayers.put(objects.getJSONObject(i).getString("id"), coopPlayer);
					}
				}catch(JSONException e){
					System.out.println(e);
				}
			}
		});
	}


}