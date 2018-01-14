package Controllers;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import API.SolitaireApi;
import App.Game;
import GUI.View;

public class ScoreboardController extends Controller
{
	public ScoreboardController(View view, SolitaireApi api)
	{
		super(view, api);
		view.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Game game = (Game)SwingUtilities.getWindowAncestor(view);
		game.setView("gameRunningView");
		api.start();
	}
}
