package GUI;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class TextureManager 
{
	static String sourceFolder = "images/";
	
	static void setSourceFolder(String source)
	{
		sourceFolder = source;
	}
	
	public static Image getTexture(String name)
	{
		String imgsrc = sourceFolder+name+".png";
		if(!new File(imgsrc).exists())
			throw new IllegalArgumentException("bad file name:  " + imgsrc);
		return new ImageIcon(imgsrc).getImage();
	}

}
