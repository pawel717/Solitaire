package ModelTests;
import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Model.Card;
import Model.Ranks;
import Model.Suits;

public class CardTest 
{

	@Test
	public void should_return_true_if_point_is_in_card()
	{
		// Arrange
		Card card = new Card(Ranks.ace, Suits.clubs, false, new Point(0,0));
		Point pointInCard = new Point(10, 10);
		Point pointOnTheEdge = new Point(0, 0);
		
		// Act
		// Assert
		assertTrue(card.isPointInCard(pointInCard.x, pointInCard.y));
		assertTrue(card.isPointInCard(pointOnTheEdge.x, pointOnTheEdge.y));
	}
	
	@Test
	public void should_return_false_if_point_is_not_in_card()
	{
		// Arrange
		Card card = new Card(Ranks.ace, Suits.clubs, false, new Point(0,0));
		Point pointOutOfCard = new Point(300, 300);
		
		// Act
		// Assert
		assertFalse(card.isPointInCard(pointOutOfCard.x, pointOutOfCard.y));
	}
	
	
	@Test
	public void should_return_black_colour_when_clubs()
	{
		// Arrange
		Card card = new Card(Ranks.ace, Suits.clubs);
		
		// Act
		String output = card.getColour();
		
		// Assert
		assertEquals("black", output);
	}
	
	@Test
	public void should_return_black_colour_when_spades()
	{
		// Arrange
		Card card = new Card(Ranks.ace, Suits.spades);
		
		// Act
		String output = card.getColour();
		
		// Assert
		assertEquals("black", output);
	}
	
	@Test
	public void should_return_red_colour_when_diamonds()
	{
		// Arrange
		Card card = new Card(Ranks.ace, Suits.diamonds);
		
		// Act
		String output = card.getColour();
		
		// Assert
		assertEquals("red", output);
	}
	
	@Test
	public void should_return_red_colour_when_hearts()
	{
		// Arrange
		Card card = new Card(Ranks.ace, Suits.hearts);
		
		// Act
		String output = card.getColour();
		
		// Assert
		assertEquals("red", output);
	}
	
}
