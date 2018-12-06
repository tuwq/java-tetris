package root.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 主frame
 * @author tuwq
 */
public class FrameGame extends JFrame {
		
	/**
	 * 标题
	 * 关闭属性
	 * 窗口大小
	 * 禁止拖动
	 * 居中
	 * 获得显示器大小
	 */
	public FrameGame() {
		this.setTitle("Java俄罗斯方块");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1168, 680);
		this.setResizable(false);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (int) ((screen.getWidth() - this.getWidth())/2) ;
		int y = (int) ((screen.getHeight() - this.getHeight())/2 - 32);
		this.setLocation(x, y);
		this.setContentPane(new PanelGame());;
	}
}
