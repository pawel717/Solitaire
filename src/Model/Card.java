package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import GUI.TextureManager;

public class Card
{
	private Ranks rank;
	private Suits suit;
	private Boolean isReversed;
	private Rectangle shape;
	private Image image;
	
	public Card(Ranks rank, Suits suit)
	{
		this.rank = rank;
		this.suit = suit;
		this.isReversed = true;
		this.image = TextureManager.getTexture("reversed");
		shape = new Rectangle(81, 113);
	}
	
	public Card(Ranks rank, Suits suit, Boolean isReversed, Point position)
	{
		this.rank = rank;
		this.suit = suit;
		this.isReversed = isReversed;
		if(isReversed)
			this.image = TextureManager.getTexture("reversed");
		else
			this.image = TextureManager.getTexture(rank+"_"+suit);
		shape = new Rectangle(position.x, position.y, 81, 113);
	}
	
	public void setPosition(int x, int y)
	{
		shape.setLocation(x, y);
	}
	
	public Rectangle getShape()
	{
		return shape;
	}
	
	public Point getPosition()
	{
		return shape.getLocation();
	}

	public Ranks getRank() 
	{
		return this.rank;
	}

	public Suits getSuit()
	{
		return this.suit;
	}

	public String getColour()
	{
		if(this.suit == Suits.clubs || this.suit == Suits.spades)
			return "black";
		return "red";
	}
	
	public boolean isReversed()
	{
		return isReversed;
	}
	
	public void reverse()
	{
		this.image = TextureManager.getTexture("reversed");
		isReversed = true;
	}
	
	public void show()
	{
		this.image = TextureManager.getTexture(rank+"_"+suit);
		isReversed = false;
	}

	public boolean isPointInCard(int x, int y) 
	{
		if (shape.contains(x, y))
			return true;
		return false;
	}
	
	public Card clone()
	{
		return new Card(rank, suit, isReversed, shape.getLocation());
	}

	public void draw(Graphics graphics) 
	{
		graphics.drawImage(image, 
						   shape.x, shape.y, 
						   shape.width, shape.height, 
						   null);
	}
}
