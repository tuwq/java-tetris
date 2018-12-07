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
		if (this.canMove(0, -1)) {
			this.gameDto.getGameAct().move(0, -1);
		}
	}

	/**
	 * 方向键下
	 */
	public void keyDown() {
		if (this.canMove(0, 1)) {
			this.gameDto.getGameAct().move(0, 1);
		}
	}

	/**
	 * 方向键左
	 */
	public void keyLeft() {
		if (this.canMove(-1, 0)) {
			this.gameDto.getGameAct().move(-1, 0);
		}
	}

	/**
	 * 方向键右
	 */
	public void keyRight() {
		if (this.canMove(1, 0)) {
			this.gameDto.getGameAct().move(1, 0);
		}
	}
	
	/**
	 * 边界判定
	 * 获取移动后的坐标
	 * 循环判断是否在地图范围内  
	 * newX < 0 || newX > 9 || newY < 0 || newY > 17
	 * @param moveX
	 * @param moveY
	 * @return
	 */
	private boolean canMove(int moveX, int moveY) {
		Point[] nowPoints = this.gameDto.getGameAct().getActPoints();
		for (int i = 0; i < nowPoints.length; i++) {
			int newX = nowPoints[i].x + moveX;
			int newY = nowPoints[i].y + moveY;
			if (newX < 0 || newX > 9 || newY < 0 || newY > 17) {
				return false;
			}
		}
		return true;
	}
}
