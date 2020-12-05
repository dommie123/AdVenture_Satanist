package game;

import game.Player;
import gui.Ad_GUI;
import gui.ItemShop;
import gui.ItemShop_v2;

import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import assets.*;

public class Game {
	private Player player;
	private Ad_GUI gui;
	private ItemShop shop;
	private ItemShop_v2 newShop;
	private List<Business> businesses;
	private List<Multiplier> multipliers;
	private List<SpeedBoost> boosts;
	private List<Manager> managers;
	private Demons demons;
	private final static Scanner s = new Scanner(System.in);

	public Game() {
		businesses = Arrays.asList(new Business(0.75, 0.50, 1, true, "Pentagrams")
				, new Business(4.00, 2, 2, false, "Hell Portals")
				, new Business(25.00, 10, 5, false, "Candied Souls")
				, new Business(100.00, 50, 30, false, "Metal Bands")
				, new Business(950.00, 400, 120, false, "BloodBaths"));
		multipliers = Arrays.asList(new Multiplier(20, 3.5, "Extra Candles")
				, new Multiplier(50.00, 4, "Flint and Steel")
				, new Multiplier(200, 4, "Corruption")
				, new Multiplier(1000, 7.77, "Golden Fiddles")
				, new Multiplier(5000, 4.1, "Bigger Chainsaws"));
		boosts = Arrays.asList(new SpeedBoost(2, 1, 20, "1 hr. Boost")
				, new SpeedBoost(2, 4, 100, "4 hr. Boost")
				, new SpeedBoost(2, 12, 400, "12 hr. Boost")
				, new SpeedBoost(2, 24, 2000, "24 hr. Boost")
				, new SpeedBoost(2, 72, 15000, "3 day Boost"));
		managers = Arrays.asList(new Manager(20, "Face McStabby")
				, new Manager(60, "Minecart Paul")
				, new Manager(500, "Red W&W")
				, new Manager(2500, "Mars Tookmalut")
				, new Manager(10500, "Count Vlad"));
		demons = new Demons(0.15);
		
		player = new Player("Player One");
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
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				s.close();
				saveGame();
			}
		});
	}

	public static void main(String[] args) {
		new Game();
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
			player.buyMultiplier(businesses.get(index), multipliers.get(index));
			break;
		case 5:
			player.buySpeedBoost(businesses, boosts.get(index));
			break;
		case 6: 
			player.buyDemons(businesses, multipliers, boosts, demons);
			break;
		case 7: 
			player.buyManager(businesses.get(index), managers.get(index));
			break;
		}
	}

	public static void saveGame() {
		// TODO save game to a file
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
}
