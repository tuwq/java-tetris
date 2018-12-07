package root.ui.layer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import root.config.ConfigFactory;
import root.config.GameConfigRead;
import root.dto.GameDto;
import root.ui.Img;

/**
 * 绘制窗口
 * @author tuwq
 */
public abstract class Layer {
	// 游戏数据对象
	protected GameDto gameDto;
	
	protected static final int PADDING;
	
	protected static final int SIZE;
	static {
		GameConfigRead gameConfigRead = ConfigFactory.getGameConfigRead();
		PADDING = gameConfigRead.getFrameConfig().getPadding();
		SIZE = gameConfigRead.getFrameConfig().getWindowSize();
	}
	
	
	
	private static int WINDOW_W = Img.WINDOW.getWidth(null);
	private static int WINDOW_H = Img.WINDOW.getHeight(null);
	
	/**
	 * 数字图片的切片宽度
	 */
	protected static final int IMG_NUMBER_W = Img.NUMBER.getWidth(null) / 10;
	/**
	 * 数字图片的切片高度
	 */
	protected static final int IMG_NUMBER_H = Img.NUMBER.getHeight(null);
	
	private static final int IMG_RECT_H = Img.RECT.getHeight(null);
	private static final int IMG_RECT_W = Img.RECT.getWidth(null);
	private static final Font DEF_FONT = new Font("黑体", Font.BOLD, 20);
	// this.expW = 
	
	protected int x;
	
	protected int y;
	
	protected int w;
	
	protected int h;

	public Layer(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	/**
	 * 正中绘图
	 * @param img
	 * @param g
	 */
	protected void drawImageAtCenter(Image img, Graphics g) {
		int imgW = img.getWidth(null);
		int imgH = img.getHeight(null);
		int imgX = this.x + (this.w - imgW >> 1);
		int imgY = this.y + (this.h - imgH >> 1);
		g.drawImage(img, imgX, imgY, null);
	}
	
	/**
	 * 绘制经验槽
	 * @param expW 值槽总宽度
	 * @param expY 值槽的y坐标
	 * @param title 值槽左内容
	 * @param number 值槽右内容
	 * @param value 当前值
	 * @param maxValue 最大值
	 * @param g 画笔
	 */
	protected void drawRect(int expW, int expY, String title, String number, double value, double maxValue, Graphics g) {
		int rect_x = this.x + (PADDING << 1);
		int rect_y = this.y + expY;
		// 绘制背景
		g.setColor(Color.BLACK);
		g.fillRect(rect_x, rect_y, expW, IMG_RECT_H + 4);
		g.setColor(Color.white);
		g.fillRect(rect_x + 1, rect_y + 1, expW - 2, IMG_RECT_H + 2);
		g.setColor(Color.BLACK);
		g.fillRect(rect_x + 2, rect_y + 2, expW - 4, IMG_RECT_H);
		// 绘制值槽
		double percentage = value / maxValue;
		int w = (int)((percentage * (expW - 4 )));
		int subIdx = (int)(percentage * IMG_RECT_W);
		g.drawImage(Img.RECT,
				rect_x + 2, rect_y + 2,
				rect_x + 2 + w, rect_y + 2 + IMG_RECT_H, 
				subIdx, 0, subIdx + 1, IMG_RECT_H,
				null);
		g.setColor(Color.WHITE);
		g.setFont(DEF_FONT);
		g.drawString(title, rect_x + 4, rect_y + 22);
		if (number != null) {
			
		}
	}
	
	/**
	 * 显示数字
	 * 把num每一位取出
	 * 左对齐数字,忽略多余位数
	 * @param x 图片左上角x坐标
	 * @param y 图片左上角y坐标
	 * @param num 要显示的数字
	 * @param maxBit 最大数字的位数
	 * @param g 画笔对象
	 */
	protected void drawNumber(int x, int y, int num, int maxBit, Graphics g) {
		String number = Integer.toString(num);
		for (int i = 0; i < maxBit; i++) {
			if (maxBit - i <= number.length()) {
				int idx = i - maxBit + number.length();
				int bit = number.charAt(idx) - '0';
				g.drawImage(Img.NUMBER, 
						this.x + x + IMG_NUMBER_W * i, 
						this.y + y, 
						this.x + x + IMG_NUMBER_W * (i + 1), 
						this.y + y + IMG_NUMBER_H,
						bit * IMG_NUMBER_W, 0, (bit + 1) * IMG_NUMBER_W, IMG_NUMBER_H, null);
			}
		}
	}
	
	/**
	 * 由子类具体绘制
	 * @param g
	 */
	public abstract void paintWindow(Graphics g);
	/**
	 * 绘制窗口
	 * @param g
	 */
	protected void createWindow(Graphics g) {
		g.drawImage(Img.WINDOW, x, y, x + SIZE, y + SIZE, 0, 0, SIZE, SIZE, null);
		g.drawImage(Img.WINDOW, x + SIZE, y, x + w - SIZE, y + SIZE, SIZE, 0, WINDOW_W -SIZE, SIZE, null);
		g.drawImage(Img.WINDOW, x + w - SIZE, y, x + w, y + SIZE, WINDOW_W - SIZE, 0, WINDOW_W, SIZE, null);
	
		g.drawImage(Img.WINDOW, x, y + SIZE, x + SIZE, y + h - SIZE, 0, SIZE, SIZE, WINDOW_H - SIZE, null);
		g.drawImage(Img.WINDOW, x + SIZE, y + SIZE, x + w - SIZE, y + h - SIZE, SIZE, SIZE, WINDOW_W - SIZE, WINDOW_H - SIZE, null);
		g.drawImage(Img.WINDOW, x + w - SIZE, y + SIZE, x + w, y + h - SIZE, WINDOW_W - SIZE, SIZE, WINDOW_W, WINDOW_H - SIZE, null);

		g.drawImage(Img.WINDOW, x, y + h - SIZE, x + SIZE, y + h, 0, WINDOW_H - SIZE, SIZE, WINDOW_H, null);
		g.drawImage(Img.WINDOW, x + SIZE, y + h - SIZE, x + w - SIZE, y + h, SIZE, WINDOW_H - SIZE, WINDOW_W - SIZE, WINDOW_H, null);
		g.drawImage(Img.WINDOW, x + w - SIZE, y + h - SIZE, x + w, y + h, WINDOW_W - SIZE, WINDOW_H - SIZE, WINDOW_W, WINDOW_H, null);
	}
	
	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	
}
