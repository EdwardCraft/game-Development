
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import util.Enums.Facing;
import util.Constants;

public class KeyInput extends KeyAdapter{
	
	private Player player;

	public KeyInput(Player player){
		this.player = player;
		if(player == null){
			System.out.println(" : (");
		}
	}


	@SuppressWarnings("deprecation")
	public void keyPressed(KeyEvent e){
		
	   int  key  = e.getKeyCode();
	 	if(key == KeyEvent.VK_RIGHT){
	 		
	 			player.setFacing(Facing.Left);
				player.setVelocityX(Constants.PLAYER_MOVEMENT_SPEDD);
	 	

		}
		if(key == KeyEvent.VK_LEFT){
			if(player != null){
				player.setFacing(Facing.Right);
				player.setVelocityX(-Constants.PLAYER_MOVEMENT_SPEDD);
			}

		}


	   if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	   
	}

	public void keyReleased(KeyEvent e){
		int  key  = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			if(player != null){
				player.setVelocityX(0);
			}

		}
		if(key == KeyEvent.VK_LEFT){
			if(player != null){
				player.setVelocityX(0);
			}
	
		}

	}

	
	
}