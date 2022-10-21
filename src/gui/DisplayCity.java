package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import buildings.Barracks;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.MilitaryBuilding;
import engine.City;
import engine.Game;
import engine.GameListener;
import engine.Player;

public class DisplayCity implements ActionListener,GameListener{
	
	private JPanel p;
	private JButton b1;
	private JButton b2;
	private JButton wv;
	private JLabel lab;
	private City c;
	private View view;
	private Game g;
	private Player pl;
	private JTextArea gold;
	
public DisplayCity(View view, Game g, Player pl, City a) {
	this.view=view;
	this.g=g;
	this.pl=pl;
	this.c=a;
//	setLayout(new BorderLayout());
//	setSize(600, 400);
//	setVisible(true);
//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	p=new JPanel(new GridBagLayout());
	//p.setPreferredSize(new Dimension(2000,2000));
	GridBagConstraints c=new GridBagConstraints();
	
	p.setBackground(Color.CYAN);

	
	lab=new JLabel(a.getName());
	b1=new JButton("Buildings");
	b1.addActionListener(this);
	b2=new JButton("Armies");
	b2.addActionListener(this);
	c.gridx=0;
	c.gridy=1;

	p.add(b1,c);
	
	c.gridx=0;
	c.gridy=2;
	p.add(b2,c);
	c.gridx=0;
	c.gridy=0;
	p.add(lab,c);
	
	wv= new JButton("return to world map");
	wv.addActionListener(this);
	c.gridx=10000;
	c.gridy=10000;
	p.add(wv,c);
	c.gridx=10100;
	c.gridy=10100;
	gold = new JTextArea(pl.getName()+"\n Gold: "+pl.getTreasury()+"\n Food:"+(int)pl.getFood()+"\n Current Turn Count: "+g.getCurrentTurnCount());
	 gold.setEditable(false);
	 p.add(gold,c);
	view.getContentPane().removeAll();
	view.getContentPane().add(p);
	view.revalidate();
	view.repaint();
	//add(p,BorderLayout.CENTER);	
	
	
}

//	public static void main (String []args) {
//		//City city=new City("Sparta");
//	
//		
//		City aa=new City("Rome");
//		Barracks b=new Barracks ();
//		Farm f=new Farm();
//		ArrayList<EconomicBuilding> conA= new ArrayList<>();
//		ArrayList<MilitaryBuilding> conB= new ArrayList<>();
//		conA.add(f);
//		conB.add(b);
//		aa.setEconomicalBuildings(conA);
//		aa.setMilitaryBuildings(conB);
//		
//		//DisplayBuilding db=new DisplayBuilding(aa);  
//		
//		DisplayCity dc=new DisplayCity(aa); 
//		
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
	if(e.getSource()==b1) {
		new DisplayBuildings(view, g,pl,c);
	}if(e.getSource()==b2) {
			new DisplayArmy(view,g,pl,c);
	}
		if(e.getSource()==wv) {
			try {
				new WorldMapController(view,g,pl);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void onGame(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTargetCity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAutoResolve() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOccupy(City city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadCitiesAndDistances() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadArmy() {
		// TODO Auto-generated method stub
		
	}

	public City getC() {
		return c;
	}

	public void setC(City c) {
		this.c = c;
	}
	

}
