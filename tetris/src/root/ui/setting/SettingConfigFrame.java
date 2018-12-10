package root.ui.setting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import root.contant.GameEnum;
import root.listener.GameListener;
import root.util.FrameUtils;

/**
 * 游戏设置窗口
 * @author tuwq
 */
public class SettingConfigFrame extends JFrame {
	
	private GameListener gameListener;
	
	private JButton btnOk = new JButton("确定");
	
	private JButton btnCancel = new JButton("取消");
	
	private JButton btnApply = new JButton("应用");
	
	// 控制按键提示说明
	private JLabel[] keyTips = new JLabel[8];
	// 控制按键输入框
	private TextCtr[] keyTexts = new TextCtr[8];
	// 控制按钮提示数组
	private static String[] KeyTips = {
		"方向上","方向下","方向左","方向右","瞬间下落","阴影开关","暂停开关","测试按钮"
	};
	// 方法映射数组
	private static String[] METHOD_NAMES = {
		"keyUp","keyDown","keyLeft","keyRight","momentDown","switchShadow","switchPause","levelUp"
	};
	private JLabel errorMsg = new JLabel();
	
	
	/**
	 * 中 主面板(选项卡面板)
	 * 下 按钮面板
	 * 初始化按键输入框
	 * @param gameListener 
	 */
	public SettingConfigFrame(GameListener gameListener) {
		this.gameListener = gameListener;
		this.setTitle("游戏设置");
		this.setLayout(new BorderLayout());
		this.initKeyTip();
		this.initKeyText();
		
		this.add(this.createMainPanel(), BorderLayout.CENTER);
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		this.setSize(640, 350);
		FrameUtils.setFrameCenter(this);
		
		this.setResizable(false);
	}
	

	/**
	 * 主面板(选项卡面板)
	 * 游戏控制面板
	 * 皮肤设置面板
	 * @return
	 */
	private JTabbedPane createMainPanel() {
		JTabbedPane jTabbedPane = new JTabbedPane();
		jTabbedPane.addTab("游戏控制", this.createControlPanel());
		jTabbedPane.addTab("皮肤设置", new JLabel("皮肤"));
		return jTabbedPane;
	}
	
	/**
	 * 游戏控制面板
	 * @return
	 */
	private JPanel createControlPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		for (int i = 0; i < keyTexts.length; i++) {
			jPanel.add(keyTexts[i]);
			jPanel.add(keyTips[i]);
		}
		return jPanel;
	}

	/**
	 * 初始化按钮提示
	 */
	private void initKeyTip() {
		int x = 100;
		int y = 56;
		int w = 64;
		int h = 20;
		for (int i = 0; i < 4; i++) {
			keyTips[i] = new JLabel(KeyTips[i]);
			keyTips[i].setBounds(x, y, w, h);
			y += 32;
		}
		x = 350;
		y = 52;
		for (int i = 4; i < 8; i++) {
			keyTips[i] = new JLabel(KeyTips[i]);
			keyTips[i].setBounds(x, y, w, h);
			y += 32;
		}
	}
	
	/**
	 * 初始化按键输入框
	 */
	private void initKeyText() {
		int x = 150;
		int y = 56;
		int w = 64;
		int h = 20;
		for (int i = 0; i < 4; i++) {
			keyTexts[i] = new TextCtr( x, y, w, h, METHOD_NAMES[i]);
			y += 32;
		}
		x = 430;
		y = 52;
		for (int i = 4; i < 8; i++) {
			keyTexts[i] = new TextCtr( x, y, w, h, METHOD_NAMES[i]);
			y += 32;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GameEnum.CONTROL_FILE_PATH.getContent()));
			HashMap<Integer, String> cfgSet = (HashMap) ois.readObject();
			ois.close();
			Set<Entry<Integer, String>> entrySet = cfgSet.entrySet();
			for (Entry<Integer, String> e : entrySet) {
				for (TextCtr tc : keyTexts) {
					if(tc.getMethodName().equals(e.getValue())) {
						tc.setKeyCode(e.getKey());
					};
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按钮面板
	 * 流式布局-右对齐
	 * @return
	 */
	private JPanel createButtonPanel() {
		JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.errorMsg.setForeground(Color.RED);
		this.btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(writeConfig()) {
					setVisible(false);
					gameListener.setOver();
				}
			}
		});
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gameListener.setOver();
			}
		});
		this.btnApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeConfig();
			}
		});
		
		jPanel.add(this.errorMsg);
		jPanel.add(this.btnOk);
		jPanel.add(this.btnCancel);
		jPanel.add(this.btnApply);
		return jPanel;
	}
	
	/**
	 * 写入游戏配置
	 * 相同按钮配置的问题解决
	 */
	public boolean writeConfig() {
		HashMap<Integer, String> keyMap = new HashMap<Integer, String>();
		for (int i = 0; i < this.keyTexts.length; i++) {
			int keyCode = this.keyTexts[i].getKeyCode();
			if (keyCode == 0) {
				this.errorMsg.setText("错误按键");
				return false;
			}
			keyMap.put(keyCode, this.keyTexts[i].getMethodName());
		}
		if (keyMap.size() != this.keyTexts.length) {
			this.errorMsg.setText("重复按键");
			return false;
		}
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GameEnum.CONTROL_FILE_PATH.getContent()));
			oos.writeObject(keyMap);
			oos.close();
		} catch (Exception e) {
			this.errorMsg.setText(e.getMessage());
			e.printStackTrace();
			return false;
		}
		this.errorMsg.setText(null);
		return true;
	}
}
