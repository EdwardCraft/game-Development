package CL;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
// when ever a  player pres a  key this class is going to be call
public class keyImput extends KeyAdapter{

	SpaceGame game;

	public keyImput(SpaceGame game){
		this.game=game;
	}

	public void keyPressed(KeyEvent e){
		game.keyPressed(e);

	}

	public void keyReleased(KeyEvent e){
		game.keyReleased(e);

	}





}