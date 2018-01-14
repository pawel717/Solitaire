package GUI;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import API.IReadOnlyApi;
import Observer.IObserver;

public abstract class View extends JPanel implements IObserver
{
	IReadOnlyApi api;
	
	public View(IReadOnlyApi api)
	{
		this.api = api;
	}
	
	public abstract void addActionListener(ActionListener listener);

	public abstract void timerUpdate();
	
}
