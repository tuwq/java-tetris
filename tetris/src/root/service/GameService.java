package root.service;

import java.awt.Point;
import java.util.List;
import java.util.Random;

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
		this.gameDto.getGameAct().init(this.gameDto.getNext());
		this.gameDto.setNext(random.nextInt(MAX_ACT_TYPE));
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
