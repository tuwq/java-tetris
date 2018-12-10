package root.ui.setting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import root.listener.GameListener;
import root.util.FrameUtils;

public class SavePointFrame extends JFrame {

	private JLabel point;
	
	private JTextField yourname;
	
	private JButton btnOk;
	
	private JLabel errMsg;
	
	private GameListener gameListener;
	
	public SavePointFrame(GameListener gameListener) {
		this.gameListener = gameListener;
		this.setTitle("保存记录");
		this.setSize(305, 150);
		
		this.setResizable(false);
		this.createComponent();
		this.createAction();
		
		FrameUtils.setFrameCenter(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 显示得分窗口
	 */
	public void showFrame(int point) {
		this.point.setText("你的得分: " + point);
		this.setVisible(true);
	}
	
	/**
	 * 监听动作行为
	 */
	private void createAction() {
		this.btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = yourname.getText();
				if (name.length() > 16 || name.length() == 0 || "".equals(name) || name == null) {
					errMsg.setText("名字长度保持在1-16位");
				} else {
					setVisible(false);
					gameListener.savePoint(name);
				}
			}
		});
	}

	/**
	 * 上提示
	 * 中输入框
	 * 下按钮
	 */
	private void createComponent() {
		this.setLayout(new BorderLayout());
		
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.point = new JLabel();
		this.errMsg = new JLabel("");
		this.errMsg.setForeground(Color.RED);
		north.add(this.point);
		north.add(this.errMsg);
		this.add(north, BorderLayout.NORTH);
		
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.yourname = new JTextField(10);
		center.add(new JLabel("你的名字"));
		center.add(this.yourname);
		this.add(center, BorderLayout.CENTER);
		
		JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.btnOk = new JButton("确定");
		south.add(this.btnOk);
		this.add(south, BorderLayout.SOUTH);
	}
	
}
