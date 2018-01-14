package APITests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import API.SolitaireApi;
import Helpers.CardStackBuilder;
import Model.Card;
import Model.CardStack;
import Model.Ranks;
import Model.Repository;
import Model.StackTypes;
import Model.Suits;

public class FindStackTest 
{

	@Test
	public void findStack_should_return_substack_from_clicked_point() 
	{
		// Arrange
		Card card1 = new Card(Ranks.ace, Suits.clubs, false, new Point(0,0));
		Card card2 = new Card(Ranks.ace, Suits.diamonds, false, new Point(0,0));
		Card card3 = new Card(Ranks.ace, Suits.hearts, false, new Point(0,0));
		
		CardStack stack = new CardStackBuilder()
				.withPosition(100,100)
				.withType(StackTypes.pile)
				.add(card1)
				.add(card2)
				.add(card3)
				.build();
		
		List<CardStack> listOfStacks = new ArrayList<CardStack>();
		listOfStacks.add(stack);
		SolitaireApi api = new SolitaireApi();
		
		try {
			Field stacksField = Repository.getInstance().getClass().getDeclaredField("stacks");
			stacksField.setAccessible(true);
			stacksField.set(Repository.getInstance(), listOfStacks);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		// Act
		CardStack foundedStack = api.findStack(105, 115);
		
		// Assert
		assertThat(foundedStack, CoreMatchers.hasItems(card2, card3));
	}
	
	@Test
	public void findStack_should_return_null_when_clicked_point_is_in_blank_area() 
	{
		// Arrange
		SolitaireApi api = new SolitaireApi();
		
		// Act
		CardStack foundedStack = api.findStack(0, 0);
		
		// Assert
		assertEquals(null, foundedStack);
	}

}
