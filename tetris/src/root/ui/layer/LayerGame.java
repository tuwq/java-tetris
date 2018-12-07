package root.ui.layer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class LayerGame extends Layer {
	
	private static Image ACT = new ImageIcon("graphics/default/game/rect.png").getImage();
	
	private static int ACT_SIZE = 32;
	
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		Point[] actPoints = this.gameDto.getGameAct().getActPoints();
		for (int i = 0; i < actPoints.length; i++) {
			g.drawImage(ACT, 
						this.x + actPoints[i].x * ACT_SIZE, 
						this.y + actPoints[i].y * ACT_SIZE, 
						this.x + actPoints[i].x * ACT_SIZE + ACT_SIZE, 
						this.y + actPoints[i].y * ACT_SIZE + ACT_SIZE,
					32, 0, 64, 32, null);
		}
	}
}
