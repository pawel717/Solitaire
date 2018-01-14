package Controllers;

import java.awt.event.ActionListener;

import API.SolitaireApi;
import GUI.View;

abstract class Controller implements ActionListener
{
	protected View view;
	protected SolitaireApi api;
	
	public Controller(View view, SolitaireApi api)
	{
		this.view = view;
		this.api = api;
		this.view.addActionListener(this);
	}
	
}
