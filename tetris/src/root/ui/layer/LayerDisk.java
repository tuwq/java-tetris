package root.ui.layer;

import java.awt.Graphics;

import root.ui.Img;
import root.ui.LayerData;

public class LayerDisk extends LayerData {
	
	public LayerDisk(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void paintWindow(Graphics g) {
		this.createWindow(g);
		this.showData(Img.DISK, this.gameDto.getDiskRecode(), g);
	}
	
}
