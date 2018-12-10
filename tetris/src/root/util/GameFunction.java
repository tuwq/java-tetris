package root.util;

/**
 * 游戏相关方法
 * @author tuwq
 */
public class GameFunction {
	
	/**
	 * 等级加速
	 * @param level
	 * @return
	 */
	public static long getSleepTimeByLevel(int level) {
		long sleep = (-50 * level + 600);
		sleep = sleep < 100 ? 100 : sleep;
		return sleep;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			System.out.println(i + ":" + GameFunction.getSleepTimeByLevel(i));
		}
	}
}
