package root.listener;

import root.service.GameService;
import root.ui.PanelGame;

/**
 * 游戏监听器
 * 监听玩家操作
 * 修改游戏数据并刷新游戏主面板
 * @author tuwq
 */
public class GameListener {
	
	// 游戏主面板
	private PanelGame panelGame;
	// 游戏逻辑业务层
	private GameService gameService;
	
	public GameListener(PanelGame panelGame, GameService gameService) {
		this.panelGame = panelGame;
		this.gameService = gameService;
	}

	public void test(int keyCode) {
		this.gameService.gameTest(keyCode);
		this.panelGame.repaint();
	}
	
}
