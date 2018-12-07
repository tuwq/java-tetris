package root.ui;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Img {
	
	private Img(){};
	
	/**
	 * 个人签名
	 */
	public static Image ABOUT = new ImageIcon("graphics/default/string/about.png").getImage();
	/**
	 * 窗口图片
	 */
	public static Image WINDOW = new ImageIcon("graphics/default/window/window.png").getImage();
	/**
	 * 数据库标题
	 */
	public static final Image DB = new ImageIcon("graphics/default/string/db.png").getImage();
	/**
	 * 本地磁盘标题
	 */
	public static final Image DISK = new ImageIcon("graphics/default/string/disk.png").getImage();
	/**
	 * 游戏方块
	 */
	public static final Image ACT = new ImageIcon("graphics/default/game/act.png").getImage();
	/**
	 * 等级标题
	 */
	public static final Image LV = new ImageIcon("graphics/default/string/level.png").getImage();
	/**
	 * 分数标题
	 */
	public static final Image POINT = new ImageIcon("graphics/default/string/point.png").getImage();
	/**
	 * 消行标题
	 */
	public static final Image RELINE = new ImageIcon("graphics/default/string/rmline.png").getImage();
	/**
	 * 数字图片
	 */
	public static final Image NUMBER = new ImageIcon("graphics/default/string/num.png").getImage();
	/**
	 * 矩形值槽
	 */
	public static final Image RECT = new ImageIcon("graphics/default/window/rect.png").getImage();
	/**
	 * 下一个方块图片数组
	 */
	public static final Image[] NEXT_ACT;
	static {
		NEXT_ACT = new Image[7];
		for (int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i] = new ImageIcon("graphics/default/game/"+ i +".png").getImage();
		}
	}
}
