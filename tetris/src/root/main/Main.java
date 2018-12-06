package root.main;

import root.controller.PlayerController;
import root.dto.GameDto;
import root.listener.GameListener;
import root.service.GameService;
import root.ui.FrameGame;
import root.ui.PanelGame;

public class Main {
	
	public static void main(String[] args) {
		GameDto gameDto = new GameDto();
		PanelGame panelGame = new PanelGame(gameDto);
		GameService gameService = new GameService(gameDto);
		GameListener gameListener = new GameListener(panelGame, gameService);
		PlayerController playerController = new PlayerController(gameListener);
		panelGame.setPlayerController(playerController);
		FrameGame frameGame = new FrameGame(panelGame);
	}
	
}