package root.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import root.config.GameConfigRead;
import root.model.GameAct;
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

	public GameDto() {
		this.gameDtoInit();
	}
	
	/**
	 * gameDto初始化
	 * 初始化所有游戏对象
	 */
	public void gameDtoInit() {
		this.gameMap = new boolean[GAMEZONE_W][GAMEZONE_H];
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
	
	
}
