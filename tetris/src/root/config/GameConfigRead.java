package root.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 读取游戏配置文件
 * @author tuwq
 */
public class GameConfigRead {
	
	// 窗口配置
	private FrameConfig frameConfig;
	// 系统配置
	private SystemConfig systemConfig;
	// 数据配置
	private DataConfig dataConfig;
	
	public GameConfigRead() throws DocumentException {
		SAXReader read = new SAXReader();
		Document doc = read.read("config/cfg.xml");
		Element game = doc.getRootElement();
		frameConfig = new FrameConfig(game.element("frame"));
		systemConfig = new SystemConfig(game.element("system"));
		dataConfig = new DataConfig(game.element("data"));
	}

	public FrameConfig getFrameConfig() {
		return frameConfig;
	}

	public SystemConfig getSystemConfig() {
		return systemConfig;
	}

	public DataConfig getDataConfig() {
		return dataConfig;
	}

}
