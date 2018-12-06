package root.dto;

import java.util.List;

import root.model.GameAct;
/**
 * 游戏数据传输对象
 * @author tuwq
 */
public class GameDto {
	
	private List<PlayerDto> dbRecode;
	
	private List<PlayerDto> diskRecode;
	
	private boolean[][] gameMap;
	
	private GameAct gameAct;
	
	private int next;
	
	private int nowLevel;
	
	private int nowPoint;
	
	private int nowRemoveLine;

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
