package game;

import game.Player;
import gui.Ad_GUI;
import gui.ItemShop;
import gui.ItemShop_v2;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import assets.*;

public class Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3891348446378790339L;
	private Player player;
	private List<Business> businesses;
	private List<Multiplier> multipliers;
	private List<Multiplier> purchasedMultipliers;
	private List<SpeedBoost> boosts;
	private List<SpeedBoost> purchasedBoosts;
	private List<Manager> managers;
	private Demons demons;
	private transient static Ad_GUI gui;
	private transient static ItemShop shop;
	private transient static ItemShop_v2 newShop;
	private transient static boolean isPreviousSave = false;
	private transient static final Scanner s = new Scanner(System.in);

	public Game() {		
		if (!isPreviousSave) {

			businesses = new ArrayList<Business>(Arrays.asList(
					new Business(4.00, 0.50, 1, true, "Pentagrams", new ArrayList<HiddenMultiplier>(Arrays.asList(
							new HiddenMultiplier(2, 10), new HiddenMultiplier(1.5, 20), new HiddenMultiplier(3, 40),
							new HiddenMultiplier(4, 50), new HiddenMultiplier(1.2, 80), new HiddenMultiplier(10, 100))),
							new ArrayList<HiddenBoost>(Arrays.asList(new HiddenBoost(2, 30), new HiddenBoost(1.5, 60),
									new HiddenBoost(2, 70), new HiddenBoost(3, 90)))),
					new Business(25.00, 2, 2, false, "Hell Portals",
							new ArrayList<HiddenMultiplier>(Arrays.asList(new HiddenMultiplier(1.5, 10),
									new HiddenMultiplier(2, 30), new HiddenMultiplier(2.9, 40),
									new HiddenMultiplier(4, 60), new HiddenMultiplier(6.66, 90))),
							new ArrayList<HiddenBoost>(Arrays.asList(new HiddenBoost(1.5, 20), new HiddenBoost(1.2, 50),
									new HiddenBoost(1.4, 70), new HiddenBoost(1.1, 80), new HiddenBoost(4, 100)))),
					new Business(150.00, 10, 5, false, "Candied Souls",
							new ArrayList<HiddenMultiplier>(Arrays.asList(new HiddenMultiplier(2.1, 10),
									new HiddenMultiplier(1.2, 20), new HiddenMultiplier(1.5, 30),
									new HiddenMultiplier(1.7, 40), new HiddenMultiplier(2.2, 50),
									new HiddenMultiplier(1.9, 70), new HiddenMultiplier(3, 80),
									new HiddenMultiplier(2.7, 90), new HiddenMultiplier(5, 100))),
							new ArrayList<HiddenBoost>(Arrays.asList(new HiddenBoost(4.5, 60)))),
					new Business(1020, 50, 30, false, "Metal Bands",
							new ArrayList<HiddenMultiplier>(Arrays.asList(new HiddenMultiplier(10, 40),
									new HiddenMultiplier(5.6, 60), new HiddenMultiplier(6.66, 90))),
							new ArrayList<HiddenBoost>(Arrays.asList(new HiddenBoost(2.5, 10), new HiddenBoost(3, 20),
									new HiddenBoost(2.1, 30), new HiddenBoost(5, 50), new HiddenBoost(6.66, 70),
									new HiddenBoost(3.5, 80), new HiddenBoost(10, 100)))),
					new Business(9990, 400, 120, false, "BloodBaths",
							new ArrayList<HiddenMultiplier>(
									Arrays.asList(new HiddenMultiplier(1.5, 10), new HiddenMultiplier(3, 30),
											new HiddenMultiplier(4, 40), new HiddenMultiplier(6.66, 60),
											new HiddenMultiplier(7, 70), new HiddenMultiplier(9.99, 90))),
							new ArrayList<HiddenBoost>(Arrays.asList(new HiddenBoost(6.66, 20),
									new HiddenBoost(1.2, 50), new HiddenBoost(2.7, 80), new HiddenBoost(5, 100)))),
					new Business(24000, 4500, 600, false, "Furnaces",
							new ArrayList<HiddenMultiplier>(Arrays.asList(new HiddenMultiplier(5, 20),
									new HiddenMultiplier(6, 30), new HiddenMultiplier(9.99, 70),
									new HiddenMultiplier(15, 90))),
							new ArrayList<HiddenBoost>(Arrays.asList(new HiddenBoost(2.2, 10), new HiddenBoost(4, 40),
									new HiddenBoost(3, 50), new HiddenBoost(6.66, 60),
									new HiddenBoost(2.6, 80), new HiddenBoost(10, 100)))),
					new Business(72000, 15000, 3600, false, "Lava Jacuzzis",
							new ArrayList<HiddenMultiplier>(Arrays.asList(new HiddenMultiplier(3.5, 10),
									new HiddenMultiplier(3, 20), new HiddenMultiplier(6.66, 60),
									new HiddenMultiplier(1.2, 70), new HiddenMultiplier(10, 80))),
							new ArrayList<HiddenBoost>(Arrays.asList(new HiddenBoost(2.5, 30), new HiddenBoost(4, 40),
									new HiddenBoost(3, 50), new HiddenBoost(2.6, 90), new HiddenBoost(10, 100)))),
					new Business(666666, 66666, 21600, false, "Fake Judgements",
							new ArrayList<HiddenMultiplier>(Arrays.asList(new HiddenMultiplier(6.66, 10),
									new HiddenMultiplier(6.66, 30), new HiddenMultiplier(6.66, 50),
									new HiddenMultiplier(6.66, 70), new HiddenMultiplier(9.99, 90))),
							new ArrayList<HiddenBoost>(Arrays.asList(new HiddenBoost(6.66, 20),
									new HiddenBoost(6.66, 40), new HiddenBoost(6.66, 60), new HiddenBoost(6.66, 80),
									new HiddenBoost(9.99, 100))))));
			
			multipliers = new ArrayList<Multiplier>(Arrays.asList(new Multiplier(20, 3.5, "Extra Candles"),
					new Multiplier(100, 4, "Flint and Steel"), new Multiplier(550, 4, "Corruption"),
					new Multiplier(6660, 7.77, "Golden Fiddles"), new Multiplier(20000, 4.1, "Bigger Chainsaws"), 
					new Multiplier(80000, 3.5, "Blast Furnaces"), new Multiplier(150000, 2.9, "Anti-Menorahs"), 
					new Multiplier(666666, 9.99, "Convincing Signatures")));
			boosts = new ArrayList<SpeedBoost>(Arrays.asList(new SpeedBoost(2, 1, 500, "1 hr. Boost"),
					new SpeedBoost(2, 4, 1500, "4 hr. Boost"), new SpeedBoost(2, 12, 8000, "12 hr. Boost"),
					new SpeedBoost(2, 24, 25000, "24 hr. Boost"), new SpeedBoost(2, 72, 75000, "3 day Boost")));
			managers = new ArrayList<Manager>(Arrays.asList(new Manager(150, "Face McStabby"),
					new Manager(1200, "Minecart Paul"), new Manager(9500, "Red W&W"),
					new Manager(60000, "Mars Tookmalut"), new Manager(240000, "Count Vlad"), 
					new Manager(1200000, "Mr. Glassman"), new Manager(3000000, "Scorps"),
					new Manager(66666666, "The Devil")));
			demons = new Demons(0.15);
			purchasedMultipliers = new ArrayList<Multiplier>();
			purchasedBoosts = new ArrayList<SpeedBoost>();
			
			for (int i = 0; i < businesses.size() && i < multipliers.size(); i++)
				multipliers.get(i).setTargetBusiness(businesses.get(i));
			for (int i = 0; i < businesses.size() && i < managers.size(); i++)
				managers.get(i).setTargetBusiness(businesses.get(i));

			player = new Player(JOptionPane.showInputDialog(null, "Please choose a username: ", "New User", JOptionPane.INFORMATION_MESSAGE));
		}
		
		gui = new Ad_GUI(this);
		shop = new ItemShop(this);
		newShop = new ItemShop_v2(this);
		shop.setVisible(false);
		newShop.setVisible(false);

		JFrame frame = new JFrame("AdVenture Satanist");

		frame.setContentPane(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/adv_satan_icon.png"));
		frame.setLocation(500, 400);
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Game g = loadGame();
		if (isPreviousSave) {
			gui = new Ad_GUI(g);
			shop = new ItemShop(g);
			newShop = new ItemShop_v2(g);
			shop.setVisible(false);
			newShop.setVisible(false);

			JFrame frame = new JFrame("AdVenture Satanist");

			frame.setContentPane(gui);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/adv_satan_icon.png"));
			frame.setLocation(500, 400);
			frame.setSize(500, 500);
			frame.setResizable(false);
			frame.setVisible(true);
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				s.close();
				saveGame(g);
				System.exit(0);
			}
		});
	}

	public void update(int code, int index) {
		switch (code) {
		case 1:
			player.buyBusiness(businesses.get(index));
			break;
		case 2:
			player.gainRevenue(businesses.get(index));
			demons.update(player);
			break;
		case 3: 
			newShop.setLocation(500, 400);
			newShop.setVisible(true);
			newShop.setExtendedState(JFrame.NORMAL);
			break;
		case 4:
			int i = index;
			while (!multipliers.get(index).getTargetBusiness().equals(businesses.get(i))) {
				if (i + 1 >= businesses.size()) i = 0;
				else i++;
			}
			player.buyMultiplier(businesses.get(i), multipliers.get(index));
			if (multipliers.get(index).isPurchased()) purchasedMultipliers.add(multipliers.remove(index));
			break;
		case 5:
			player.buySpeedBoost(businesses, boosts.get(index));
			if (boosts.get(index).isPurchased()) purchasedBoosts.add(boosts.remove(index));
			break;
		case 6: 
			player.buyDemons(businesses, multipliers, boosts, demons);
			break;
		case 7: 
			int j = index;
			while (!managers.get(index).getTargetBusiness().equals(businesses.get(j))) {
				if (j + 1 >= businesses.size()) j = 0;
				else j++;
			}
			player.buyManager(businesses.get(j), managers.get(index));
			if (managers.get(index).isPurchased()) managers.remove(index);
			break;
		}
		
		int delay = 500;
		ActionListener updateBoosts = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < purchasedBoosts.size(); i++) {
					if (!purchasedBoosts.get(i).isPurchased()) boosts.add(purchasedBoosts.remove(i));
				}				
			}
		};
		
		new Timer(delay, updateBoosts).start();
	}
	
	public List<Business> getBusinesses() {
		return businesses;
	}

	public List<Multiplier> getMultipliers() {
		return multipliers;
	}

	public List<SpeedBoost> getBoosts() {
		return boosts;
	}
	
	public List<Manager> getManagers() {
		return managers;
	}
	
	public Business getBusinessByIndex(int index) {
		return businesses.get(index);
	}

	public Multiplier getMultiplierByIndex(int index) {
		return multipliers.get(index);
	}

	public SpeedBoost getBoostByIndex(int index) {
		return boosts.get(index);
	}
	
	public Manager getManagerByIndex(int index) {
		return managers.get(index);
	}

	public Player getPlayer() {
		return player;
	}
	
	public Demons getDemons() {
		return demons;
	}
	
	public Scanner getScanner() {
		return s;
	}
	
	private static void saveGame(Game g) {
		try {
			FileOutputStream fileOut = new FileOutputStream("files/savedGame.ser");
			ObjectOutputStream output = new ObjectOutputStream(fileOut);
			output.writeObject(g);
			output.close();
			fileOut.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERROR! Could not save game! Exiting Program...", e.getLocalizedMessage()
					, JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private static Game loadGame() {
		try {
			FileInputStream fileIn = new FileInputStream("files/savedGame.ser");
			ObjectInputStream input = new ObjectInputStream(fileIn);
			Game g = (Game) input.readObject();
			input.close();
			fileIn.close();
			isPreviousSave = true;
			return g;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not find an existing save file! Starting a new game..."
					, e.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
			return new Game();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Could not find a game object! Starting a new game..."
					, e.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
			return new Game();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An unknown error occurred while loading the save file! Starting a new game..."
					, e.getLocalizedMessage(), JOptionPane.ERROR_MESSAGE);
			return new Game();
		}
	}
}
