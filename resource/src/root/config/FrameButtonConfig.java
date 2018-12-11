package root.config;

import org.dom4j.Element;

/**
 * 页面按钮配置
 * @author tuwq
 */
public class FrameButtonConfig {
	
	private final int buttonW;
	
	private final int buttonH;
	
	private final int startX;
	
	private final int startY;
	
	private final int settingX;
	
	private final int settingY;
	
	public FrameButtonConfig(Element button) {
		this.buttonW = Integer.parseInt(button.attributeValue("w"));
		this.buttonH = Integer.parseInt(button.attributeValue("h"));
		Element startElement = button.element("start");
		Element settingElement = button.element("setting");
		this.startX = Integer.parseInt(startElement.attributeValue("x"));
		this.startY = Integer.parseInt(startElement.attributeValue("y"));
		this.settingX = Integer.parseInt(settingElement.attributeValue("x"));
		this.settingY = Integer.parseInt(settingElement.attributeValue("y"));
	}

	public int getButtonW() {
		return buttonW;
	}

	public int getButtonH() {
		return buttonH;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getSettingX() {
		return settingX;
	}

	public int getSettingY() {
		return settingY;
	}
	
}
