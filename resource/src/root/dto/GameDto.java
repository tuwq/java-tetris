package root.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import root.config.GameConfigRead;
import root.model.GameAct;
import root.util.GameFunction;
/**
 * 游戏数据传输对象
 * @author tuwq
 */
public class GameDto {
	// 游戏地图范围
	public static final int GAMEZONE_W = GameConfigRead.getSystemConfig().getMaxX() + 1;
	public static final int GAMEZONE_H = GameConfigRead.getSystemConfig().getMaxY() + 1;
	/**
	 * 数据库数据
	 */
	private List<PlayerDto> dbRecode;
	/**
	 * 硬盘数据
	 */
	private List<PlayerDto> diskRecode;
	/**
	 * 游戏地图
	 */
	private boolean[][] gameMap;
	/**
	 * 方块对象
	 */
	private GameAct gameAct;
	/**
	 * 下一个方块的code
	 */
	private int next;
	/**
	 * 当前等级
	 */
	private int nowLevel;
	/**
	 * 当前分数
	 */
	private int nowPoint;
	/**
	 * 当前消行数量
	 */
	private int nowRemoveLine;
	/**
	 * 游戏是否开始状态
	 */
	private boolean start;
	/**
	 * 是否显示阴影
	 */
	private boolean showShadow = true;
	/**
	 * 暂停状态
	 */
	private boolean pause;
	/**
	 * 等级加速毫秒值
	 */
	private long sleepTime;

	public GameDto() {
		this.dtoInit();
	}
	
	
	public List<PlayerDto> setFillRecode(List<PlayerDto> players) {
		if (players == null) {
			players = new ArrayList<PlayerDto>();
		}
		while(players.size() < 5) {
			players.add(new PlayerDto("NoData", 0));
		}
		Collections.sort(players);
		return players;
	}
	
	public List<PlayerDto> getDbRecode() {
		return dbRecode;
	}

	public void setDbRecode(List<PlayerDto> dbRecode) {
		this.dbRecode = this.setFillRecode(dbRecode);
	}
	
	public List<PlayerDto> getDiskRecode() {
		return diskRecode;
	}

	public void setDiskRecode(List<PlayerDto> diskRecode) {
		this.diskRecode = this.setFillRecode(diskRecode);
	}

	public boolean[][] getGameMap() {
		return gameMap;
	}

	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}

	public GameAct getGameAct() {
		return gameAct;
	}

	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}


	public int getNowLevel() {
		return nowLevel;
	}

	public void setNowLevel(int nowLevel) {
		this.nowLevel = nowLevel;
		this.sleepTime = GameFunction.getSleepTimeByLevel(this.nowLevel);
	}

	public int getNowPoint() {
		return nowPoint;
	}

	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}

	public int getNowRemoveLine() {
		return nowRemoveLine;
	}

	public void setNowRemoveLine(int nowRemoveLine) {
		this.nowRemoveLine = nowRemoveLine;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isShowShadow() {
		return showShadow;
	}

	public void switchShowShadow() {
		this.showShadow = !this.showShadow;
	}

	public boolean isPause() {
		return pause;
	}

	public void switchPause() {
		this.pause = !this.pause;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	/**
	 * 游戏数据初始化
	 */
	public void dtoInit() {
		this.gameMap = new boolean[GAMEZONE_W][GAMEZONE_H];
		this.nowLevel = 1;
		this.nowPoint = 0;
		this.nowRemoveLine = 0;
		this.pause = false;
		this.sleepTime = GameFunction.getSleepTimeByLevel(1);
	}
	
}
