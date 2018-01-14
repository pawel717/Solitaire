package GUI;

import API.IReadOnlyApi;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameRunningView extends View
{
	private MenuComp menuComp;
	private BoardComp boardComp;
	private StatusBarComp statusBarComp;
	
	public GameRunningView(IReadOnlyApi api)
	{
		super(api);
        setLayout(new BorderLayout());

        menuComp = new MenuComp();
        add(menuComp, BorderLayout.NORTH);
        
        boardComp = new BoardComp(api);
        add(boardComp, BorderLayout.CENTER);
        
        statusBarComp = new StatusBarComp(api);
        add(statusBarComp, BorderLayout.SOUTH);
	}
	
    @Override
    public Dimension getPreferredSize() 
    {
        return new Dimension(850, 550);
    }
    
	@Override
	public void update()
	{
		boardComp.repaint();
		repaint();
	}

	@Override
	public void addActionListener(ActionListener listener) 
	{
		menuComp.addActionListener(listener);
		boardComp.addActionListener(listener);
	}
	
	@Override
	public void addMouseListener(MouseListener listener) 
	{
		boardComp.addMouseListener(listener);
	}
	
	@Override
	public void addMouseMotionListener(MouseMotionListener listener) 
	{
		boardComp.addMouseMotionListener(listener);
	}

	@Override
	public void timerUpdate() 
	{
		this.statusBarComp.timerUpdate();		
	}
}
