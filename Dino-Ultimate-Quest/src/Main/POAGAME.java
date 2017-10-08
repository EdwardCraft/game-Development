package Main;
import Classes.Game;
import FrameWork.Window;
import Utils.Constants;

public class POAGAME {

	public static void main(String[] args) {
		new Window(
				Constants.GAME_WINDOW_WIDTH, 
				Constants.GAME_WINDOW_HEIGHT,
				Constants.GAME_NAME,
				new Game()
				);

	}

}
