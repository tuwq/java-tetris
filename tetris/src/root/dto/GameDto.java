package root.dto;

import java.util.List;

import root.model.GameAct;
/**
 * 游戏数据传输对象
 * @author tuwq
 */
public class GameDto {
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
		// TODO 配置
		this.gameMap = new boolean[10][18];
	}
	
	public List<PlayerDto> getDbRecode() {
		return dbRecode;
	}

	public void setDbRecode(List<PlayerDto> dbRecode) {
		this.dbRecode = dbRecode;
	}

	public List<PlayerDto> getDiskRecode() {
		return diskRecode;
	}

	public void setDiskRecode(List<PlayerDto> diskRecode) {
		this.diskRecode = diskRecode;
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
