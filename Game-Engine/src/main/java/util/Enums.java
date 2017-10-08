package util;

public class Enums {
	
	public enum RectangleBounds{
		Top,
		Botton,
		Left,
		Right,
		R,
		L,
		M,
		N
	}
	
	public enum Facing{
		Left,
		Right
	}


	public enum BinState {
		PLASTIC,
		FOOD,
		METAL,
		BIN
	}

	public enum ObjectId {
		foodTrash(),
		metalTrash(),
		plasticTrash(),
		foodBin(),
		metalBin(),
		plasticBin(),
		LadderMaster(),
		Lines(),
		Spider(),
		Block(),
		Player(),
		Ball()

	}	


	public enum ScreenState{
		Menu,
		Pause,
		Game,
		Death,
		Ladder,
		Bolley
	}
	
}


