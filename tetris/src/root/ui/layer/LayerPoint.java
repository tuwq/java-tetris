package root.ui.layer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import root.config.GameConfigRead;
import root.ui.Img;

public class LayerPoint extends Layer {
	/**
	 * 分数最大位数
	 */
	private static final int POINT_BIT = 5;
	/**
	 * 升级所需消行数
	 */
	private static final int LEVEL_UP = GameConfigRead.getSystemConfig().getLevelUp();
	
	// 分数与消行x坐标
	private int comX;
	// 分数y坐标
	private final int pointY;
	// 消行y坐标
	private final int rmlineY;
	// 经验槽y坐标
	private int expY;
	// 经验槽总宽度
	private final int expW;
	
	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.comX = this.w - IMG_NUMBER_W * POINT_BIT - PADDING;
		this.pointY = PADDING;
		this.rmlineY = this.pointY + Img.POINT.getHeight(null) + (PADDING << 1);
		this.expW = this.w - (PADDING << 2);
		this.expY = this.rmlineY + Img.RELINE.getHeight(null) + (PADDING << 1);
	}
	
	/**
	 * 标题分数
	 * 标题消行
	 * 经验值槽
	 */
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		g.drawImage(Img.POINT, this.x + PADDING, this.y + pointY, null);
		this.drawNumber(comX, pointY + PADDING, this.gameDto.getNowPoint(), POINT_BIT, g);
		g.drawImage(Img.RELINE, this.x + PADDING, this.y + rmlineY, null);
		this.drawNumber(comX, rmlineY + PADDING, this.gameDto.getNowRemoveLine(), POINT_BIT, g);
		int rmLine = this.gameDto.getNowRemoveLine();
		this.drawRect(this.expW, this.expY, "下一级", "123", (double)(rmLine % LEVEL_UP)/(double)LEVEL_UP, g);
	}
	
	/**
	 * 不使用
	 * @param hp
	 * @param maxHp
	 * @return
	 */
	@Deprecated
	private Color getNowColor(double hp, double maxHp) {
		int colorR = 0;
		int colorG = 255;
		int colorB = 0;
		double hpHalf = maxHp / 2;
		if (hp > hpHalf) {
			colorR = 255 - (int)((hp - hpHalf) / (maxHp) / 2);
			colorG = 255;
		} else {
			colorR = 255;
			colorG = (int)(hp / (maxHp / 2) * 255);
		}
		return new Color(colorR, colorG, colorB);
	}
}
