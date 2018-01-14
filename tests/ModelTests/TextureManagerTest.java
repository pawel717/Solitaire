package ModelTests;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Test;

import GUI.TextureManager;

public class TextureManagerTest 
{

	@Test
	public void getTexture_should_return_image_when_file_exists() 
	{
		// Arrange
		String name = "reversed";
		Image image = null;
		
		// Act
		image = TextureManager.getTexture(name);
		
		// Assert
		assertNotNull(image);
	}
	
	@Test
	public void getTexture_should_throw_IllegalArgumentException_when_file_dont_exists() 
	{
		// Arrange
		String name = "not_existing_name";
		Image image = null;
		
		// Act
		try
		{
			image = TextureManager.getTexture(name);
			fail("IllegalArgumentException expected");
		}
		catch (IllegalArgumentException exception){}
		
		// Assert
		assertNull(image);
	}

}
