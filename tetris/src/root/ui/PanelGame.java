package root.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import root.ui.model.Lay;

public class PanelGame extends JPanel {
	
	private Lay lay1;
	private Lay lay2;
	private Lay lay3;
	private Lay[] lays = null;
	public PanelGame() {
		lays = new Lay[] {
				new Lay(40, 32, 334, 279),
				new Lay(40, 343, 334, 279),
				new Lay(414, 32, 334, 590),
				new Lay(788, 32, 334, 124),
				new Lay(788, 188, 176, 148),
				new Lay(964, 188, 158, 148),
				new Lay(788, 368, 334, 200)
		};
	}
	/**
	 * 绘制框
	 */
	@Override
	protected void paintComponent(Graphics g) {
		for (Lay lay : lays) {
			lay.createWindow(g);
		}
	}
}
