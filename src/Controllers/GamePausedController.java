package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import API.SolitaireApi;
import App.Game;
import GUI.View;

public class GamePausedController extends Controller
{

	public GamePausedController(View view, SolitaireApi api)
	{
		super(view, api);
		api.addTimerListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				view.timerUpdate();
			}    	
        });
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Game game;
		switch(e.getActionCommand())
		{
		
		case "Rozpocznij":
			game = (Game)SwingUtilities.getWindowAncestor(view);
			game.setView("gameRunningView");
			api.reset();
			break;
			
		case "Pauza/Wznów":
			game = (Game)SwingUtilities.getWindowAncestor(view);
			game.setView("gameRunningView");
			api.start();
			break;
			
		case "Tabela wyników":
			game = (Game)SwingUtilities.getWindowAncestor(view);
			game.setView("scoreboardView");
			break;
			
		case "Zakoñcz":
			System.exit(0);
			break;

		}	
	}
}
