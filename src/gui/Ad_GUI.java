package gui;

import javax.swing.JProgressBar;

import game.Game;
import gui.custom.CircleButton;
import gui.custom.ProgressUI;
import gui.custom.RoundedRectangleButton;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * This is the GUI for the AdVenture Satanist game. Like the <code>Game</code> class, it is 
 * the heart of the program, allowing for user interaction and ensuring that they have a
 * pleasant experience whilst playing. 
 * @author Dominick Wiley
 * @see Game
 *
 */
public class Ad_GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	
	// Check if each of the businesses is done generating revenue.
	private boolean pentagramsDone = true;
	private boolean portalsDone = true;
	private boolean soulsDone = true;
	private boolean metalBandsDone = true;
	private boolean bloodBathsDone = true;
	private boolean furnacesDone = true;
	private boolean jacuzzisDone = true;
	private boolean judgementsDone = true;
	
	// SwingWorkers for each of the progress bars in the GUI.
	private transient Worker worker0;
	private transient Worker worker1;
	private transient Worker worker2;
	private transient Worker worker3;
	private transient Worker worker4;
	private transient Worker worker5;
	private transient Worker worker6;
	private transient Worker worker7;
	
	// String representations of how many of each business is purchased.
	private String pentagramsPurchased;
	private String portalsPurchased;
	private String soulsPurchased;
	private String metalBandsPurchased;
	private String bloodBathsPurchased;
	private String furnacesPurchased;
	private String jacuzzisPurchased;
	private String judgementsPurchased;
	
	private Game game;
	
	// A list of idle workers to be replaced after a one-time use.
	private List<Worker> workers = new ArrayList<Worker>(Arrays.asList(new Worker(), new Worker(), new Worker()
									, new Worker(), new Worker(), new Worker(), new Worker(), new Worker()));
	/**
	 * The timer used to execute background tasks while the GUI display updates.
	 */
	public Timer guiTimer;

	
	/**
	 * Create the panel.
	 */
	public Ad_GUI(Game game) {
		this.game = game;
		setLayout(new MigLayout("", "[158.00px][158.00px][136.00px]", "[60px][][][][35.00][][][][][][][][60px][][][][][][][][][]"));
		setBackground(Color.ORANGE);
		
		JLabel lblName = new JLabel("Player: " + game.getPlayer().getName());
		lblName.setFont(new Font("Permanent Marker", Font.PLAIN, 15));
		lblName.setForeground(Color.WHITE);
		add(lblName, "cell 0 0,alignx left,aligny top");

		JLabel lblAdventure = new JLabel("AdVenture");
		lblAdventure.setForeground(Color.WHITE);
		lblAdventure.setFont(new Font("Limelight", Font.PLAIN, 24));
		add(lblAdventure, "cell 1 0");
		
		JLabel lblMoney = new JLabel("Money: " + game.getPlayer().getMoneyAsString());
		lblMoney.setForeground(Color.WHITE);
		lblMoney.setFont(new Font("Permanent Marker", Font.PLAIN, 15));
		add(lblMoney, "cell 2 0,alignx right,aligny top");
				
		JLabel lblSatanist = new JLabel("Satanist!");
		lblSatanist.setForeground(Color.WHITE);
		lblSatanist.setFont(new Font("Limelight", Font.PLAIN, 24));
		add(lblSatanist, "cell 1 1");
		
		JLabel lblDemons = new JLabel(game.getDemons().toString());
		lblDemons.setForeground(Color.WHITE);
		lblDemons.setFont(new Font("Permanent Marker", Font.PLAIN, 15));
		add(lblDemons, "cell 2 1,alignx right,aligny top");
		
		JProgressBar progressPentagrams = new JProgressBar(0, 100);
//		progressPentagrams.getGraphics().setClip(new RoundRectangle2D.Float(
//				progressPentagrams.getX(), progressPentagrams.getY(), progressPentagrams.getWidth(),
//				progressPentagrams.getHeight(), 10, 10));
		progressPentagrams.setBackground(new Color(255, 140, 0, 255));
		progressPentagrams.setUI(new ProgressUI());
		progressPentagrams.setBorder(BorderFactory.createEmptyBorder());
		add(progressPentagrams, "cell 1 4,growx");
		
		JProgressBar progressPortals = new JProgressBar(0, 100);
//		progressPortals.getGraphics().setClip(new RoundRectangle2D.Float(
//				progressPortals.getX(), progressPortals.getY(), progressPortals.getWidth(),
//				progressPortals.getHeight(), 10, 10));
		progressPortals.setBackground(new Color(255, 140, 0, 255));
		progressPortals.setUI(new ProgressUI());
		progressPortals.setBorder(BorderFactory.createEmptyBorder());
		add(progressPortals, "cell 1 5,growx");
		
		JProgressBar progressSouls = new JProgressBar(0, 100);
//		progressSouls.getGraphics().setClip(new RoundRectangle2D.Float(
//				progressSouls.getX(), progressSouls.getY(), progressSouls.getWidth(),
//				progressSouls.getHeight(), 10, 10));
		progressSouls.setBackground(new Color(255, 140, 0, 255));
		progressSouls.setUI(new ProgressUI());
		progressSouls.setBorder(BorderFactory.createEmptyBorder());
		add(progressSouls, "cell 1 6,growx");
		
		JProgressBar progressMetalBands = new JProgressBar(0, 100);
//		progressMetalBands.getGraphics().setClip(new RoundRectangle2D.Float(
//				progressMetalBands.getX(), progressMetalBands.getY(), progressMetalBands.getWidth(),
//				progressMetalBands.getHeight(), 10, 10));
		progressMetalBands.setBackground(new Color(255, 140, 0, 255));
		progressMetalBands.setUI(new ProgressUI());
		progressMetalBands.setBorder(BorderFactory.createEmptyBorder());
		add(progressMetalBands, "cell 1 7,growx");
		
		JProgressBar progressBloodBaths = new JProgressBar(0, 100);
//		progressBloodBaths.getGraphics().setClip(new RoundRectangle2D.Float(
//				progressBloodBaths.getX(), progressBloodBaths.getY(), progressBloodBaths.getWidth(),
//				progressBloodBaths.getHeight(), 10, 10));
		progressBloodBaths.setBackground(new Color(255, 140, 0, 255));
		progressBloodBaths.setUI(new ProgressUI());
		progressBloodBaths.setBorder(BorderFactory.createEmptyBorder());
		add(progressBloodBaths, "cell 1 8,growx");
		
		JProgressBar progressFurnaces = new JProgressBar(0, 100);
//		progressFurnaces.getGraphics().setClip(new RoundRectangle2D.Float(
//				progressFurnaces.getX(), progressFurnaces.getY(), progressFurnaces.getWidth(),
//				progressFurnaces.getHeight(), 10, 10));
		progressFurnaces.setBackground(new Color(255, 140, 0, 255));
		progressFurnaces.setUI(new ProgressUI());
		progressFurnaces.setBorder(BorderFactory.createEmptyBorder());
		add(progressFurnaces, "cell 1 9,growx");
		
		JProgressBar progressJacuzzis = new JProgressBar(0, 100);
//		progressJacuzzis.getGraphics().setClip(new RoundRectangle2D.Float(
//				progressJacuzzis.getX(), progressJacuzzis.getY(), progressJacuzzis.getWidth(),
//				progressJacuzzis.getHeight(), 10, 10));
		progressJacuzzis.setBackground(new Color(255, 140, 0, 255));
		progressJacuzzis.setUI(new ProgressUI());
		progressJacuzzis.setBorder(BorderFactory.createEmptyBorder());
		add(progressJacuzzis, "cell 1 10,growx");
		
		JProgressBar progressJudgements = new JProgressBar(0, 100);
//		progressJudgements.getGraphics().setClip(new RoundRectangle2D.Float(
//				progressJudgements.getX(), progressJudgements.getY(), progressJudgements.getWidth(),
//				progressJudgements.getHeight(), 10, 10));
		progressJudgements.setBackground(new Color(255, 140, 0, 255));
		progressJudgements.setUI(new ProgressUI());
		progressJudgements.setBorder(BorderFactory.createEmptyBorder());
		add(progressJudgements, "cell 1 11,growx");
		
		// Triggers a background thread for updating the progress bar
		pentagramsPurchased = String.valueOf(game.getBusinessByIndex(0).getQuantityPurchased());
		CircleButton btnPentagrams = new CircleButton(pentagramsPurchased, new ImageIcon("images/pentagrams.png"));
		btnPentagrams.addActionListener(l -> {
			btnPentagrams.setEnabled(false);
			pentagramsDone = false;
			progressPentagrams.setValue(0);
//			final Worker myWorker = new Worker();
//			myWorker.execute();
			worker0 = workers.remove(0);
			worker0.execute();
			
			worker0.update(10.0 / game.getBusinessByIndex(0).getWaitTime());
			worker0.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressPentagrams.setValue(value);
					if (btnPentagrams.isEnabled()) btnPentagrams.setEnabled(false);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					pentagramsDone = true;
					game.update(2, 0);
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnPentagrams.setEnabled(true);
					progressPentagrams.setValue(progressPentagrams.getMinimum());	
					workers.add(new Worker());
					if (game.getBusinessByIndex(0).isAutoManaged()) {
						btnPentagrams.doClick();
					}
				}
			});
		});
		add(btnPentagrams, "cell 0 4,growx");
		
		RoundedRectangleButton btnBuyPentagrams = new RoundedRectangleButton("Buy 1 (" + game.getBusinessByIndex(0).getCostAsString() + ")");
		btnBuyPentagrams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 0);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				pentagramsPurchased = String.valueOf(game.getBusinessByIndex(0).getQuantityPurchased());
				btnPentagrams.setText(pentagramsPurchased);
				btnBuyPentagrams.setText("Buy 1 (" + game.getBusinessByIndex(0).getCostAsString() + ")");
			}
		});
		add(btnBuyPentagrams, "cell 2 4,growx");
		
		portalsPurchased = String.valueOf(game.getBusinessByIndex(1).getQuantityPurchased());
		CircleButton btnPortals = new CircleButton(portalsPurchased, new ImageIcon("images/portals.png"));
		btnPortals.addActionListener(l -> {
			btnPortals.setEnabled(false);
			portalsDone = false;
			progressPortals.setValue(0);
			worker1 = workers.remove(0);
			worker1.execute();
			
			worker1.update(10.0 / game.getBusinessByIndex(1).getWaitTime());
			worker1.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressPortals.setValue(value);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					game.update(2, 1);
					portalsDone = true;
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnPortals.setEnabled(true);
					progressPortals.setValue(progressPortals.getMinimum());
					workers.add(new Worker());
					if (game.getBusinessByIndex(1).isAutoManaged()) {
						btnPortals.doClick();
					}
				}
			});
		});
		if (!game.getBusinessByIndex(1).isPurchased()) btnPortals.setEnabled(false);
		add(btnPortals, "cell 0 5,growx");
		
		RoundedRectangleButton btnBuyPortals = new RoundedRectangleButton("Buy 1 (" + game.getBusinessByIndex(1).getCostAsString() + ")");
		btnBuyPortals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 1);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				portalsPurchased = String.valueOf(game.getBusinessByIndex(1).getQuantityPurchased());
				btnPortals.setText(portalsPurchased);
				btnBuyPortals.setText("Buy 1 (" + game.getBusinessByIndex(1).getCostAsString() + ")");
				if (!btnPortals.isEnabled() && game.getBusinessByIndex(1).isPurchased()) btnPortals.setEnabled(true);
			}
		});
		add(btnBuyPortals, "cell 2 5,growx");		
		
		soulsPurchased = String.valueOf(game.getBusinessByIndex(2).getQuantityPurchased());
		CircleButton btnCandiedSouls = new CircleButton(soulsPurchased, new ImageIcon("images/souls.png"));
		btnCandiedSouls.addActionListener(l -> {
			btnCandiedSouls.setEnabled(false);
			soulsDone = false;
			progressSouls.setValue(0);
			worker2 = workers.remove(0);
			worker2.execute();
			
			worker2.update(10.0 / game.getBusinessByIndex(2).getWaitTime());
			worker2.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressSouls.setValue(value);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					game.update(2, 2);
					soulsDone = true;
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnCandiedSouls.setEnabled(true);
					progressSouls.setValue(progressSouls.getMinimum());
					workers.add(new Worker());
					if (game.getBusinessByIndex(2).isAutoManaged()) {
						btnCandiedSouls.doClick();
					}
				}
			});
		});
		if (!game.getBusinessByIndex(2).isPurchased()) btnCandiedSouls.setEnabled(false);
		add(btnCandiedSouls, "cell 0 6,growx");
		
		RoundedRectangleButton btnBuySouls = new RoundedRectangleButton("Buy 1 (" + game.getBusinessByIndex(2).getCostAsString() + ")");
		btnBuySouls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 2);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				soulsPurchased = String.valueOf(game.getBusinessByIndex(2).getQuantityPurchased());
				btnCandiedSouls.setText(soulsPurchased);
				btnBuySouls.setText("Buy 1 (" + game.getBusinessByIndex(2).getCostAsString() + ")");
				if (!btnCandiedSouls.isEnabled() && game.getBusinessByIndex(2).isPurchased()) btnCandiedSouls.setEnabled(true);
			}
		});
		add(btnBuySouls, "cell 2 6,growx");
		
		metalBandsPurchased = String.valueOf(game.getBusinessByIndex(3).getQuantityPurchased());
		CircleButton btnMetalBands = new CircleButton(metalBandsPurchased, new ImageIcon("images/metal-bands.png"));
		btnMetalBands.addActionListener(l -> {
			btnMetalBands.setEnabled(false);
			metalBandsDone = false;
			progressMetalBands.setValue(0);
			worker3 = workers.remove(0);
			worker3.execute();

			worker3.update(10.0 / game.getBusinessByIndex(3).getWaitTime());
			worker3.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressMetalBands.setValue(value);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					game.update(2, 3);
					metalBandsDone = true;
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnMetalBands.setEnabled(true);
					progressMetalBands.setValue(progressMetalBands.getMinimum());
					workers.add(new Worker());
					if (game.getBusinessByIndex(3).isAutoManaged()) {
						btnMetalBands.doClick();
					}
				}
			});
		});
		if (!game.getBusinessByIndex(3).isPurchased()) btnMetalBands.setEnabled(false);
		add(btnMetalBands, "cell 0 7,growx");
		
		RoundedRectangleButton btnBuyMetalBands = new RoundedRectangleButton("Buy 1 (" + game.getBusinessByIndex(3).getCostAsString() + ")");
		btnBuyMetalBands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 3);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				metalBandsPurchased = String.valueOf(game.getBusinessByIndex(3).getQuantityPurchased());
				btnMetalBands.setText(metalBandsPurchased);
				btnBuyMetalBands.setText("Buy 1 (" + game.getBusinessByIndex(3).getCostAsString() + ")");
				if (!btnMetalBands.isEnabled() && game.getBusinessByIndex(3).isPurchased()) btnMetalBands.setEnabled(true);
			}
		});
		add(btnBuyMetalBands, "cell 2 7,growx");
		
		bloodBathsPurchased = String.valueOf(game.getBusinessByIndex(4).getQuantityPurchased());
		CircleButton btnBloodBaths = new CircleButton(bloodBathsPurchased, new ImageIcon("images/baths.png"));
		btnBloodBaths.addActionListener(l -> {
			btnBloodBaths.setEnabled(false);
			bloodBathsDone = false;
			progressBloodBaths.setValue(0);
			worker4 = workers.remove(0);
			worker4.execute();
			
			worker4.update(10.0 / game.getBusinessByIndex(4).getWaitTime());
			worker4.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressBloodBaths.setValue(value);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					game.update(2, 4);
					bloodBathsDone = true;
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnBloodBaths.setEnabled(true);
					progressBloodBaths.setValue(progressBloodBaths.getMinimum());
					workers.add(new Worker());
					if (game.getBusinessByIndex(4).isAutoManaged()) {
						btnBloodBaths.doClick();
					}
				}
			});
		});
		if (!game.getBusinessByIndex(4).isPurchased()) btnBloodBaths.setEnabled(false);
		add(btnBloodBaths, "cell 0 8,growx");
		
		RoundedRectangleButton btnBuyBloodBaths = new RoundedRectangleButton("Buy 1 (" + game.getBusinessByIndex(4).getCostAsString() + ")");
		btnBuyBloodBaths.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 4);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				bloodBathsPurchased = String.valueOf(game.getBusinessByIndex(4).getQuantityPurchased());
				btnBloodBaths.setText(bloodBathsPurchased);
				btnBuyBloodBaths.setText("Buy 1 (" + game.getBusinessByIndex(4).getCostAsString() + ")");
				if (!btnBloodBaths.isEnabled() && game.getBusinessByIndex(4).isPurchased()) btnBloodBaths.setEnabled(true);
			}
		});
		add(btnBuyBloodBaths, "cell 2 8,growx");
		
		furnacesPurchased = String.valueOf(game.getBusinessByIndex(5).getQuantityPurchased());
		CircleButton btnFurnaces = new CircleButton(furnacesPurchased, new ImageIcon("images/furnace.png"));
		btnFurnaces.addActionListener(l -> {
			btnFurnaces.setEnabled(false);
			furnacesDone = false;
			progressFurnaces.setValue(0);
			worker5 = workers.remove(0);
			worker5.execute();
			
			worker5.update(10.0 / game.getBusinessByIndex(5).getWaitTime());
			worker5.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressFurnaces.setValue(value);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					game.update(2, 4);
					furnacesDone = true;
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnFurnaces.setEnabled(true);
					progressFurnaces.setValue(progressFurnaces.getMinimum());
					workers.add(new Worker());
					if (game.getBusinessByIndex(5).isAutoManaged()) {
						btnFurnaces.doClick();
					}
				}
			});
		});
		if (!game.getBusinessByIndex(5).isPurchased()) btnFurnaces.setEnabled(false);
		add(btnFurnaces, "cell 0 9,growx");
		
		RoundedRectangleButton btnBuyFurnaces = new RoundedRectangleButton("Buy 1 (" + game.getBusinessByIndex(5).getCostAsString() + ")");
		btnBuyFurnaces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 4);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				furnacesPurchased = String.valueOf(game.getBusinessByIndex(5).getQuantityPurchased());
				btnFurnaces.setText(furnacesPurchased);
				btnBuyFurnaces.setText("Buy 1 (" + game.getBusinessByIndex(5).getCostAsString() + ")");
				if (!btnFurnaces.isEnabled() && game.getBusinessByIndex(5).isPurchased()) btnFurnaces.setEnabled(true);
			}
		});
		add(btnBuyFurnaces, "cell 2 9,growx");
		
		jacuzzisPurchased = String.valueOf(game.getBusinessByIndex(6).getQuantityPurchased());
		CircleButton btnJacuzzis = new CircleButton(jacuzzisPurchased, new ImageIcon("images/jacuzzis.png"));
		btnJacuzzis.addActionListener(l -> {
			btnJacuzzis.setEnabled(false);
			jacuzzisDone = false;
			progressJacuzzis.setValue(0);
			worker6 = workers.remove(0);
			worker6.execute();
			
			worker6.update(10.0 / game.getBusinessByIndex(6).getWaitTime());
			worker6.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressJacuzzis.setValue(value);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					game.update(2, 4);
					jacuzzisDone = true;
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnJacuzzis.setEnabled(true);
					progressJacuzzis.setValue(progressJacuzzis.getMinimum());
					workers.add(new Worker());
					if (game.getBusinessByIndex(6).isAutoManaged()) {
						btnJacuzzis.doClick();
					}
				}
			});
		});
		if (!game.getBusinessByIndex(6).isPurchased()) btnJacuzzis.setEnabled(false);
		add(btnJacuzzis, "cell 0 10,growx");
		
		RoundedRectangleButton btnBuyJacuzzis = new RoundedRectangleButton("Buy 1 (" + game.getBusinessByIndex(6).getCostAsString() + ")");
		btnBuyJacuzzis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 4);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				jacuzzisPurchased = String.valueOf(game.getBusinessByIndex(6).getQuantityPurchased());
				btnJacuzzis.setText(jacuzzisPurchased);
				btnBuyJacuzzis.setText("Buy 1 (" + game.getBusinessByIndex(6).getCostAsString() + ")");
				if (!btnJacuzzis.isEnabled() && game.getBusinessByIndex(6).isPurchased()) btnJacuzzis.setEnabled(true);
			}
		});
		add(btnBuyJacuzzis, "cell 2 10,growx");
		
		judgementsPurchased = String.valueOf(game.getBusinessByIndex(7).getQuantityPurchased());
		CircleButton btnJudgements = new CircleButton(judgementsPurchased, new ImageIcon("images/judgements.png"));
		btnJudgements.addActionListener(l -> {
			btnJudgements.setEnabled(false);
			judgementsDone = false;
			progressJudgements.setValue(0);
			worker7 = workers.remove(0);
			worker7.execute();
			
			worker7.update(10.0 / game.getBusinessByIndex(7).getWaitTime());
			worker7.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressJudgements.setValue(value);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					game.update(2, 4);
					bloodBathsDone = true;
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnJudgements.setEnabled(true);
					progressJudgements.setValue(progressJudgements.getMinimum());
					workers.add(new Worker());
					if (game.getBusinessByIndex(7).isAutoManaged()) {
						btnJudgements.doClick();
					}
				}
			});
		});
		if (!game.getBusinessByIndex(7).isPurchased()) btnJudgements.setEnabled(false);
		add(btnJudgements, "cell 0 11,growx");
		
		RoundedRectangleButton btnBuyJudgements = new RoundedRectangleButton("Buy 1 (" + game.getBusinessByIndex(7).getCostAsString() + ")");
		btnBuyJudgements.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 4);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				btnJudgements.setText(judgementsPurchased);
				judgementsPurchased = String.valueOf(game.getBusinessByIndex(7).getQuantityPurchased());
				btnBuyJudgements.setText("Buy 1 (" + game.getBusinessByIndex(7).getCostAsString() + ")");
				if (!btnJudgements.isEnabled() && game.getBusinessByIndex(7).isPurchased()) btnJudgements.setEnabled(true);
			}
		});
		add(btnBuyJudgements, "cell 2 11,growx");
		
		RoundedRectangleButton btnShop = new RoundedRectangleButton("Shop");
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(3);
			}
		});
		

		add(btnShop, "cell 1 14,growx");
		
		setVisible(true);

		// Update the frame every half-second
		int delay = 500; // delay in milliseconds
		ActionListener updateGUI = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (!pentagramsDone) btnPentagrams.setEnabled(false);
				if (!portalsDone) btnPortals.setEnabled(false);
				if (!soulsDone) btnCandiedSouls.setEnabled(false);
				if (!metalBandsDone) btnMetalBands.setEnabled(false);
				if (!bloodBathsDone) btnBloodBaths.setEnabled(false);
				if (!furnacesDone) btnFurnaces.setEnabled(false);
				if (!jacuzzisDone) btnJacuzzis.setEnabled(false);
				if (!judgementsDone) btnJudgements.setEnabled(false);
				
				pentagramsPurchased = String.valueOf(game.getBusinessByIndex(0).getQuantityPurchased());
				portalsPurchased = String.valueOf(game.getBusinessByIndex(1).getQuantityPurchased());
				soulsPurchased = String.valueOf(game.getBusinessByIndex(2).getQuantityPurchased());
				metalBandsPurchased = String.valueOf(game.getBusinessByIndex(3).getQuantityPurchased());
				bloodBathsPurchased = String.valueOf(game.getBusinessByIndex(4).getQuantityPurchased());
				furnacesPurchased = String.valueOf(game.getBusinessByIndex(5).getQuantityPurchased());
				jacuzzisPurchased = String.valueOf(game.getBusinessByIndex(6).getQuantityPurchased());
				judgementsPurchased = String.valueOf(game.getBusinessByIndex(7).getQuantityPurchased());

				if (game.getBusinessByIndex(0).isAutoManaged() && btnPentagrams.isEnabled()) 
					btnPentagrams.doClick();
				if (game.getBusinessByIndex(1).isAutoManaged() && btnPortals.isEnabled())
					btnPortals.doClick();
				if (game.getBusinessByIndex(2).isAutoManaged() && btnCandiedSouls.isEnabled()) 
					btnCandiedSouls.doClick();
				if (game.getBusinessByIndex(3).isAutoManaged() && btnMetalBands.isEnabled()) 
					btnMetalBands.doClick();
				if (game.getBusinessByIndex(4).isAutoManaged() && btnBloodBaths.isEnabled()) 
					btnBloodBaths.doClick();
				if (game.getBusinessByIndex(5).isAutoManaged() && btnFurnaces.isEnabled()) 
					btnFurnaces.doClick();
				if (game.getBusinessByIndex(6).isAutoManaged() && btnJacuzzis.isEnabled()) 
					btnJacuzzis.doClick();
				if (game.getBusinessByIndex(7).isAutoManaged() && btnJudgements.isEnabled()) 
					btnJudgements.doClick();
				
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				lblDemons.setText(game.getDemons().toString());
				btnPentagrams.setText(pentagramsPurchased);
				btnBuyPentagrams.setText("Buy 1 (" + game.getBusinessByIndex(0).getCostAsString() + ")");
				btnPortals.setText(portalsPurchased);
				btnBuyPortals.setText("Buy 1 (" + game.getBusinessByIndex(1).getCostAsString() + ")");
				btnCandiedSouls.setText(soulsPurchased);
				btnBuySouls.setText("Buy 1 (" + game.getBusinessByIndex(2).getCostAsString() + ")");
				btnMetalBands.setText(metalBandsPurchased);
				btnBuyMetalBands.setText("Buy 1 (" + game.getBusinessByIndex(3).getCostAsString() + ")");
				btnBloodBaths.setText(bloodBathsPurchased);
				btnBuyBloodBaths.setText("Buy 1 (" + game.getBusinessByIndex(4).getCostAsString() + ")");
				btnFurnaces.setText(furnacesPurchased);
				btnBuyFurnaces.setText("Buy 1 (" + game.getBusinessByIndex(5).getCostAsString() + ")");
				btnJacuzzis.setText(jacuzzisPurchased);
				btnBuyJacuzzis.setText("Buy 1 (" + game.getBusinessByIndex(6).getCostAsString() + ")");
				btnJudgements.setText(judgementsPurchased);
				btnBuyJudgements.setText("Buy 1 (" + game.getBusinessByIndex(7).getCostAsString() + ")");
				
				revalidate();
				repaint();
			}
		};
		guiTimer = new Timer(delay, updateGUI);
		guiTimer.start();	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g.create();
		ImageIcon icon = new ImageIcon("images/test_background_1.jpg");
		
		g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2D.drawImage(icon.getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}
	/**
	 * Pauses and unpauses the swingworkers. (This method is deprecated and no longer in use.)
	 * @deprecated 
	 */
	public void updateWorkers() {
		for (Worker w : workers) {
			if (w.isPaused())
				w.resume();
			else
				w.pause();
		}
	}
	/**
	 * Gets the game associated with this GUI.
	 * @return the game associated with the GUI
	 */
	public Game getGame() {
		return game;
	}
}

/**
 * This is a custom-made SwingWorker designed to update the progress bars on the GUI, which
 * track the progress of the various businesses in generating revenue for the player. Without this
 * class, the progress bars would be static and unchanging, making it impossible for the player to
 * track progress.
 * @author Dominick Wiley
 *
 */
class Worker extends SwingWorker<Void, Integer> {

	private double inc = 1;		// Progress Increment
	private int delay = 100;
	private boolean paused = false;
	
	/**
	 * Pauses the worker, halting progress until it is resumed. (This method is deprecated.)
	 * @deprecated
	 */
	public void pause() {
		paused = true;
	}
	/**
	 * If paused, resumes the progress of the worker. (This method is deprecated.)
	 * @deprecated
	 */
	public void resume() {
		paused = false;
	}
	/**
	 * Checks whether the worker is paused. (This method is deprecated.)
	 * @return false if the worker is making progress
	 * @deprecated
	 */
	public boolean isPaused() {
		return paused;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		if (!paused) {
			int progress = 0;
			setProgress(progress);
			while (progress < 100) {
				if (progress + inc > 100)
					progress = 100;
				else
					progress += inc;
				// progress = Math.min(progress, 100);
				TimeUnit.MILLISECONDS.sleep(delay);
				setProgress(progress);
			}
		}
		return null;
	}
	
	@Override
	protected void done() {
		try {
			get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			System.exit(ImageObserver.ERROR);
		}
	}
	/**
	 * Changes the worker's increment value depending on the multiplier set by the program.
	 * @param multiplier - the value to multiply the increment value by
	 */
	protected void update(double multiplier) {
		inc *= multiplier;
		// If progress increment value is a decimal less than 1, multiply delay by 
		// denominator of increment's simplest fraction and set increment equal to 1.
		if (inc < 1 && inc > 0 ) {
			delay /= inc;
			inc /= multiplier;
		}
	}
}
