package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ui {
	JPanel controlPanel = new JPanel();
	public Ui() {
		JFrame frame = new JFrame("KWIC");
		
		controlPanel.setLayout(new FlowLayout());
		frame.setSize(400,400);
		frame.setLayout(new GridLayout(3, 1));

		JTextField input = new JTextField(20);
		input.setBounds(10, 10, 300, 30);
		input.setMinimumSize(new Dimension(300, 30));
		input.setPreferredSize(new Dimension(300, 30));
		input.setMaximumSize(input.getPreferredSize());
		JButton addTitle = new JButton("+ Add as Title");
		JButton addIgnoredWord = new JButton("+ Add as Ignored Word");
		
		controlPanel.add(input);	
		controlPanel.add(addTitle);
		controlPanel.add(addIgnoredWord);
		frame.add(controlPanel);
		frame.setBounds(100, 100, 550, 140);
		frame.setMinimumSize(new Dimension(550,140));
		//frame.setMaximizedBounds(new Rectangle(new Dimension(550,(int) frame.getPreferredSize().getHeight())));;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Ui test = new Ui();
	}

	/*class MyCustomTextField extends javax.swing.JTextField {

		private int originallimit;
		int previousLength;
		int length;

		public MyCustomTextField(int limit) {

			previousLength = 0;
			originallimit = limit;

			this.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {

					JTextField textField = (JTextField) e.getSource();
					length = textField.getText().length();

					if (length >= originallimit) {

						if (length > previousLength) {
							controlPanel.setSize(new Dimension(controlPanel
									.getWidth() + 5, controlPanel.getHeight()));
						} else {
							if (length < previousLength)
								controlPanel.setSize(new Dimension(controlPanel
										.getWidth() - 5, controlPanel.getHeight()));
						}
						previousLength = length;

					} else {
						controlPanel.setSize(controlPanel.getPreferredSize());
					}
				}
			});
		}

	}*/
}