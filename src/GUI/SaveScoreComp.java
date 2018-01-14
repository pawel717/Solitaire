package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Graphics;

import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatter;

import API.IReadOnlyApi;


public class SaveScoreComp extends JPanel
{
	private JFormattedTextField textField;
	private NameFormatter nameFormatter;
	private IReadOnlyApi api;
	private JLabel scoreLabel;
	private JButton saveButton;
	
	SaveScoreComp(IReadOnlyApi api)
	{
		this.api = api;
		
		setBackground(new Color(0,115,0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{46, 118, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 34, 0, 42, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblWygrana = new JLabel("Wygrana!");
		lblWygrana.setForeground(Color.WHITE);
		lblWygrana.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblWygrana = new GridBagConstraints();
		gbc_lblWygrana.gridheight = 2;
		gbc_lblWygrana.gridwidth = 4;
		gbc_lblWygrana.insets = new Insets(0, 0, 5, 5);
		gbc_lblWygrana.gridx = 0;
		gbc_lblWygrana.gridy = 0;
		add(lblWygrana, gbc_lblWygrana);
		
		
		JLabel label1 = new JLabel("Imie");
		label1.setForeground(Color.WHITE);
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.anchor = GridBagConstraints.EAST;
		gbc_label1.insets = new Insets(0, 0, 5, 15);
		gbc_label1.gridx = 0;
		gbc_label1.gridy = 2;
		add(label1, gbc_label1);
		
		nameFormatter = new NameFormatter();
		textField = new JFormattedTextField(nameFormatter);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.EAST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);		
		
		saveButton = new JButton("Zapisz");
		saveButton.setRolloverEnabled(false);
		saveButton.setBackground(SystemColor.control);
		saveButton.setFocusable(false);
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.anchor = GridBagConstraints.WEST;
		gbc_saveButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveButton.gridx = 2;
		gbc_saveButton.gridy = 2;
		add(saveButton, gbc_saveButton);
		
		JLabel lblPunkty = new JLabel("Punkty");
		lblPunkty.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPunkty = new GridBagConstraints();
		gbc_lblPunkty.anchor = GridBagConstraints.EAST;
		gbc_lblPunkty.insets = new Insets(0, 0, 5, 15);
		gbc_lblPunkty.gridx = 0;
		gbc_lblPunkty.gridy = 3;
		add(lblPunkty, gbc_lblPunkty);
		
		scoreLabel = new JLabel(String.valueOf(api.getScore()));
		scoreLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 3;
		add(scoreLabel, gbc_label);
	}
	
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		scoreLabel.setText(String.valueOf(api.getScore()));
	}
	
	public void addActionListener(ActionListener listener)
	{
		saveButton.addActionListener(listener);
	}
	
	public String getInput()
	{
		return textField.getText();
	}
	
	public boolean validateInput() 
	{
		try {
			textField.commitEdit();
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),"B³¹d", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		saveButton.setEnabled(false);
		saveButton.setText("Zapisano");
		textField.setEnabled(false);
		return true;
	}
	
	private class NameFormatter extends DefaultFormatter
	{
		private static final long serialVersionUID = 1L;
		
		public NameFormatter()
		{
			setAllowsInvalid(true);
			setCommitsOnValidEdit(false);
		}
		
		@Override
		public Object stringToValue(String string) throws ParseException
		{
			if(string.contains(";"))
				throw new ParseException("' ; ' znak niedozwolony", 0);
			if(string.equals(""))
				throw new ParseException("Wpisz nazwe", 0);
			return super.stringToValue(string);
		}
	}
}
