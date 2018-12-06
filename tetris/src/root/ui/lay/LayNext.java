package root.ui.lay;

import java.awt.Graphics;

public class LayNext extends Lay {
	
	public LayNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paintWindow(Graphics g) {
		this.createWindow(g);
	}
}
