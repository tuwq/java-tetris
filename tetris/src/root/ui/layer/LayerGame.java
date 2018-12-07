package root.ui.layer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.ImageIcon;

import root.model.GameAct;

public class LayerGame extends Layer {
	
	private static final Image ACT = new ImageIcon("graphics/default/game/rect.png").getImage();
	
	// private static int ACT_SIZE = 32;
	private static final int ACT_SIZE_ROL = 5;
	
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	/**
	 * 打印方块
	 * 打印地图
	 * 根据方块编号渲染颜色
	 */
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		Point[] actPoints = this.gameDto.getGameAct().getActPoints();
		int typeCode = this.gameDto.getGameAct().getTypeCode();
		
		for (int i = 0; i < actPoints.length; i++) {
			this.drawActByPoint(actPoints[i].x, actPoints[i].y, typeCode + 1, g);
		}
		boolean[][] gameMap = this.gameDto.getGameMap();
		for (int x = 0; x < gameMap.length; x++) {
			for (int y = 0; y < gameMap[x].length; y++) {
				if (gameMap[x][y]) {
					this.drawActByPoint(x, y, 0, g);
				}
			}
		}
	}
	
	/**
	 * 绘制正方形块
	 * @param x
	 * @param y
	 * @param imgIdx
	 * @param g
	 */
	private void drawActByPoint(int x, int y, int imgIdx, Graphics g) {
		g.drawImage(ACT, 
				this.x + (x << ACT_SIZE_ROL) + 7, 
				this.y + (y << ACT_SIZE_ROL) + 7, 
				this.x + (x + 1 << ACT_SIZE_ROL) + 7, 
				this.y + (y + 1 << ACT_SIZE_ROL) + 7,
				imgIdx << ACT_SIZE_ROL, 0, (imgIdx + 1) << ACT_SIZE_ROL, 1 << ACT_SIZE_ROL, null);
	}
}
/*g.drawImage(ACT, 
		this.x + actPoints[i].x * ACT_SIZE + 7, 
		this.y + actPoints[i].y * ACT_SIZE + 7, 
		this.x + actPoints[i].x * ACT_SIZE + ACT_SIZE + 7, 
		this.y + actPoints[i].y * ACT_SIZE + ACT_SIZE + 7,
(typeCode + 1) * 32, 0, (typeCode + 1) * ACT_SIZE + ACT_SIZE, ACT_SIZE, null);*/

/*boolean[][] gameMap = this.gameDto.getGameMap();
for (int x = 0; x < gameMap.length; x++) {
	for (int y = 0; y < gameMap[x].length; y++) {
		if (gameMap[x][y]) {
			g.drawImage(ACT, 
					this.x + x * ACT_SIZE + 7, 
					this.y + y * ACT_SIZE + 7, 
					this.x + x * ACT_SIZE + ACT_SIZE + 7, 
					this.y + y * ACT_SIZE + ACT_SIZE + 7,
				0, 0, 32, 32, null);
		}
	}
}*/
