package Model;

import java.util.Random;

public class Deck extends CardStack
{
	
	public Deck()
	{
		super(10,10,StackTypes.deck);
		
		for(Ranks rank : Ranks.values())
			for(Suits suit : Suits.values())
				stack.add(new Card(rank, suit));
	}
	
	public Card pop()
	{
		Card card = stack.get(0);
		stack.remove(0);
		return card;
	}
	
	
	public Card getRandomCard()
	{
		Random rand = new Random();
		int index;
		
		if(stack.size() == 0) 
			return null;
		else
			index = rand.nextInt(stack.size());
		
		Card card = stack.get(index);
		stack.remove(index);
		
		return card;
	}
	
}
