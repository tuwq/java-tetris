package root.ui.layer;

import java.awt.Graphics;

import root.ui.Img;
import root.ui.LayerData;

public class LayerDataBase extends LayerData {
	
	public LayerDataBase(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	/**
	 * 绘制历史玩家记录
	 * 打破记录的清空
	 */
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		this.showData(Img.DB, this.gameDto.getDbRecode(), g);
	}
}
