package root.config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import root.config.model.LayerConfigModel;

/**
 * 读取窗口的配置
 * @author tuwq
 */
public class FrameConfig {

	private int width;
	private int height;
	private int windowSize;
	private int padding;
	
	private List<LayerConfigModel> layersConfigModelList;
	
	public FrameConfig(Element frame) {
		this.width = Integer.parseInt(frame.attributeValue("width"));
		this.height = Integer.parseInt(frame.attributeValue("height"));
		this.windowSize = Integer.parseInt(frame.attributeValue("windowSize"));
		this.padding = Integer.parseInt(frame.attributeValue("padding"));
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

	public int getWindowSize() {
		return windowSize;
	}

	public int getPadding() {
		return padding;
	}

	public List<LayerConfigModel> getLayersConfigModelList() {
		return layersConfigModelList;
	}
	
}
