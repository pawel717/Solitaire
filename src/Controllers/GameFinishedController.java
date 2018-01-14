package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

import API.SolitaireApi;
import App.Game;
import GUI.GameFinishedView;
import GUI.View;

public class GameFinishedController extends Controller
{
	public GameFinishedController(View view, SolitaireApi api)
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
		case "Zapisz":
			GameFinishedView gameFinishedView = (GameFinishedView) view;
			if(gameFinishedView.validateInput())
				api.saveScore(gameFinishedView.getInput());
			break;
		
		case "Rozpocznij":
			game = (Game)SwingUtilities.getWindowAncestor(view);
			game.setView("gameRunningView");
			api.reset();
			break;
			
		case "Zakoñcz":
			System.exit(0);
			break;
		}
	}
}
