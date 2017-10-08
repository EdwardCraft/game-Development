import FrameWork.Window;
import util.Constants;
import Logic.RecycleGame;
public class Game{

	public static void main(String[] args) {
		new Window(
			Constants.GAME_WIDTH, 
			Constants.GAME_HEIGHT, 
			"RecycleGame", 
			new RecycleGame()
			);
	}

}