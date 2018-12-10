package root.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import root.contant.GameEnum;

/**
 * 读取游戏配置文件
 * @author tuwq
 */
public class GameConfigRead {
	
	// 窗口配置
	private static FrameConfig FRAME_CONFIG;
	// 系统配置
	private static SystemConfig SYSTEM_CONFIG;
	// 数据配置
	private static DataConfig DATA_CONFIG;

	static {
		try {
			SAXReader read = new SAXReader();
			Document doc = read.read(GameEnum.CONFIG_FILE_PATH.getContent());
			Element game = doc.getRootElement();
			FRAME_CONFIG = new FrameConfig(game.element("frame"));
			SYSTEM_CONFIG = new SystemConfig(game.element("system"));
			DATA_CONFIG = new DataConfig(game.element("data"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
	private GameConfigRead() {};

	public static FrameConfig getFrameConfig() {
		return FRAME_CONFIG;
	}

	public static SystemConfig getSystemConfig() {
		return SYSTEM_CONFIG;
	}

	public static DataConfig getDataConfig() {
		return DATA_CONFIG;
	}

}
