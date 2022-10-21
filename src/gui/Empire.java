package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import engine.Game;


public class Empire implements ActionListener {
private View view;
private Game model;
private JTextField tf;
public Empire() throws IOException {
	view = new View();
	tf = new JTextField();
	tf.setPreferredSize(new Dimension(250,50));
	view.getWelcome().add(tf);
	JButton b = new JButton("Start");
	b.addActionListener(this);
	view.getWelcome().add(b);
	
	
	view.revalidate();
	view.repaint();
	
}
public static void main(String[] args) throws IOException {
	new Empire();
}
public void actionPerformed(ActionEvent e) {
 String s=tf.getText();
 if(!s.isEmpty()) {
WorldMapView w=null;
try {
	w = new WorldMapView(s,view);
} catch (IOException e1) {
	
	e1.printStackTrace();
}
 view.getContentPane().removeAll();
 view.getContentPane().add(w.getMap());
 view.revalidate();
 view.repaint();}
 else {
	 JOptionPane.showMessageDialog(null, "You must enter player name!");
	 return;
 }
}

}
