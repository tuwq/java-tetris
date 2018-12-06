package root.ui.lay;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayBackground extends Lay {

	private static Image IMG_BG_TEMP = new ImageIcon("graphics/default/background/bg01.jpg").getImage();
	
	public LayBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void paintWindow(Graphics g) {
		g.drawImage(IMG_BG_TEMP, 0, 0, 1162, 654, null);
	}
	
}
