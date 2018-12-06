package root.ui.layer;

import java.awt.Font;
import java.awt.Graphics;

public class LayerGame extends Layer {
	
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		g.setFont(new Font("黑体", Font.BOLD, 64));
		String nowPoint = Integer.toString(this.gameDto.getNowPoint());
		g.drawString(nowPoint, this.x + PADDING, this.y + PADDING + 64);
	}
}
