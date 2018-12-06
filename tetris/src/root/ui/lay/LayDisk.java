package root.ui.lay;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayDisk extends Lay {
	
	private static final Image IMG_DISK = new ImageIcon("graphics/default/string/disk.png").getImage();
	
	public LayDisk(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		g.drawImage(IMG_DISK, this.x + PADDING, this.y + PADDING, null);
	}
	
}
