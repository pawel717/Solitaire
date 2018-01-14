package GUI;

import java.awt.event.ActionListener;

import API.IReadOnlyApi;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

public class GamePausedView extends View
{
	private MenuComp menuComp;
	private StatusBarComp statusBarComp;
	
	public GamePausedView(IReadOnlyApi api)
	{
		super(api);
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblPauza = new JLabel("Gra zatrzymana");
		lblPauza.setForeground(Color.WHITE);
		setBackground(new Color(0,115,0));
		lblPauza.setFont(new Font("Calibri", Font.BOLD, 16));
		lblPauza.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPauza.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPauza, BorderLayout.CENTER);
		
		 menuComp = new MenuComp();
	     add(menuComp, BorderLayout.NORTH);
	     
	     statusBarComp = new StatusBarComp(api);
	     add(statusBarComp, BorderLayout.SOUTH);
	}

	@Override
	public void update() {}

	@Override
	public void addActionListener(ActionListener listener) 
	{
		menuComp.addActionListener(listener);
	}

	@Override
	public void timerUpdate() 
	{
		this.statusBarComp.timerUpdate();	
	}
}
