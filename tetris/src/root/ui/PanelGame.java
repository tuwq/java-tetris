package root.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import root.ui.model.Lay;

public class PanelGame extends JPanel {
	
	private Lay lay1 = new Lay(128, 32, 320, 576);
	/**
	 * 绘制单个框
	 */
	@Override
	protected void paintComponent(Graphics g) {
		lay1.createWindow(g);
	}
}
