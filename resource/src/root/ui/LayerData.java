package root.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import root.config.GameConfigRead;
import root.dto.PlayerDto;
import root.ui.layer.Layer;

/**
 * Layer数据相关基类,间接继承Layer
 * @author tuwq
 */
public abstract class LayerData extends Layer {
	
	// 最大数据行 
	private static final int MAX_ROW = GameConfigRead.getDataConfig().getMaxRow();
	// 起始Y坐标
	private static int START_Y = 0;
	// 值槽间距
	private static int SPA = 0;
	// 值槽高度
	private static final int RECT_H = IMG_RECT_H + 4;
	// 值槽总宽度
	private int expW;
	
	public LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		SPA = (this.h - RECT_H * 5 - (PADDING << 1) - Img.DB.getHeight(null)) / MAX_ROW;
		expW = this.w - (PADDING << 2);
		START_Y = PADDING + Img.DB.getHeight(null);
	}
	
	@Override
	public  abstract void paintWindow(Graphics g);
	
	/**
	 * 绘制所有值槽
	 * @param imgTitle 标题图片
	 * @param players 数据源
	 * @param g 画笔
	 */
	protected void showData(Image imgTitle, List<PlayerDto> players, Graphics g) {
		g.drawImage(imgTitle, this.x + PADDING, this.y + PADDING, null);
		int nowPoint = this.gameDto.getNowPoint();
		for (int i = 0; i < MAX_ROW; i++) {
			PlayerDto playerDto = players.get(i);
			int recodePoint = playerDto.getPoint();
			double percentage = (double) nowPoint / recodePoint;
			percentage = percentage > 1? 1.0 : percentage;
			String strPoint = recodePoint == 0 ? null : Integer.toString(recodePoint);
			this.drawRect(expW, START_Y + i * (RECT_H + SPA), playerDto.getName(), strPoint,
					percentage, g);
		}
	}
}
