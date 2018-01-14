package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scoreboard
{
	private String filePath;
	private String separator;
	
	public Scoreboard()
	{
		filePath = "files/scores.csv";
		separator = ";";	
	}
	
	public List<String> getUsernames()
	{
		List<String> usernames = new ArrayList<String>();
		Scanner scanner;
		
		try 
		{
			scanner = new Scanner(new File(filePath));	
		} 
		catch (FileNotFoundException e) 
		{
			return null;
		}
		
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			usernames.add(line.split(separator)[0]);
		}
		System.out.println(usernames); //to debugging
		scanner.close();
		return usernames;
	}
	
	public List<String> getScores()
	{
		List<String> scores = new ArrayList<String>();
		Scanner scanner;
		
		try 
		{
			scanner = new Scanner(new File(filePath));
		}
		catch (FileNotFoundException e) 
		{
			return null;
		}
		
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			scores.add(line.split(separator)[1]);
		}
		System.out.println(scores);  //to debugging
		scanner.close();
		return scores;
	}
	
	public void saveScore(String name, String score)
	{
		List<String> names = getUsernames();
		List<String> scores = getScores();
		
		if(scores.size() == 0)
		{
			names.add(name);
			scores.add(score);
		}
		else
		{
			for(int i=0; i<scores.size(); i++)
			{
				if(Integer.parseInt(score) >= Integer.parseInt(scores.get(i)))
				{
					names.add(i, name);
					scores.add(i,score);
					break;
				}	
			}
		}
		System.out.println(names.size());  // to debugging
		
		try 
		{
			FileWriter writer = new FileWriter(new File(filePath));
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			
			for(int i=0; i<scores.size() && i<5; i++)
			{
				bufferedWriter.write(names.get(i)+";"+scores.get(i));
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			writer.close();		
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return;
		}
		//int i=0; can be deleted?
	}
}
