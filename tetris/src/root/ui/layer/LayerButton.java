package root.ui.layer;

import java.awt.Graphics;

public class LayerButton extends Layer {

	public LayerButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paintWindow(Graphics g) {
		this.createWindow(g);
	}
}
