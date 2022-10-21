package gui;

import javax.swing.*;

public class GameView extends JFrame {
private JPanel City;
private JTextArea information;
public GameView() {
	this.setVisible(true);
	this.setTitle("Empire");
	this.setExtendedState(MAXIMIZED_BOTH);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setMaximizedBounds(getMaximizedBounds());
	
	
	
	this.revalidate();
	this.repaint();
	
}
//public static void main(String[] args) {
//	new GameView();
//}
public JPanel getCity() {
	return City;
}
public void setCity(JPanel city) {
	City = city;
}
public JTextArea getInformation() {
	return information;
}
public void setInformation(JTextArea information) {
	this.information = information;
}

}
