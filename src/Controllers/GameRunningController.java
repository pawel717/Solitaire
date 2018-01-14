package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import API.SolitaireApi;
import App.Game;
import GUI.View;


public class GameRunningController extends Controller
{
	
	public GameRunningController(View view, SolitaireApi api)
	{
		super(view, api);
		view.addMouseListener(new MouseHandler());
		view.addMouseMotionListener(new MouseMotionHandler());
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
			api.reset();
			api.start();
			break;
			
		case "Pauza/Wznów":
			api.stop();
			game = (Game)SwingUtilities.getWindowAncestor(view);
			game.setView("gamePausedView");
			break;
			
		case "Tabela wyników":
			api.stop();
			game = (Game)SwingUtilities.getWindowAncestor(view);
			game.setView("scoreboardView");
			break;
			
		case "Zakoñcz":
			System.exit(0);
			break;
			
		case "wasteBtn":
			api.nextWaste();
			break;
			
		}
	}
	
	private class MouseHandler extends MouseAdapter
	{
/**
 * Pressing mouse button causes 
 */
		public void mousePressed(MouseEvent event)
		{
			if(event.getButton() == MouseEvent.BUTTON1)
			{
				int x=event.getX();
				int y=event.getY();
				api.setDraggedStack(api.findStack(x,y));
				System.out.println(x+" "+y+" "+api.getDraggedStack());
			}
		}
		
/**
 *  Single click on reversed card causes showing this card
 *  double click on non-reversed card causes adding card to foundation
 *  **/
		public void mouseClicked(MouseEvent event)
		{	
			if(event.getButton() == MouseEvent.BUTTON1)
			{
				if(event.getClickCount() == 1)
				api.showCurrentCard();
			
				else if(event.getClickCount() == 2)
				{
					api.moveToFoundation();
					if(api.isGameWon())
					{
						api.stop();
						Game game = (Game)SwingUtilities.getWindowAncestor(view);
						game.setView("gameFinishedView");
					}	
				}
				System.out.println("clicked"); //to debugging
			}
		}
		
/**
 * Releasing mouse button causes dropping dragged stack. 
 * Method is also checking if it is winning move.
 */
		public void mouseReleased(MouseEvent event)
		{
			if(event.getButton() == MouseEvent.BUTTON1)
			{
				api.dropStack();
				if(api.isGameWon())
				{
					api.stop();
					Game game = (Game)SwingUtilities.getWindowAncestor(view);
					game.setView("gameFinishedView");
				}
				System.out.println("released"); //to debugging
			}
		}
	}
	
	private class MouseMotionHandler implements MouseMotionListener
	{
/**
 * Dragging mouse causes dragging a stack.
*/
		@Override
		public void mouseDragged(MouseEvent event) 
		{
			if(SwingUtilities.isLeftMouseButton(event))
				api.dragStack(event.getX(), event.getY());
		}

		@Override
		public void mouseMoved(MouseEvent event) {}
	}
}
