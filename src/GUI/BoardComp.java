package GUI;

import javax.swing.JPanel;

import API.IReadOnlyApi;
import Model.Card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Insets;

public class BoardComp extends JPanel
{
	private IReadOnlyApi api;
	private JButton wasteBtn;

	
	public BoardComp(IReadOnlyApi api) 
	{
		
		setBackground(new Color(0,115,0));
		this.api = api;
		setLayout(null);
		
		wasteBtn = new JButton("");
		wasteBtn.setActionCommand("wasteBtn");
		wasteBtn.setSize(87, 119);
		wasteBtn.setLocation(new Point(17, 17));
		wasteBtn.setContentAreaFilled(false);
		wasteBtn.setBorderPainted(false);
		wasteBtn.setMargin(new Insets(0, 0, 0, 0));
		wasteBtn.setIcon(new ImageIcon(TextureManager.getTexture("waste")));
		wasteBtn.setFocusable(false);
		wasteBtn.setFocusPainted(false);
		add(wasteBtn);
	}
	

	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		drawStacks(graphics);
		drawCards(graphics);
	}


	public void drawStacks(Graphics graphics)
	{
		List<Point> stacksHooks = api.readStacksHooks();
		
		for(Point hook : stacksHooks)
			graphics.drawImage(TextureManager.getTexture("stack"), hook.x, hook.y, 78, 109, null);
	}
	
	private void drawCards(Graphics graphics)
	{
		List<Card> cards = api.readCards();
		
		for(Card card : cards)
			card.draw(graphics);
	}

	public void addActionListener(ActionListener listener)
	{
		wasteBtn.addActionListener(listener);
	}

}
