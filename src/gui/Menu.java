package gui;

import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import settings.Settings;

public class Menu extends JFrame implements ActionListener {

	private JPanel contentPane;

	private GridBagConstraints constraints;

	private JButton newFile, openFile;

	private JLabel headline;

	public Menu() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Settings.WIDTH, Settings.HEIGHT);
		this.setLayout(new GridBagLayout());
		this.constraints = new GridBagConstraints();
		this.setLocationRelativeTo(null);
		this.contentPane = new GradientPanel(Settings.MENU_BACKGROUND_COLOR_1, Settings.MENU_BACKGROUND_COLOR_2);
		this.contentPane.setLayout(new GridBagLayout());
		this.setContentPane(contentPane);

		constraints.gridy = 0;
		constraints.gridx = 0;

		constraints.insets = new Insets(0, 0, 0, 0);

		headline = new JLabel("Texteditor");
		headline.setHorizontalAlignment(SwingConstants.CENTER);
		headline.setForeground(Color.BLACK);
		headline.setFont(new Font("Bahnschrift", Font.BOLD, 100));
		contentPane.add(headline, constraints);

		constraints.insets = new Insets(10, 0, 10, 0);

		constraints.gridy = 1;

		newFile = new JButton("New File");
		newFile.setFocusable(false);
		newFile.setPreferredSize(new Dimension(Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT));
		newFile.setHorizontalAlignment(SwingConstants.CENTER);
		newFile.setForeground(Color.BLACK);
		newFile.setBackground(new Color(0, 0, 0, 0));
		newFile.setContentAreaFilled(false);
		newFile.setBorder(new RoundedBorder(25));
		newFile.setFont(new Font("Arial", Font.BOLD, 40));
		newFile.addActionListener(this);
		contentPane.add(newFile, constraints);

		constraints.gridy = 2;

		openFile = new JButton("Open File");
		openFile.setFocusable(false);
		openFile.setPreferredSize(new Dimension(Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT));
		openFile.setHorizontalAlignment(SwingConstants.CENTER);
		openFile.setForeground(Color.BLACK);
		openFile.setBackground(new Color(0, 0, 0, 0));
		openFile.setContentAreaFilled(false);
		openFile.setBorder(new RoundedBorder(25));
		openFile.setFont(new Font("Arial", Font.BOLD, 40));
		openFile.addActionListener(this);
		contentPane.add(openFile, constraints);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == newFile) {
			Texteditor texteditor = new Texteditor();
			texteditor.setVisible(true);
			texteditor.setResizable(false);
			texteditor.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
				}

				@Override
				public void windowIconified(WindowEvent e) {
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
				}

				@Override
				public void windowClosing(WindowEvent e) {
					if (!texteditor.isFileSaved()) {
						JOptionPane.showMessageDialog(null, "Are you sure you want to exit without saving?");
					}
				}

				@Override
				public void windowClosed(WindowEvent e) {
				}

				@Override
				public void windowActivated(WindowEvent e) {
				}
			});
		}
		if (e.getSource() == openFile) {
			JFileChooser fileChooser = new JFileChooser();

			int rueckgabeWert = fileChooser.showOpenDialog(null);

			if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
				Texteditor texteditor = new Texteditor(fileChooser.getSelectedFile());
				texteditor.setVisible(true);
				texteditor.setResizable(false);
				texteditor.addWindowListener(new WindowListener() {

					@Override
					public void windowOpened(WindowEvent e) {
					}

					@Override
					public void windowIconified(WindowEvent e) {
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
					}

					@Override
					public void windowClosing(WindowEvent e) {
						if (!texteditor.isFileSaved()) {
							JOptionPane.showMessageDialog(null, "Are you sure you want to exit without saving?");
						}
					}

					@Override
					public void windowClosed(WindowEvent e) {
					}

					@Override
					public void windowActivated(WindowEvent e) {
					}
				});
			}
		}
	}
}
