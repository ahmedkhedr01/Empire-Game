package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class View extends JFrame {
private JPanelBackgrd welcome;
private JTextArea name1;
public View() throws IOException {
	this.setVisible(true);
	this.setTitle("Empire");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setMaximizedBounds(getMaximizedBounds());
	this.setExtendedState(MAXIMIZED_BOTH);
	this.setLayout(new BorderLayout());
	this.setSize(new Dimension(5000,5000));
//	this.setBackground(Color.RED);
	welcome= new JPanelBackgrd("Background1.jpg");
	welcome.setBackground(Color.BLACK);
	welcome.setPreferredSize(new Dimension(2000,2000));
	//welcome.setPreferredSize(new Dimension(800,this.getHeight()));
	this.add(welcome,BorderLayout.CENTER);
//	JTextArea test = new JTextArea();
//	this.add(test,BorderLayout.NORTH);
	name1=new JTextArea("Enter player name: ");
	name1.setEditable(false);
	name1.setPreferredSize(new Dimension(250,40) );
	
	welcome.add(name1);
	//this.pack();
	this.revalidate();
	this.repaint();
}
public JPanelBackgrd getWelcome() {
	return welcome;
}
public void setWelcome(JPanelBackgrd welcome) {
	this.welcome = welcome;
}

public JTextArea getName1() {
	return name1;
}
public void setName1(JTextArea name1) {
	this.name1 = name1;
}



}
