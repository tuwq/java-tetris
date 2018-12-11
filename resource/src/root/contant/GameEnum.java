package root.contant;

/***
 * 一些全局枚举常量
 * @author tuwq
 */
public enum GameEnum {
	
	CONFIG_FILE_PATH("config/cfg.xml", "游戏核心配置文件的位置"),
	CONTROL_FILE_PATH("setting/control.dat", "玩家控制设置的配置文件文件位置");
	
	private String content;
	private String description;
	
	GameEnum(String content, String description) {
		this.content = content;
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public String getDescription() {
		return description;
	}
	
}
