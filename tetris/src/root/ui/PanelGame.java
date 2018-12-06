package root.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.lang.reflect.Constructor;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import root.ui.lay.Lay;
import root.ui.lay.LayBackground;
import root.ui.lay.LayButton;
import root.ui.lay.LayDataBase;
import root.ui.lay.LayDisk;
import root.ui.lay.LayGame;
import root.ui.lay.LayLevel;
import root.ui.lay.LayNext;
import root.ui.lay.LayPoint;

public class PanelGame extends JPanel {

	private static final long serialVersionUID = 1465367605068499712L;
	private Lay[] lays = null;
	
	public PanelGame() {
		lays = new Lay[] {
				new LayBackground(0, 0, 0, 0),
				new LayDataBase(40, 32, 334, 279),
				new LayDisk(40, 343, 334, 279),
				new LayGame(414, 32, 334, 590),
				new LayButton(788, 32, 334, 124),
				new LayNext(788, 188, 176, 148),
				new LayLevel(964, 188, 158, 148),
				new LayPoint(788, 368, 334, 200)
		};
	}
	
	/**
	 * 绘制框
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < lays.length; i++) {
			lays[i].paintWindow(g);
		}
	}
}
