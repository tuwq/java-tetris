package root.ui.layer;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerNext extends Layer {
	
	private static final Image[] NEXT_ACT;
	
	static {
		NEXT_ACT = new Image[7];
		for (int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i] = new ImageIcon("graphics/default/game/"+ i +".png").getImage();
		}
	}
	
	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	/**
	 * 渲染下一个方块图片
	 */
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		this.drawImageAtCenter(NEXT_ACT[this.gameDto.getNext()], g);
	}
	
	/**
	 * 正中对齐
	 */
	private void drawImageAtCenter(Image img, Graphics g) {
		int imgW = img.getWidth(null);
		int imgH = img.getHeight(null);
		int imgX = this.x + (this.w - imgW >> 1);
		int imgY = this.y + (this.h - imgH >> 1);
		g.drawImage(img, imgX, imgY, null);
	}
}
