package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets.Business;
import game.Game;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ItemShop extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6638382264002972318L;
	private JPanel contentPane;
	public Game game;
	private JTextField txtDebug;

	/**
	 * Create the frame.
	 */
	public ItemShop(Game game) {
		this.game = game;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][grow][][][][][][][][][]", "[][][][][][][]"));
		contentPane.setBackground(Color.RED);

		JLabel lblShop = new JLabel("SHOP");
		lblShop.setFont(new Font("Limelight", Font.BOLD, 24));
		contentPane.add(lblShop, "cell 7 0,alignx center,aligny center");
		
		txtDebug = new JTextField();
		contentPane.add(txtDebug, "cell 7 1,growx");
		txtDebug.setColumns(10);
		
		JButton btnDebug = new JButton("");
		btnDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Business dBusiness = new Business(0, 0, 0, true, "Debug", null, null);
					
					if (txtDebug.getText().equals("expireAll")) {
						for (int i = 0; i < 5; i++) {
							game.getBoostByIndex(i).setExpireDate(LocalDate.now());
							game.getBoostByIndex(i).setExpireTime(LocalTime.now());
						}
						System.out.println("All Boosts Expired!");
					}
					else if (txtDebug.getText().equals("setMoney")) {
						System.out.println("Set Player Money to: ");
						double money = game.getScanner().nextDouble();
						dBusiness.setRevenue(money - game.getPlayer().getCurrentMoney());
						game.getPlayer().gainRevenue(dBusiness);
						game.getDemons().update(game.getPlayer());
						System.out.println("Successfully changed money value!");
					}
					else {
						System.out.println("Unknown Command: \"" + txtDebug.getText() + "\"");
					}
				} catch (NullPointerException e1) {
					System.out.println("There is nothing in the Debug Text Field! ");
				} finally {
					txtDebug.setText("");
				}
			}
		});
		btnDebug.setForeground(Color.RED);
		btnDebug.setBackground(Color.DARK_GRAY);
		contentPane.add(btnDebug, "cell 13 1");

		JLabel lblMultipliers = new JLabel("Multipliers");
		lblMultipliers.setFont(new Font("Limelight", Font.PLAIN, 13));
		contentPane.add(lblMultipliers, "cell 2 2,alignx center");

		JLabel lblDemonInvestors = new JLabel("Demon Investors");
		lblDemonInvestors.setFont(new Font("Limelight", Font.PLAIN, 13));
		contentPane.add(lblDemonInvestors, "cell 7 2,alignx center");

		JLabel lblSpeedBoosts = new JLabel("Speed Boosts");
		lblSpeedBoosts.setFont(new Font("Limelight", Font.PLAIN, 13));
		contentPane.add(lblSpeedBoosts, "cell 13 2,alignx center");

		JButton btnExtraCandles = new JButton("Extra Candles ($20.00)");
		btnExtraCandles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(4, 0);
				if (game.getMultiplierByIndex(0).isPurchased()) btnExtraCandles.setEnabled(false);
			}
		});
		contentPane.add(btnExtraCandles, "cell 2 3,growx");

		JLabel lblYouCanReset = new JLabel("You can reset for");
		contentPane.add(lblYouCanReset, "cell 7 3,alignx center");

		JButton btnShortBoost = new JButton("1 hr. Boost ($20.00)");
		btnShortBoost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(5, 0);
				
				Runnable r = new Runnable() {

					@Override
					public void run() {
						while (game.getBoostByIndex(0).isPurchased()) {
							btnShortBoost.setEnabled(false);
						}
						if (!game.getBoostByIndex(0).isPurchased())
							btnShortBoost.setEnabled(true);
					}
					
				};
				
				new Thread(r).start();
			}
		});
		contentPane.add(btnShortBoost, "cell 13 3");

		JButton btnHellPortals = new JButton("Flint and Steel ($50.00)");
		btnHellPortals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(4, 1);
				if (game.getMultiplierByIndex(1).isPurchased()) btnHellPortals.setEnabled(false);
			}
		});
		contentPane.add(btnHellPortals, "cell 2 4,growx");
		
		JLabel lblDemons = new JLabel("0 Demons!");
		lblDemons.setFont(new Font("Quicksand", Font.PLAIN, 13));
		contentPane.add(lblDemons, "cell 7 4,alignx center");

		int delay = 1000;	// Delay in miliseconds
		ActionListener updateShop = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (game.getDemons().getQuantityCanPurchase() == 1)
						lblDemons.setText(game.getDemons().getQuantityCanPurchase() + " Demon!");
					else 
						lblDemons.setText(game.getDemons().getQuantityCanPurchase() + " Demons!");
				} catch (NullPointerException ex) {
					lblDemons.setText("0 Demons!");
				} finally {
					contentPane.revalidate();
					contentPane.repaint();
				}
			}
		};
		new Timer(delay, updateShop).start();
		
		JButton btnMidBoost = new JButton("4 hr. Boost ($100.00)");
		btnMidBoost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(5, 1);
				
				Runnable r = new Runnable() {

					@Override
					public void run() {
						while (game.getBoostByIndex(1).isPurchased()) {
							btnMidBoost.setEnabled(false);
						}
						if (!game.getBoostByIndex(1).isPurchased())
							btnMidBoost.setEnabled(true);
					}
				};
				
				new Thread(r).start();
			}
		});
		contentPane.add(btnMidBoost, "cell 13 4,growx");

		JButton btnCorruption = new JButton("Corruption ($200.00)");
		btnCorruption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(4, 2);
				if (game.getMultiplierByIndex(2).isPurchased()) btnCorruption.setEnabled(false);
			}
		});
		contentPane.add(btnCorruption, "cell 2 5,growx");

		JButton btnResetEmpire = new JButton("Reset Empire");
		btnResetEmpire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to sell your empire for " 
						+ game.getDemons().getQuantityCanPurchase() + " Demons? ", "Confirm Reset", 
						JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION)
					game.update(6, 0);
			}
		});
		contentPane.add(btnResetEmpire, "cell 7 5,growx");

		JButton btnLongBoost = new JButton("12 hr. Boost ($400.00)");
		btnLongBoost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(5, 2);
				
				Runnable r = new Runnable() {

					@Override
					public void run() {
						while (game.getBoostByIndex(2).isPurchased()) {
							btnLongBoost.setEnabled(false);
						}
						if (!game.getBoostByIndex(2).isPurchased())
							btnLongBoost.setEnabled(true);
					}
				};
				new Thread(r).start();
			}
		});
		contentPane.add(btnLongBoost, "cell 13 5,growx");
		
		JButton btnFiddles = new JButton("Golden Fiddles ($1,000.00)");
		btnFiddles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(4, 3);
				if (game.getMultiplierByIndex(3).isPurchased()) btnFiddles.setEnabled(false);
			}
		});
		contentPane.add(btnFiddles, "cell 2 6,growx");
	}
}