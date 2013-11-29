package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Trenner extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final float MIN_WIDTH = 6, MIN_HEIGHT = 10;
	private boolean[] segment = new boolean[] { true, true };
	private Color borderColor = Color.GRAY;

	public Trenner(int x, int y) {
		super();
		init();
		setBackground(Color.BLACK);
		setSize(10, 50);
		setBorder(new LineBorder(Color.BLACK));
		setSegmentBorderColor(Color.BLACK);
		setForeground(Color.BLUE);
		setLocation(x, y);
		setAllSegments(false);
	}

	public Trenner(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		init();
	}

	public Trenner(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		init();
	}

	public Trenner(LayoutManager layout) {
		super(layout);
		init();
	}

	private void init() {
		setForeground(Color.BLUE);
		setSize((int) MIN_WIDTH, (int) MIN_HEIGHT);
		setPreferredSize(new Dimension((int) MIN_WIDTH * 2,
				(int) MIN_HEIGHT * 2));
		setMinimumSize(new Dimension((int) MIN_WIDTH, (int) MIN_HEIGHT));
	}

	public Color getSegmentBorderColor() {
		return borderColor;
	}

	public void setSegmentBorderColor(Color c) {
		borderColor = c;
		repaint();
	}

	public void setAllSegments(boolean state) {
		for (int i = 0; i < 2; i++) {
			segment[i] = state;
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		Graphics2D g = (Graphics2D) gr;
		paintSegment(g);
	}

	private void paintSegment(Graphics2D g) {
		horizontal(g, 0);
		horizontal(g, 8);
	}

	private void horizontal(Graphics2D g, int row) {
		float fieldWidth = (getWidth()) / 12f;
		float fieldHeight = (getHeight()) / 20f;

		Rectangle2D.Float rect = new Rectangle2D.Float();
		float y = fieldHeight + (row + 4) * fieldHeight;
		rect.setRect(fieldWidth * 3, y, fieldWidth * 6, fieldHeight * 2);
		g.setPaint(getForeground());
		g.fill(rect);

	}

}
