package root.ui.layer;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import root.config.ConfigFactory;
import root.config.GameConfigRead;
import root.dto.GameDto;

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
	
	private static Image WINDOW_IMG = new ImageIcon("graphics/default/window/window.png").getImage();
	
	private static int WINDOW_W = WINDOW_IMG.getWidth(null);
	private static int WINDOW_H = WINDOW_IMG.getHeight(null);
	
	/**
	 * 数字图片
	 */
	private static final Image IMG_NUMBER = new ImageIcon("graphics/default/string/num.png").getImage();
	/**
	 * 数字图片的切片宽度
	 */
	protected static final int IMG_NUMBER_W = IMG_NUMBER.getWidth(null) / 10;
	/**
	 * 数字图片的切片高度
	 */
	protected static final int IMG_NUMBER_H = IMG_NUMBER.getHeight(null);
	
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
				g.drawImage(IMG_NUMBER, 
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
		g.drawImage(WINDOW_IMG, x, y, x + SIZE, y + SIZE, 0, 0, SIZE, SIZE, null);
		g.drawImage(WINDOW_IMG, x + SIZE, y, x + w - SIZE, y + SIZE, SIZE, 0, WINDOW_W -SIZE, SIZE, null);
		g.drawImage(WINDOW_IMG, x + w - SIZE, y, x + w, y + SIZE, WINDOW_W - SIZE, 0, WINDOW_W, SIZE, null);
	
		g.drawImage(WINDOW_IMG, x, y + SIZE, x + SIZE, y + h - SIZE, 0, SIZE, SIZE, WINDOW_H - SIZE, null);
		g.drawImage(WINDOW_IMG, x + SIZE, y + SIZE, x + w - SIZE, y + h - SIZE, SIZE, SIZE, WINDOW_W - SIZE, WINDOW_H - SIZE, null);
		g.drawImage(WINDOW_IMG, x + w - SIZE, y + SIZE, x + w, y + h - SIZE, WINDOW_W - SIZE, SIZE, WINDOW_W, WINDOW_H - SIZE, null);

		g.drawImage(WINDOW_IMG, x, y + h - SIZE, x + SIZE, y + h, 0, WINDOW_H - SIZE, SIZE, WINDOW_H, null);
		g.drawImage(WINDOW_IMG, x + SIZE, y + h - SIZE, x + w - SIZE, y + h, SIZE, WINDOW_H - SIZE, WINDOW_W - SIZE, WINDOW_H, null);
		g.drawImage(WINDOW_IMG, x + w - SIZE, y + h - SIZE, x + w, y + h, WINDOW_W - SIZE, WINDOW_H - SIZE, WINDOW_W, WINDOW_H, null);
	}
	
	public void setGameDto(GameDto gameDto) {
		this.gameDto = gameDto;
	}
	
	public Layer() {
		super();
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
