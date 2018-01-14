package APITests;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import API.SolitaireApi;
import Helpers.CardStackBuilder;
import Model.Card;
import Model.CardStack;
import Model.Ranks;
import Model.Repository;
import Model.StackTypes;
import Model.Suits;

public class DroppingInvalidStackTest 
{
	private SolitaireApi api;
	private CardStack draggedStack;
	private CardStack invalidDestStack;
	
	@Before
	public void init()
	{
		api = new SolitaireApi();
		draggedStack = new CardStackBuilder()
				.withPosition(10, 10)
				.add(new Card(Ranks.ace, Suits.clubs))
				.build();
	
		api.setDraggedStack(draggedStack);
		
		invalidDestStack = new CardStackBuilder()
				.withPosition(50, 50)
				.withType(StackTypes.pile)
				.build();
		
		List<CardStack> listOfStacks = new ArrayList<CardStack>();
		listOfStacks.add(invalidDestStack);
		
		
		try {
			Field stacksField = Repository.getInstance().getClass().getDeclaredField("stacks");
			stacksField.setAccessible(true);
			stacksField.set(Repository.getInstance(), listOfStacks);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@After
	public void reset()
	{
		try {
			
			Field instance = Repository.getInstance().getClass().getDeclaredField("instance");
			instance.setAccessible(true);
			instance.set(null, null);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void should_not_add_draggedStack_when_move_is_invalid() 
	{	
		// Arrange
		int initialStackSize = invalidDestStack.size();
		
		// Act
		api.dropStack();
		
		// Assert
		assertEquals(initialStackSize, invalidDestStack.size());
	}
	
	@Test
	public void should_add_draggedStack_to_currentStack_when_move_is_invalid() 
	{	
		// Arrange
		int initialStackSize = api.getCurrentStack().size();
		
		// Act
		api.dropStack();
		
		// Assert
		assertTrue(initialStackSize < api.getCurrentStack().size());
	}
	
	@Test
	public void should_not_increase_score_when_move_is_invalid()
	{
		//Arrange
		int initialScore = Repository.getInstance().getScore();
		
		// Act
		api.dropStack();
		
		// Assert
		assertEquals(initialScore, Repository.getInstance().getScore());
	}
}
