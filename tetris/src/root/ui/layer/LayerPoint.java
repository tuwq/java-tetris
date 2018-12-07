package root.ui.layer;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerPoint extends Layer {
	/**
	 * 标题图片分数
	 */
	private static final Image IMG_POINT = new ImageIcon("graphics/default/string/point.png").getImage();

	private static int POINT_X;
	private static final int POINT_Y = PADDING;
	/**
	 * 标题图片消行
	 */
	private static final Image IMG_RELINE = new ImageIcon("graphics/default/string/rmline.png").getImage();
	
	private static final int RELINE_Y = IMG_RELINE.getHeight(null) + (PADDING << 1);
	/**
	 * 分数最大位数
	 */
	private static final int POINT_BIT = 5;
	
	
	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		POINT_X = IMG_NUMBER_W * POINT_BIT - PADDING;
	}
	
	/**
	 * 标题图片分数
	 * 标题图片消行
	 */
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		g.drawImage(IMG_POINT, this.x + PADDING, this.y + POINT_Y, null);
		this.drawNumber(POINT_X, POINT_Y + PADDING, this.gameDto.getNowPoint(), POINT_BIT, g);
		g.drawImage(IMG_RELINE, this.x + PADDING, this.y + RELINE_Y, null);
		this.drawNumber(POINT_X, RELINE_Y + PADDING, this.gameDto.getNowRemoveLine(), POINT_BIT, g);
	}
}
