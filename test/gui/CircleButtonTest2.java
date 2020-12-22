package gui;

import java.awt.Color;
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
		
		// Adds Buttons to the GUI
		contentPane.add(pentagrams, "cell 0 0");
		contentPane.add(portals, "cell 1 0");
		contentPane.add(souls, "cell 2 0");
		contentPane.add(metalBands, "cell 3 0");
		contentPane.add(baths, "cell 4 0");
	}

}
