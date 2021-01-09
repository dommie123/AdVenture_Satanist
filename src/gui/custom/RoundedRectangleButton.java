package gui.custom;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class RoundedRectangleButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6082394952171393422L;
	private boolean mousePressed = false;
	private boolean mouseOver = false;
	
	public RoundedRectangleButton(String text) {
		super(text);
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		
		MouseAdapter mouseListener = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent me) {
				if (contains(me.getX(), me.getY())) {
					mousePressed = true;
					repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				mousePressed = false;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent me) {
				mouseOver = false;
				mousePressed = false;
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent me) {
				mouseOver = contains(me.getX(), me.getY());
				repaint();
			}
		};

		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2D = (Graphics2D) g.create();
		
		if (isEnabled()) 
			g2D.setColor(Color.GRAY);
		else
			g2D.setColor(Color.LIGHT_GRAY);
		g2D.fillRoundRect(0, 0, width, height, 10, 10);
		
		if (isEnabled()) {
			if (mouseOver)
				g2D.setColor(Color.RED);
			else
				g2D.setColor(Color.BLACK);
			
			if (mousePressed)
				g2D.setColor(Color.WHITE);
		} else {
			g2D.setColor(Color.DARK_GRAY);
		}
		g2D.drawRoundRect(0, 0, width, height, 10, 10);
		
		if (isEnabled()) g.setColor(new Color(235, 235, 235));
		else g.setColor(Color.BLACK);
		g.setFont(new Font("Permanent Marker", Font.PLAIN, 15));
		FontMetrics metrics = g.getFontMetrics(getFont());
		int stringWidth = metrics.stringWidth(getText());
		int stringHeight = metrics.getHeight();
		g.drawString(getText(), getWidth() / 2 - stringWidth / 2 - stringWidth / 7, getHeight() / 2 + stringHeight / 4);
	}
}
