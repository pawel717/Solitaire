package ModelTests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import API.IReadOnlyApi;
import Model.Card;
import Model.CardStack;
import Model.Repository;
import Model.StackTypes;

public class RepositoryTest 
{

	@Test
	public void should_have_only_one_instance()
	{
		// Arrange
		Repository firstRepository = Repository.getInstance();

		// Act
		Repository secondRepository = Repository.getInstance();
		
		// Assert
		assertThat(firstRepository, CoreMatchers.is(secondRepository));
	}

	@Test
	public void init_should_reset_fields()
	{
		// Arrange
		Repository repository = Repository.getInstance();
		repository.setScore(10);
		repository.setOffset(new Point(10, 10));
		repository.setDraggedStack(new CardStack(100, 100, StackTypes.waste));
		repository.setCurrentStack(new CardStack(100, 100, StackTypes.waste));
		
		// Act
		repository.init();
		
		// Assert
		assertEquals(0, repository.getScore());
		assertEquals(new Point(0, 0), repository.getOffset());
		assertEquals(StackTypes.undefined, repository.getDraggedStack().getType());
		assertEquals(StackTypes.undefined, repository.getCurrentStack().getType());
	}
	
	@Test
	public void score_should_not_be_negative()
	{
		// Arrange
		Repository repository = Repository.getInstance();
		
		// Act
		repository.setScore(-10);
		
		// Assert
		assertFalse(repository.getScore() < 0);
	}
	
	@Test
	public void IReadOnlyApi_should_not_allow_to_modify_fields()
	{
		// Arrange
		IReadOnlyApi repository = Repository.getInstance();
		String tickCount = repository.getTickCount();
		int actualScore = repository.getScore();
		List<String> scores = repository.readScores();
		List<String> usernames = repository.readUsernames();
		List<Card> cards = repository.readCards();
		List<Point> stackHooks = repository.readStacksHooks();

		// Act
		tickCount += 1;
		actualScore += 1;
		scores.clear();
		usernames.clear();
		cards.clear();
		stackHooks.clear();
		
		// Assert
		assertNotEquals(tickCount, repository.getTickCount());
		assertNotEquals(actualScore, repository.getScore());
		assertNotEquals(scores, repository.readScores());
		assertNotEquals(usernames, repository.readUsernames());
		assertNotEquals(cards, repository.readCards());
		assertNotEquals(stackHooks, repository.readStacksHooks());
	}
	
}
