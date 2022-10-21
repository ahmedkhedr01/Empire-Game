package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import engine.Game;

public class WorldMapView implements ActionListener {
private View view;
private JPanel map;
private String playerName;
private int maxTurns;
private JButton rome;
private JButton cairo;
private JButton sparta;
public WorldMapView(String p, View view) throws IOException {
	this.view=view;
	playerName=p;
	map= new JPanelBackgrd("Background1.jpg");
	map.setPreferredSize(new Dimension(2000,2000));
	JTextArea playerN= new JTextArea(p);
playerN.setEditable(false);
	JTextArea cityN = new JTextArea("Choose Starting City");
	cityN.setEditable(false);
	map.add(playerN);
	map.add(cityN);
	 rome = new JButton("Rome");
	rome.addActionListener(this);
	 cairo = new JButton("Cairo");
	cairo.addActionListener(this);
	 sparta = new JButton("Sparta");
	sparta.addActionListener(this);
	map.add(rome,BorderLayout.NORTH);
	map.add(cairo,BorderLayout.SOUTH);
	map.add(sparta,BorderLayout.EAST);

}
public JPanel getMap() {
	return map;
}
public void setMap(JPanel map) {
	this.map = map;
}
public String getPlayerName() {
	return playerName;
}
public void setPlayerName(String playerName) {
	this.playerName = playerName;
}
public int getMaxTurns() {
	return maxTurns;
}

public void actionPerformed(ActionEvent e) {
Game g=null;
	if(e.getSource().equals((JButton)rome)) {
	try {
		 g =new Game(this.getView(),playerName,"Rome");
	} catch (IOException e1) {
		
		e1.printStackTrace();
	}
}
	 if(e.getSource()==cairo) {
		try {
			 g =new Game(this.getView(),playerName,"Cairo");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	 }
	 if(e.getSource()==sparta) {	
	 try {
			 g =new Game(this.getView(), playerName,"Sparta");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	 }
		
	
}
public View getView() {
	return view;
}
public void setView(View view) {
	this.view = view;
}
	
}



