package root.config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import root.config.model.LayerConfigModel;

/**
 * 读取窗口和视图层的配置
 * @author tuwq
 */
public class FrameConfig {

	private final int width;
	private final int height;
	private final int border;
	private final int padding;
	private final String title;
	private final int windowUp;
	private final int sizeRol;
	// 游戏失败图片
	private final int loseIdx;
	
	private List<LayerConfigModel> layersConfigModelList;
	
	public FrameConfig(Element frame) {
		this.width = Integer.parseInt(frame.attributeValue("width"));
		this.height = Integer.parseInt(frame.attributeValue("height"));
		this.border = Integer.parseInt(frame.attributeValue("border"));
		this.padding = Integer.parseInt(frame.attributeValue("padding"));
		this.title = frame.attributeValue("title");
		this.windowUp = Integer.parseInt(frame.attributeValue("windowUp"));
		this.sizeRol = Integer.parseInt(frame.attributeValue("sizeRol"));
		this.loseIdx = Integer.parseInt(frame.attributeValue("loseIdx"));
		List<Element> layers = frame.elements("layer");
		layersConfigModelList = new ArrayList<LayerConfigModel>();
		for (Element layer : layers) {
			LayerConfigModel layerConfigModel = new LayerConfigModel(
					layer.attributeValue("className"),
					Integer.parseInt(layer.attributeValue("x")),Integer.parseInt(layer.attributeValue("y")),
					Integer.parseInt(layer.attributeValue("w")),Integer.parseInt(layer.attributeValue("h")));
			layersConfigModelList.add(layerConfigModel);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getBorder() {
		return border;
	}

	public int getPadding() {
		return padding;
	}

	public List<LayerConfigModel> getLayersConfigModelList() {
		return layersConfigModelList;
	}

	public String getTitle() {
		return title;
	}

	public int getWindowUp() {
		return windowUp;
	}

	public int getSizeRol() {
		return sizeRol;
	}

	public int getLoseIdx() {
		return loseIdx;
	}
	
}
