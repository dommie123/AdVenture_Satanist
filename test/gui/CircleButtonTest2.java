package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import gui.custom.*;

public class CircleButtonTest2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -815567478875680296L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CircleButtonTest2 frame = new CircleButtonTest2();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public CircleButtonTest2() {
		// Initializes GUI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[] [] [] [] []", "[]"));
		contentPane.setBackground(Color.RED);
		
		// Initializes Buttons
		CircleButton pentagrams = new CircleButton(new ImageIcon("images/pentagrams.png"));
		CircleButton portals = new CircleButton(new ImageIcon("images/portals.png"));
		CircleButton souls = new CircleButton(new ImageIcon("images/souls.png"));
		CircleButton metalBands = new CircleButton(new ImageIcon("images/metal-bands.png"));
		CircleButton baths = new CircleButton(new ImageIcon("images/baths.png"));
		
		CircleButton tPentagrams = new CircleButton("0", new ImageIcon("images/pentagrams.png"));
		CircleButton tPortals = new CircleButton("0", new ImageIcon("images/portals.png"));
		CircleButton tSouls = new CircleButton("0", new ImageIcon("images/souls.png"));
		CircleButton tMetalBands = new CircleButton("0", new ImageIcon("images/metal-bands.png"));
		CircleButton tBaths = new CircleButton("0", new ImageIcon("images/baths.png"));
		
		CircleButton dPentagrams = new CircleButton("0", new ImageIcon("images/pentagrams.png"));
		CircleButton dPortals = new CircleButton("0", new ImageIcon("images/portals.png"));
		CircleButton dSouls = new CircleButton("0", new ImageIcon("images/souls.png"));
		CircleButton dMetalBands = new CircleButton("0", new ImageIcon("images/metal-bands.png"));
		CircleButton dBaths = new CircleButton("0", new ImageIcon("images/baths.png"));
		
		ArrayList<CircleButton> buttons = new ArrayList<CircleButton>(Arrays.asList(
				pentagrams, portals, souls, metalBands, baths,
				tPentagrams, tPortals, tSouls, tMetalBands, tBaths,
				dPentagrams, dPortals, dSouls, dMetalBands, dBaths));
		
		// Adds Buttons to the GUI
		int s = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 5; j++) {
				contentPane.add(buttons.get(s), "cell " + j + " " + i);
				s++;
			}
		
		int t = s - 5;
		while (s > t) {
			buttons.get(s - 1).setEnabled(false);
			s--;
		}
	}

}
