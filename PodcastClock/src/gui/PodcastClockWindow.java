package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import logic.Timer;

public class PodcastClockWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final String START_STRING = new String(new char[] {9654});
	
	private static final String STOP_STRING = new String(new char[] {9724});

	Segmente[] seven = null;
	Trenner[] trenner = null;
	JButton buttonStart = null;
	JButton buttonStop = null;
	JButton buttonPause = null;
	JButton buttonResume = null;
	JButton buttonMarker = null;
	JButton buttonNotice = null;
	JTextArea text;
	ActionListener buttonListener = null;
	JPanel cp = null;
	public Timer timer = null;

	public PodcastClockWindow(String title) {

		super(title);
		timer = new Timer();
		buildButtonListener();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(310, 270);
		setBackground(Color.BLACK);
		setForeground(Color.BLACK);
		int digitOffset = 65;

		JPanel cp = (JPanel) getContentPane();
		cp.setBackground(Color.BLACK);
		cp.setLayout(null);

		seven = new Segmente[5];
		trenner = new Trenner[2];

		seven[0] = new Segmente(5 + digitOffset, 5); 
		trenner[0] = new Trenner(35 + digitOffset, 5); 
		seven[1] = new Segmente(45 + digitOffset, 5);
		seven[2] = new Segmente(75 + digitOffset, 5);
		trenner[1] = new Trenner(105 + digitOffset, 5);
		seven[3] = new Segmente(115 + digitOffset, 5);
		seven[4] = new Segmente(145 + digitOffset, 5);

		cp.add(seven[0]);
		cp.add(seven[1]);
		cp.add(seven[2]);
		cp.add(seven[3]);
		cp.add(seven[4]);
		cp.add(trenner[0]);
		cp.add(trenner[1]);

		// Init Segments
		for (Segmente s : seven) {
			s.showNumber(0);
		}
		trenner[0].setAllSegments(true);
		trenner[1].setAllSegments(true);

		// Controls
		buttonStart = new JButton(START_STRING);
		buttonStart.addActionListener(buttonListener);
		buttonStart.setEnabled(true);
		buttonStart.setBounds(10, 23, 50, 35);
		buttonStart.repaint();
		cp.add(buttonStart);

		buttonStop = new JButton(STOP_STRING);
		buttonStop.addActionListener(buttonListener);
		buttonStop.setEnabled(false);
		buttonStop.setBounds(250, 23, 50, 35);
		buttonStop.repaint();
		cp.add(buttonStop);

		text = new JTextArea();
		text.setEditable(true);
		text.setEnabled(true);
		text.setAutoscrolls(true);
		text.setLineWrap(true);
		text.setForeground(Color.WHITE);
		text.setBackground(Color.BLACK);
		Border border = BorderFactory.createLineBorder(Color.BLUE);
		text.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		text.setCaretColor(Color.WHITE);
		text.setBounds(10, 75, 210, 160);
		text.repaint();
		JScrollPane textScrollPane = new JScrollPane(text);
		textScrollPane.repaint();
		textScrollPane.setBounds(10, 75, 210, 160);
		textScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		cp.add(textScrollPane);

		buttonNotice = new JButton("Notice");
		buttonNotice.addActionListener(buttonListener);
		buttonNotice.setEnabled(true);
		buttonNotice.setBounds(225, 75, 80, 35);
		buttonNotice.repaint();
		cp.add(buttonNotice);

		buttonMarker = new JButton("Chapter");
		buttonMarker.addActionListener(buttonListener);
		buttonMarker.setEnabled(false);
		buttonMarker.setBounds(225, 110, 80, 35);
		buttonMarker.repaint();
		cp.add(buttonMarker);

		buttonPause = new JButton("Pause");
		buttonPause.addActionListener(buttonListener);
		buttonPause.setEnabled(false);
		buttonPause.setBounds(225, 165, 80, 35);
		buttonPause.repaint();
		cp.add(buttonPause);

		buttonResume = new JButton("Resume");
		buttonResume.addActionListener(buttonListener);
		buttonResume.setEnabled(false);
		buttonResume.setBounds(225, 200, 80, 35);
		buttonResume.repaint();
		cp.add(buttonResume);


	}

	public void showTime(int h, int mm, int ss, boolean greyIfZeros) {

		int s1 = ss / 10;
		int s2 = ss - (s1 * 10);

		int m1 = mm / 10;
		int m2 = mm - (m1 * 10);

		// h
		seven[0].showNumber(h);
		// mm
		seven[1].showNumber(m1);
		seven[2].showNumber(m2);
		// ss
		seven[3].showNumber(s1);
		seven[4].showNumber(s2);

		if (h == 0) {
			seven[0].setForeground(Color.DARK_GRAY);
			trenner[0].setForeground(Color.DARK_GRAY);
		} else {
			seven[0].setForeground(Color.BLUE);
			trenner[0].setForeground(Color.BLUE);
		}

		if (h == 0 && m1 == 0) {
			seven[1].setForeground(Color.DARK_GRAY);
		} else {
			seven[1].setForeground(Color.BLUE);
		}

		if (h == 0 && mm == 0 && ss == 0 && greyIfZeros) {
			seven[2].setForeground(Color.DARK_GRAY);
			seven[3].setForeground(Color.DARK_GRAY);
			seven[4].setForeground(Color.DARK_GRAY);
			trenner[1].setForeground(Color.DARK_GRAY);
		} else {
			seven[2].setForeground(Color.BLUE);
			seven[3].setForeground(Color.BLUE);
			seven[4].setForeground(Color.BLUE);
			trenner[1].setForeground(Color.BLUE);
		}
	}

	private void buildButtonListener() {

		buttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource() instanceof JButton) {
					String b = ((JButton) ae.getSource()).getText();
					System.out.println("ButtonListener " + b);
					if (b.equals(START_STRING)) {
						text.setText("");						
						timer.start();
						appendLineAndSetFocus(b);
						buttonStart.setEnabled(false);
						buttonStop.setEnabled(true);
						buttonPause.setEnabled(true);
						buttonResume.setEnabled(true);
						buttonMarker.setEnabled(true);
					}
					if (b.equals(STOP_STRING)) {
						timer.stop();
						appendLineAndSetFocus(b);
						buttonStart.setEnabled(true);
						buttonStop.setEnabled(false);
						buttonPause.setEnabled(false);
						buttonResume.setEnabled(false);
						buttonMarker.setEnabled(false);
					}
					if (b.equals("Notice")) {
						if (!text.getText().startsWith("-- \n")) {
							String newText = "-- " + "\n" + text.getText();
							// newText = newText.trim();
							text.setText(newText);
						}
						text.setCaretPosition("-- ".length());
						text.requestFocus();
					}
					if (b.equals("Pause")) {
						appendLineAndSetFocus(b);
					}
					if (b.equals("Resume")) {
						appendLineAndSetFocus(b);
					}
					if (b.equals("Chapter")) {
						appendLineAndSetFocus("");
					}
				}
			}

			private void appendLineAndSetFocus(String s) {
				String newText = text.getText().trim() + "\n" + timer.timeFormatted() + " - " + s;
				newText = newText.trim();
				if (s.length() == 0) {
					newText = newText + " ";
				}
				text.setText(newText);
				text.setCaretPosition(newText.length());
				text.requestFocus();
			}
		};
	}

}
