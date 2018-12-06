package root.service;

import root.dto.GameDto;

/**
 * 游戏逻辑业务层
 * @author tuwq
 */
public class GameService {
	// 游戏数据对象
	private GameDto gameDto;
	
	public GameService(GameDto gameDto) {
		this.gameDto = gameDto;
	}

	public void gameTest(int keyCode) {
		int temp = gameDto.getNowPoint();
		gameDto.setNowPoint(temp + 1);
	}
}
