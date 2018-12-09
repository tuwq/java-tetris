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

import root.config.FrameConfig;
import root.config.GameConfigRead;
import root.config.model.LayerConfigModel;
import root.controller.PlayerController;
import root.dto.GameDto;
import root.listener.GameListener;
import root.service.GameService;
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
 * 游戏主面板
 * @author tuwq
 *
 */
public class PanelGame extends JPanel {
	
	// 视图层列表
	private List<Layer> layers = null;
	// 游戏数据对象
	private GameDto gameDto = null;
	
	private JButton btnStart;
	
	private JButton btnSetting;
	
	private static final int BTN_SIZE_W = GameConfigRead.getFrameConfig().getFrameButtonConfig().getButtonW();
	private static final int BTN_SIZE_H = GameConfigRead.getFrameConfig().getFrameButtonConfig().getButtonH();
	/**
	 * 初始化按钮
	 * 初始化组件
	 * 初始化视图层
	 * @param gameDto 
	 */
	public PanelGame(GameDto gameDto) {
		this.gameDto = gameDto;
		this.initLayers();
		this.initButton();
	}
	
	/**
	 * 初始化开始和设置按钮
	 */
	private void initButton() {
		this.setLayout(null);
		this.btnStart = new JButton(Img.BTN_START);
		btnStart.setBounds(
				GameConfigRead.getFrameConfig().getFrameButtonConfig().getStartX(), 
				GameConfigRead.getFrameConfig().getFrameButtonConfig().getStartY(), 
				BTN_SIZE_W, BTN_SIZE_H);
		this.add(btnStart);
		btnStart.setContentAreaFilled(false);
		btnStart.setBorder(null);
		this.btnSetting = new JButton(Img.BTN_SETTING);
		btnSetting.setBounds(
				GameConfigRead.getFrameConfig().getFrameButtonConfig().getSettingX(), 
				GameConfigRead.getFrameConfig().getFrameButtonConfig().getSettingY(),
				BTN_SIZE_W, BTN_SIZE_H);
		this.add(btnSetting);
		btnSetting.setContentAreaFilled(false);
		btnSetting.setBorder(null);
		this.btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		this.btnSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	/**
	 * 设置玩家控制器
	 */
	public void setPlayerController(PlayerController playerController) {
		this.addKeyListener(playerController);
	}
	
	/**
	 * 初始化视图层
	 * 读取配置文件
	 * 反射创建视图层
	 */
	private void initLayers() {
		try {
			FrameConfig frameConfig = GameConfigRead.getFrameConfig();
			List<LayerConfigModel> layersConfigModelList = frameConfig.getLayersConfigModelList();
			layers = new ArrayList<Layer>(layersConfigModelList.size());
			for (LayerConfigModel layerConfigModel : layersConfigModelList) {
				Class<?> c = Class.forName(layerConfigModel.getClassName());
				Constructor<?> ctr = c.getConstructor(int.class, int.class, int.class, int.class);
				Layer layer = (Layer) ctr.newInstance(layerConfigModel.getX(), layerConfigModel.getY(), layerConfigModel.getW(), layerConfigModel.getH());
				layer.setGameDto(this.gameDto);
				layers.add(layer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 绘制视图层
	 * 返回本模板的焦点
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.size(); layers.get(i++).paintWindow(g));
		this.requestFocus();
	}
}
