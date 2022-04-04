package gui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jdk.jfr.ContentType;
import settings.Settings;

public class Texteditor extends JFrame implements ActionListener {

	private JPanel contentPane;

	private JLabel headline;

	private JButton save, select;

	private GridBagConstraints constraints;

	private JTextArea textArea;

	private JSpinner fontSizeSpinner;

	private JComboBox<String> comboBox;

	private boolean fileExists = false, isFileSaved = false;

	private File file;

	String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

	int width = (int) size.getWidth();

	int height = (int) size.getHeight();

	public Texteditor() { // New File

		fileExists = false;
		isFileSaved = false;
		
		this.setSize(Settings.WIDTH, Settings.HEIGHT);
		this.setLayout(new GridBagLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.contentPane = new GradientPanel(Settings.MENU_BACKGROUND_COLOR_1, Settings.MENU_BACKGROUND_COLOR_2);
		this.contentPane.setLayout(new GridBagLayout());
		this.setContentPane(contentPane);

		constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(-780, 0, 0, 1224);

		headline = new JLabel("Texteditor");
		headline.setPreferredSize(new Dimension(500, 150));
		headline.setForeground(Color.BLACK);
		headline.setFont(new Font("Bahnschrift", Font.BOLD, 100));
		contentPane.add(headline, constraints);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(-780, 0, 0, -1468);

		save = new JButton("Save File");
		save.setFocusable(false);
		save.setPreferredSize(new Dimension(Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT));
		save.setHorizontalAlignment(SwingConstants.CENTER);
		save.setForeground(Color.BLACK);
		save.setBackground(new Color(0, 0, 0, 0));
		save.setContentAreaFilled(false);
		save.setBorder(new RoundedBorder(25));
		save.setFont(new Font("Arial", Font.BOLD, 40));
		save.addActionListener(this);
		contentPane.add(save, constraints);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 0, 0);

		textArea = new JTextArea("");
		textArea.setForeground(Color.BLACK);
		textArea.setPreferredSize(new Dimension(width - 200, height - 400));
		textArea.setMinimumSize(new Dimension(width - width / 2, height - height / 2));
		textArea.setFont(new Font("Arial", Font.BOLD, 40));
		contentPane.add(textArea, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(25, 0, 0, 960);

		comboBox = new JComboBox<String>(fonts);
		comboBox.setForeground(Color.BLACK);
		comboBox.setFont(new Font("Arial", Font.BOLD, 45));
		comboBox.addActionListener(this);
		contentPane.add(comboBox, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(25, 0, 0, 50);

		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(40, 0, 99, 5);

		fontSizeSpinner = new JSpinner(spinnerModel);
		JFormattedTextField formattedTextField = ((JSpinner.DefaultEditor) fontSizeSpinner.getEditor()).getTextField();
		formattedTextField.setEditable(false);
		fontSizeSpinner.getEditor().getComponent(0).setForeground(Color.BLACK);
		fontSizeSpinner.setFont(new Font("Arial", Font.BOLD, 48));
		fontSizeSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				textArea.setFont(
						new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));

			}
		});

		contentPane.add(fontSizeSpinner, constraints);

	}

	public Texteditor(File file) { // Open File

		fileExists = true;
		isFileSaved = false;

		this.file = file;

		this.setSize(Settings.WIDTH, Settings.HEIGHT);
		this.setLayout(new GridBagLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.contentPane = new GradientPanel(Settings.MENU_BACKGROUND_COLOR_1, Settings.MENU_BACKGROUND_COLOR_2);
		this.contentPane.setLayout(new GridBagLayout());
		this.setContentPane(contentPane);

		constraints = new GridBagConstraints();

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(-780, 0, 0, 1224);

		headline = new JLabel("Texteditor");
		headline.setPreferredSize(new Dimension(500, 150));
		headline.setForeground(Color.BLACK);
		headline.setFont(new Font("Bahnschrift", Font.BOLD, 100));
		contentPane.add(headline, constraints);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(-780, 0, 0, -1468);

		save = new JButton("Save File");
		save.setFocusable(false);
		save.setPreferredSize(new Dimension(Settings.BUTTON_WIDTH, Settings.BUTTON_HEIGHT));
		save.setHorizontalAlignment(SwingConstants.CENTER);
		save.setForeground(Color.BLACK);
		save.setBackground(new Color(0, 0, 0, 0));
		save.setContentAreaFilled(false);
		save.setBorder(new RoundedBorder(25));
		save.setFont(new Font("Arial", Font.BOLD, 40));
		save.addActionListener(this);
		contentPane.add(save, constraints);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 0, 0);

		textArea = new JTextArea(readFile(file));
		textArea.setForeground(Color.BLACK);
		textArea.setPreferredSize(new Dimension(width - 200, height - 400));
		textArea.setMinimumSize(new Dimension(width - width / 2, height - height / 2));
		textArea.setFont(new Font("Arial", Font.BOLD, 40));
		contentPane.add(textArea, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(25, 0, 0, 960);

		comboBox = new JComboBox<String>(fonts);
		comboBox.setForeground(Color.BLACK);
		comboBox.setFont(new Font("Arial", Font.BOLD, 45));
		comboBox.addActionListener(this);
		contentPane.add(comboBox, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(25, 0, 0, 50);

		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(40, 0, 99, 5);

		fontSizeSpinner = new JSpinner(spinnerModel);
		JFormattedTextField formattedTextField = ((JSpinner.DefaultEditor) fontSizeSpinner.getEditor()).getTextField();
		formattedTextField.setEditable(false);
		fontSizeSpinner.getEditor().getComponent(0).setForeground(Color.BLACK);
		fontSizeSpinner.setFont(new Font("Arial", Font.BOLD, 48));
		fontSizeSpinner.setValue(40);
		fontSizeSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				textArea.setFont(
						new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));

			}
		});
		contentPane.add(fontSizeSpinner, constraints);

	}

	public boolean isFileSaved() {
		return isFileSaved;
	}

	private String readFile(File file) { 
		String output = "";
		java.util.Scanner scanner = null;
		try {
			scanner = new java.util.Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			output = output.concat(scanner.nextLine() + "\n");
		}
		scanner.close();
		return output;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == comboBox) {

			textArea.setFont(new Font((String) comboBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));

		}
		if (e.getSource() == save) {
			this.isFileSaved = true;
			if (fileExists) { // Save file to filepath 'file'
				FileWriter fileWriter;
				try {
					fileWriter = new FileWriter(this.file + ".txt");
					fileWriter.write(textArea.getText());
					fileWriter.close();
					System.out.println("Saved file successfully");
				} catch (IOException e2) {
					e2.printStackTrace();
					System.out.println("Error while saving file");
				}
			} else { // Open JFileChooser and save file
				JFileChooser fileChooser = new JFileChooser();

				int rueckgabeWert = fileChooser.showOpenDialog(null);

				if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fileWriter = new FileWriter(fileChooser.getSelectedFile() + ".txt");
						fileWriter.write(textArea.getText());
						fileWriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		}

	}

}
