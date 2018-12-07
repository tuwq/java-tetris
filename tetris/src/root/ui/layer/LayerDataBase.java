package root.ui.layer;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import root.ui.Img;

public class LayerDataBase extends Layer {
	
	public LayerDataBase(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		g.drawImage(Img.DB, this.x + PADDING, this.y + PADDING, null);
	}
}
