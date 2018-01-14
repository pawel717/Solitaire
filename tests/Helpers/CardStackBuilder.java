package Helpers;

import java.awt.Rectangle;
import java.util.ArrayList;

import Model.Card;
import Model.CardStack;
import Model.StackTypes;

public class CardStackBuilder 
{
	private ArrayList<Card> stack = new ArrayList<Card>();
	private Rectangle shape = new Rectangle(81, 113);
	private StackTypes type = StackTypes.undefined;
	
	public CardStackBuilder(){}
	
	public CardStackBuilder add(Card card)
	{
		this.stack.add(card);
		return this;
	}
	
	public CardStackBuilder withType(StackTypes type)
	{
		this.type = type;
		return this;
	}
	
	public CardStackBuilder withPosition(int x, int y)
	{
		this.shape.x = x;
		this.shape.y = y;
		return this;
	}
	
	public CardStack build()
	{
		CardStack cardStack = new CardStack(shape.x, shape.y, type);
		for(Card card : this.stack)
			cardStack.add(card);
		
		return cardStack;
	}
}
