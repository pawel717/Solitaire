package Model;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import API.IReadOnlyApi;

public class Repository implements IReadOnlyApi
{
	private static Repository instance;
	private int score;
	private TickTimer timer;
	private List<CardStack> stacks;
	private Deck deck;
	private CardStack draggedStack;
	private CardStack currentStack;
	private Point offset;	
	private Scoreboard scoreboard;
	
	private Repository()
	{
		init();
		this.timer = new TickTimer(null);
		this.timer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(timer.getTickCount())%10 == 0)
					setScore(score-2);
			}
			
		});
	}
	
	public static Repository getInstance()
	{
		if(instance == null)
			instance = new Repository();
		return instance;
	}
	
	public void init()
	{
		this.score = 0;
		this.scoreboard = new Scoreboard();
		this.deck = new Deck();
		this.stacks = new ArrayList<CardStack>(12);
		createPiles();
		createFoundations();
		createWaste();
		this.draggedStack = new CardStack();
		this.currentStack = new CardStack();
		this.offset = new Point(0,0);
	}
	
	private void createPiles()
	{
		if(deck != null)
		{
			for(int i=0; i<7; i++)
			{
				CardStack pile = new CardStack(20+100*i, 180, StackTypes.pile);
				
				for(int j=0; j<=i; j++)
					pile.add(deck.getRandomCard());
				
				pile.getFirstCard().show();
				stacks.add(pile);
			}
		}
	}
	
	private void createFoundations()
	{
		for(int i=0; i<4; i++)
			stacks.add(new CardStack(320+i*100,20,StackTypes.foundation));
	}
	
	private void createWaste()
	{
		CardStack waste = new CardStack(120,20,StackTypes.waste);
		
		for(int i=0; i<24; i++)
		{
			Card card = deck.getRandomCard();
			card.show();
			waste.add(card);
		}
		
		stacks.add(waste);
	}

	@Override
	public List<Point> readStacksHooks() 
	{
		/* need to make deep copy of hooks */
		ArrayList<Point> hooks = new ArrayList<Point>();
		
		for(CardStack s : stacks)
			hooks.add(new Point(s.getHook().x, s.getHook().y));

		return hooks;
	}

	@Override
	public List<Card> readCards() 
	{
		/*  need to make deep copy of cards */
		ArrayList<Card> cards = new ArrayList<Card>();
		
		for(CardStack stack : stacks)
			for(Card card : stack)
				cards.add(card.clone());
		
		if(draggedStack != null)
			for(Card card : draggedStack)
				cards.add(card.clone());
				
		return cards;
	}

	public void setDraggedStack(CardStack stack) 
	{
		this.draggedStack = stack;
	}

	public List<CardStack> getStacks() 
	{
		return this.stacks;
	}

	public void setOffset(Point offset) 
	{
		this.offset = offset;
		
	}
	
	public void setScore(int score) 
	{
		if(score > 0)
			this.score = score;
		else 
			this.score = 0;
	}
	
	public void setCurrentStack(CardStack stack) 
	{
		this.currentStack = stack;
	}

	public CardStack getDraggedStack() 
	{
		return this.draggedStack;
	}

	public CardStack getCurrentStack() 
	{
		return this.currentStack;
	}
	
	public Point getOffset()
	{
		return this.offset;
	}

	public Deck getDeck()
	{
		return this.deck;
	}

	public Timer getTimer() 
	{
		return this.timer;
	}
	
	public void saveScore(String username) 
	{
		scoreboard.saveScore(username, String.valueOf(score));
	}
	
	@Override
	public String getTickCount() 
	{
		return timer.getTickCount();
	}

	@Override
	public int getScore() 
	{
		return this.score;
	}

	@Override
	public List<String> readScores() {
		return scoreboard.getScores();
	}

	@Override
	public List<String> readUsernames() {
		return scoreboard.getUsernames();
	}

}
