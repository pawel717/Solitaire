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

public class DroppingValidStackTest 
{
	private SolitaireApi api;
	private CardStack draggedStack;
	private CardStack validDestStack;
	private List<CardStack> listOfStacks;
	
	@Before
	public void init()
	{
		api = new SolitaireApi();
		draggedStack = new CardStackBuilder()
				.withPosition(10, 10)
				.add(new Card(Ranks.ace, Suits.clubs))
				.build();
	
		api.setDraggedStack(draggedStack);
		
		validDestStack = new CardStackBuilder()
				.withPosition(50, 50)
				.withType(StackTypes.foundation)
				.build();
		
		listOfStacks = new ArrayList<CardStack>();
		listOfStacks.add(validDestStack);
		
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
	public void should_add_draggedStack_if_move_is_valid() 
	{	
		// Arrange
		int initialStackSize = validDestStack.size();
		
		// Act
		api.dropStack();
		
		// Assert
		assertTrue(validDestStack.size() > initialStackSize);
	}
	
	@Test
	public void should_increase_score_when_move_is_valid()
	{
		//Arrange
		int initialScore = Repository.getInstance().getScore();
		
		// Act
		api.dropStack();
		
		// Assert
		assertTrue(Repository.getInstance().getScore() > initialScore);
	}

	@Test
	public void should_add_draggedStack_to_proper_destStack_when_intersects_few_destStacks()
	{
		//Arrange
		int initialStackSize = validDestStack.size();
		listOfStacks.add(new CardStackBuilder()
				.withPosition(70, 70)
				.withType(StackTypes.foundation)
				.build());
		
		// Act
		api.dropStack();
		
		// Assert
		assertTrue(validDestStack.size() > initialStackSize);
	}
	
}
