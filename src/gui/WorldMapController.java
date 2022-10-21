package gui;

import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import engine.City;
import engine.Game;
import engine.GameListener;
import engine.Player;
import units.Army;
import units.Status;
import units.Unit;

public class WorldMapController  implements GameListener,ActionListener{
private View view;
private JPanelBackgrd map;
private JButton rome;
private JButton cairo;
private JButton sparta;	
private Player p;
private Game g;
private JTextArea gold;
private JButton end;
public WorldMapController (View view ,Game game, Player player) throws IOException {
	p=player;
	g=game;
	g.setListener(this);
//	if(g.isGameOver())
//		this.gameOver();
	this.gameOver();
	this.view=view;
		map= new JPanelBackgrd("Background1.jpg");
		map.setLayout(new GridBagLayout());
		map.setPreferredSize(new Dimension(5000,5000));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		gold = new JTextArea(p.getName()+"\n Gold: "+p.getTreasury()+"\n Food:"+(int)p.getFood()+"\n Current Turn Count: "+g.getCurrentTurnCount());
		 gold.setEditable(false);
		 map.add(gold,c);
		 c.gridy=1;
	
		 rome = new JButton("Rome");
			rome.addActionListener(this);
			 cairo = new JButton("Cairo");
			cairo.addActionListener(this);
			 sparta = new JButton("Sparta");
			sparta.addActionListener(this);
			map.add(rome,c);
			
			c.gridx=1;
			map.add(cairo,c);
			c.gridx=2;
			map.add(sparta,c);
			
			for (int i = 0; i < p.getControlledArmies().size(); i++) {
				Army a = p.getControlledArmies().get(i);
				//if(a.getCurrentStatus().equals(Status.IDLE)) {
				c.gridx=0;
				c.gridy=6*(i+1);
					//JButton b = new JButton("Idle Army "+(i+1));
				//b.addActionListener(this);
				//map.add(b,c);
				JTextArea ta= new JTextArea("Army"+(i+1)+" Current location: "+a.getCurrentLocation()+"   Target:  "+a.getTarget()+"    Distance to target: "+a.getDistancetoTarget()+"   Current Status:  "+a.getCurrentStatus()+"\n-----\n ");
				ArrayList <Unit>ua = a.getUnits();
				for (int j = 0; j < ua.size(); j++) {
					Unit u = ua.get(j);
					ta.setText(ta.getText()+"Unit "+(j+1)+"Current Soldier count:"+u.getCurrentSoldierCount()+"Max soldier count"+u.getMaxSoldierCount()+"\n");
				}
				c.gridx=2;
				ta.setEditable(false);
				map.add(ta,c);
				//}
				
			 }
//			for (int i = 0; i < p.getControlledArmies().size(); i++) {
//				Army a = p.getControlledArmies().get(i);
//				if(a.getCurrentStatus().equals(Status.MARCHING)) {
//					c.gridx=1;
//					c.gridy=3*(i+1);
//					JButton b = new JButton("Marching Army "+(i+1));
//					b.addActionListener(this);
//					map.add(b,c);
//					
//				}
//			 }
//			for (int i = 0; i < p.getControlledArmies().size(); i++) {
//				Army a = p.getControlledArmies().get(i);
//				if(a.getCurrentStatus().equals(Status.BESIEGING)) {
//					c.gridx=1;
//					c.gridy=4*(i+1);
//					JButton b = new JButton("Sieging Army "+(i+1));
//					b.addActionListener(this);
//					map.add(b,c);
//				}
//			}
                  
			 end= new JButton("End Turn");
			end.addActionListener(this);
			map.add(end);
			//System.out.println(p.getControlledCities().get(0).getName());
			
			view.getContentPane().removeAll();
			view.getContentPane().add(map);
			view.revalidate();
			view.repaint();

}

//public static void main(String[] args) {
//	new WorldMapController();
//}
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==rome) {
		boolean flag =false;
		int index=0;
		for (int i = 0; i <p.getControlledCities().size(); i++) {
			if(p.getControlledCities().get(i).getName().toLowerCase().equals("rome")) {
				flag=true;
				index=i;
				break;}
		}
		if(flag)
		new DisplayCity(view,g,p,p.getControlledCities().get(index));
	else {
		int i=0;
		for ( i = 0; i < g.getAvailableCities().size(); i++) {
		if(g.getAvailableCities().get(i).getName().toLowerCase().equals("rome"))
        break;
		}
			try {
				new DisplayUncontrolled(view,g,p,g.getAvailableCities().get(i));
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
	}
			
	}
	if(e.getSource()==cairo) {
		boolean flag =false;
		int index=0;
		for (int i = 0; i <p.getControlledCities().size(); i++) {
			if(p.getControlledCities().get(i).getName().toLowerCase().equals("cairo")) {
				flag=true;
				index=i;
				break;}
		}
		if(flag)
		new DisplayCity(view,g,p,p.getControlledCities().get(index));
	else {
		int i=0;
		for ( i = 0; i < g.getAvailableCities().size(); i++) {
		if(g.getAvailableCities().get(i).getName().toLowerCase().equals("cairo"))
        break;
		}
			try {
				new DisplayUncontrolled(view,g,p,g.getAvailableCities().get(i));
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
	}
			
	}
	if(e.getSource()==sparta) {
		boolean flag =false;
		int index=0;
		for (int i = 0; i <p.getControlledCities().size(); i++) {
			if(p.getControlledCities().get(i).getName().toLowerCase().equals("sparta")) {
				flag=true;
				index=i;
				break;}
		}
		if(flag)
		new DisplayCity(view,g,p,p.getControlledCities().get(index));
	else {
		int i=0;
		for ( i = 0; i < g.getAvailableCities().size(); i++) {
		if(g.getAvailableCities().get(i).getName().toLowerCase().equals("sparta"))
        break;
		}
			try {
				new DisplayUncontrolled(view,g,p,g.getAvailableCities().get(i));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
			
	}
	if(e.getSource()==end) {
		g.endTurn();
	}
	
	
}

public void onGame(Player p) {
	this.p=p;
	
}


public void onTargetCity() {
	// TODO Auto-generated method stub
	
}


public void onEndTurn() {
	Army a=null;
	for (int i = 0; i < g.getAvailableCities().size(); i++) {
		if(g.getAvailableCities().get(i).getTurnsUnderSiege()>2) {
			JOptionPane.showMessageDialog(null, "You have sieged "+g.getAvailableCities().get(i).getName()+" for 3 turns you must attack now!");
			for (int j = 0; j < p.getControlledArmies().size(); j++) {
				if(p.getControlledArmies().get(j).getCurrentLocation().equals(g.getAvailableCities().get(i).getName())&&p.getControlledArmies().get(j).getCurrentStatus().equals(Status.BESIEGING)) {
					a=p.getControlledArmies().get(j);
					new BattleView(new JTextArea("Battle log: \n------------\n"),view,a,g.getAvailableCities().get(i),0,g,p);
					return;
				}
			}
//			try {
//				new DisplayUncontrolled(view, g, p, g.getAvailableCities().get(i));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return;
		}
	}
	try {
		new WorldMapController(this.view, this.g,this.p);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}


public void onAutoResolve() {
	// TODO Auto-generated method stub
	
}


public void onOccupy(City city) {
	// TODO Auto-generated method stub
	
}


public void onLoadCitiesAndDistances() {
	// TODO Auto-generated method stub
	
}


public void onLoadArmy() {
	JButton b = new JButton("Defending Army");
	map.add(b);
	map.revalidate();
	map.repaint();
}

public JPanelBackgrd getMap() {
	return map;
}

public void setMap(JPanelBackgrd map) {
	this.map = map;
}

public void gameOver() {
	
	
	if(p.getControlledCities().size()>= 3 && g.getCurrentTurnCount()<=g.getMaxTurnCount()) {
		JOptionPane.showMessageDialog(null, "Congratulations you won! you have conquered the world!");
		System.exit(0);
	}
	if(g.getCurrentTurnCount()>g.getMaxTurnCount()) {
		JOptionPane.showMessageDialog(null, "You failed to conquer the world!");
		System.exit(0);
	}

	
	
}


}
