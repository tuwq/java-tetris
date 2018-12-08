package root.ui.layer;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import root.ui.Img;

public class LayerBackground extends Layer {

	public LayerBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	@Override
	public void paintWindow(Graphics g) {
		int bgIndex = this.gameDto.getNowLevel() % Img.BG_LIST.size();
		g.drawImage(Img.BG_LIST.get(bgIndex), 0, 0, 1162, 654, null);
	}
	
}
