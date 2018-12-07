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
	 * 87 83 65 68
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP: 
				this.gameListener.keyUp();
				break;
			case KeyEvent.VK_DOWN: 
				this.gameListener.keyDown();
				break;
			case KeyEvent.VK_LEFT: 
				this.gameListener.keyLeft();
				break;
			case KeyEvent.VK_RIGHT: 
				this.gameListener.keyRight();
				break;
			default:
		}
	}
	
}
