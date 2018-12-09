package root.config;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

/**
 * 读取系统配置
 * 方块与升级相关
 * @author tuwq
 */
public class SystemConfig {
	
	private final int minX;
	
	private final int maxX;
	
	private final int minY;
	
	private final int maxY;
	
	private final int levelUp;
	
	private final List<Point[]> rects;
	// 方块是否旋转的映射数组
	private final List<Boolean> rectRounds;
	// 成功消行的对应分数
	private final Map<Integer, Integer> plusPointMap;
	
	public SystemConfig(Element system) {
		this.minX = Integer.parseInt(system.attributeValue("minX"));
		this.maxX = Integer.parseInt(system.attributeValue("maxX"));
		this.minY = Integer.parseInt(system.attributeValue("minY"));
		this.maxY = Integer.parseInt(system.attributeValue("maxY"));
		this.levelUp = Integer.parseInt(system.attributeValue("levelUp"));
		List<Element> rectElements = system.elements("rect");
		this.rects = new ArrayList<Point[]>(rectElements.size());
		this.rectRounds = new ArrayList<Boolean>(rectElements.size());
		for (Element rectElement : rectElements) {
			this.rectRounds.add(Boolean.valueOf(rectElement.attributeValue("round")));
			List<Element> pointElements = rectElement.elements("point");
			Point[] points = new Point[pointElements.size()];
			for (int i = 0; i < pointElements.size(); i++) {
				int x = Integer.parseInt(pointElements.get(i).attributeValue("x"));
				int y = Integer.parseInt(pointElements.get(i).attributeValue("y"));
				points[i] = new Point(x, y);
			}
			this.rects.add(points);
		}
		this.plusPointMap = new HashMap<Integer, Integer>();
		List<Element> plusPointElements = system.elements("plusPoint");
		for (Element plusPointElement: plusPointElements) {
			int rm = Integer.parseInt(plusPointElement.attributeValue("rm"));
			int score = Integer.parseInt(plusPointElement.attributeValue("score"));
			this.plusPointMap.put(rm, score);
		}
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getLevelUp() {
		return levelUp;
	}

	public List<Point[]> getRects() {
		return rects;
	}

	public List<Boolean> getRectRounds() {
		return rectRounds;
	}

	public Map<Integer, Integer> getPlusPointMap() {
		return plusPointMap;
	}
	
}
