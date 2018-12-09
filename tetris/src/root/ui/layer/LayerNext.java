package root.ui.layer;

import java.awt.Graphics;

import root.ui.Img;

public class LayerNext extends Layer {
	
	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	/**
	 * 渲染下一个方块图片
	 */
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		if (this.gameDto.isStart()) {
			this.drawImageAtCenter(Img.NEXT_ACT[this.gameDto.getNext()], g);
		}
	}
	
}
