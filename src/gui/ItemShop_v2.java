package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import game.Game;
import assets.Manager;
import assets.Multiplier;
import assets.SpeedBoost;

public class ItemShop_v2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2401537811030572833L;
	private JPanel contentPane;
	
	public Game game;

	/**
	 * Create the frame.
	 */
	public ItemShop_v2(Game game) {
		this.game = game;
		setTitle("Item Shop");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 525, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[37.00][117.00][102.00,grow][102.00][grow]", "[][][100][40][]"));
		
		JLabel lblShop = new JLabel("SHOP");
		lblShop.setFont(new Font("Limelight", Font.BOLD, 22));
		contentPane.add(lblShop, "cell 2 0,alignx center");
		
		DefaultListModel<Manager> modelManagers = new DefaultListModel<Manager>();
		modelManagers.addAll(game.getManagers());
		JList<Manager> listManagers = new JList<Manager>(modelManagers);
		listManagers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listManagers.setLayoutOrientation(JList.VERTICAL);
		contentPane.add(listManagers, "cell 1 2,grow");
		
		DefaultListModel<Multiplier> modelMultipliers = new DefaultListModel<Multiplier>();
		modelMultipliers.addAll(game.getMultipliers());
		JList<Multiplier> listMultipliers = new JList<Multiplier>(modelMultipliers);
		listMultipliers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMultipliers.setLayoutOrientation(JList.VERTICAL);
		contentPane.add(listMultipliers, "cell 2 2,grow");
		
		DefaultListModel<SpeedBoost> modelBoosts = new DefaultListModel<SpeedBoost>();
		modelBoosts.addAll(game.getBoosts());
		JList<SpeedBoost> listBoosts = new JList<SpeedBoost>(modelBoosts);
		listBoosts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listBoosts.setLayoutOrientation(JList.VERTICAL);
		contentPane.add(listBoosts, "cell 3 2, grow");
		
		JButton btnBuyManager = new JButton("Buy Manager");
		btnBuyManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Manager current = game.getManagerByIndex(listManagers.getSelectedIndex());
					game.update(7, listManagers.getSelectedIndex());
					if (!game.getManagers().contains(current)) {
						modelManagers.remove(listManagers.getSelectedIndex());
						listManagers.setModel(modelManagers);
					}
					
					contentPane.revalidate();
					contentPane.repaint();
				} catch (ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null, "Please select a manager!", "No Manager Selected"
							, JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		contentPane.add(btnBuyManager, "cell 1 3,growx");
		
		JButton btnBuyMultiplier = new JButton("Buy Multiplier");
		btnBuyMultiplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Multiplier current = game.getMultiplierByIndex(listMultipliers.getSelectedIndex());
					game.update(4, listMultipliers.getSelectedIndex());
					if (!game.getMultipliers().contains(current)) {
						modelMultipliers.remove(listMultipliers.getSelectedIndex());
						listMultipliers.setModel(modelMultipliers);
					}
					
					contentPane.revalidate();
					contentPane.repaint();
				} catch (ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null, "Please select a multiplier!", "No Multiplier Selected"
							, JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		contentPane.add(btnBuyMultiplier, "cell 2 3,growx");
		
		JButton btnBuyBoost = new JButton("Buy Speed Boost\r\n");
		btnBuyBoost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SpeedBoost current = game.getBoostByIndex(listBoosts.getSelectedIndex());
					game.update(5, listBoosts.getSelectedIndex());
					if (!game.getBoosts().contains(current)) {
						modelBoosts.remove(listBoosts.getSelectedIndex());
						listBoosts.setModel(modelBoosts);
					}

					contentPane.revalidate();
					contentPane.repaint();
				} catch (ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null, "Please select a speed boost!", "No Speed Boost Selected"
							, JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		contentPane.add(btnBuyBoost, "cell 3 3,growx");
		
		JButton btnDemonInvestors = new JButton("Demon Investors");
		contentPane.add(btnDemonInvestors, "cell 2 4,growx");
		
		int delay = 1000;
		ActionListener updateShop = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (SpeedBoost b : game.getBoosts()) {
					if (!b.isPurchased() && !modelBoosts.contains(b)) {
						modelBoosts.add(game.getBoosts().indexOf(b), b);
						
					}
				}
			}
			
		};
		new Timer(delay, updateShop).start();
	}
}
