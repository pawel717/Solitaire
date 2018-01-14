package Model;

import java.awt.Point;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class CardStack implements Iterable <Card>
{
	protected ArrayList<Card> stack;
	private Rectangle shape;
	protected int bias;			//number of pixels each card position is biased
	private StackTypes type;
	
	
	public CardStack()
	{
		this.stack = new ArrayList<Card>();
		this.shape = new Rectangle(81, 113);
		this.type = StackTypes.undefined;
		this.bias = 15;
	}
	
	public CardStack(int x, int y, StackTypes type)
	{
		this.stack = new ArrayList<Card>();
		this.shape = new Rectangle(x, y, 81, 113);
		this.type = type;
		if(type == StackTypes.waste || type == StackTypes.foundation) 
			this.bias = 0;
		else this.bias = 15;
	}
	
	public StackTypes getType() 
	{
		return type;
	}
	
	public Point getHook()
	{
		return shape.getLocation();
	}
	
	public Rectangle getShape() 
	{
		return this.shape;
	}
	
	public int size()
	{
		return stack.size();
	}
	
	public boolean isEmpty() 
	{
		return stack.isEmpty();
	}
	
	public void setPosition(int x, int y)
	{
		shape.setLocation(x, y);
		int i = 0;
		
		for(Card c : stack)
			c.setPosition(shape.x, shape.y+bias*i++);
	}
	
	public Card getLastCard()
	{
		try
		{
			return stack.get(0);
		}
		catch(Exception e)
		{
			return null;
		} 
	}
	
	public Card getFirstCard()
	{
		try
		{
			return stack.get(stack.size()-1);
		}
		catch(Exception e)
		{
			return null;
		}  
	}
	
	public void add(Card card)
	{
		add(stack.size(),card);
	}
	
	public void add(int index, Card card)
	{
		stack.add(index, card);
		
		for(int i = index; i<stack.size(); i++)
			card.setPosition(shape.x, shape.y+bias*index);
	}
	
	public void remove(Card card)
	{
		stack.remove(card);
	}
	
	public void clear()
	{
		stack.clear();
	}
	
	public CardStack getSubstack(int index)
	{
		CardStack substack = new CardStack();
		if(index > size())
			return substack;
		/*if(index == 0)
		{
			substack.add(stack.get(0));
			stack.remove(0);
			return substack;
		}*/
		
		for(int i = index; i>0; i--)
		{
			substack.add(stack.get(stack.size()-i));
			stack.remove(stack.size()-i);
		}
		return substack;
	}
	
	public void addSubstack(CardStack substack)
	{
		ListIterator<Card> iterator=substack.getIterator();
		
		while(iterator.hasNext())
			this.add(iterator.next());
	}
	
	public ListIterator<Card> getReversedIterator()
	{
		return stack.listIterator(stack.size());
	}
	
	public ListIterator<Card> getIterator()
	{
		return stack.listIterator();
	}
	
	@Override
	public Iterator<Card> iterator() 
	{
		return stack.iterator();
	}

}
