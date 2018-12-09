package root.ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import root.config.GameConfigRead;

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
	public static Image DB = new ImageIcon("graphics/default/string/db.png").getImage();
	/**
	 * 本地磁盘标题
	 */
	public static Image DISK = new ImageIcon("graphics/default/string/disk.png").getImage();
	/**
	 * 游戏方块
	 */
	public static Image ACT = new ImageIcon("graphics/default/game/act.png").getImage();
	/**
	 * 阴影
	 */
	public static Image Shadow = new ImageIcon("graphics/default/game/shadow.png").getImage();
	/**
	 * 等级标题
	 */
	public static Image LV = new ImageIcon("graphics/default/string/level.png").getImage();
	/**
	 * 分数标题
	 */
	public static Image POINT = new ImageIcon("graphics/default/string/point.png").getImage();
	/**
	 * 消行标题
	 */
	public static Image RELINE = new ImageIcon("graphics/default/string/rmline.png").getImage();
	/**
	 * 数字图片
	 */
	public static Image NUMBER = new ImageIcon("graphics/default/string/num.png").getImage();
	/**
	 * 矩形值槽
	 */
	public static Image RECT = new ImageIcon("graphics/default/window/rect.png").getImage();
	/**
	 * 开始标题
	 */
	public static ImageIcon BTN_START = new ImageIcon("graphics/default/string/start.png");
	/**
	 * 设置标题
	 */
	public static ImageIcon BTN_SETTING = new ImageIcon("graphics/default/string/setting.png");
	/**
	 * 下一个方块图片数组
	 */
	public static Image[] NEXT_ACT;
	private static final int ACT_RECT_LENGTH = GameConfigRead.getSystemConfig().getRects().size();
	/**
	 * 背景图片数组
	 */
	public static List<Image> BG_LIST;
	
	/**
	 * 初始化方块图片数组
	 * 初始化背景图片目录
	 */
	static {
		NEXT_ACT = new Image[ACT_RECT_LENGTH];
		for (int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i] = new ImageIcon("graphics/default/game/"+ i +".png").getImage();
		}
		File dir = new File("graphics/default/background");
		File[] files = dir.listFiles();
		BG_LIST = new ArrayList<Image>();
		for (File file: files) {
			if (file.isDirectory()) {
				continue;
			}
			BG_LIST.add(new ImageIcon(file.getPath()).getImage());
		}
	}
}
