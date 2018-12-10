package root.ui.setting;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

/**
 * 配置输入框的封装
 * @author tuwq
 */
public class TextCtr extends JTextField {
	
	private int keyCode;
	
	private String methodName;
	
	/**
	 * 初始化输入框文本
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param keyCode
	 * @param methodName
	 */
	public TextCtr(int x, int y, int w, int h, String methodName) {
		this.methodName = methodName;
		this.setBounds(x, y, w, h);
		this.setText(null);
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				setText(null);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				setKeyCode(e.getKeyCode());
			}
			@Override
			public void keyPressed(KeyEvent e) {
				setText(null);
			}
		});
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		this.setText(KeyEvent.getKeyText(this.keyCode));
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}
