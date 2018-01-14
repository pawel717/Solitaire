package APITests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import API.SolitaireApi;
import Model.CardStack;
import Model.Repository;
import Model.StackTypes;

public class IsGameWonTest 
{

	@Test
	public void isGameWon_should_return_true_when_all_stacks_except_foundation_are_empty() 
	{
		// Arrange
		SolitaireApi api = new SolitaireApi();
		List<CardStack> stacks = Repository.getInstance().getStacks();
		for(CardStack stack : stacks)
		{
			if(stack.getType() != StackTypes.foundation)
				stack.clear();
		}
		
		// Act
		boolean isGameWon = api.isGameWon();
		
		// Assert
		assertTrue(isGameWon);
	}
	
	@Test
	public void isGameWon_should_return_false_when_stacks_are_not_empty() 
	{
		// Arrange
		SolitaireApi api = new SolitaireApi();
		
		// Act
		boolean isGameWon = api.isGameWon();
		
		// Assert
		assertFalse(isGameWon);
	}
}
