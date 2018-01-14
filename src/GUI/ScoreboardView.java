package GUI;
import javax.swing.JLabel;
import javax.swing.JButton;

import API.IReadOnlyApi;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Dimension;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardView extends View 
{
	JButton btn1;
	private List<JLabel> nameLabels;
	private List<JLabel> scoreLabels;
	
	public ScoreboardView(IReadOnlyApi api) 
	{
		super(api);
		setSize(new Dimension(300, 300));
		setPreferredSize(new Dimension(850, 550));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{220, 0, 227, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 30, 30, 30, 30, 30, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		nameLabels = new ArrayList<JLabel>(5);
		scoreLabels = new ArrayList<JLabel>(5);
		
		JLabel lblNewLabel = new JLabel("Tabela wynik\u00F3w");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel name1 = new JLabel("");
		name1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_name1 = new GridBagConstraints();
		gbc_name1.insets = new Insets(0, 0, 5, 5);
		gbc_name1.gridx = 0;
		gbc_name1.gridy = 2;
		add(name1, gbc_name1);
		nameLabels.add(name1);
		
		JLabel score1 = new JLabel("");
		score1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_score1 = new GridBagConstraints();
		gbc_score1.gridwidth = 2;
		gbc_score1.anchor = GridBagConstraints.WEST;
		gbc_score1.insets = new Insets(0, 0, 5, 0);
		gbc_score1.gridx = 1;
		gbc_score1.gridy = 2;
		add(score1, gbc_score1);
		scoreLabels.add(score1);
		
		JLabel name2 = new JLabel("");
		name2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_name2 = new GridBagConstraints();
		gbc_name2.insets = new Insets(0, 0, 5, 5);
		gbc_name2.gridx = 0;
		gbc_name2.gridy = 3;
		add(name2, gbc_name2);
		nameLabels.add(name2);
		
		JLabel score2 = new JLabel("");
		score2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_score2 = new GridBagConstraints();
		gbc_score2.gridwidth = 2;
		gbc_score2.insets = new Insets(0, 0, 5, 0);
		gbc_score2.anchor = GridBagConstraints.WEST;
		gbc_score2.gridx = 1;
		gbc_score2.gridy = 3;
		add(score2, gbc_score2);
		scoreLabels.add(score2);
		
		JLabel name3 = new JLabel("");
		name3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_name3 = new GridBagConstraints();
		gbc_name3.insets = new Insets(0, 0, 5, 5);
		gbc_name3.gridx = 0;
		gbc_name3.gridy = 4;
		add(name3, gbc_name3);
		nameLabels.add(name3);
		
		JLabel score3 = new JLabel("");
		score3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_score3 = new GridBagConstraints();
		gbc_score3.gridwidth = 2;
		gbc_score3.anchor = GridBagConstraints.WEST;
		gbc_score3.insets = new Insets(0, 0, 5, 0);
		gbc_score3.gridx = 1;
		gbc_score3.gridy = 4;
		add(score3, gbc_score3);
		scoreLabels.add(score3);
		
		JLabel name4 = new JLabel("");
		name4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_name4 = new GridBagConstraints();
		gbc_name4.insets = new Insets(0, 0, 5, 5);
		gbc_name4.gridx = 0;
		gbc_name4.gridy = 5;
		add(name4, gbc_name4);
		nameLabels.add(name4);
		
		JLabel score4 = new JLabel("");
		score4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_score4 = new GridBagConstraints();
		gbc_score4.gridwidth = 2;
		gbc_score4.anchor = GridBagConstraints.WEST;
		gbc_score4.insets = new Insets(0, 0, 5, 0);
		gbc_score4.gridx = 1;
		gbc_score4.gridy = 5;
		add(score4, gbc_score4);
		scoreLabels.add(score4);
		
		JLabel name5 = new JLabel("");
		name5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_name5 = new GridBagConstraints();
		gbc_name5.insets = new Insets(0, 0, 5, 5);
		gbc_name5.gridx = 0;
		gbc_name5.gridy = 6;
		add(name5, gbc_name5);
		nameLabels.add(name5);
		
		JLabel score5 = new JLabel("");
		score5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_score5 = new GridBagConstraints();
		gbc_score5.gridwidth = 2;
		gbc_score5.insets = new Insets(0, 0, 5, 0);
		gbc_score5.anchor = GridBagConstraints.WEST;
		gbc_score5.gridx = 1;
		gbc_score5.gridy = 6;
		add(score5, gbc_score5);
		scoreLabels.add(score5);
		
		btn1 = new JButton("Zamknij tabele");
		GridBagConstraints gbc_btn1 = new GridBagConstraints();
		gbc_btn1.gridheight = 4;
		gbc_btn1.gridwidth = 3;
		gbc_btn1.gridx = 0;
		gbc_btn1.gridy = 7;
		btn1.setRolloverEnabled(false);
		btn1.setBackground(SystemColor.control);
		btn1.setFocusable(false);
		add(btn1, gbc_btn1);
	}
	
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		List<String> scores = api.readScores();
		List<String> usernames = api.readUsernames();
		int i;
		
		for(i=0; i<usernames.size(); i++)
		{
			nameLabels.get(i).setText(usernames.get(i));
			scoreLabels.get(i).setText(scores.get(i));
		}
		for(;i<5; i++)
		{
			nameLabels.get(i).setText("");
			scoreLabels.get(i).setText("");
		}
	}
	
	@Override
	public void addActionListener(ActionListener listener) 
	{
		btn1.addActionListener(listener);
	}
	
	@Override
	public void update() {}

	@Override
	public void timerUpdate() {}

}
