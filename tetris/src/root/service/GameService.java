package root.service;

import java.awt.Point;

import root.dto.GameDto;
import root.model.GameAct;

/**
 * 游戏逻辑业务层
 * @author tuwq
 */
public class GameService {
	// 游戏数据对象
	private GameDto gameDto;
	
	public GameService(GameDto gameDto) {
		this.gameDto = gameDto;
		GameAct gameAct = new GameAct();
		gameDto.setGameAct(gameAct);
	}

	/**
	 * 方向键上
	 */
	public void keyUp() {
		this.gameDto.getGameAct().round();
	}

	/**
	 * 方向键下
	 * 落地坐标系添入游戏地图
	 * 立刻下落
	 */
	public void keyDown() {
		if(!this.gameDto.getGameAct().move(0, 1)) {
			boolean[][] gameMap = this.gameDto.getGameMap();
			Point[] actPoints = this.gameDto.getGameAct().getActPoints();
			for (int i = 0; i < actPoints.length; i++) {
				gameMap[actPoints[i].x][actPoints[i].y] = true;
			}
		}
	}

	/**
	 * 方向键左
	 */
	public void keyLeft() {
		this.gameDto.getGameAct().move(-1, 0);
	}

	/**
	 * 方向键右
	 */
	public void keyRight() {
		this.gameDto.getGameAct().move(1, 0);
	}
	
}
