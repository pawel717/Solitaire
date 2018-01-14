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

public class MoveToFoundationTest 
{
	private SolitaireApi api;
	private CardStack currentStack;
	private CardStack validFoundation;
	private CardStack invalidFoundation;
	private List<CardStack> listOfStacks;
	
	@Before
	public void init()
	{
		api = new SolitaireApi();
		currentStack = new CardStackBuilder()
				.withType(StackTypes.pile)
				.withPosition(10, 10)
				.add(new Card(Ranks.ace, Suits.clubs))
				.build();
		
		listOfStacks = new ArrayList<CardStack>();
		listOfStacks.add(currentStack);
		try {
			Field stacksField = Repository.getInstance().getClass().getDeclaredField("stacks");
			stacksField.setAccessible(true);
			stacksField.set(Repository.getInstance(), listOfStacks);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Repository.getInstance().setCurrentStack(currentStack);
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
	public void should_move_card_to_foundation_when_move_is_valid() 
	{
		// Arrange
		validFoundation = new CardStackBuilder()
				.withPosition(50, 50)
				.withType(StackTypes.foundation)
				.build();
		
		listOfStacks.add(validFoundation);
		
		// Act
		api.moveToFoundation();
		
		// Assert
		assertEquals(1, validFoundation.size());
	}

	@Test
	public void should_not_move_card_to_foundation_when_move_is_not_valid() 
	{
		// Arrange
		invalidFoundation = new CardStackBuilder()
				.withPosition(50, 50)
				.withType(StackTypes.foundation)
				.add(new Card(Ranks.king, Suits.clubs))
				.build();
		
		listOfStacks.add(invalidFoundation);
		
		// Act
		api.moveToFoundation();
		
		// Assert
		assertEquals(1, invalidFoundation.size());
	}
}
