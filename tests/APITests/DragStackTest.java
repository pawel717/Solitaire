package APITests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import API.SolitaireApi;

public class DragStackTest 
{

	@Test
	public void dragStack_should_change_draggedStack_position() 
	{
		// Arrange
		SolitaireApi api = new SolitaireApi();
		Point initialPosition = api.getDraggedStack().getHook();
		
		// Act
		api.dragStack(10, 10);
		
		// Assert
		assertNotEquals(initialPosition, api.getDraggedStack().getHook());
	}
}
