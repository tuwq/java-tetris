package root.ui.lay;

import java.awt.Graphics;

public class LayGame extends Lay {
	
	public LayGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paintWindow(Graphics g) {
		this.createWindow(g);
	}
}
