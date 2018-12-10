package root.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import root.config.DataConfig;
import root.config.GameConfigRead;
import root.config.model.DataDaoConfigModel;
import root.contant.GameEnum;
import root.controller.PlayerController;
import root.dao.DataDao;
import root.dto.GameDto;
import root.dto.PlayerDto;
import root.service.GameService;
import root.ui.FrameGame;
import root.ui.PanelGame;
import root.ui.setting.SavePointFrame;
import root.ui.setting.SettingConfigFrame;

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
	// 游戏显示数据
	private GameDto gameDto;
	// 游戏保存得分窗口
	private SavePointFrame savePointFrame;
	// 玩家设置窗口
	private SettingConfigFrame settingConfigFrame;
	// 玩家操作的行为映射
	private Map<Integer, Method> actionList;
	// 数据访问-数据库
	private DataDao dataBaseDao;
	// 数据访问-本地磁盘
	private DataDao diskDao;
	// 开启数据库
	private boolean isOpenDataBase = false;
	// 游戏方块下落线程
	private Thread gameThread = null;
	
	public GameListener() {
		DataConfig dataConfig = GameConfigRead.getDataConfig();
		this.isOpenDataBase = dataConfig.isOpenDataBase();
		this.dataBaseDao = this.isOpenDataBase?
						createDataDaoObject(dataConfig.getDbDaoImpl())
						:createDataDaoObject(dataConfig.getDefaultDaoImpl());
		this.diskDao = createDataDaoObject(dataConfig.getDiskDaoImpl());
		this.gameDto = new GameDto();
		this.gameDto.setDbRecode(this.dataBaseDao.loadData());
		this.gameDto.setDiskRecode(this.diskDao.loadData());
		
		this.gameService = new GameService(this.gameDto);
		PlayerController playerController = new PlayerController(this);
		this.panelGame = new PanelGame(this.gameDto);
		this.panelGame.setPlayerController(playerController);
		this.savePointFrame = new SavePointFrame(this);
		this.settingConfigFrame = new SettingConfigFrame(this);
		this.setControlSetting();
		new FrameGame(this.panelGame);
		
	}
	
	/**
	 * 读取玩家控制设置
	 */
	private void setControlSetting() {
		this.actionList = new HashMap<Integer, Method>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GameEnum.CONTROL_FILE_PATH.getContent()));
			HashMap<Integer, String> cfgSet = (HashMap) ois.readObject();
			Set<Entry<Integer, String>> entrySet = cfgSet.entrySet();
			for (Entry<Integer, String> e : entrySet) {
				actionList.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
			}
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
	 * 暂停无法行动
	 * @param keyCode
	 */
	public void actionByKeyCode(int keyCode) {
		if(!this.gameDto.isStart()) { return; } 
		try {
			if (this.actionList.containsKey(keyCode)) {
				this.actionList.get(keyCode).invoke(this.gameService);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.panelGame.repaint();
	}

	/**
	 * 开始游戏
	 * 禁止面板按钮点击
	 * 游戏数据初始化
	 * 启动方块下落线程
	 */
	public void start() {
		this.panelGame.buttonSwitch(false);
		// TODO 关闭其他窗口
		this.settingConfigFrame.setVisible(false);
		this.savePointFrame.setVisible(false);
		this.gameService.startGame();
		this.gameThread = new Thread(new MainThread());
		this.gameThread.start();
		panelGame.repaint();
	}
	
	/**
	 * 游戏方块线程
	 * 每隔一段时间下落方块
	 * 是否暂停
	 * 游戏结束
	 * @author tuwq
	 */
	private class MainThread implements Runnable {
		@Override
		public void run() {
			while(gameDto.isStart()) {
				try {
					Thread.sleep(gameDto.getSleepTime());
					if(gameDto.isPause()) {
						continue;
					}
					gameService.mainAction();
					panelGame.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			afterLose();
		}
	}

	/**
	 * 保存分数
	 * @param name
	 */
	public void savePoint(String name) {
		PlayerDto playerDto = new PlayerDto(name, this.gameDto.getNowPoint());
		this.diskDao.saveData(playerDto); 
		this.gameDto.setDiskRecode(this.diskDao.loadData());
		if(this.isOpenDataBase) { 
			this.dataBaseDao.saveData(playerDto); 
			this.gameDto.setDbRecode(this.dataBaseDao.loadData());
		}
		this.panelGame.repaint();
	}
	
	/**
	 * 游戏结束
	 * 保存得分窗口
	 * 使按钮可以点击
	 */
	public void afterLose() {
		this.savePointFrame.showFrame(this.gameDto.getNowPoint());
		this.panelGame.buttonSwitch(true);
	}

	public void showSettingFrame() {
		this.settingConfigFrame.setVisible(true);
	}
	/**
	 * 子窗口关闭事件
	 */
	public void setOver() {
		this.panelGame.repaint();
		this.setControlSetting();;
	}
}
