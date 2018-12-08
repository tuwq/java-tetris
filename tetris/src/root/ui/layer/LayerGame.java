package root.ui.layer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.ImageIcon;

import root.model.GameAct;
import root.ui.Img;

public class LayerGame extends Layer {
	
	private static final int ACT_SIZE_ROL = 5;
	private static final int LEFT_SIDE = 0;
	// TODO
	private static final int RIGHT_SIDE = 9;
	
	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	/**
	 * 打印方块
	 * 打印地图
	 * 绘制阴影
	 * 根据方块编号渲染颜色
	 * 渲染堆积方块的颜色
	 */
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		Point[] actPoints = this.gameDto.getGameAct().getActPoints();
		if (true) {
			this.drawShadow(actPoints, true, g);
		}
		int typeCode = this.gameDto.getGameAct().getTypeCode();
		for (int i = 0; i < actPoints.length; i++) {
			this.drawActByPoint(actPoints[i].x, actPoints[i].y, typeCode + 1, g);
		}
		boolean[][] gameMap = this.gameDto.getGameMap();
		int nowLevel = this.gameDto.getNowLevel();
		int imgIndex1 = nowLevel == 0 ? nowLevel : (nowLevel - 1) % 7 + 1;
		
		for (int x = 0; x < gameMap.length; x++) {
			for (int y = 0; y < gameMap[x].length; y++) {
				if (gameMap[x][y]) {
					this.drawActByPoint(x, y, imgIndex1, g);
				}
			}
		}
	}
	
	/**
	 * 绘制阴影
	 * @param actPoints
	 * @param b
	 */
	private void drawShadow(Point[] actPoints, boolean isShowShadow, Graphics g) {
		if (!isShowShadow) {
			return;
		}
		int leftX = RIGHT_SIDE;
		int rightX = LEFT_SIDE;
		for(Point p : actPoints) {
			leftX = p.x < leftX ? p.x : leftX;
			rightX = p.x > rightX ? p.x : rightX;
		}
		g.drawImage(Img.Shadow, 
				this.x + SIZE + (leftX << ACT_SIZE_ROL),
				this.y + SIZE,
				(rightX - leftX + 1) << ACT_SIZE_ROL,
				this.h - (ACT_SIZE_ROL << 1), 
				null);
	}

	/**
	 * 绘制正方形块
	 * @param x
	 * @param y
	 * @param imgIdx
	 * @param g
	 */
	private void drawActByPoint(int x, int y, int imgIdx, Graphics g) {
		g.drawImage(Img.ACT, 
				this.x + (x << ACT_SIZE_ROL) + 7, 
				this.y + (y << ACT_SIZE_ROL) + 7, 
				this.x + (x + 1 << ACT_SIZE_ROL) + 7, 
				this.y + (y + 1 << ACT_SIZE_ROL) + 7,
				imgIdx << ACT_SIZE_ROL, 0, (imgIdx + 1) << ACT_SIZE_ROL, 1 << ACT_SIZE_ROL, null);
	}
}
