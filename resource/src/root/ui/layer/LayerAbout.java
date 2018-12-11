package root.ui.layer;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import root.ui.Img;

public class LayerAbout extends Layer {
	
	public LayerAbout(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		this.drawImageAtCenter(Img.ABOUT, g);
	}
}
