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
import Model.Deck;
import Model.Ranks;
import Model.Repository;
import Model.StackTypes;
import Model.Suits;

public class NextWasteTest 
{

	private SolitaireApi api;
	private CardStack waste;
	private CardStack deck;
	private List<CardStack> listOfStacks;
	
	@Before
	public void init()
	{
		deck = new Deck();
		deck.clear();
		
		waste = new CardStackBuilder()
				.withType(StackTypes.waste)
				.add(new Card(Ranks.ace, Suits.clubs))
				.add(new Card(Ranks.eight, Suits.clubs))
				.build();
		
		api = new SolitaireApi();
		listOfStacks = new ArrayList<CardStack>();
		listOfStacks.add(waste);
		
		try {
			Field stacksField = Repository.getInstance().getClass().getDeclaredField("stacks");
			stacksField.setAccessible(true);
			stacksField.set(Repository.getInstance(), listOfStacks);
			Field deckField = Repository.getInstance().getClass().getDeclaredField("deck");
			deckField.setAccessible(true);
			deckField.set(Repository.getInstance(), deck);
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
	public void should_show_next_card_when_waste_is_not_empty() 
	{
		// Arrange
		Card previousCard = waste.getFirstCard();
		
		// Act
		api.nextWaste();
		
		// Assert
		assertNotEquals(previousCard, waste.getFirstCard());
	}
	
	@Test
	public void should_show_next_card_when_waste_is_empty_and_deck_is_not_empty() 
	{
		// Arrange
		waste.clear();
		deck.add(new Card(Ranks.four, Suits.hearts));
		Card previousCard = waste.getFirstCard();
		
		// Act
		api.nextWaste();
		
		// Assert
		assertNotEquals(previousCard, waste.getFirstCard());
	}
	
	@Test
	public void should_not_show_any_card_when_waste_is_empty_and_deck_is_empty() 
	{
		// Arrange
		waste.clear();
		deck.clear();
		
		// Act
		api.nextWaste();
		
		// Assert
		assertEquals(null, waste.getFirstCard());
	}
	
}
