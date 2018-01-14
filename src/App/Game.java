package App;
import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import API.IReadOnlyApi;
import API.SolitaireApi;
import Controllers.GameFinishedController;
import Controllers.GamePausedController;
import Controllers.GameRunningController;
import Controllers.ScoreboardController;
import GUI.GameFinishedView;
import GUI.GamePausedView;
import GUI.GameRunningView;
import GUI.ScoreboardView;
import Model.Repository;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout layout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						new Game();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() 
	{
		setTitle("Solitaire @by Pawe³ Suchanicz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout = new CardLayout();
		contentPane = new JPanel(layout);
		
		IReadOnlyApi readOnlyApi = Repository.getInstance();
		SolitaireApi solitaireApi = new SolitaireApi();
	
		GameRunningView gameRunningView = new GameRunningView(readOnlyApi);
		GamePausedView gamePausedView = new GamePausedView(readOnlyApi);
		GameFinishedView gameFinishedView = new GameFinishedView(readOnlyApi);
		ScoreboardView scoreboardView = new ScoreboardView(readOnlyApi);	
		
		new GameRunningController(gameRunningView, solitaireApi);
		new GamePausedController(gamePausedView, solitaireApi);
		new GameFinishedController(gameFinishedView, solitaireApi);
		new ScoreboardController(scoreboardView, solitaireApi);
		
		solitaireApi.addObserver(gameRunningView);
		contentPane.add(gameRunningView, "gameRunningView");
		contentPane.add(gameFinishedView, "gameFinishedView");
		contentPane.add(gamePausedView, "gamePausedView");
		contentPane.add(scoreboardView, "scoreboardView");
		setView("gameRunningView");
		setContentPane(contentPane);
		pack();
        setLocationRelativeTo(null);
        setVisible(true);
        solitaireApi.start();
	}
	
	public void setView(String name)
	{
		layout.show(contentPane, name);	
	}

}
