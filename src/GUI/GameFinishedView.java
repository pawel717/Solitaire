package GUI;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

import API.IReadOnlyApi;
import javax.swing.BoxLayout;

public class GameFinishedView extends View
{
	private MenuComp menuComp;
	private StatusBarComp statusBarComp;
	private SaveScoreComp saveScoreComp;

	public GameFinishedView(IReadOnlyApi api)
	{
		super(api);
		setBackground(new Color(0,115,0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		 menuComp = new MenuComp();
		 JMenuBar menuBar = (JMenuBar) menuComp.getComponent(0);
		 menuBar.getComponent(2).setEnabled(false);
		 menuBar.getComponent(4).setEnabled(false);
	     add(menuComp);
	     
	     saveScoreComp = new SaveScoreComp(api);
	     add(saveScoreComp);
	     
	     statusBarComp = new StatusBarComp(api);
	     add(statusBarComp); 
	}

	public String getInput()
	{
		return saveScoreComp.getInput();
	}

	public boolean validateInput() 
	{
		return saveScoreComp.validateInput();
	}
	
	@Override
	public void update() 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void addActionListener(ActionListener listener) 
	{
		menuComp.addActionListener(listener);	
		saveScoreComp.addActionListener(listener);
	}

	@Override
	public void timerUpdate() 
	{
		this.statusBarComp.timerUpdate();
	}
}
