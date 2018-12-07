package root.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * 游戏方块
 * @author tuwq
 */
public class GameAct {
	
	/**
	 * 方块坐标系集合
	 */
	private Point[] actPoints;
	/**
	 * 方块编号
	 */
	private int typeCode;
	
	private static int MIN_X = 0;
	private static int MAX_X = 9;
	private static int MIN_Y = 0;
	private static int MAX_Y = 17;
	
	private static List<Point[]> TYPE_CONFIG;
	
	static {
		TYPE_CONFIG = new ArrayList<Point[]>(7);
		TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(6, 0)});
		TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(4, 1)});
		TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(3, 1)});
		TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(5, 0), new Point(3, 1), new Point(4, 1)});
		TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(5, 0), new Point(4, 1), new Point(5, 1)});
		TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(5, 1)});
		TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(4, 1), new Point(5, 1)});
	}
	
	public GameAct(int typeCode) {
		this.init(typeCode);
	}
	
	/**
	 * 初始化方块
	 * 根据actCode值刷新方块
	 * ！注意BUG: 需要新的对象,而不是对象引用,否则同样方块会隐藏在上一个方块之后
	 * @param actCode
	 */
	public void init(int typeCode) {
		this.typeCode = typeCode;
		// 直接引用会触发引用相关BUG,需要重新拷贝一份数组
		// this.actPoints = TYPE_CONFIG.get(actCode);
		Point[] points = TYPE_CONFIG.get(typeCode);
		this.actPoints = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			this.actPoints[i] = new Point(points[i].x, points[i].y);
		}
	}

	public Point[] getActPoints() {
		return actPoints;
	}
	
	/**
	 * 方块移动
	 * 当前坐标 + 偏移量
	 * 是否越界
	 * 越界时除游戏范围外也要考虑堆积的游戏地图
	 * @param moveX X轴偏移量
	 * @param moveY Y轴偏移量
	 */
	public boolean move(int moveX, int moveY, boolean[][] gameMap) {
		for (int i = 0; i < actPoints.length; i++) {
			int newX = actPoints[i].x + moveX;
			int newY = actPoints[i].y + moveY;
			if (this.isOverZone(newX, newY, gameMap)) {
				return false;
			}
		}
		for (int i = 0; i < actPoints.length; i++) {
			actPoints[i].x += moveX;
			actPoints[i].y += moveY;
		}
		return true;
	}

	/**
	 * 方块旋转
	 * 顺时针:
	 * A: 新的坐标   o: 中心点  B: 当前点
	 * A.x = o.y + o.x - B.y
	 * A.y = o.y - o.x + B.x
	 */
	public void round(boolean[][] gameMap) {
		// TODO 配置
		if (this.typeCode == 4) {
			return;
		}
		for (int i = 0; i < actPoints.length; i++) {
			int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
			int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
			if (this.isOverZone(newX, newY, gameMap)) {
				return;
			}
		}
		for (int i = 0; i < actPoints.length; i++) {
			int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
			int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
			actPoints[i].x = newX;
			actPoints[i].y = newY;
		}
	}
	
	/**
	 * 边界判定
	 * @param x
	 * @param y
	 * @param gameMap 
	 * @return
	 */
	private boolean isOverZone(int x, int y, boolean[][] gameMap) {
		return x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y || gameMap[x][y];
	}

	public int getTypeCode() {
		return typeCode;
	}

}
