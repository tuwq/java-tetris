package root.service;

import java.awt.Point;
import java.util.Random;

import root.dto.GameDto;
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
	// 方块种类个数  + 1
	private static final int MAX_ACT_TYPE = 6;
	
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
	 * 算分操作
	 * 能否升级
	 * 刷新一个新方块
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
		this.gameDto.getGameAct().init(random.nextInt(MAX_ACT_TYPE));
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
	
}
