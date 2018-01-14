package ModelTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import Model.Card;
import Model.Deck;
import Model.Ranks;
import Model.Suits;

public class DeckTest
{
	
	@Test
	public void sampling_should_be_without_replacement() 
	{
		// Arrange
		Deck deck = new Deck();
		
		// Act
		Card randomCard = deck.getRandomCard();
		
		// Assert
		assertThat(deck, not(hasItem(randomCard)));
	}
	
	@Test
	public void getRandomCard_should_return_card_from_stack_when_deck_is_not_empty() 
	{
		// Arrange
		Deck deck = new Deck();
		ArrayList<Card> cards = new ArrayList<Card>();
		deck.forEach(cards::add);
		
		// Act
		Card randomCard = deck.getRandomCard();
		
		// Assert
		assertThat(cards, hasItem(randomCard));
	}
	
	@Test
	public void getRandomCard_should_return_null_when_deck_is_empty() 
	{
		// Arrange
		Deck deck = new Deck();
		deck.clear();
		
		// Act
		Card randomCard = deck.getRandomCard();
		
		// Assert
		assertNull(randomCard);
	}
	
	@Test
	public void getRandomCard_should_return_card_from_stack_when_deck_has_one_card() 
	{
		// Arrange
		Card card = new Card(Ranks.ace, Suits.clubs);
		Deck deck = new Deck();
		deck.clear();
		deck.add(card);
		
		// Act
		Card randomCard = deck.getRandomCard();
		
		// Assert
		assertSame(randomCard, card);
	}

}
