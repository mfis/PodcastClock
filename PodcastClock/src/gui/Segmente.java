package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Segmente extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final float MIN_WIDTH = 12, MIN_HEIGHT = 20;

	private static final boolean[] _0 = new boolean[] { true, true, true, true,
			true, true, false }, _1 = new boolean[] { false, true, true, false,
			false, false, false }, _2 = new boolean[] { true, true, false,
			true, true, false, true }, _3 = new boolean[] { true, true, true,
			true, false, false, true }, _4 = new boolean[] { false, true, true,
			false, false, true, true }, _5 = new boolean[] { true, false, true,
			true, false, true, true }, _6 = new boolean[] { true, false, true,
			true, true, true, true }, _7 = new boolean[] { true, true, true,
			false, false, false, false }, _8 = new boolean[] { true, true,
			true, true, true, true, true }, _9 = new boolean[] { true, true,
			true, true, false, true, true };

	private boolean[] segment = _0.clone();

	private Color borderColor = Color.GRAY;

	public Segmente(int x, int y) {
		super();
		init();
		setBackground(Color.BLACK);
		setSize(30, 50);
		setBorder(new LineBorder(Color.BLACK));
		setSegmentBorderColor(Color.BLACK);
		setForeground(Color.BLUE);
		setLocation(x, y);
		setAllSegments(false);
	}

	public Segmente(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		init();
	}

	public Segmente(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		init();
	}

	public Segmente(LayoutManager layout) {
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

	public void showNumber(int num) {

		if (num < 0) {
			num = 0;
		} else if (num > 9) {
			num = 9;
		}
		switch (num) {
		case 0:
			segment = _0.clone();
			break;
		case 1:
			segment = _1.clone();
			break;
		case 2:
			segment = _2.clone();
			break;
		case 3:
			segment = _3.clone();
			break;
		case 4:
			segment = _4.clone();
			break;
		case 5:
			segment = _5.clone();
			break;
		case 6:
			segment = _6.clone();
			break;
		case 7:
			segment = _7.clone();
			break;
		case 8:
			segment = _8.clone();
			break;
		case 9:
			segment = _9.clone();
			break;
		}
		repaint();
	}

	public void setSegment(int segment, boolean state) {
		if (segment < 0)
			segment = 0;
		else if (segment > 6)
			segment = 6;

		this.segment[segment] = state;

		repaint();
	}

	public void setAllSegments(boolean state) {
		for (int i = 0; i < 7; i++) {
			segment[i] = state;
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		Graphics2D g = (Graphics2D) gr;

		for (int i = 0; i < segment.length; i++) {
			if (segment[i])
				paintSegment(g, i);
		}
	}

	private void paintSegment(Graphics2D g, int segment) {

		switch (segment) {
		case 0:
			horizontal(g, 0);
			break;
		case 6:
			horizontal(g, 8);
			break;
		case 3:
			horizontal(g, 16);
			break;
		case 5:
			vertikal(g, 0, 0);
			break;
		case 1:
			vertikal(g, 0, 8);
			break;
		case 4:
			vertikal(g, 8, 0);
			break;
		case 2:
			vertikal(g, 8, 8);
			break;
		}
	}

	private void horizontal(Graphics2D g, int row) {
		float fieldWidth = (getWidth()) / 12f;
		float fieldHeight = (getHeight()) / 20f;

		Path2D.Float poly1 = new Path2D.Float();
		Path2D.Float poly2 = new Path2D.Float();
		Path2D.Float border = new Path2D.Float();
		Rectangle2D.Float rect = new Rectangle2D.Float();

		poly1.moveTo(fieldWidth * 2, fieldHeight * 2 + row * fieldHeight);
		poly1.lineTo(fieldWidth * 3, fieldHeight + row * fieldHeight);
		poly1.lineTo(fieldWidth * 3, fieldHeight * 3 + row * fieldHeight);
		poly1.lineTo(fieldWidth * 2, fieldHeight * 2 + row * fieldHeight);

		rect.setRect(fieldWidth * 3, fieldHeight + row * fieldHeight,
				fieldWidth * 6, fieldHeight * 2);

		poly2.moveTo(fieldWidth * 9, fieldHeight + row * fieldHeight);
		poly2.lineTo(fieldWidth * 10, fieldHeight * 2 + row * fieldHeight);
		poly2.lineTo(fieldWidth * 9, fieldHeight * 3 + row * fieldHeight);
		poly2.lineTo(fieldWidth * 9, fieldHeight + row * fieldHeight);

		g.setPaint(getForeground());
		g.fill(poly1);
		g.fill(rect);
		g.fill(poly2);

		border.moveTo(fieldWidth * 2, fieldHeight * 2 + row * fieldHeight);
		border.lineTo(fieldWidth * 3, fieldHeight + row * fieldHeight);
		border.lineTo(fieldWidth * 9, fieldHeight + row * fieldHeight);
		border.lineTo(fieldWidth * 10, fieldHeight * 2 + row * fieldHeight);
		border.lineTo(fieldWidth * 9, fieldHeight * 3 + row * fieldHeight);
		border.lineTo(fieldWidth * 3, fieldHeight * 3 + row * fieldHeight);
		border.lineTo(fieldWidth * 2, fieldHeight * 2 + row * fieldHeight);

		g.setPaint(borderColor);
		g.draw(border);
	}

	private void vertikal(Graphics2D g, int row, int column) {
		float fieldWidth = (getWidth()) / 12f;
		float fieldHeight = (getHeight()) / 20f;

		Path2D.Float poly1 = new Path2D.Float();
		Path2D.Float poly2 = new Path2D.Float();
		Path2D.Float border = new Path2D.Float();
		Rectangle2D.Float rect = new Rectangle2D.Float();

		poly1.moveTo(fieldWidth * 2 + column * fieldWidth, fieldHeight * 2
				+ row * fieldHeight);
		poly1.lineTo(fieldWidth * 3 + column * fieldWidth, fieldHeight * 3
				+ row * fieldHeight);
		poly1.lineTo(fieldWidth + column * fieldWidth, fieldHeight * 3 + row
				* fieldHeight);
		poly1.lineTo(fieldWidth * 2 + column * fieldWidth, fieldHeight * 2
				+ row * fieldHeight);

		rect.setRect(fieldWidth + column * fieldWidth, fieldHeight * 3 + row
				* fieldHeight, fieldWidth * 2, fieldHeight * 6);

		poly2.moveTo(fieldWidth + column * fieldWidth, fieldHeight * 9 + row
				* fieldHeight);
		poly2.lineTo(fieldWidth * 3 + column * fieldWidth, fieldHeight * 9
				+ row * fieldHeight);
		poly2.lineTo(fieldWidth * 2 + column * fieldWidth, fieldHeight * 10
				+ row * fieldHeight);
		poly2.lineTo(fieldWidth + column * fieldWidth, fieldHeight * 9 + row
				* fieldHeight);

		g.setPaint(getForeground());
		g.fill(poly1);
		g.fill(rect);
		g.fill(poly2);

		border.moveTo(fieldWidth * 2 + column * fieldWidth, fieldHeight * 2
				+ row * fieldHeight);
		border.lineTo(fieldWidth * 3 + column * fieldWidth, fieldHeight * 3
				+ row * fieldHeight);
		border.lineTo(fieldWidth * 3 + column * fieldWidth, fieldHeight * 9
				+ row * fieldHeight);
		border.lineTo(fieldWidth * 2 + column * fieldWidth, fieldHeight * 10
				+ row * fieldHeight);
		border.lineTo(fieldWidth + column * fieldWidth, fieldHeight * 9 + row
				* fieldHeight);
		border.lineTo(fieldWidth + column * fieldWidth, fieldHeight * 3 + row
				* fieldHeight);
		border.lineTo(fieldWidth * 2 + column * fieldWidth, fieldHeight * 2
				+ row * fieldHeight);

		g.setPaint(borderColor);
		g.draw(border);

	}

}
