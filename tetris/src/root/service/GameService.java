package root.service;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import root.config.GameConfigRead;
import root.dto.GameDto;
import root.dto.PlayerDto;
import root.model.GameAct;

/**
 * 游戏逻辑业务层
 * @author tuwq
 */
public class GameService {
	// 游戏数据对象
	private GameDto gameDto;
	// 随机数生成器
	private Random random = new Random();
	// 方块种类个数  + 1(下标)
	private static final int MAX_ACT_TYPE = 6;
	// 升级行数
	private static final int LEVEL_UP = GameConfigRead.getSystemConfig().getLevelUp();
	// 成功消行得分表
	private static final Map<Integer, Integer> PLUS_POINT = GameConfigRead.getSystemConfig().getPlusPointMap();
	
	public GameService(GameDto gameDto) {
		this.gameDto = gameDto;
		GameAct gameAct = new GameAct(random.nextInt(MAX_ACT_TYPE));
		gameDto.setGameAct(gameAct);
	}

	/**
	 * 方向键上
	 */
	public void keyUp() {
		this.gameDto.getGameAct().round(this.gameDto.getGameMap());
	}

	/**
	 * 方向键下
	 * 落地坐标系堆积入游戏地图
	 * 能否消行
	 * 增加经验值
	 * 算分操作
	 * 能否升级
	 * 创建下一个方块
	 * 随机生成再一下方块
	 */
	public void keyDown() {
		if(this.gameDto.getGameAct().move(0, 1, this.gameDto.getGameMap())) {
			return;
		}
		boolean[][] gameMap = this.gameDto.getGameMap();
		Point[] actPoints = this.gameDto.getGameAct().getActPoints();
		for (int i = 0; i < actPoints.length; i++) {
			gameMap[actPoints[i].x][actPoints[i].y] = true;
		}
		
		int removeLineNumber = this.getRemoveLineNumber();
		if (removeLineNumber > 0) {
			this.plusPoint(removeLineNumber);
		}
		this.gameDto.getGameAct().init(this.gameDto.getNext());
		this.gameDto.setNext(random.nextInt(MAX_ACT_TYPE));
	}

	/**
	 * 加分升级操作
	 * @param removeLineNumber
	 */
	private void plusPoint(int plusExp) {
		int nowLevel = this.gameDto.getNowLevel();
		int nowRemoveLine = this.gameDto.getNowRemoveLine();
		int nowPoint = this.gameDto.getNowPoint();
		if (nowRemoveLine % LEVEL_UP + plusExp >= LEVEL_UP) {
			this.gameDto.setNowLevel(++nowLevel);
		}
		this.gameDto.setNowRemoveLine(nowRemoveLine + plusExp);
		if (PLUS_POINT.containsKey(plusExp)) {
			this.gameDto.setNowPoint(nowPoint + PLUS_POINT.get(plusExp));
		}
	}
	
	/**
	 * 消行数得分
	 * @param removeLineNumber
	 * @return
	 */
	private int getPlusExp(int removeLineNumber) {
		return removeLineNumber;
	}

	/**
	 * 方向键左
	 */
	public void keyLeft() {
		this.gameDto.getGameAct().move(-1, 0, this.gameDto.getGameMap());
	}

	/**
	 * 方向键右
	 */
	public void keyRight() {
		this.gameDto.getGameAct().move(1, 0, this.gameDto.getGameMap());
	}
	
	/**
	 * 判断消行数量
	 * 返回成功消行数
	 * 逐行扫描
	 * 当前行有一个false不满足条件
	 */
	private int getRemoveLineNumber() {
		boolean[][] gameMap = this.gameDto.getGameMap();
		int getRemoveLineNumber = 0;
		for (int y = 0; y < this.gameDto.GAMEZONE_H; y++) {
			if (this.isCanRemoveLine(y, gameMap)) {
				this.removeLine(y, gameMap);
				getRemoveLineNumber++;
			}
		}
		return getRemoveLineNumber;
	}
	
	/**
	 * 消行处理
	 * 从下至上往下移动
	 */
	private void removeLine(int rowNumber, boolean[][] gameMap) {
		for (int x = 0; x < this.gameDto.GAMEZONE_W; x++) {
			for (int y = rowNumber; y > 0; y--) {
				gameMap[x][y] = gameMap[x][y - 1];
			}
			gameMap[x][0] = false;
		}
	}

	/**
	 * 判断某一行能否可消
	 * @param y
	 * @return
	 */
	private boolean isCanRemoveLine(int y, boolean[][] gameMap) {
		for (int x = 0; x < this.gameDto.GAMEZONE_W; x++) {
			if (!gameMap[x][y]) {
				return false;
			}
		}
		return true;
	}

	// TODO 测试等级提升
	public void testLevelUp() {
		int point = this.gameDto.getNowPoint();
		int rmline = this.gameDto.getNowRemoveLine();
		int level = this.gameDto.getNowLevel();
		point+=10;
		rmline+=1;
		if (rmline % 20 == 0) {
			level += 1;
		}
		this.gameDto.setNowPoint(point);
		this.gameDto.setNowLevel(level);
		this.gameDto.setNowRemoveLine(rmline);
	}
	
	public void setDbRecode(List<PlayerDto> players) {
		this.gameDto.setDbRecode(players);
	}
	
	public void setDiskRecode(List<PlayerDto> players) {
		this.gameDto.setDiskRecode(players);
	}
	
}
