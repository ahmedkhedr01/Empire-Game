package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import buildings.*;
import engine.City;
import engine.Game;
import engine.GameListener;
import engine.Player;
import engine.PlayerListener;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Army;

public class DisplayBuildings implements ActionListener,PlayerListener,GameListener  {
	
	private JPanel p;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JLabel lab;
	private JLabel lab2;
	private City ci;
	private View view;
	private Game g;
	private Player pl;
	private JButton back;
	private JButton market;
	private JButton farm;
	private JButton aRange;
	private JButton barracks;
	private JButton stable;
	private JTextArea gold;
	private ArrayList<JButton> listUpgradeEco;
	private ArrayList<JButton> listUpgradeMili;
	private ArrayList<JButton> listRecruit;
	private Building building;
	private JButton end;
public DisplayBuildings (View view, Game g, Player pl, City city) {
	this.view=view;
	this.g=g;
	this.pl=pl;
	pl.setListener(this);
	this.g.setListener(this);
//	this.setVisible(true);
//	this.setTitle("Empire");
//	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//	this.setMaximizedBounds(getMaximizedBounds());
//	this.setExtendedState(MAXIMIZED_BOTH);
	//this.setLayout(new FlowLayout());

	this.ci=city;
	p=new JPanel(new GridBagLayout());
	GridBagConstraints c=new GridBagConstraints();
	p.setBackground(Color.CYAN);
	listUpgradeEco=new ArrayList<JButton>();
	 listUpgradeMili=new ArrayList<JButton>();
	 listRecruit=new ArrayList<JButton>();
	 //edited
	 c.gridx=80;
		c.gridy=2000;
		gold = new JTextArea(pl.getName()+"\n Gold: "+pl.getTreasury()+"\n Food:"+(int)pl.getFood()+"\n Current Turn Count: "+g.getCurrentTurnCount());
		 gold.setEditable(false);
		 p.add(gold,c);
		 c.gridx=100;
		 end= new JButton("End Turn");
		 end.addActionListener(this);
		 p.add(end,c);
		 
		 //edit
		 
	for(int i=0;i<city.getEconomicalBuildings().size();i++) {
		Building b=city.getEconomicalBuildings().get(i);
		c.gridx=80;
		c.gridy=10*(i+1);
		b1=new JButton("upgrade \n Cost: "+b.getUpgradeCost());
		b1.addActionListener(this);
		p.add(b1,c);
		listUpgradeEco.add(b1);
		if(b instanceof Farm) {
			lab=new JLabel("type: Farm ");
		}else {
			lab=new JLabel("type: Market ");
		}
		lab2=new JLabel("level: "+Integer.toString(b.getLevel()));
		c.gridx=50;
		//c.gridy=10*(i+1);
		p.add(lab,c);
		c.gridx=70;
		//c.gridy=2000*(i+1);
		p.add(lab2,c);
	}
	c.gridy=20;
	for(int j=0;j<city.getMilitaryBuildings().size();j++) {
		Building b=city.getMilitaryBuildings().get(j);
		c.gridx=50;
		c.gridy=c.gridy+10;
		JLabel lab3=new JLabel("level: "+Integer.toString(b.getLevel()));
		p.add(lab3,c);
		//c.gridx=20000*(j+1);
		//c.gridy=30000*(j+1);
		if(b instanceof ArcheryRange ) {
			lab3.setText("Type: Archery Range       "+lab3.getText());
			b2=new JButton("recruit archer   Cost: "+((ArcheryRange)b).getRecruitmentCost());
			
		}if(b instanceof Barracks) {
			lab3.setText("Type: Barracks      "+lab3.getText());
			b2=new JButton("recruit infantry   Cost: "+((Barracks)b).getRecruitmentCost());
	
		}if(b instanceof Stable) {
			lab3.setText("Type: Stable      "+lab3.getText());
			b2=new JButton("recruit cavalry  Cost: "+((Stable) b).getRecruitmentCost());
		}
		c.gridx=70;
		b3=new JButton("upgrade \n Cost: "+b.getUpgradeCost());
		b3.addActionListener(this);
		b2.addActionListener(this);
		p.add(b3,c);
		//c.gridx=20000*(j+1);
		c.gridx=80;
		//c.gridy=40000*(j+1);
		p.add(b2,c); 
		listRecruit.add(b2);
		listUpgradeMili.add(b3);
		
	}
	c.gridx=60;
	c.gridy=2000;
	back= new JButton("Back");
	back.addActionListener(this);
	p.add(back,c);
	boolean flag =false;
	for(int i=0; i<ci.getEconomicalBuildings().size();i++) {
	if(ci.getEconomicalBuildings().get(i) instanceof Market)
		flag=true;
	}
	if(!flag) {
	market=new JButton ("Build market \n price: 1500");
	market.addActionListener(this);
	c.gridx=5;
	c.gridy=0;
	p.add(market,c);
	}
	flag=false;
	for(int i=0; i<ci.getEconomicalBuildings().size();i++) {
		if(ci.getEconomicalBuildings().get(i) instanceof Farm)
			flag=true;
		}
		if(!flag) {
		farm=new JButton ("Build Farm \n price: 1000");
		farm.addActionListener(this);
		c.gridx=25;
		c.gridy=20;
		p.add(farm,c);
		}
		flag=false;
		for(int i=0; i<ci.getMilitaryBuildings().size();i++) {
			if(ci.getMilitaryBuildings().get(i) instanceof ArcheryRange)
				flag=true;
			}
			if(!flag) {
			aRange=new JButton ("Build Archery Range \n price: 1500");
			aRange.addActionListener(this);
			c.gridx=45;
			c.gridy=40;
			p.add(aRange,c);
			}
			flag=false;
			for(int i=0; i<ci.getMilitaryBuildings().size();i++) {
				if(ci.getMilitaryBuildings().get(i) instanceof Barracks)
					flag=true;
				}
				if(!flag) {
				barracks=new JButton ("Build Barracks \n price: 2000");
				barracks.addActionListener(this);
				c.gridx=65;
				c.gridy=60;
				p.add(barracks,c);
				}
				flag=false;
				for(int i=0; i<ci.getMilitaryBuildings().size();i++) {
					if(ci.getMilitaryBuildings().get(i) instanceof Stable)
						flag=true;
					}
					if(!flag) {
					stable=new JButton ("Build Stable \n price: 2500");
					stable.addActionListener(this);
					c.gridx=85;
					c.gridy=80;
					p.add(stable,c);
					}
					
	view.getContentPane().removeAll();
	view.getContentPane().add(p);
	view.revalidate();
	view.repaint();
		
	}
//	public static void main (String [] args) throws IOException {
//		City c=new City("Rome");
//		Farm b=new Farm();
//		ArcheryRange a=new ArcheryRange();
//		
//		ArrayList<EconomicBuilding> eb=new ArrayList();
//		 ArrayList<MilitaryBuilding> mb=new ArrayList();
//		 
//		 eb.add(b);
//		 mb.add(a);
//		 
//		c.setEconomicalBuildings(eb);
//		 c.setMilitaryBuildings(mb);
//		 DisplayBuildings db=new DisplayBuildings(c);
//		 
//		
//		
//		
//	}


@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==back) {//return to city
			new DisplayCity(view,g,pl,ci);
	}
	if(listUpgradeEco.contains( (JButton) e.getSource())) { //upgrade eco
		int i = listUpgradeEco.indexOf((JButton)e.getSource());
		
try {
	pl.upgradeBuilding(ci.getEconomicalBuildings().get(i));
} catch (NotEnoughGoldException e1) {

	JOptionPane.showMessageDialog(null, "You dont have enough gold!");
}
        catch (MaxLevelException e1) {
			
			JOptionPane.showMessageDialog(null, "Max level Reached!");
		}
		catch (BuildingInCoolDownException e1) {
			
			JOptionPane.showMessageDialog(null, "Building in Cool Down!");
		} 
	}
	if(listUpgradeMili.contains((JButton) e.getSource())) {// upgrade mili
		int i = listUpgradeMili.indexOf((JButton)e.getSource());
			try {
				pl.upgradeBuilding(ci.getMilitaryBuildings().get(i));
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "You dont have enough gold!");
			}
		
        catch (MaxLevelException e1) {
			
			JOptionPane.showMessageDialog(null, "Max level Reached!");
		}
		catch (BuildingInCoolDownException e1) {
			
			JOptionPane.showMessageDialog(null, "Building in Cool Down!");
		} 
	}
	if(e.getSource()==market) {//build
		try {
			pl.build("Market", ci.getName());
		} catch (NotEnoughGoldException e1) {
			JOptionPane.showMessageDialog(null, "You Don't have enough gold!");
		}
	}
	if(e.getSource()==farm) {
		try {
			pl.build("Farm", ci.getName());
		} catch (NotEnoughGoldException e1) {
			JOptionPane.showMessageDialog(null, "You Don't have enough gold!");
		}
	}
	if(e.getSource()==aRange) {
		try {
			pl.build("ArcheryRange", ci.getName());
		} catch (NotEnoughGoldException e1) {
			JOptionPane.showMessageDialog(null, "You Don't have enough gold!");
		}
	}
	if(e.getSource()==barracks) {
		try {
			pl.build("Barracks", ci.getName());
		} catch (NotEnoughGoldException e1) {
			JOptionPane.showMessageDialog(null, "You Don't have enough gold!");
		}
	}
	if(e.getSource()==stable) {
		try {
			pl.build("Stable", ci.getName());
		} catch (NotEnoughGoldException e1) {
			JOptionPane.showMessageDialog(null, "You Don't have enough gold!");
		}
	}
	if(listRecruit.contains(e.getSource())) {//recruit
		int i = listRecruit.indexOf((JButton)e.getSource());
		Building b = ci.getMilitaryBuildings().get(i);
		if(b instanceof ArcheryRange) {
		try {
			pl.recruitUnit("Archer", ci.getName());
		} catch (NotEnoughGoldException e1) {
			JOptionPane.showMessageDialog(null, "You Don't have enough gold!");
		}
		catch (BuildingInCoolDownException e1) {
			JOptionPane.showMessageDialog(null, "Building in cool down!");
			
		} catch (MaxRecruitedException e1) {
			
			JOptionPane.showMessageDialog(null, "Max Recruit reached!");
		}
	}
		if(b instanceof Stable) {
			try {
				pl.recruitUnit("Cavalry", ci.getName());
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "You Don't have enough gold!");
			}
			catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(null, "Building in cool down!");
			
			} catch (MaxRecruitedException e1) {
			
				JOptionPane.showMessageDialog(null, "Max Recruit reached!");
			}
		}
		if(b instanceof Barracks) {
			try {
				pl.recruitUnit("Infantry", ci.getName());
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "You Don't have enough gold!");
			}
			catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(null, "Building in cool down!");
			} catch (MaxRecruitedException e1) {
				JOptionPane.showMessageDialog(null, "Max Recruit reached!");
			}
		}
		
	}
	if(e.getSource()==end) {
		g.endTurn();
	}
}


@Override
public void onRecruitUnit() {
	JOptionPane.showMessageDialog(null, "Recruit added in "+ci.getName()+" defending Army");
	new DisplayBuildings(view,g,pl,ci);
	
}


@Override
public void onBuild() {
	new DisplayBuildings(view,g,pl,ci);
	
}


@Override
public void onUpgradeBuilding() {
	new DisplayBuildings(view,g,pl,ci);
	
}


@Override
public void onInitiateArmy() {
	// TODO Auto-generated method stub
	
}


@Override
public void onLaySiege() {
	// TODO Auto-generated method stub
	
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
	Army a=null;
	for (int i = 0; i < g.getAvailableCities().size(); i++) {
		if(g.getAvailableCities().get(i).getTurnsUnderSiege()>2) {
			JOptionPane.showMessageDialog(null, "You have sieged "+g.getAvailableCities().get(i).getName()+" for 3 turns you must attack now!");
			for (int j = 0; j < pl.getControlledArmies().size(); j++) {
				if(pl.getControlledArmies().get(j).getCurrentLocation().equals(g.getAvailableCities().get(i).getName())) {
					a=pl.getControlledArmies().get(j);
					new BattleView(new JTextArea("Battle log: \n------------\n"),view,a,g.getAvailableCities().get(i),0,g,pl);
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
	new DisplayBuildings(view, g, pl, ci);
	
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

	
}
	
	
	
	


