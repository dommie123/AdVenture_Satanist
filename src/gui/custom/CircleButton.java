package gui.custom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CircleButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -91854781551352553L;
	private boolean mouseOver = false;
	private boolean mousePressed = false;
	private boolean hasIcon;
	private ImageIcon icon;
	
	public CircleButton(String text) {
		super(text);
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		hasIcon = false;

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
	

	
	public CircleButton(ImageIcon icon) {
		super(icon);
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		
		Image img = icon.getImage().getScaledInstance(icon.getIconWidth() / 2, icon.getIconHeight() / 2, 0);
		icon.setImage(img);
		this.icon = icon;
		hasIcon = true;

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
	
	public CircleButton(String text, ImageIcon icon) {
		super(text);
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		
		
		Image img = icon.getImage().getScaledInstance(icon.getIconWidth() / 2, icon.getIconHeight() / 2, 0);
		icon.setImage(img);
		this.icon = icon;
		hasIcon = true;
		
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

	private int getDiameter() {
		int diameter;
		if (getText().equalsIgnoreCase("")) diameter = Math.min(getWidth(), getHeight()) - 10;
		else diameter = Math.min(getWidth(), getHeight());
		return diameter;
	}

	@Override
	public Dimension getPreferredSize() {
		FontMetrics metrics = getGraphics().getFontMetrics(getFont());
		int minDiameter = 10 + Math.max(metrics.stringWidth(getText()), metrics.getHeight());
		return new Dimension(minDiameter, minDiameter);
	}

	@Override
	public boolean contains(int x, int y) {
		int radius = getDiameter() / 2;
		return Point2D.distance(x, y, getWidth() / 2, getHeight() / 2) < radius;
	}

	@Override
	public void paintComponent(Graphics g) {
		
		int diameter = getDiameter();
		int radius = diameter / 2;
		int x = getWidth() / 2 - radius;
		int y = getHeight() / 2 - radius;
		float opacity = 0.5f;
		Graphics2D g2D = (Graphics2D) g.create();
		
		if (hasIcon) {
			if (isEnabled()) 
				g2D.setColor(Color.GRAY);
			else 
				g2D.setColor(Color.WHITE);
			
			g2D.fillOval(x, y, diameter, diameter);
			
			if (!getText().equals("")) {
				g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
				Image img = icon.getImage().getScaledInstance(diameter, diameter, 0);
				icon.setImage(img);
			}
			g2D.setClip(new Ellipse2D.Float(x, y, diameter, diameter));
			g2D.drawImage(icon.getImage(), x, y, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());
			
		} else {
			if (mousePressed) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.ORANGE);
			}
			g.fillOval(x, y, diameter, diameter);
		}
		
		if (mouseOver && isEnabled()) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLACK);
		}
		
		if (mousePressed && hasIcon && isEnabled()) {
			g.setColor(Color.WHITE);
		}
		g.drawOval(x, y, diameter, diameter);
		
		if (isEnabled()) g.setColor(new Color(235, 235, 235));
		else g.setColor(Color.BLACK);
		g.setFont(new Font("Permanent Marker", Font.PLAIN, 15));
		FontMetrics metrics = g.getFontMetrics(getFont());
		int stringWidth = metrics.stringWidth(getText());
		int stringHeight = metrics.getHeight();
		g.drawString(getText(), getWidth() / 2 - stringWidth / 2, getHeight() / 2 + stringHeight / 4);
	}
}
