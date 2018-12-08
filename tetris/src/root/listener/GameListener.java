package root.listener;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import root.config.DataConfig;
import root.config.GameConfigRead;
import root.config.model.DataDaoConfigModel;
import root.dao.DataDao;
import root.dao.impl.DataDefaultDaoImpl;
import root.dao.impl.DataBaseDaoImpl;
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
	// 开启数据库
	private boolean isOpen = false;
	
	public GameListener(PanelGame panelGame, GameService gameService) {
		this.panelGame = panelGame;
		this.gameService = gameService;
		DataConfig dataConfig = GameConfigRead.getDataConfig();
		this.isOpen = dataConfig.isOpenDataBase();
		this.dataBaseDao = this.isOpen?
						createDataDaoObject(dataConfig.getDbDaoImpl())
						:createDataDaoObject(dataConfig.getDefaultDaoImpl());
		this.diskDao = createDataDaoObject(dataConfig.getDiskDaoImpl());
		this.gameService.setDbRecode(this.dataBaseDao.loadData());
		this.gameService.setDiskRecode(this.diskDao.loadData());
	}
	
	/**
	 * 创建数据源对象
	 * @param dataDaoConfigModel
	 * @return
	 */
	private DataDao createDataDaoObject(DataDaoConfigModel dataDaoConfigModel) {
		try {
			Class<?> cls = Class.forName(dataDaoConfigModel.getClassName());
			Constructor<?> ctr = cls.getConstructor(HashMap.class);
			return (DataDao) ctr.newInstance(dataDaoConfigModel.getParamMap());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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

	/**
	 * 玩家操作控制的方法映射
	 * @param keyCode
	 */
	public void actionByKeyCode(int keyCode) {
		
	}
	
}
