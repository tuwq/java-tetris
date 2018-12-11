package root.service;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

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
	// 方块种类个数
	private static final int MAX_ACT_TYPE = GameConfigRead.getSystemConfig().getRects().size() - 1;
	// 升级行数
	private static final int LEVEL_UP = GameConfigRead.getSystemConfig().getLevelUp();
	// 成功消行得分表
	private static final Map<Integer, Integer> PLUS_POINT = GameConfigRead.getSystemConfig().getPlusPointMap();
	
	public GameService(GameDto gameDto) {
		this.gameDto = gameDto;
	}

	/**
	 * 方向键上
	 */
	public boolean keyUp() {
		if (this.gameDto.isPause()) {
			return true;
		}
		synchronized(this.gameDto) {
			this.gameDto.getGameAct().round(this.gameDto.getGameMap());
		}
		return true;
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
	 * 是否失败
	 */
	public boolean keyDown() {
		if (this.gameDto.isPause()) {
			return true;
		}
		synchronized(this.gameDto) {
			if(this.gameDto.getGameAct().move(0, 1, this.gameDto.getGameMap())) {
				return false;
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
			if (this.checkLose()) {
				this.gameDto.setStart(false);
			}
		}
		return true;
	}

	/**
	 * 检查游戏是否失败
	 * 获得现在的活动方块
	 * 获得现在得游戏地图
	 */
	private boolean checkLose() {
		Point[] actionPoints = this.gameDto.getGameAct().getActPoints();
		boolean[][] gameMap = this.gameDto.getGameMap();
		for (int i = 0; i < actionPoints.length; i++) {
			if (gameMap[actionPoints[i].x][actionPoints[i].y]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 加分升级操作
	 * 加快等级加速
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
	public boolean keyLeft() {
		if (this.gameDto.isPause()) {
			return true;
		}
		synchronized(this.gameDto) {
			this.gameDto.getGameAct().move(-1, 0, this.gameDto.getGameMap());
		}
		return true;
	}

	/**
	 * 方向键右
	 */
	public boolean keyRight() {
		if (this.gameDto.isPause()) {
			return true;
		}
		synchronized(this.gameDto) {
			this.gameDto.getGameAct().move(1, 0, this.gameDto.getGameMap());
		}
		return true;
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

	public void levelUp() {
		this.plusPoint(4);
	}
	/**
	 * 瞬间下落
	 */
	public void momentDown() {
		if (this.gameDto.isPause()) {
			return;
		}
		while(!this.keyDown());
	}
	/**
	 * 阴影开关
	 */
	public void switchShadow() {
		this.gameDto.switchShowShadow();
	}
	
	/**
	 * 暂停开关
	 */
	public void switchPause() {
		if (this.gameDto.isStart()) {
			this.gameDto.switchPause();
		}
	}

	/**
	 * 开始游戏
	 * 游戏数据初始化
	 */
	public void startGame() {
		this.gameDto.setNext(random.nextInt(MAX_ACT_TYPE));
		GameAct gameAct = new GameAct(random.nextInt(MAX_ACT_TYPE));
		this.gameDto.setGameAct(gameAct);
		this.gameDto.setStart(true);
		this.gameDto.dtoInit();
	}
	
	/**
	 * 游戏主要行为动作
	 */
	public void mainAction() {
		this.keyDown();
	}
}
