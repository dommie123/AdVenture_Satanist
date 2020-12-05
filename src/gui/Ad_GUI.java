package gui;

import javax.swing.JProgressBar;

import game.Game;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

public class Ad_GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean pentagramsDone = true;
	private boolean portalsDone = true;
	private boolean soulsDone = true;
	private boolean metalBandsDone = true;
	private boolean bloodBathsDone = true;
	
	public Timer guiTimer;

	public Game game;
	/**
	 * Create the panel.
	 */
	public Ad_GUI(Game game) {
		this.game = game;
		setLayout(new MigLayout("", "[150px][158.00px][136.00px]", "[60px][][][][][][][60px][][60px][60px][60px]"));
		setBackground(Color.RED);

		JLabel lblName = new JLabel("Player: " + game.getPlayer().getName());
		add(lblName, "cell 0 0,alignx left,aligny top");

		JLabel lblAdventure = new JLabel("AdVenture");
		lblAdventure.setFont(new Font("Limelight", Font.PLAIN, 24));
		add(lblAdventure, "cell 1 0");

		JLabel lblMoney = new JLabel("Money: " + game.getPlayer().getMoneyAsString());
		add(lblMoney, "cell 2 0,alignx right,aligny top");
				
		JLabel lblSatanist = new JLabel("Satanist!");
		lblSatanist.setFont(new Font("Limelight", Font.PLAIN, 24));
		add(lblSatanist, "cell 1 1");
				
		JLabel lblDemons = new JLabel(game.getDemons().toString());
		add(lblDemons, "cell 2 1,alignx right,aligny top");
		
		JProgressBar progressPentagrams = new JProgressBar(0, 100);
		add(progressPentagrams, "cell 1 4,growx");
		
		JProgressBar progressPortals = new JProgressBar(0, 100);
		add(progressPortals, "cell 1 6,growx");
		
		JProgressBar progressSouls = new JProgressBar(0, 100);
		add(progressSouls, "cell 1 7,growx");
		
		JProgressBar progressMetalBands = new JProgressBar(0, 100);
		add(progressMetalBands, "cell 1 8,growx");
		
		JProgressBar progressBloodBaths = new JProgressBar(0, 100);
		add(progressBloodBaths, "cell 1 9,growx");
		
		JButton btnShop = new JButton("Shop");
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(3, 0);
			}
		});
		
		// Triggers a background thread for updating the progress bar
		JButton btnPentagrams = new JButton(game.getBusinessByIndex(0).toString());
		btnPentagrams.addActionListener(l -> {
			btnPentagrams.setEnabled(false);
			pentagramsDone = false;
			progressPentagrams.setValue(0);
			final Worker myWorker = new Worker();
			myWorker.execute();
			
			myWorker.update(10.0 / game.getBusinessByIndex(0).getWaitTime());
			myWorker.addPropertyChangeListener(pcEvent -> {
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
					try {
						myWorker.get();
						if (game.getBusinessByIndex(0).isAutoManaged())
							btnPentagrams.doClick();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
						System.exit(ERROR);
					}
				}
			});
		});
		add(btnPentagrams, "cell 0 4,growx");
		
		JButton btnBuyPentagrams = new JButton("Buy 1 (" + game.getBusinessByIndex(0).getCostAsString() + ")");
		btnBuyPentagrams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 0);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				btnPentagrams.setText(game.getBusinessByIndex(0).toString());
				btnBuyPentagrams.setText("Buy 1 (" + game.getBusinessByIndex(0).getCostAsString() + ")");
			}
		});
		add(btnBuyPentagrams, "cell 2 4,growx");
		

		add(btnShop, "cell 1 11,growx");
		
		JButton btnPortals = new JButton(game.getBusinessByIndex(1).toString());
		btnPortals.setEnabled(false);
		btnPortals.addActionListener(l -> {
			btnPortals.setEnabled(false);
			portalsDone = false;
			progressPortals.setValue(0);
			final Worker myWorker = new Worker();
			myWorker.execute();
			
			myWorker.update(10.0 / game.getBusinessByIndex(1).getWaitTime());
			myWorker.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressPortals.setValue(value);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					game.update(2, 1);
					portalsDone = true;
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnPortals.setEnabled(true);
					progressPortals.setValue(progressPortals.getMinimum());
					try {
						myWorker.get();
						if (game.getBusinessByIndex(1).isAutoManaged())
							btnPortals.doClick();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
						System.exit(ERROR);
					}
				}
			});
		});
		add(btnPortals, "cell 0 6,growx");
		
		JButton btnBuyPortals = new JButton("Buy 1 (" + game.getBusinessByIndex(1).getCostAsString() + ")");
		btnBuyPortals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 1);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				btnPortals.setText(game.getBusinessByIndex(1).toString());
				btnBuyPortals.setText("Buy 1 (" + game.getBusinessByIndex(1).getCostAsString() + ")");
				if (!btnPortals.isEnabled() && game.getBusinessByIndex(1).isPurchased()) btnPortals.setEnabled(true);
			}
		});
		add(btnBuyPortals, "cell 2 6,growx");		
		
		JButton btnCandiedSouls = new JButton(game.getBusinessByIndex(2).toString());
		btnCandiedSouls.setEnabled(false);
		btnCandiedSouls.addActionListener(l -> {
			btnCandiedSouls.setEnabled(false);
			soulsDone = false;
			progressSouls.setValue(0);
			final Worker myWorker = new Worker();
			myWorker.execute();
			
			myWorker.update(10.0 / game.getBusinessByIndex(2).getWaitTime());
			myWorker.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressSouls.setValue(value);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					game.update(2, 2);
					soulsDone = true;
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnCandiedSouls.setEnabled(true);
					progressSouls.setValue(progressSouls.getMinimum());
					try {
						myWorker.get();
						if (game.getBusinessByIndex(2).isAutoManaged())
							btnCandiedSouls.doClick();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
						System.exit(ERROR);
					}
				}
			});
		});
		add(btnCandiedSouls, "cell 0 7,growx");
		
		JButton btnBuySouls = new JButton("Buy 1 (" + game.getBusinessByIndex(2).getCostAsString() + ")");
		btnBuySouls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 2);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				btnCandiedSouls.setText(game.getBusinessByIndex(2).toString());
				btnBuySouls.setText("Buy 1 (" + game.getBusinessByIndex(2).getCostAsString() + ")");
				if (!btnCandiedSouls.isEnabled() && game.getBusinessByIndex(2).isPurchased()) btnCandiedSouls.setEnabled(true);
			}
		});
		add(btnBuySouls, "cell 2 7,growx");
		
		JButton btnMetalBands = new JButton(game.getBusinessByIndex(3).toString());
		btnMetalBands.setEnabled(false);
		btnMetalBands.addActionListener(l -> {
			btnMetalBands.setEnabled(false);
			metalBandsDone = false;
			progressMetalBands.setValue(0);
			final Worker myWorker = new Worker();
			myWorker.execute();
			
			myWorker.update(10.0 / game.getBusinessByIndex(3).getWaitTime());
			myWorker.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressMetalBands.setValue(value);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					game.update(2, 3);
					metalBandsDone = true;
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnMetalBands.setEnabled(true);
					progressMetalBands.setValue(progressMetalBands.getMinimum());
					try {
						myWorker.get();
						if (game.getBusinessByIndex(3).isAutoManaged())
							btnMetalBands.doClick();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
						System.exit(ERROR);
					}
				}
			});
		});
		add(btnMetalBands, "cell 0 8,growx");
		
		JButton btnBuyMetalBands = new JButton("Buy 1 (" + game.getBusinessByIndex(3).getCostAsString() + ")");
		btnBuyMetalBands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 3);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				btnMetalBands.setText(game.getBusinessByIndex(3).toString());
				btnBuyMetalBands.setText("Buy 1 (" + game.getBusinessByIndex(3).getCostAsString() + ")");
				if (!btnMetalBands.isEnabled() && game.getBusinessByIndex(3).isPurchased()) btnMetalBands.setEnabled(true);
			}
		});
		add(btnBuyMetalBands, "cell 2 8,growx");
		
		JButton btnBloodBaths = new JButton(game.getBusinessByIndex(4).toString());
		btnBloodBaths.addActionListener(l -> {
			btnBloodBaths.setEnabled(false);
			bloodBathsDone = false;
			progressBloodBaths.setValue(0);
			final Worker myWorker = new Worker();
			myWorker.execute();
			
			myWorker.update(10.0 / game.getBusinessByIndex(4).getWaitTime());
			myWorker.addPropertyChangeListener(pcEvent -> {
				if (pcEvent.getPropertyName().equals("progress")) {
					int value = (int) pcEvent.getNewValue();
					progressBloodBaths.setValue(value);
				} else if (pcEvent.getNewValue() == SwingWorker.StateValue.DONE) {
					game.update(2, 4);
					bloodBathsDone = true;
					lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
					btnBloodBaths.setEnabled(true);
					progressBloodBaths.setValue(progressBloodBaths.getMinimum());
					try {
						myWorker.get();
						if (game.getBusinessByIndex(4).isAutoManaged())
							btnBloodBaths.doClick();
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
						System.exit(ERROR);
					}
				}
			});
		});
		btnBloodBaths.setEnabled(false);
		add(btnBloodBaths, "cell 0 9,growx");
		
		JButton btnBuyBloodBaths = new JButton("Buy 1 (" + game.getBusinessByIndex(4).getCostAsString() + ")");
		btnBuyBloodBaths.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.update(1, 4);
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				btnBloodBaths.setText(game.getBusinessByIndex(4).toString());
				btnBuyBloodBaths.setText("Buy 1 (" + game.getBusinessByIndex(4).getCostAsString() + ")");
				if (!btnBloodBaths.isEnabled() && game.getBusinessByIndex(4).isPurchased()) btnBloodBaths.setEnabled(true);
			}
		});
		add(btnBuyBloodBaths, "cell 2 9,growx");
		
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
				
//				if (game.getBusinessByIndex(0).isAutoManaged() && btnPentagrams.isEnabled()) 
//					btnPentagrams.doClick();
//				if (game.getBusinessByIndex(1).isAutoManaged() && btnPortals.isEnabled())
//					btnPortals.doClick();
//				if (game.getBusinessByIndex(2).isAutoManaged() && btnCandiedSouls.isEnabled()) 
//					btnCandiedSouls.doClick();
//				if (game.getBusinessByIndex(3).isAutoManaged() && btnMetalBands.isEnabled()) 
//					btnMetalBands.doClick();
//				if (game.getBusinessByIndex(4).isAutoManaged() && btnBloodBaths.isEnabled()) 
//					btnBloodBaths.doClick();
				
				lblMoney.setText("Money: " + game.getPlayer().getMoneyAsString());
				lblDemons.setText(game.getDemons().toString());
				btnPentagrams.setText(game.getBusinessByIndex(0).toString());
				btnBuyPentagrams.setText("Buy 1 (" + game.getBusinessByIndex(0).getCostAsString() + ")");
				btnPortals.setText(game.getBusinessByIndex(1).toString());
				btnBuyPortals.setText("Buy 1 (" + game.getBusinessByIndex(1).getCostAsString() + ")");
				btnCandiedSouls.setText(game.getBusinessByIndex(2).toString());
				btnBuySouls.setText("Buy 1 (" + game.getBusinessByIndex(2).getCostAsString() + ")");
				btnMetalBands.setText(game.getBusinessByIndex(3).toString());
				btnBuyMetalBands.setText("Buy 1 (" + game.getBusinessByIndex(3).getCostAsString() + ")");
				btnBloodBaths.setText(game.getBusinessByIndex(4).toString());
				btnBuyBloodBaths.setText("Buy 1 (" + game.getBusinessByIndex(4).getCostAsString() + ")");	

				revalidate();
				repaint();
			}
		};
		guiTimer = new Timer(delay, updateGUI);
		guiTimer.start();	
	}

	public Game getGame() {
		return game;
	}
}

class Worker extends SwingWorker<Void, Integer> {

	private double inc = 1;		// Progress Increment
	private int delay = 100;
	private boolean paused = false;
	
	
	public void pause() {
		paused = true;
	}
	
	public void resume() {
		paused = false;
		this.notify();
	}
	
	@Override
	protected Void doInBackground() {
		while (!isCancelled()) {
			try {
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
				else {
					System.out.println("Worker " + this.getClass().getName() + " Paused...");
					synchronized(this) {
						wait(1000);
					}
				}
			} catch (InterruptedException e) {
				System.out.println("Worker " + this.getClass().getName() + " Interrupted!");
			}
		}
		return null;
	}
	
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
