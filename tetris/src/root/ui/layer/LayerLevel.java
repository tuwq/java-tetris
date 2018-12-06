package root.ui.layer;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerLevel extends Layer {

	private static Image IMG_LV = new ImageIcon("graphics/default/string/level.png").getImage();
	
	public LayerLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		g.drawImage(IMG_LV, this.x + PADDING, this.y + PADDING, null);
	}
	
}
