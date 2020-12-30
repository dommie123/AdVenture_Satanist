package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.custom.RoundedRectangleButton;
import net.miginfocom.swing.MigLayout;

public class RoundedRectangleButtonTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6210013016463034883L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RoundedRectangleButtonTest frame = new RoundedRectangleButtonTest();
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
	public RoundedRectangleButtonTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[] [] [] [] []", "[]"));
		
		RoundedRectangleButton btnTest = new RoundedRectangleButton("Click Me");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Clicked!");
			}
		});
		contentPane.add(btnTest, "cell 0 0");
		
		RoundedRectangleButton btnTestDisabled = new RoundedRectangleButton("Click Me");
		btnTestDisabled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTestDisabled.setEnabled(false);
				btnTestDisabled.setText("Disabled");
				System.out.println("Button Disabled!");
			}
		});
		contentPane.add(btnTestDisabled, "cell 0 1");
	}

}
