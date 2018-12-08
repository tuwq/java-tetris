package root.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import root.config.FrameConfig;
import root.config.GameConfigRead;

/**
 * 主窗口
 * @author tuwq
 */
public class FrameGame extends JFrame {
		
	/**
	 * 标题
	 * 关闭属性
	 * 窗口大小
	 * 禁止拖动
	 * 获得显示器大小使主窗口居中 x,y=(显示器宽-主窗口宽)/2
	 * 设置主面板
	 * 显示窗口
	 */
	public FrameGame(PanelGame panelGame) {
		FrameConfig frameConfig = GameConfigRead.getFrameConfig();
		this.setTitle(frameConfig.getTitle());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(frameConfig.getWidth(), frameConfig.getHeight());
		this.setResizable(false);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = screen.width - this.getWidth() >> 1 ;
		int y = (screen.height - this.getHeight() >> 1) - frameConfig.getWindowUp();
		this.setLocation(x, y);
		this.setContentPane(panelGame);;
		this.setVisible(true);
	}
}
