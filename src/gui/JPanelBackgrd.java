package gui;

import java.awt.Graphics
;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelBackgrd extends JPanel  {
private Image Background;
public JPanelBackgrd() {
	super();
}
public JPanelBackgrd(String filePath) throws IOException {
	Background = ImageIO.read(new File(filePath));
}
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(Background,0,0,this);
	
}
}
