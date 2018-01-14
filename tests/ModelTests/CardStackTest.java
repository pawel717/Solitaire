package ModelTests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.Field;
import java.util.Iterator;

import org.junit.Test;

import Model.Card;
import Model.CardStack;
import Model.Ranks;
import Model.StackTypes;
import Model.Suits;

public class CardStackTest 
{

	@Test
	public void getSubstack_should_return_empty_stack_when_stack_isEmpty()
	{
		// Arrange
		CardStack stack = new CardStack();

		// Act
		boolean output = stack.getSubstack(2).isEmpty();
		
		// Assert
		assertTrue(output);
	}
	
	@Test
	public void getSubstack_should_return_empty_stack_when_index_is_outOfBound()
	{
		// Arrange
		CardStack stack = new CardStack();
		stack.add(new Card(Ranks.ace, Suits.clubs));
	
		// Act
		boolean output = stack.getSubstack(5).isEmpty();
		
		// Assert
		assertTrue(output);
	}
	
	@Test
	public void getFirstCard_should_return_null_when_stack_isEmpty()
	{
		// Arrange
		CardStack stack = new CardStack();
		
		// Act
		Card output = stack.getFirstCard();
		
		// Assert
		assertEquals(null, output);
	}
	
	@Test
	public void getLastCard_should_return_null_when_stack_isEmpty()
	{
		// Arrange
		CardStack stack = new CardStack();
			
		// Act
		Card output = stack.getLastCard();
		
		// Assert
		assertEquals(null, output);
	}
	
	@Test
	public void stack_should_be_unchanged_when_added_empty_substack()
	{
		// Arrange
		CardStack stack = new CardStack();
		stack.add(new Card(Ranks.ace, Suits.clubs));
		CardStack substack = new CardStack();
		int expected = stack.size();
		
		// Act
		stack.addSubstack(substack);
		int output = stack.size();
		
		// Assert
		assertEquals(expected, output);
	}
	
	@Test
	public void setPositions_should_shift_also_cards_positions()
	{
		// Arrange
		CardStack stack = new CardStack();
		stack.add(new Card(Ranks.ace, Suits.clubs));
		stack.add(new Card(Ranks.ace, Suits.diamonds));;
		int expectedX = 100;
		int expectedY = 150;
		
		// Act
		stack.setPosition(expectedX, expectedY);
		Iterator<Card> iterator = stack.iterator();
		Point output1 = iterator.next().getPosition();
		Point output2 = iterator.next().getPosition();
	
		// Assert
		assertEquals(expectedX, output1.x);
		assertEquals(expectedY, output1.y);
		assertEquals(expectedX, output2.x);
		assertEquals(expectedY+15, output2.y);
	}
	
	@Test
	public void should_change_cards_positions_when_added_to_stack()
	{
		// Arrange
		CardStack stack = new CardStack();
		int expectedX = 100;
		int expectedY = 150;
		stack.setPosition(expectedX, expectedY);
		Card card = new Card(Ranks.ace, Suits.clubs, true, new Point(0,0));
		
		// Act
		stack.add(card);
		Point output = card.getPosition();
		
		// Assert
		assertEquals(expectedX, output.x);
		assertEquals(expectedY, output.y);
	}
	
	@Test
	public void constructor_should_set_default_fields()
	{
		// Act
		CardStack stack = new CardStack();
		int bias = 0;
		try {
			Field field = stack.getClass().getDeclaredField("bias");
			field.setAccessible(true);
			bias = (int) field.get(stack);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert
		assertEquals(new Rectangle(81, 113), stack.getShape());
		assertEquals(StackTypes.undefined, stack.getType());
		assertEquals(15, bias);
	
	}

}
