package root.config;

import org.dom4j.DocumentException;

/**
 * 配置文件工厂
 * 用于把游戏配置文件单例包装化
 * @author tuwq
 *
 */
public class ConfigFactory {

	private static GameConfigRead GAME_CONFIG_READ = null;
	static {
		try {
			GAME_CONFIG_READ = new GameConfigRead();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static GameConfigRead getGameConfigRead() {
		return GAME_CONFIG_READ;
	}

}
