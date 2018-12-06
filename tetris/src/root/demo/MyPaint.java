package root.demo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
 
public class MyPaint extends JFrame {
     
    private class MyPanel extends JPanel{
         
        public void paintComponent(Graphics g) {
            Image image = new ImageIcon("graphics/default/background/bg01.jpg").getImage();
            g.drawImage(image,0,0,100,100,this);
            super.paintComponents(g);
             
        }
         
    }
 
    public void service(){
         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel panel = new MyPanel();
         
        add(panel);
        setPreferredSize(new Dimension(300,200));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
         
    }
     
    public static void main(String[] args) {
         
        MyPaint demo = new MyPaint();
        demo.service();
         
    }
     
}
