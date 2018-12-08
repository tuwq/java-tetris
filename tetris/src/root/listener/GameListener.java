package root.listener;

import root.dao.DataDao;
import root.dao.impl.DataTestDao;
import root.dao.impl.DataDiskDaoImpl;
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
	// 数据访问-数据库
	private DataDao dataBaseDao;
	// 数据访问-本地磁盘
	private DataDao diskDao;
	// 数据访问-测试
	private DataDao dataTest;
	
	public GameListener(PanelGame panelGame, GameService gameService) {
		this.panelGame = panelGame;
		this.gameService = gameService;
		this.dataTest = new DataTestDao();
		this.diskDao = new DataDiskDaoImpl();
		this.gameService.setDbRecode(this.dataTest.loadData());
		this.gameService.setDiskRecode(this.diskDao.loadData());
	}
	
	/**
	 * 方向键上
	 */
	public void keyUp() {
		this.gameService.keyUp();
		this.panelGame.repaint();
	}

	/**
	 * 方向键下
	 */
	public void keyDown() {
		this.gameService.keyDown();
		this.panelGame.repaint();
	}

	/**
	 * 方向键左
	 */
	public void keyLeft() {
		this.gameService.keyLeft();
		this.panelGame.repaint();
	}

	/**
	 * 方向键右
	 */
	public void keyRight() {
		this.gameService.keyRight();
		this.panelGame.repaint();
	}

	/**
	 * 测试等级提升
	 */
	public void testLevelUp() {
		this.gameService.testLevelUp();
		this.panelGame.repaint();
	}
	
}
