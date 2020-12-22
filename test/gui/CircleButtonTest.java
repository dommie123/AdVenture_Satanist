package gui;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

import gui.custom.CircleButton;

public class CircleButtonTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1253463332014129886L;
	private JPanel contentPane;

	/**
	 * Launches Window for Testing Circle Buttons
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CircleButtonTest frame = new CircleButtonTest();
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
	public CircleButtonTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[]"));
		
		CircleButton test = new CircleButton(new ImageIcon("images/adv_satan_icon.png"));
		contentPane.add(test, "cell 1 1");
	}

}
