package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import gui.custom.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import net.miginfocom.swing.MigLayout;


public class ProgressTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8494548762200148084L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ProgressTest frame = new ProgressTest();
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
	public ProgressTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[428px]", "[30px][97.00px][23px]"));
		
		JProgressBar test = new JProgressBar();
		test.setUI(new ProgressUI());
		test.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(test, "cell 0 1,alignx center");
		
		Timer timer = new Timer(100, new ActionListener() {
			private int count = 0;

			public void actionPerformed(ActionEvent e) {
				test.setValue(count);
				count++;
				
				if (count >= 100) {
					((Timer) e.getSource()).stop();
					count = 0;
					test.setValue(count);
				}
			}
		});
		
		CircleButton btnTest = new CircleButton("Test", new ImageIcon("images/portals.png"));
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.start();
			}
		});
		contentPane.add(btnTest, "cell 0 2,growx,aligny top");
		
		JLabel lbTest = new JLabel("Test");
		lbTest.setHorizontalAlignment(SwingConstants.CENTER);
		lbTest.setHorizontalTextPosition(SwingConstants.CENTER);
		lbTest.setFont(new Font("Limelight", Font.PLAIN, 24));
		contentPane.add(lbTest, "cell 0 0,growx,aligny top");
		
	}

}
