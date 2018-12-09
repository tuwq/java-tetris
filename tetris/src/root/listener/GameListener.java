package root.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import root.config.DataConfig;
import root.config.GameConfigRead;
import root.config.model.DataDaoConfigModel;
import root.dao.DataDao;
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
	// 玩家操作的映射Map
	private Map<Integer, Method> actionList;
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
		actionList = new HashMap<Integer, Method>();
		try {
			actionList.put(87, this.gameService.getClass().getMethod("keyUp"));
			actionList.put(83, this.gameService.getClass().getMethod("keyDown"));
			actionList.put(65, this.gameService.getClass().getMethod("keyLeft"));
			actionList.put(68, this.gameService.getClass().getMethod("keyRight"));
			actionList.put(38, this.gameService.getClass().getMethod("testLevelUp"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
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
	 * 玩家操作控制的方法映射
	 *  87 83 65 68 38
	 * @param keyCode
	 */
	public void actionByKeyCode(int keyCode) {
		try {
			if (this.actionList.containsKey(keyCode)) {
				this.actionList.get(keyCode).invoke(this.gameService);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.panelGame.repaint();
	}
	
}
