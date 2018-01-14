package GUI;

import API.IReadOnlyApi;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.Box;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Dimension;

public class StatusBarComp extends JPanel
{
	JLabel timeLabel;
	JLabel scoreLabel;
	private IReadOnlyApi api;
	
	public StatusBarComp(IReadOnlyApi api) {
		setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		this.api = api;
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(false);
		menuBar.setBorderPainted(false);
		add(menuBar);
		
		JLabel lblNewLabel_1 = new JLabel("Czas:");
		menuBar.add(lblNewLabel_1);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalGlue_1.setSize(new Dimension(5, 0));
		horizontalGlue_1.setPreferredSize(new Dimension(5, 0));
		menuBar.add(horizontalGlue_1);
		
		timeLabel = new JLabel(api.getTickCount());
		timeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		menuBar.add(timeLabel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setPreferredSize(new Dimension(20, 0));
		horizontalGlue.setSize(new Dimension(20, 0));
		menuBar.add(horizontalGlue);
		
		JLabel lblNewLabel = new JLabel("Wynik:");
		menuBar.add(lblNewLabel);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalGlue_2.setSize(new Dimension(5, 0));
		horizontalGlue_2.setPreferredSize(new Dimension(5, 0));
		menuBar.add(horizontalGlue_2);
		
		scoreLabel = new JLabel(String.valueOf(api.getScore()));
		scoreLabel.setAlignmentX(1.0f);
		menuBar.add(scoreLabel);
		
	}

	public void timerUpdate() 
	{
		this.timeLabel.setText(api.getTickCount());
	}
	
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		scoreLabel.setText(String.valueOf(api.getScore()));
	}
	
}
