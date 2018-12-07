package root.ui.layer;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import root.ui.Img;

public class LayerLevel extends Layer {
	 
	private static final int IMG_LV_W = Img.LV.getWidth(null);
	
	public LayerLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	/**
	 * 显示等级标题
	 * 显示等级数字
	 */
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		int centerX = this.w - IMG_LV_W >> 1;
		g.drawImage(Img.LV, this.x + centerX, this.y + PADDING, null);
		this.drawNumber(centerX, 64, this.gameDto.getNowLevel(), 2, g);
	}
	
}
