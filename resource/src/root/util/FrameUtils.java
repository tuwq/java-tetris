package root.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import root.config.FrameConfig;
import root.config.GameConfigRead;
import root.dto.GameDto;

public class FrameUtils {
	
	
	public static void setFrameCenter(JFrame jFrame){
		FrameConfig frameConfig = GameConfigRead.getFrameConfig();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = screen.width - jFrame.getWidth() >> 1 ;
		int y = (screen.height - jFrame.getHeight() >> 1) - frameConfig.getWindowUp();
		jFrame.setLocation(x, y);
	};
}
