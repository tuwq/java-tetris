package root.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.lang.reflect.Constructor;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import root.config.ConfigFactory;
import root.config.GameConfigRead;
import root.config.model.LayerConfigModel;
import root.ui.layer.Layer;
import root.ui.layer.LayerBackground;
import root.ui.layer.LayerButton;
import root.ui.layer.LayerDataBase;
import root.ui.layer.LayerDisk;
import root.ui.layer.LayerGame;
import root.ui.layer.LayerLevel;
import root.ui.layer.LayerNext;
import root.ui.layer.LayerPoint;

/**
 * 主panel
 * @author tuwq
 *
 */
public class PanelGame extends JPanel {
	
	// 子窗口列表
	private List<Layer> layers = null;
	/**
	 * 读取配置文件
	 * 反射创建子窗口
	 */
	public PanelGame() {
		try {
			GameConfigRead gameConfigRead = ConfigFactory.getGameConfigRead();
			List<LayerConfigModel> layersConfigModelList = gameConfigRead.getFrameConfig().getLayersConfigModelList();
			layers = new ArrayList<Layer>(layersConfigModelList.size());
			for (LayerConfigModel layerConfigModel : layersConfigModelList) {
				Class<?> c = Class.forName(layerConfigModel.getClassName());
				Constructor<?> ctr = c.getConstructor(int.class, int.class, int.class, int.class);
				Layer layer = (Layer) ctr.newInstance(layerConfigModel.getX(), layerConfigModel.getY(), layerConfigModel.getW(), layerConfigModel.getH());
				layers.add(layer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 绘制子窗口
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.size(); layers.get(i++).paintWindow(g));
	}
}
