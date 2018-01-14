package API;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import Model.Card;
import Model.CardStack;
import Model.Deck;
import Model.MoveValidator;
import Model.Repository;
import Model.StackTypes;
import Observer.IObservable;
import Observer.IObserver;

public class SolitaireApi implements IObservable
{
	private List<IObserver> observers;
	private Repository repository;
	
	public SolitaireApi()
	{
		this.observers = new ArrayList<IObserver>();
		this.repository = Repository.getInstance();
	}
	
	public CardStack getDraggedStack() 
	{
		return repository.getDraggedStack();
	}
	
	public CardStack getCurrentStack() 
	{
		return repository.getCurrentStack();
	}
	
	public void setDraggedStack(CardStack stack) 
	{
		repository.setDraggedStack(stack);
	}
	
	public void start()
	{
		repository.getTimer().start();
	}
	
	public void stop() 
	{
		repository.getTimer().stop();
	}
	
	public void reset() 
	{
		repository.init();
		repository.getTimer().restart();
		notifyObservers();
	}
	
	public void addTimerListener(ActionListener listener)
	{
		repository.getTimer().addActionListener(listener);
	}
	
	public boolean isGameWon()
	{
		for(CardStack stack : repository.getStacks())
		{
			if(!stack.isEmpty() && stack.getType() != StackTypes.foundation)
				return false;
		}
			
		return true;
	}
	
	public void saveScore(String username) 
	{
		repository.saveScore(username);
	}
	
	public CardStack findStack(int x, int y) 
	{
		List<CardStack> stacks = repository.getStacks();
		
		for(CardStack stack : stacks)
		{	
			ListIterator<Card> reverseIterator = stack.getReversedIterator();
			int i = 0;
			
			while(reverseIterator.hasPrevious())
			{
				i++;
				Card c = reverseIterator.previous();
				
				if(c.isPointInCard(x, y)) 
				{
					System.out.println("founded "+c.getRank()+" "+c.getSuit()); //to debugging
					repository.setCurrentStack(stack);
					if(c.isReversed()) 
						return null;
					repository.setOffset(new Point(x-c.getPosition().x, y-c.getPosition().y));
					CardStack buffStack = new CardStack(stack.getHook().x, stack.getHook().y, StackTypes.undefined);
					buffStack.addSubstack(stack.getSubstack(i));
					
					return buffStack;
				}
			}
		}
		repository.setCurrentStack(null);
		return null;
	}
	
	public void dragStack(int x, int y)
	{
		CardStack draggedStack = repository.getDraggedStack();
		
		if(draggedStack != null)
		{
			draggedStack.setPosition(x-repository.getOffset().x, y-repository.getOffset().y);
			notifyObservers();
		}
	}
	
	public void dropStack() 
	{
		CardStack draggedStack = repository.getDraggedStack();

		if(draggedStack != null)
		{
			List<CardStack> stacks = repository.getStacks();

			for(Integer[] intersection : intersections())
			{
				
				CardStack destStack = stacks.get(intersection[0]);
				
				if(MoveValidator.isValid(draggedStack, destStack))
				{
					destStack.addSubstack(draggedStack);
					
					if(destStack.getType() == StackTypes.foundation)
						repository.setScore(repository.getScore()+10);
					else if(repository.getCurrentStack().getType() == StackTypes.waste
							&& destStack.getType() == StackTypes.pile)
						repository.setScore(repository.getScore()+5);
					
					notifyObservers();
					return;
				}
			}
			repository.getCurrentStack().addSubstack(draggedStack);
			draggedStack.clear();
			notifyObservers();
		}
	}

	private List<Integer[]> intersections()
	{
		Rectangle dragged = repository.getDraggedStack().getLastCard().getShape();
		Rectangle rectangle;
		List<Integer[]> intersections = new ArrayList<Integer[]>();
		int index = 0;
		
		for(CardStack stack : repository.getStacks())
		{
			if(stack.getFirstCard() == null)
				rectangle = dragged.intersection(stack.getShape());
			else
				rectangle = dragged.intersection(stack.getFirstCard().getShape());
		
			if(rectangle.height>0 && rectangle.width>0)
			{
				Integer[] array = {index, rectangle.height*rectangle.width};		
				intersections.add(array);
			}
			index++;
		}
		
		Collections.sort(intersections, new Comparator<Integer[]>() {
	           
				@Override
				public int compare(Integer[] o1, Integer[] o2) {
					if(o1[1] > o2[1])
						return -1;
					return 1;
				}
	        });
		System.out.println(intersections); // to debugging
		return intersections;
	}
	
	public void showCurrentCard() 
	{
		CardStack currentStack = repository.getCurrentStack();
		
		if(currentStack != null && !currentStack.isEmpty() && currentStack.getFirstCard().isReversed())
		{
			currentStack.getFirstCard().show();
			repository.setScore(repository.getScore()+5);
			notifyObservers();
		}
	}

	public void nextWaste() 
	{
		List<CardStack> stacks = repository.getStacks();
		Deck deck = repository.getDeck();
		
		for(CardStack stack : stacks)
		{
			if(stack.getType() == StackTypes.waste)
			{ 				
				Card wasteCard;
				
				if((wasteCard = stack.getFirstCard()) != null)
				{
					deck.add(0, wasteCard);
					stack.remove(wasteCard);
				}
				else if(!deck.isEmpty())
				{
					repository.setScore(repository.getScore()-50);
					stack.addSubstack(deck);
					deck.clear();
				}
				
				notifyObservers();
				return;
			}
		}
	}
	
	public void moveToFoundation() 
	{
		CardStack currentStack = repository.getCurrentStack();
		
		if(currentStack != null && !currentStack.isEmpty())
		{
			List<CardStack> stacks = repository.getStacks();
			CardStack movingStack = currentStack.getSubstack(1);
		
			for(CardStack stack : stacks)
			{
				if(stack.getType() == StackTypes.foundation && MoveValidator.isValid(movingStack, stack))
				{
					stack.add(movingStack.getFirstCard());
					repository.setScore(repository.getScore()+10);
					notifyObservers();
					return;
				}
			}
			currentStack.addSubstack(movingStack);
		}
	}

	@Override
	public void addObserver(IObserver observer) 
	{
		observers.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) 
	{
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() 
	{
		for(IObserver o : observers)
			o.update();
	}
}
