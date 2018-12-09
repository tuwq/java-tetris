package root.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import root.listener.GameListener;

/**
 * 玩家控制器
 * 由玩家控制操作
 * @author tuwq
 */
public class PlayerController extends KeyAdapter {
	// 游戏监听器
	private GameListener gameListener;

	public PlayerController(GameListener gameListener) {
		this.gameListener = gameListener;
	}

	/**
	 * 玩家键盘按下事件
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		this.gameListener.actionByKeyCode(e.getKeyCode());
	}

	/**
	 * 开始游戏
	 */
	public void start() {
		this.gameListener.start();
	}
	
}
