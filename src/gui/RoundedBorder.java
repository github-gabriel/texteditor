package gui;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.Border;

import settings.Settings;

public class RoundedBorder implements Border {

	private int radius;

	RoundedBorder(int radius) {
		this.radius = radius;
	}

	public Insets getBorderInsets(Component c) {
		return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
	}

	public boolean isBorderOpaque() {
		return true;
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setStroke(new BasicStroke(Settings.BORDER_WIDTH));
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setColor(Settings.BUTTON_BORDER_COLOR);
		g2D.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
	}
}
