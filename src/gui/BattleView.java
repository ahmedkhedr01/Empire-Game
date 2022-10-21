package gui;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import engine.City;
import engine.Game;
import engine.GameListener;
import engine.Player;
import engine.PlayerListener;
import exceptions.FriendlyFireException;
import units.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buildings.*;
import engine.City;
import engine.Game;
import engine.Player;

/**
 * @author ahmedkhedr
 *
 */
public class BattleView implements ActionListener,PlayerListener,ArmyListener,GameListener{
	private JPanel p;
	private JButton b1;
	private JButton b2;
	private JButton wv;
	private JLabel lab;
	private JLabel lab1;
	private JLabel lab2;
	private JLabel lab3;
	private JLabel lab4;
	private JLabel lab5;
	private JLabel lab6;
	private JLabel lab7;
	private JLabel lab8;
	private JLabel lab9;
	private City ci;
	private View view;
	private JButton autores;
	private Game g;
	private Player pl;
	private JButton back;
	private JButton attack;
	private JButton attack1;
	private ArrayList<JButton> listInitiate;
	private JPanel p2;
	private JTextArea battlelog;
	private JRadioButton unitt;
	private ArrayList<JRadioButton> listRadio;
	private JRadioButton unit2;
	private ArrayList<JRadioButton> listRadio2;
	private Army attackingArmy;
	private int count;
	private int count1;
	private Unit unitCount;
	private boolean flag;
	public BattleView ( JTextArea bl, View view, Army att, City city, int count,Game g,Player pl) {
//		this.setVisible(true);
//		this.setTitle("Empire");
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setMaximizedBounds(getMaximizedBounds());
//		this.setExtendedState(MAXIMIZED_BOTH);
//		this.setLayout(new BorderLayout());
//		this.setSize(new Dimension(5000,5000));
		this.g=g;
		g.setListener(this);
		this.pl=pl;
		this.count=count;
		this.attackingArmy=att;
		this.flag=true;
	//uncomment after you are done	att.setCurrentStatus(Status.IDLE);
		
		p=new JPanel(new GridBagLayout());
		p2=new JPanel(new GridBagLayout());
		battlelog= bl;
		battlelog.setSize(2000,0);
		battlelog.setEditable(false);
		attackingArmy.setListener(this);
		GridBagConstraints c=new GridBagConstraints();
		p.setBackground(Color.CYAN);
		ci=city;
		ci.getDefendingArmy().setListener(this);
		this.view=view;
	//uncomment when u are done	ci.setTurnsUnderSiege(-1);
	//	ci.setUnderSiege(false);
		listInitiate = new ArrayList<JButton> ();
		
		//for(int j=0;j<pl.getControlledArmies().size();j++) {
			//Army a=pl.getControlledArmies().get(j);
			//if(attackingArmy.getTarget().equals(city.getName()) && attackingArmy.getDistancetoTarget()==0 && attackingArmy.getCurrentStatus().equals(Status.BESIEGING)) {
                
				c.gridx=0;
				c.gridy=0;
				lab=new JLabel("Player's attacking army: ");
				p2.add(lab,c);
				c.gridx=0;
				c.gridy=100;
				lab3=new JLabel("Units of army: " );
				p2.add(lab3,c);
				
				listRadio= new ArrayList<JRadioButton>();
				listRadio2= new ArrayList<JRadioButton>();
				ArrayList<Unit> u=attackingArmy.getUnits();
				for(int i=0;i<u.size();i++) {
					Unit unit=	u.get(i);
					c.gridx=-10;
					c.gridy=200*(i+1);
					unitt=new JRadioButton();
					unitt.addActionListener(this);
					p2.add(unitt,c);
					listRadio.add(unitt);
					c.gridx=0;
					c.gridy=200*(i+1);
					if(unit instanceof Cavalry) {
						lab=new JLabel(" type: Cavalry");
						p2.add(lab,c);
					}if(unit instanceof Archer) {
						lab=new JLabel(" type: Archer");
						p2.add(lab,c);
					}if(unit instanceof Infantry) {
						lab=new JLabel(" type: Infantry");
						p2.add(lab,c);
					}
					
					c.gridx=10;
					c.gridy=200*(i+1);
					lab1=new JLabel(" level:"+u.get(i).getLevel());
					p2.add(lab1,c);
					
					c.gridx=20;
					c.gridy=200*(i+1);
					lab2=new JLabel(" Current Soldier Count:"+u.get(i).getCurrentSoldierCount());
					p2.add(lab2,c);
					
					c.gridx=30;
					c.gridy=200*(i+1);
					lab3=new JLabel("  Max Soldier Count:"+u.get(i).getMaxSoldierCount());
					p2.add(lab3,c);
					
				}	
			//}	
		//}
		c.gridx=0;
		c.gridy+=100;
		attack= new JButton("Start Attack");
		attack.addActionListener(this);
		p2.add(attack,c);
		
		
		Army a=city.getDefendingArmy();
		
		c.gridx=0;
		c.gridy=0;
		lab=new JLabel("Defending Army: ");
		p.add(lab,c);
		
		c.gridx=0;
		c.gridy=100;
		lab3=new JLabel("Units of army: " );
		p.add(lab3,c);
		
		ArrayList<Unit> u1=a.getUnits();
		for(int i=0;i<u1.size();i++) {
			c.gridx=0;
			c.gridy=200*(i+1);
			Unit unit=	u1.get(i);
			unit2=new JRadioButton();
			unit2.addActionListener(this);
			listRadio2.add(unit2);
			p.add(unit2,c);
			if(unit instanceof Cavalry) {
				c.gridx=5;
				c.gridy=200*(i+1);
				lab=new JLabel(" type: Cavalry");
				p.add(lab,c);
				//c.gridx=40;
			}if(unit instanceof Archer) {
				c.gridx=5;
				c.gridy=200*(i+1);
				lab=new JLabel(" type: Archer");
				p.add(lab,c);
				//c.gridx=40;
			}if(unit instanceof Infantry) {
				c.gridx=5;
				c.gridy=200*(i+1);
				lab=new JLabel(" type: Infantry");
				p.add(lab,c);
				//c.gridx=40;
			}
			c.gridx=10;
			c.gridy=200*(i+1);
			lab1=new JLabel(" level:"+u1.get(i).getLevel());
			p.add(lab1,c);
			
			c.gridx=20;
			c.gridy=200*(i+1);
			lab2=new JLabel(" Current Soldier Count:"+u1.get(i).getCurrentSoldierCount());
			p.add(lab2,c);
			
			c.gridx=30;
			c.gridy=200*(i+1);
			lab3=new JLabel("  Max Soldier Count:"+u1.get(i).getMaxSoldierCount());
			p.add(lab3,c);
		}
		c.gridx=0;
		c.gridy+=100;
		attack1 = new JButton("Start Attack");
		attack1.addActionListener(this);
		p.add(attack1,c);
		c.gridx=20;
		autores= new JButton("Auto-Resolve");
		autores.addActionListener(this);
		p.add(autores,c);
view.getContentPane().removeAll();
view.getContentPane().add(p2,BorderLayout.WEST);
view.getContentPane().add(battlelog,BorderLayout.CENTER);
view.getContentPane().add(p,BorderLayout.EAST);
view.revalidate();
view.repaint();

	}

//	public static void main(String [] args) {
//		
//		
//			Army a=new Army("Rome");
//			a.setCurrentStatus(Status.BESIEGING);
//			a.setDistancetoTarget(0);	
//			a.setTarget("Sparta");
//			
//			City city=new City("Sparta");	
//			Army b=new Army("Sparta");
//			b.setCurrentLocation("Sparta");
//			b.setCurrentStatus(Status.IDLE);
//			b.setDistancetoTarget(-1);
//			
//			Archer arch=new Archer(1, 2, 3, 4, 5);
//			Cavalry c=new Cavalry(1, 2, 3, 4, 5);
//			arch.setParentArmy(a);
//			c.setParentArmy(a);
//			Archer arch2=new Archer(1, 2, 3, 4, 5);
//			Archer arch3=new Archer(1, 2, 3, 4, 5);
//			arch2.setParentArmy(b);
//			arch3.setParentArmy(b);
//			ArrayList<Unit> units=new ArrayList<Unit>();
//			ArrayList<Unit> units1=new ArrayList<Unit>();
//			
//			units.add(arch);
//			units.add(c);
//			units1.add(arch2);
//			units1.add(arch3);
//		
//			a.setUnits(units);
//			b.setUnits(units1);
//			city.setDefendingArmy(b);
//			
//			Player player=new Player("ceasar");
//	
//			
//			ArrayList<Army> controlA = new ArrayList<Army>();
//			player.getControlledArmies().add(a);
//			BattleView aa=new BattleView(a,player, city);
//	}
	
	@Override
	public void onRecruitUnit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBuild() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgradeBuilding() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitiateArmy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLaySiege() {
		// TODO Auto-generated method stub
		
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==attack) {
			if(count%2!=0) {
			JOptionPane.showMessageDialog(null, "It's Defending Army Turn"); 
			return;	
			}
			int c=0;
			int c1=0;
			Unit a=null;
			Unit d=null;
			for (int i = 0; i < listRadio.size(); i++) {
				if(listRadio.get(i).isSelected())
					c++;
			}
			if(c!=1) {
				JOptionPane.showMessageDialog(null, "You Must select one attacking Unit!");
				return;
			}
			for (int i = 0; i < listRadio2.size(); i++) {
				if(listRadio2.get(i).isSelected())
					c1++;
			}
			if(c1!=1) {
				JOptionPane.showMessageDialog(null, "You Must select one defending Unit!");
				return;
			}
			for (int i = 0; i < listRadio.size(); i++) {
				if(listRadio.get(i).isSelected()) {
					 a= attackingArmy.getUnits().get(i);
				}		
			}
			for (int j = 0; j < listRadio2.size(); j++) {
				if(listRadio2.get(j).isSelected())
					 d = ci.getDefendingArmy().getUnits().get(j);
			}
			this.setCount(this.getCount()+1);
			 count1= d.getCurrentSoldierCount();
			 unitCount=d;
			try {
				a.attack(d);
			} catch (FriendlyFireException e1) {
				JOptionPane.showMessageDialog(null, "Friendly Fire");
			}
		
		}
		if(e.getSource()==attack1) {
			if(count%2==0) {
				JOptionPane.showMessageDialog(null, "It's Attacking Army Turn"); 
				return;	
				}
			Unit unit1 = attackingArmy.getUnits().get((int) (Math.random() * attackingArmy.getUnits().size()));
			Unit unit2 = ci.getDefendingArmy().getUnits().get((int) (Math.random() * ci.getDefendingArmy().getUnits().size()));
			this.setCount(this.getCount()+1);
		    count1=unit1.getCurrentSoldierCount();
		    unitCount=unit1;
			try {
				unit2.attack(unit1);
			} catch (FriendlyFireException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//				int c=0;
//				int c1=0;
//				Unit a=null;
//				Unit d=null;
//				for (int i = 0; i < listRadio.size(); i++) {
//					if(listRadio.get(i).isSelected())
//						c++;
//				}
//				if(c!=1) {
//					JOptionPane.showMessageDialog(null, "You Must select one attacking Unit!");
//					return;
//				}
//				for (int i = 0; i < listRadio2.size(); i++) {
//					if(listRadio2.get(i).isSelected())
//						c1++;
//				}
//				if(c1!=1) {
//					JOptionPane.showMessageDialog(null, "You Must select one defending Unit!");
//					return;
//				}
//				for (int i = 0; i < listRadio.size(); i++) {
//					if(listRadio.get(i).isSelected()) {
//						 a= attackingArmy.getUnits().get(i);
//					}		
//				}
//				for (int j = 0; j < listRadio2.size(); j++) {
//					if(listRadio2.get(j).isSelected())
//						 d = ci.getDefendingArmy().getUnits().get(j);
//				}
//				this.setCount(this.getCount()+1);
//				count1=a.getCurrentSoldierCount();
//				unitCount=a;
//				try {
//					d.attack(a);
//				} catch (FriendlyFireException e1) {
//					JOptionPane.showMessageDialog(null, "Friendly Fire");
//				}
		}
	if(e.getSource()==autores) {
		flag=false;
		ci.setTurnsUnderSiege(-1);
		ci.setUnderSiege(false);
		attackingArmy.setCurrentStatus(Status.IDLE);
		try {
			g.autoResolve(attackingArmy, ci.getDefendingArmy());
		} catch (FriendlyFireException e1) {
			JOptionPane.showMessageDialog(null, "Friendly fire!");
		}
	}
	}

	@Override
	public void onAttack(int countt) {
		if(ci.getDefendingArmy().getUnits().size()==0) {
			    if(flag)
				g.occupy(attackingArmy, ci.getName());
				
			try {
				new WorldMapController(view, g, pl);
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			JOptionPane.showMessageDialog(null, "You Won This Battle "+ci.getName()+" Occupied");
			return;
		}if(attackingArmy.getUnits().size()==0) {
			try {
				new WorldMapController(view, g, pl);
			} catch (IOException e) {
				e.printStackTrace();
			} 
			JOptionPane.showMessageDialog(null, "You Lost The Battle");
			//pl.getControlledArmies().remove((Army)attackingArmy);
			return;
		}
		count1=count1-countt;
		battlelog.setText(battlelog.getText()+"The attacked unit has lost "+count1+" Soldiers\n");
		new BattleView(battlelog,view,attackingArmy,ci,count,g,pl);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public int getPrevCount() {
		return 0;
	}

	@Override
	public void onRelocate() {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAutoResolve() {
		if(ci.getDefendingArmy().getUnits().size()==0) {
			JOptionPane.showMessageDialog(null, "You Won This Battle"+ci.getName()+" Occupied");
//			if(pl.getControlledCities().size()>1)
//				g.occupy(attackingArmy, ci.getName());
		}else {
			JOptionPane.showMessageDialog(null, "You Lost The Battle");
			ci.setUnderSiege(false);
			ci.setTurnsUnderSiege(-1);
			//pl.getControlledArmies().remove((Army)attackingArmy);
		}
		try {
			new WorldMapController(view, g, pl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public void onOccupy(City city) {
		new DisplayCity(view,g,pl,city);
		
	}

	@Override
	public void onLoadCitiesAndDistances() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadArmy() {
		// TODO Auto-generated method stub
		
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	

}
